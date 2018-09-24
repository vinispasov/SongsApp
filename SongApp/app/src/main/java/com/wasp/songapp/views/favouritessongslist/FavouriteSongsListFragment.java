package com.wasp.songapp.views.favouritessongslist;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.wasp.songapp.R;
import com.wasp.songapp.models.Song;
import com.wasp.songapp.utils.Constants;
import com.wasp.songapp.views.mysongslist.SongsArrayAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;


public class FavouriteSongsListFragment extends Fragment implements FavouriteSongsListContracts.View{

    @BindView(R.id.lv_favorite_songs_list_view)
    ListView mSongsListView;

    @BindView(R.id.prb_loading_view)
    ProgressBar mProgressBarView;

    @Inject
    SongsArrayAdapter mSongsArrayAdapter;

    private FavouriteSongsListContracts.Presenter mPresenter;
    private FavouriteSongsListContracts.Navigator mNavigator;


    @Inject
    public FavouriteSongsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite_songs_list, container, false);
        ButterKnife.bind(this, view);

        mSongsListView.setAdapter(mSongsArrayAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.showSongsList();
    }


    @Override
    public void showAllSongs(List<Song> allSongs) {
        mSongsArrayAdapter.clear();
        mSongsArrayAdapter.addAll(allSongs);
        mSongsArrayAdapter.notifyDataSetChanged();
    }


    @Override
    public void showMessage(String message) {
        Toast
                .makeText(getContext(), message, Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void showError(Throwable error) {
        String errorMessage = Constants.ERROR_MESSAGE + error.getMessage();
        Toast
                .makeText(getContext(), errorMessage, Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void showProgressBarLoading() {
        mProgressBarView.setVisibility(View.VISIBLE);
        mSongsListView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgressBarLoading() {

        mProgressBarView.setVisibility(View.GONE);
        mSongsListView.setVisibility(View.VISIBLE);
    }

    @OnItemClick(R.id.lv_favorite_songs_list_view)
    public void onItemClick(int position) {

        Song selectedSong = mSongsArrayAdapter.getItem(position);
        mPresenter.songIsSelected(selectedSong);
    }

    @Override
    public void showSongDetails(Song song) {
        mNavigator.navigateToSongDetailsWith(song);
    }

    @Override
    public void setPresenter(FavouriteSongsListContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    public void setNavigator(FavouriteSongsListContracts.Navigator navigator) {
        mNavigator = navigator;
    }
}
