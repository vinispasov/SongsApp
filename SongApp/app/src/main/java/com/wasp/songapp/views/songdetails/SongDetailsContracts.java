package com.wasp.songapp.views.songdetails;

import com.wasp.songapp.models.Song;

public interface SongDetailsContracts {

    interface View {
        void showSong(Song song);

        void setPresenter(Presenter presenter);

        void showError(Throwable error);

        void showLoading();

        void hideLoading();

        void showMessage(String message);
    }

    interface Presenter {
        void subscribe(View view);

        void loadSong();

        void setSongId(int id);

        void playIsSelected();
    }
}
