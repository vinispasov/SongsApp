package com.wasp.songappspring.services;

import com.wasp.songappspring.models.Song;

import java.util.List;

public interface SongsServiceBase {

    void addSong(Song newSong);

    void deleteSong(int id);

    Song updateSongPlayCounter(Song songToUpdate, int id);

    Song getSongById(int id);

    List<Song> getSongs();
}
