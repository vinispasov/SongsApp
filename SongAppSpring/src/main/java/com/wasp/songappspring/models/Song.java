package com.wasp.songappspring.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Song {
    //fields


    public int id;

    @NotNull
    @Size(min = 2, max = 60)
    public String songTitle;

    @NotNull
    @Size(min = 2, max = 60)
    public String authorName;

    @NotNull
    @Size(min = 2, max = 60)
    public String songDuration;

    @NotNull
    @Min(value = 0)
    public int playsCount;

    @NotNull
    @Size(min = 5, max = 350)
    public String imageUrl;


    //constructor

    public Song() {

    }

    public Song(String songTitle, String authorName, String songDuration, int playsCount, String imageUrl) {
        this.songTitle = songTitle;
        this.authorName = authorName;
        this.songDuration = songDuration;
        this.playsCount = playsCount;
        this.imageUrl = imageUrl;
    }

    public Song(int id, String songTitle, String authorName, String songDuration, int playsCount, String imageUrl) {
        this(songTitle, authorName, songDuration, playsCount, imageUrl);
        this.setId(id);
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getSongDuration() {
        return songDuration;
    }

    public void setSongDuration(String songDuration) {
        this.songDuration = songDuration;
    }

    public int getPlaysCount() {
        return playsCount;
    }

    public void setPlaysCount(int playsCount) {
        this.playsCount = playsCount;
    }
}
