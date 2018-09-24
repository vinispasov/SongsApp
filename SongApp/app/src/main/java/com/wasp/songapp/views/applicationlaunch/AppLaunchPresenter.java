package com.wasp.songapp.views.applicationlaunch;

import android.os.Handler;

import com.wasp.songapp.async.base.SchedulerProvider;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

public class AppLaunchPresenter implements AppLaunchContracts.Presenter {

    private AppLaunchContracts.View mView;

    @Inject
    public AppLaunchPresenter() {

    }

    @Override
    public void subscribe(AppLaunchContracts.View view) {
        mView = view;
    }

    @Override
    public void loadAppLaunchPicture() {

        mView.showAppLaunchPicture();

    }

    @Override
    public void getNavigation() {

        new Handler()
                .postDelayed(() -> mView.getNavigation(),
                        2500);
    }
}
