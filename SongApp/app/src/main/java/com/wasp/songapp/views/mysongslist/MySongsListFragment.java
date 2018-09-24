package com.wasp.songapp.views.mysongslist;


import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.wasp.songapp.R;
import com.wasp.songapp.models.Song;
import com.wasp.songapp.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;
import butterknife.OnTextChanged;


public class MySongsListFragment extends Fragment implements MySongsListContracts.View {

    @BindView(R.id.et_search_songs)
    EditText mSearchBar;

    @BindView(R.id.lv_my_songs_list_view)
    ListView mSongsListView;

    @BindView(R.id.prb_loading_view)
    ProgressBar mProgressBarView;

    @Inject
    SongsArrayAdapter mSongsArrayAdapter;

    private Button mPositiveDialogButton;
    private Button mNegativeDialogButton;
    private AlertDialog mDeletionDialog;
    private AlphaAnimation mButtonClickAnimation;
    private MySongsListContracts.Presenter mPresenter;
    private MySongsListContracts.Navigator mNavigator;


    @Inject
    public MySongsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_songs_list, container, false);
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
    public void showDialogForDeletion(Song songToDelete) {

        setupDeletionDialog();

        mDeletionDialog.show();

        mPositiveDialogButton
                .setOnClickListener(view -> {
                    view.startAnimation(mButtonClickAnimation);
                    mPresenter.getActionOnConfirmedDeletion(songToDelete);
                });
        mNegativeDialogButton
                .setOnClickListener(view -> {
                    view.startAnimation(mButtonClickAnimation);
                    mPresenter.getActionOnCancelledDeletion();
                });
    }

    private void setupDeletionDialog() {
        LayoutInflater inflater = LayoutInflater.from(this.getContext());
        View dialogView = inflater.inflate(R.layout.song_deletion_dialog_layout, null);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setView(dialogView);
        mDeletionDialog = dialogBuilder.create();
        mPositiveDialogButton = dialogView.findViewById(R.id.btn_answer_yes);
        mNegativeDialogButton = dialogView.findViewById(R.id.btn_answer_no);
        mButtonClickAnimation = new AlphaAnimation(Constants.FROM_ALPHA_ANIMATION, Constants.TO_ALPHA_ANIMATION);
    }

    @Override
    public void hideDeletionDialog() {
        mDeletionDialog.cancel();
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

    @OnItemClick(R.id.lv_my_songs_list_view)
    public void onItemClick(int position) {

        Song selectedSong = mSongsArrayAdapter.getItem(position);
        mPresenter.songIsSelected(selectedSong);
    }

    @OnItemLongClick(R.id.lv_my_songs_list_view)
    public boolean onItemLongClick(int position) {
        Song songToDelete = mSongsArrayAdapter.getItem(position);
        mPresenter.songForDeletionIsSelected(songToDelete);
        return true;
    }

    @OnTextChanged(R.id.et_search_songs)
    public void onTextChanged() {
        String searchQuery = mSearchBar
                .getText()
                .toString();
        mPresenter.filterSongsWith(searchQuery);
    }

    @Override
    public void showSongDetails(Song song) {
        mNavigator.navigateToSongDetailsWith(song);
    }

    @Override
    public void setPresenter(MySongsListContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    public void setNavigator(MySongsListContracts.Navigator navigator) {
        mNavigator = navigator;
    }

}
