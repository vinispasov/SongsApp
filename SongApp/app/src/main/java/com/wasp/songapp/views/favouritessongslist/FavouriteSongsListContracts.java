package com.wasp.songapp.views.favouritessongslist;

import com.wasp.songapp.models.Song;


import java.util.List;

public interface FavouriteSongsListContracts {

    interface View {

        void setPresenter(FavouriteSongsListContracts.Presenter presenter);

        void showSongDetails(Song song);

        void showProgressBarLoading();

        void hideProgressBarLoading();

        void showError(Throwable error);

        void showAllSongs(List<Song> allSongs);

        void showMessage(String message);
    }

    interface Presenter {

        void subscribe(FavouriteSongsListContracts.View view);

        void songIsSelected(Song song);

        void showSongsList();

        void presentSongsToView(List<Song> allSongs, String message);

    }

    interface Navigator {

        void navigateToSongDetailsWith(Song song);
    }
}
