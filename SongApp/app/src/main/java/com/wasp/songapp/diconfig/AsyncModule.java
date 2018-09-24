package com.wasp.songapp.diconfig;

import com.wasp.songapp.async.AsyncSchedulerProvider;
import com.wasp.songapp.async.base.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AsyncModule {
    @Provides
    @Singleton
    public SchedulerProvider schedulerProvider() {
        return AsyncSchedulerProvider.getInstance();
    }
}
