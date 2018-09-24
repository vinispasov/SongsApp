package com.wasp.songapp.diconfig;

import com.wasp.songapp.views.addnewsong.SongCreateContracts;
import com.wasp.songapp.views.addnewsong.SongCreateFragment;
import com.wasp.songapp.views.addnewsong.SongCreatePresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SongCreateModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract SongCreateFragment songCreateFragment();

    @ActivityScoped
    @Binds
    abstract SongCreateContracts.Presenter presenter(SongCreatePresenter presenter);
}
