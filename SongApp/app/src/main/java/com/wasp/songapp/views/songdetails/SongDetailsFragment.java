package com.wasp.songapp.views.songdetails;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.wasp.songapp.R;
import com.wasp.songapp.models.Song;
import com.wasp.songapp.utils.Constants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SongDetailsFragment extends Fragment implements SongDetailsContracts.View {

    //fields
    private SongDetailsContracts.Presenter mPresenter;

    @BindView(R.id.prb_load_view)
    ProgressBar mProgressLoadView;

    @BindView(R.id.tv_author_name_field)
    TextView mAuthorNameFieldTextView;
    @BindView(R.id.tv_author_name)
    TextView mAuthorTextView;

    @BindView(R.id.tv_song_title_field)
    TextView mTitleFieldTextView;
    @BindView(R.id.tv_song_title)
    TextView mTitleTextView;

    @BindView(R.id.tv_song_duration_field)
    TextView mSongDurationFieldTextView;
    @BindView(R.id.tv_song_duration)
    TextView mSongDurationTextView;


    @BindView(R.id.tv_song_plays_count_field)
    TextView mSongPlaysCountFieldTextView;
    @BindView(R.id.tv_song_plays_count)
    TextView mSongPlaysCountTextView;

    @BindView(R.id.song_image)
    ImageView mSongImageView;

    //constructor
    @Inject
    public SongDetailsFragment() {
        // Required empty constructor here
    }

    //methods
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_song_details, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.loadSong();
    }

    @Override
    public void showSong(Song song) {
        mAuthorNameFieldTextView.setText(Constants.SONG_AUTHOR_FIELD);
        mAuthorTextView.setText(song.getAuthorName());

        mTitleFieldTextView.setText(Constants.SONG_TITLE_FIELD);
        mTitleTextView.setText(song.getSongTitle());

        mSongDurationFieldTextView.setText(Constants.SONG_DURATION_FIELD);
        mSongDurationTextView.setText(String.valueOf(song.getSongDuration()));

        mSongPlaysCountFieldTextView.setText(Constants.SONG_PLAYS_COUNT_FIELD);
        mSongPlaysCountTextView.setText(String.valueOf(song.getPlaysCount()));
        Picasso
                .get()
                .load(song.getImageUrl())
                .into(mSongImageView);
    }

    @Override
    public void setPresenter(SongDetailsContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showError(Throwable error) {
        String errorMessage = Constants.ERROR_MESSAGE + error.getMessage();
        Toast
                .makeText(getContext(), errorMessage, Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void showLoading() {
        mProgressLoadView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {

        mProgressLoadView.setVisibility(View.INVISIBLE);

    }

    @Override
    public void showMessage(String message) {
        Toast
                .makeText(getContext(), message, Toast.LENGTH_SHORT)
                .show();
    }

    @OnClick(R.id.ib_play_button)
    public void onClick(View view) {
        view.startAnimation(new AlphaAnimation(Constants.FROM_ALPHA_ANIMATION, Constants.TO_ALPHA_ANIMATION));
        mPresenter.playIsSelected();
    }
}
