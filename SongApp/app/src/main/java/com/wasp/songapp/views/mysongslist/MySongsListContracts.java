package com.wasp.songapp.views.mysongslist;

import com.wasp.songapp.models.Song;

import java.util.List;

public interface MySongsListContracts {
    interface View {

        void setPresenter(MySongsListContracts.Presenter presenter);

        void showSongDetails(Song song);

        void showProgressBarLoading();

        void hideProgressBarLoading();

        void showError(Throwable error);

        void showAllSongs(List<Song> allSongs);

        void showDialogForDeletion(Song songToDelete);

        void hideDeletionDialog();

        void showMessage(String message);
    }

    interface Presenter {

        void subscribe(MySongsListContracts.View view);

        void songIsSelected(Song song);

        void showSongsList();

        void presentSongsToView(List<Song> allSongs, String message);

        void filterSongsWith(String searchQuery);

        void songForDeletionIsSelected(Song songToDelete);

        void getActionOnCancelledDeletion();

        void getActionOnConfirmedDeletion(Song songToDelete);
    }

    interface Navigator {
        void navigateToSongDetailsWith(Song song);
    }
}
