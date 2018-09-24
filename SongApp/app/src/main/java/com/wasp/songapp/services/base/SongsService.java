package com.wasp.songapp.services.base;

import com.wasp.songapp.models.Song;

import java.io.IOException;
import java.util.List;

public interface SongsService {

    void addSong(Song newSong) throws Exception;

    void deleteSong(int id) throws Exception;

    Song updateSongPlayCounter(Song songToUpdate, int id) throws Exception;

    Song getSongById(int id) throws IOException;

    List<Song> getAllSongs() throws IOException;

    List<Song> getFilteredSongs(String pattern) throws IOException;

    List<Song> getFavouriteSongs()throws IOException;

}
