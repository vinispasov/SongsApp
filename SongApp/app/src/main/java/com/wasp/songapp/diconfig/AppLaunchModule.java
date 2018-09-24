package com.wasp.songapp.diconfig;

import com.wasp.songapp.views.applicationlaunch.AppLaunchContracts;
import com.wasp.songapp.views.applicationlaunch.AppLaunchFragment;
import com.wasp.songapp.views.applicationlaunch.AppLaunchPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AppLaunchModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract AppLaunchFragment appLaunchFragment();

    @ActivityScoped
    @Binds
    abstract AppLaunchContracts.Presenter presenter(AppLaunchPresenter presenter);

}
