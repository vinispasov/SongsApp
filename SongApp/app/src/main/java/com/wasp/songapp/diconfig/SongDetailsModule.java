package com.wasp.songapp.diconfig;

import com.wasp.songapp.views.songdetails.SongDetailsContracts;
import com.wasp.songapp.views.songdetails.SongDetailsFragment;
import com.wasp.songapp.views.songdetails.SongDetailsPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SongDetailsModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract SongDetailsFragment songDetailsFragment();

    @ActivityScoped
    @Binds
    abstract SongDetailsContracts.Presenter presenter(SongDetailsPresenter presenter);
}
