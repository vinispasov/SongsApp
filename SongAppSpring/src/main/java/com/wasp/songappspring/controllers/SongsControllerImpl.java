package com.wasp.songappspring.controllers;

import com.wasp.songappspring.controllers.base.SongsControllerBase;
import com.wasp.songappspring.models.Song;
import com.wasp.songappspring.services.SongsServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/songs")
public class SongsControllerImpl implements SongsControllerBase {

    private SongsServiceBase songsService;

    @Autowired
    public SongsControllerImpl(SongsServiceBase songsService) {
        setSongsService(songsService);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @Override
    public void addSong(@RequestBody @Valid Song newSong) {
        songsService.addSong(newSong);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @Override
    public void deleteSong(@PathVariable(value = "id") @Valid int id) {
        songsService.deleteSong(id);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)

    @Override
    public Song updateSongPlayCounter(@RequestBody @Valid Song songToUpdate, @PathVariable(value = "id") @Valid int id) {
        return songsService.updateSongPlayCounter(songToUpdate,id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Override
    public Song getSongById(@PathVariable(value = "id") @Valid int id) {
        return songsService.getSongById(id);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @Override
    public List<Song> getSongs() {
        return songsService.getSongs();
    }

    private void setSongsService(SongsServiceBase songsService) {
        this.songsService = songsService;
    }
}
