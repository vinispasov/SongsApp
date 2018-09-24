package com.wasp.songapp.views.mysongslist;

import com.wasp.songapp.async.base.SchedulerProvider;
import com.wasp.songapp.models.Song;
import com.wasp.songapp.services.base.SongsService;
import com.wasp.songapp.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

public class MySongsListPresenter implements MySongsListContracts.Presenter {

    private MySongsListContracts.View mView;
    private final SongsService mSongsService;
    private final SchedulerProvider mSchedulerProvider;

    @Inject
    MySongsListPresenter(SongsService songsService, SchedulerProvider schedulerProvider) {

        mSongsService = songsService;
        mSchedulerProvider = schedulerProvider;

    }


    @Override
    public void subscribe(MySongsListContracts.View view) {
        mView = view;
    }


    @Override
    public void showSongsList() {

        mView.showProgressBarLoading();

        Disposable observable = Observable
                .create((ObservableOnSubscribe<List<Song>>) emitter -> {
                    List<Song> songs = mSongsService.getAllSongs();
                    emitter.onNext(songs);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBarLoading)
                .subscribe(allSongs -> presentSongsToView(allSongs, Constants.NO_SONGS_AVAILABLE_MESSAGE), mView::showError);
    }

    @Override
    public void filterSongsWith(String searchQuery) {
        mView.showProgressBarLoading();

        Disposable observable = Observable
                .create((ObservableOnSubscribe<List<Song>>) emitter -> {
                    List<Song> songsResultSet = mSongsService.getFilteredSongs(searchQuery);
                    emitter.onNext(songsResultSet);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBarLoading)
                .subscribe(allSongs -> presentSongsToView(allSongs, Constants.NO_SONGS_FOUND_ON_SEARCH_MESSAGE),
                        error -> mView.showError(error));
    }

    @Override
    public void presentSongsToView(List<Song> allSongs, String message) {

        if (allSongs.isEmpty()) {
            mView.showMessage(message);
        } else {
            mView.showAllSongs(allSongs);
        }
    }

    @Override
    public void songForDeletionIsSelected(Song songToDelete) {
        mView.showDialogForDeletion(songToDelete);
    }

    @Override
    public void getActionOnConfirmedDeletion(Song songToDelete) {

        int idOfSongToDelete = songToDelete.getId();

        Disposable observable = Observable
                .create((ObservableOnSubscribe<Void>) emitter -> {
                    mSongsService.deleteSong(idOfSongToDelete);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideDeletionDialog)
                .subscribe(aVoid -> { },
                        error -> mView.showError(error),
                        () -> { mView.showMessage(Constants.SUCCESSFUL_DELETION_OF_SONG);
                        this.showSongsList();
                });
    }

    @Override
    public void getActionOnCancelledDeletion() {
        mView.hideDeletionDialog();
    }

    @Override
    public void songIsSelected(Song song) {
        mView.showSongDetails(song);
    }
}
