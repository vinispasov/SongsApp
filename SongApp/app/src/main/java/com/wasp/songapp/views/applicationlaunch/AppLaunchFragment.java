package com.wasp.songapp.views.applicationlaunch;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wasp.songapp.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AppLaunchFragment extends Fragment implements AppLaunchContracts.View {

    @BindView(R.id.iv_app_launch_picture)
    ImageView mAppLaunchImage;

    private AppLaunchContracts.Navigator mNavigator;
    private AppLaunchContracts.Presenter mPresenter;

    @Inject
    public AppLaunchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_app_launch, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.loadAppLaunchPicture();
    }

    @Override
    public void setPresenter(AppLaunchContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showAppLaunchPicture() {
        mAppLaunchImage
                .setVisibility(View.VISIBLE);
        mPresenter
                .getNavigation();
    }

    @Override
    public void getNavigation() {
        mNavigator.navigateToNextActivity();
    }

    public void setNavigator(AppLaunchContracts.Navigator navigator) {
        this.mNavigator = navigator;
    }
}
