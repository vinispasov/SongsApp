package com.wasp.songapp.diconfig;

import com.wasp.songapp.views.mysongslist.MySongsListContracts;
import com.wasp.songapp.views.mysongslist.MySongsListFragment;
import com.wasp.songapp.views.mysongslist.MySongsListPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MySongsListModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract MySongsListFragment mySongsListFragment();

    @ActivityScoped
    @Binds
    abstract MySongsListContracts.Presenter presenter(MySongsListPresenter presenter);

}
