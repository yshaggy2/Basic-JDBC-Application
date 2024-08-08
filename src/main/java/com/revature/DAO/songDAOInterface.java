package com.revature.DAO;

import com.revature.model.Song;

import java.util.ArrayList;

public interface songDAOInterface {

//1. Select all items from the items table (songs)
    ArrayList<Song> getSongs();
//2. Select all songs belonging to an artist_id
    ArrayList<Song> getSongsFromArtist(int artist_id);
    //Same thing but by artist name
    ArrayList<Song> getSongsFromArtist(String artist_name);
    //3. Insert a new song into the songs table
    Song insertSong(Song song);
//6. Delete a song
    void deleteSong(int song_id);

}
