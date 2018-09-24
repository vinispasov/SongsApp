package com.wasp.songapp.diconfig;

import com.wasp.songapp.models.Song;
import com.wasp.songapp.parsers.GsonJsonParser;
import com.wasp.songapp.parsers.base.JsonParser;

import dagger.Module;
import dagger.Provides;

@Module
public class ParsersModule {
    @Provides
    public JsonParser<Song> songsJsonParser() {
        return new GsonJsonParser<>(Song.class, Song[].class);
    }
}
