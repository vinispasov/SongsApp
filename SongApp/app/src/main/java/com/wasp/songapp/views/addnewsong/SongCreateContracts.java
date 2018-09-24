package com.wasp.songapp.views.addnewsong;

import com.wasp.songapp.models.Song;

public interface SongCreateContracts {
    interface View {

        void setPresenter(Presenter presenter);

        void navigateToHome();

        void showError(Throwable error);

        void hideLoading();

        void showLoading();

        void showMessage(String message);
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void save(Song song);
    }

    interface Navigator {

        void navigateToHome();
    }

}
