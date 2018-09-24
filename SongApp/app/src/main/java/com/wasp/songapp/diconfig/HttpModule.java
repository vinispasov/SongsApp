package com.wasp.songapp.diconfig;

import com.wasp.songapp.utils.Constants;
import com.wasp.songapp.http.OkHttpHttpRequester;
import com.wasp.songapp.http.base.HttpRequester;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class HttpModule {
    @Provides
    public HttpRequester httpRequester() {
        return new OkHttpHttpRequester();
    }

    @Provides
    @Named("baseServerUrl")
    public String baseServerUrl() {
        return Constants.BASE_SERVER_URL;
    }
}
