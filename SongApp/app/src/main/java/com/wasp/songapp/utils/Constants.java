package com.wasp.songapp.utils;

public class Constants {

    public static final String BASE_SERVER_URL = "http://172.20.10.9:9396/api/songs";
    public static final String ERROR_MESSAGE = "Error: ";
    public static final String NO_SONGS_AVAILABLE_MESSAGE = "Your song list is empty";
    public static final String NO_SONGS_FOUND_ON_SEARCH_MESSAGE = "No songs match your search";
    public static final String SUCCESSFUL_DELETION_OF_SONG = "Song was successfully deleted!";
    public static final String ADD_OF_SONG_SUCCESS_MESSAGE = "Song was successfully added!";
    public static final String ADD_OF_SONG_FAIL_MESSAGE = "You must fill in all fields correctly!";
    public static final String SUCCESSFUL_PLAY_OF_SONG = "Song play completed!";


    public static final String SONG_AUTHOR_FIELD = "Author: ";
    public static final String SONG_TITLE_FIELD = "Title: ";
    public static final String SONG_DURATION_FIELD = "Duration: ";
    public static final String SONG_PLAYS_COUNT_FIELD = "Plays count: ";

    public static final float FROM_ALPHA_ANIMATION = 1F;
    public static final float TO_ALPHA_ANIMATION = 0.3F;
    public static final int SONG_PLAY_INCREMENT_VALUE = 1;

    public static final int MIN_SONG_PLAYS_COUNT_TO_BE_FAVORITE = 15;

    public static final int MIN_SONG_DURATION = 0;
    public static final int MIN_SONG_FIELD_LENGTH = 2;
    public static final int MAX_SONG_FIELD_LENGTH = 60;
    public static final int MIN_SONG_IMAGE_URL_LENGTH = 5;
    public static final int MAX_SONG_IMAGE_URL_ = 350;


}
