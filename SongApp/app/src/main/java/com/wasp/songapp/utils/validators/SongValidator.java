package com.wasp.songapp.utils.validators;

import com.wasp.songapp.models.Song;
import com.wasp.songapp.utils.Constants;
import com.wasp.songapp.utils.validators.base.Validator;

import java.util.Objects;

public class SongValidator implements Validator<Song> {
    @Override
    public boolean isItemValid(Song song) {
        return (!Objects.equals(song, null)) &&
                isTitleNameValid(song)
                && isAuthorNameValid(song)
                && isDurationValid(song)
                && isImageUrlValid(song);
    }

    private boolean isTitleNameValid(Song song) {
        return song.getSongTitle().length() >= Constants.MIN_SONG_FIELD_LENGTH &&
                song.getSongTitle().length() <= Constants.MAX_SONG_FIELD_LENGTH;
    }

    private boolean isAuthorNameValid(Song song) {
        return song.getAuthorName().length() >= Constants.MIN_SONG_FIELD_LENGTH &&
                song.getAuthorName().length() <= Constants.MAX_SONG_FIELD_LENGTH;
    }

    private boolean isDurationValid(Song song) {
        return (!Objects.equals(song.getSongDuration(), null)) &&
                (Double.parseDouble(song.getSongDuration()) > Constants.MIN_SONG_DURATION);
    }

    private boolean isImageUrlValid(Song song) {
        return song.getImageUrl().length() >= Constants.MIN_SONG_IMAGE_URL_LENGTH &&
                song.getImageUrl().length() <= Constants.MAX_SONG_IMAGE_URL_;
    }
}
