package com.wasp.songapp.views.favouritessongslist;

import android.content.Intent;
import android.os.Bundle;

import com.wasp.songapp.R;
import com.wasp.songapp.models.Song;
import com.wasp.songapp.views.BaseDrawerActivity;
import com.wasp.songapp.views.songdetails.SongDetailsActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class FavouriteSongsListActivity extends BaseDrawerActivity implements FavouriteSongsListContracts.Navigator {
    public static final int DRAWER_IDENTIFIER = 315;

    @Inject
    FavouriteSongsListFragment mFavouriteSongsListFragment;

    @Inject
    FavouriteSongsListPresenter mFavouriteSongsListPresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_songs_list);
        ButterKnife.bind(this);

        mFavouriteSongsListFragment.setNavigator(this);
        mFavouriteSongsListFragment.setPresenter(mFavouriteSongsListPresenter);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fr_favourite_songs_list_fragment, mFavouriteSongsListFragment)
                .commit();
    }


    @Override
    public void navigateToSongDetailsWith(Song song) {

        Intent intent = new Intent(this, SongDetailsActivity.class);
        intent.putExtra(SongDetailsActivity.SONG_EXTRA_KEY, song);
        startActivity(intent);
    }

    @Override
    protected long getIdentifier() {
        return DRAWER_IDENTIFIER;
    }

}