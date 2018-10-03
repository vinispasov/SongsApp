package com.wasp.songappspring.songs_service_tests;

import com.wasp.songappspring.models.Song;
import com.wasp.songappspring.repositories.SongsRepository;
import com.wasp.songappspring.services.SongsServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SongsServiceImplTests {

    private static final int NUMBER_SONGS_COUNT = 3;
    @Mock
    SongsRepository mockRepository;

    @InjectMocks
    SongsServiceImpl service;

    List<Song> defaultTestInput= Arrays.asList(
            new Song(
                    "Song1",
                    "Author1",
                    "3.12",
                    10,
                    "fkdflskdflskfsf"
            ),
            new Song(
                    "Song2",
                    "Author2",
                    "4,40",
                    0,
                    "fkdfl4134134skdflskfsf"
            ),
            new Song(
                    "Song3",
                    "Author3",
                    "5.07",
                    10312,
                    "fkdflskdfsf2f233fflskfsf"
            )
    );



    @Test
    public void addSongInList_Should_ExistInTheList(){
        Song song=new Song("Song7","Author7","3,21",0,"3131fsdfsdfsfs");
        service.addSong(song);
        verify(mockRepository).addSong(song);
    }

    @Test
    public void getAllSongs_Should_ReturnAllSongs_InTheList(){
        //Arrange
        Mockito.when(mockRepository.getSongs())
                .thenReturn(defaultTestInput);

        List<Song>result=service.getSongs();

        //Act & Assert
        Assert.assertTrue(NUMBER_SONGS_COUNT==result.size());
    }

    @Test
    public void getSongById_ExpectToReturn_SongById(){
        //Arrange
        Song song=new Song("Song4","Author4","4.30",1,"fsdfsdfsdfdsf");
        song.setId(1);

        Mockito.when(mockRepository.getSongById(1))
                .thenReturn(song);

        Song result=service.getSongById(song.getId());

        // Assert
        Assert.assertEquals(1,result.getId());
    }

    @Test
    public void updateSongPlayCounter_Expect_PlaysCounterUpdated(){
        //Arrange
        Song song=new Song("Song5","Author5","4.37",0,"fsdfsdfsdfgfsgdgdsf");
        song.setId(1);

        Mockito.when(mockRepository.updateSongPlayCounter(song,song.getId()))
                .thenReturn(song);

        Song result=service.updateSongPlayCounter(song,song.getId());

        //Assert
        Assert.assertEquals(song.getPlaysCount(),result.getPlaysCount());
    }

    @Test
    public void deleteSong_Expected_SongDoesNotExistInTheList(){
       service.deleteSong(1);
        verify(mockRepository).deleteSong(1);
    }

}
