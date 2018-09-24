package com.wasp.songapp.views.addnewsong;

import com.wasp.songapp.async.base.SchedulerProvider;
import com.wasp.songapp.models.Song;
import com.wasp.songapp.services.base.SongsService;
import com.wasp.songapp.utils.Constants;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

public class SongCreatePresenter implements SongCreateContracts.Presenter {
    private final SongsService mSongsService;
    private final SchedulerProvider mSchedulerProvider;
    private SongCreateContracts.View mView;

    @Inject
    public SongCreatePresenter(SongsService songsService, SchedulerProvider schedulerProvider) {
        mSongsService = songsService;
        mSchedulerProvider = schedulerProvider;
    }

    @Override
    public void subscribe(SongCreateContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void save(Song song) {

        mView.showLoading();
        Disposable disposable = Observable
                .create((ObservableOnSubscribe<Song>) emitter -> {
                    mSongsService.addSong(song);
                    emitter.onNext(song);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideLoading)
                .subscribe(s -> {
                    mView.showMessage(Constants.ADD_OF_SONG_SUCCESS_MESSAGE);
                    mView.navigateToHome();
                }, error->mView.showError(error));
    }

}
