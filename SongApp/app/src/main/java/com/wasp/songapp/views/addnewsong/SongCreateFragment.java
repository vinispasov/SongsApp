package com.wasp.songapp.views.addnewsong;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.wasp.songapp.R;
import com.wasp.songapp.models.Song;
import com.wasp.songapp.utils.Constants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SongCreateFragment extends Fragment implements SongCreateContracts.View {

    private SongCreateContracts.Presenter mPresenter;

    @BindView(R.id.et_new_song_title)
    EditText mNewSongTitle;

    @BindView(R.id.et_new_author_name)
    EditText mNewAuthorName;

    @BindView(R.id.et_new_song_duration)
    EditText mNewSongDuration;

    @BindView(R.id.et_new_song_image_url)
    EditText mNewSongImage;

    @BindView(R.id.prb_load_bar_on_add_song)
    ProgressBar mProgressBarView;

    private SongCreateContracts.Navigator mNavigator;

    @Inject
    public SongCreateFragment() {
        // Required empty constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_song_create, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @OnClick(R.id.btn_save)
    public void onSongAddButtonClick(View view) {
        String newTitle = mNewSongTitle.getText().toString();
        String newAuthorName = mNewAuthorName.getText().toString();
        String newSongDuration = mNewSongDuration.getText().toString();
        String newImageUrl = mNewSongImage.getText().toString();

        Song newSong = new Song(newTitle, newAuthorName, newSongDuration, newImageUrl);
        view.startAnimation(new AlphaAnimation(Constants.FROM_ALPHA_ANIMATION, Constants.TO_ALPHA_ANIMATION));
        mPresenter.save(newSong);
    }

    @Override
    public void setPresenter(SongCreateContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void navigateToHome() {
        mNavigator.navigateToHome();
    }

    @Override
    public void showError(Throwable error) {
        String errorMessage = Constants.ERROR_MESSAGE + error.getMessage();
        Toast
                .makeText(getContext(), errorMessage, Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void hideLoading() {
        mProgressBarView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLoading() {

        mProgressBarView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessage(String message) {
        Toast
                .makeText(getContext(), message, Toast.LENGTH_LONG)
                .show();
    }

    public void setNavigator(SongCreateContracts.Navigator navigator) {
        mNavigator = navigator;
    }

}
