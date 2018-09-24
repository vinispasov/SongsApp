package com.wasp.songapp.diconfig;

import com.wasp.songapp.views.addnewsong.SongCreateActivity;
import com.wasp.songapp.views.applicationlaunch.AppLaunchActivity;
import com.wasp.songapp.views.favouritessongslist.FavouriteSongsListActivity;
import com.wasp.songapp.views.mysongslist.MySongsListActivity;
import com.wasp.songapp.views.songdetails.SongDetailsActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = AppLaunchModule.class)
    abstract AppLaunchActivity appLaunchActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = MySongsListModule.class)
    abstract MySongsListActivity mySongsListActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = SongDetailsModule.class)
    abstract SongDetailsActivity songDetailsActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = SongCreateModule.class)
    abstract SongCreateActivity songCreateActivity();


    @ActivityScoped
    @ContributesAndroidInjector(modules = FavouriteSongsListModule.class)
    abstract FavouriteSongsListActivity favouriteSongsListActivity();
}
