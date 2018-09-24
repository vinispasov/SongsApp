package com.wasp.songapp.diconfig;

import com.wasp.songapp.http.base.HttpRequester;
import com.wasp.songapp.models.Song;
import com.wasp.songapp.parsers.base.JsonParser;
import com.wasp.songapp.repositories.HttpRepository;
import com.wasp.songapp.repositories.base.Repository;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoriesModule {

    @Provides
    @Singleton
    public Repository<Song> songsRepository(
            @Named("baseServerUrl") String baseServerUrl,
            HttpRequester httpRequester,
            JsonParser<Song> jsonParser) {

        return new HttpRepository<>(baseServerUrl, httpRequester, jsonParser);
    }
}
