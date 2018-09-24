package com.wasp.songappspring.controllers.base;

import com.wasp.songappspring.models.Song;

import java.util.List;

public interface SongsControllerBase {

    void addSong(Song newSong);

    void deleteSong( int id);

    Song updateSongPlayCounter(Song songToUpdate, int id);

    Song getSongById(int id);

    List<Song> getSongs();

}
