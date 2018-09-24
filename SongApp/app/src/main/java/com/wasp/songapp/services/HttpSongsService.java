package com.wasp.songapp.services;

import com.wasp.songapp.models.Song;
import com.wasp.songapp.repositories.base.Repository;
import com.wasp.songapp.services.base.SongsService;
import com.wasp.songapp.utils.Constants;
import com.wasp.songapp.utils.validators.base.Validator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HttpSongsService implements SongsService {

    private final Repository<Song> mSongsRepository;
    private final Validator<Song> mSongValidator;

    public HttpSongsService(Repository<Song> songsRepository, Validator<Song> songValidator) {
        mSongsRepository = songsRepository;
        mSongValidator = songValidator;
    }


    @Override
    public void addSong(Song newSong) throws Exception {
        boolean isSongValid = mSongValidator.isItemValid(newSong);
        if (!isSongValid) {
            throw new Exception(Constants.ADD_OF_SONG_FAIL_MESSAGE);
        }
        mSongsRepository.add(newSong);
    }

    @Override
    public void deleteSong(int id) throws Exception {
        mSongsRepository.delete(id);
    }

    @Override
    public Song updateSongPlayCounter(Song songToUpdate, int id) throws Exception {

        return mSongsRepository.update(songToUpdate, id);
    }

    @Override
    public Song getSongById(int id) throws IOException {
        return mSongsRepository.getById(id);
    }

    @Override
    public List<Song> getAllSongs() throws IOException {
        return mSongsRepository.getAll();
    }

    @Override
    public List<Song> getFilteredSongs(String searchPattern) throws IOException {

        String searchPatternLowerCase = searchPattern.toLowerCase();

        List<Song> allSongs = getAllSongs();
        List<Song> filteredSongs = new ArrayList<>();

        allSongs
                .stream()
                .filter(song -> song.getAuthorName().toLowerCase().contains(searchPatternLowerCase))
                .forEach(filteredSongs::add);
        allSongs
                .stream()
                .filter(song -> song.getSongTitle().toLowerCase().contains(searchPatternLowerCase))
                .forEach(filteredSongs::add);

        return filteredSongs;

    }

    @Override
    public List<Song> getFavouriteSongs() throws IOException {

        return getAllSongs()
                .stream()
                .filter(song -> song.getPlaysCount() >= Constants.MIN_SONG_PLAYS_COUNT_TO_BE_FAVORITE)
                .sorted()
                .collect(Collectors.toList());

    }
}
