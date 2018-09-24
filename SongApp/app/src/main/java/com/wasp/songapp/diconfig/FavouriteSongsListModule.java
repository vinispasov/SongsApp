package com.wasp.songapp.diconfig;

import com.wasp.songapp.views.favouritessongslist.FavouriteSongsListContracts;
import com.wasp.songapp.views.favouritessongslist.FavouriteSongsListFragment;
import com.wasp.songapp.views.favouritessongslist.FavouriteSongsListPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FavouriteSongsListModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract FavouriteSongsListFragment favouriteSongsListFragment();

    @ActivityScoped
    @Binds
    abstract FavouriteSongsListContracts.Presenter presenter(FavouriteSongsListPresenter presenter);

}
