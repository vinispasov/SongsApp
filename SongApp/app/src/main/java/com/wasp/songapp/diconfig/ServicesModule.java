package com.wasp.songapp.diconfig;

import com.wasp.songapp.models.Song;
import com.wasp.songapp.repositories.base.Repository;
import com.wasp.songapp.services.HttpSongsService;
import com.wasp.songapp.services.base.SongsService;
import com.wasp.songapp.utils.validators.base.Validator;

import dagger.Module;
import dagger.Provides;

@Module
public class ServicesModule {
    @Provides
    public SongsService songsService(Repository<Song> repository, Validator<Song> songValidator) {
        return new HttpSongsService(repository, songValidator);
    }
}
