package com.revature.DAO;

import com.revature.model.Artist;

import java.util.ArrayList;

public interface artistDAOInterface {
    /*
4. Insert a new user into the users table
-->Insert Artist
5. Update a user
-->Update Artist*/
    ArrayList<Artist> getArtists();
    Artist insertArtist(Artist artist);
    //Update Artist: Change genre
    String updateArtistGenre(int artist_id, String newGenre);
    Artist getArtistById(int id);
}
