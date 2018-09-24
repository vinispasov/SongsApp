package com.wasp.songapp.diconfig;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.wasp.songapp.models.Song;
import com.wasp.songapp.views.mysongslist.SongsArrayAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class AdaptersModule {

    @Provides
    public ArrayAdapter<Song> getSongsArrayAdapter(Context context) {
        return new SongsArrayAdapter(context);
    }
}
