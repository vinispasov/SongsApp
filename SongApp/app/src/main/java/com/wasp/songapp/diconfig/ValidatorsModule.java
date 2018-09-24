package com.wasp.songapp.diconfig;

import com.wasp.songapp.models.Song;
import com.wasp.songapp.utils.validators.SongValidator;
import com.wasp.songapp.utils.validators.base.Validator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ValidatorsModule {
    @Provides
    @Singleton
    public Validator<Song> songValidator() {
        return new SongValidator();
    }
}
