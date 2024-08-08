package com.revature.controller;

import com.revature.DAO.artistDAO;
import com.revature.model.Artist;
import io.javalin.http.Handler;

import java.util.ArrayList;

public class ArtistController {
    //instantiate an artist Data Access Object
    artistDAO aDAO = new artistDAO();
    //Need Handler for insertArtist

    public Handler getArtistsHandler = ctx -> {
        ArrayList<Artist> artists = aDAO.getArtists();

        ctx.json(artists);

        //we can also set the HTTP response status code with ctx.status()
        ctx.status(200);
    };
    public Handler insertArtistHandler = ctx -> {
        Artist newArtist = ctx.bodyAsClass(Artist.class);
        Artist insertedArtist = aDAO.insertArtist(newArtist);
        if (insertedArtist == null) {
            ctx.status(400);
            ctx.result("Failed to insert Artist!");
        } else {
            ctx.result("Artist indexed properly, use Get /artists to check");
            ctx.status(201);
            ctx.json(insertedArtist);
        }
    };
    //Need Handler for updateArtistGenre
    public Handler updateArtistGenreHandler = ctx -> {
        int artist_id = Integer.parseInt(ctx.pathParam("id"));
        String genre = ctx.body();
        if (aDAO.updateArtistGenre(artist_id, genre) != null) {
            ctx.status(200);
            ctx.result("Artist genre updated to " + genre);
        } else {
            ctx.status(400);
            ctx.result("update failed");
        }
    };
}
