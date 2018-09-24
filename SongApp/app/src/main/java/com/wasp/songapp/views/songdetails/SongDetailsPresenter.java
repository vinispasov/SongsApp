package com.wasp.songapp.views.songdetails;

import com.wasp.songapp.async.base.SchedulerProvider;
import com.wasp.songapp.models.Song;
import com.wasp.songapp.services.base.SongsService;
import com.wasp.songapp.utils.Constants;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

public class SongDetailsPresenter implements SongDetailsContracts.Presenter {
    //fields
    private final SongsService mSongsService;
    private final SchedulerProvider mSchedulerProvider;
    private SongDetailsContracts.View mView;
    private int mSongId;
    private Song mSelectedSong;

    //constructor
    @Inject
    public SongDetailsPresenter(SongsService songsService, SchedulerProvider schedulerProvider) {
        mSongsService = songsService;
        mSchedulerProvider = schedulerProvider;
    }

    //methods
    @Override
    public void subscribe(SongDetailsContracts.View view) {
        mView = view;
    }

    @Override
    public void loadSong() {
        mView.showLoading();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<Song>) emitter -> {
                    Song song = mSongsService.getSongById(mSongId);
                    emitter.onNext(song);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideLoading)
                .subscribe(song -> {
                            mView.showSong(song);
                            mSelectedSong = song;
                        },
                        error -> mView.showError(error));
    }

    @Override
    public void setSongId(int songId) {
        mSongId = songId;
    }

    @Override
    public void playIsSelected() {
        mView.showLoading();

        int newPlaysCount = mSelectedSong
                .getPlaysCount()
                + Constants.SONG_PLAY_INCREMENT_VALUE;

        mSelectedSong.setPlaysCount(newPlaysCount);

        Disposable disposable = Observable
                .create((ObservableOnSubscribe<Song>) emitter -> {
                    Song songToUpdate = mSongsService.updateSongPlayCounter(mSelectedSong, mSongId);
                    emitter.onNext(songToUpdate);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideLoading)
                .subscribe(updatedSong -> {
                            mSelectedSong = updatedSong;
                            mView.showMessage(Constants.SUCCESSFUL_PLAY_OF_SONG);
                            mView.showSong(updatedSong);
                        },
                        mView::showError);
    }
}
