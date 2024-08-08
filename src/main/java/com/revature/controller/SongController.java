package com.revature.controller;

import com.revature.DAO.songDAO;
import com.revature.model.Artist;
import com.revature.model.Song;
import io.javalin.http.Handler;

import java.util.ArrayList;

public class SongController {
    songDAO sDAO = new songDAO();

    public Handler getSongsHandler = ctx -> {
        ArrayList<Song> songs = sDAO.getSongs();

        ctx.json(songs);
        ctx.status(200);
    };
    public Handler getSongsbyArtistHandler = ctx -> {
        ArrayList<Song> songs = sDAO.getSongsFromArtist(Integer.parseInt(ctx.pathParam("id")));
        if (songs == null) {
            ctx.status(400);    //bad request - Artist not found or Artist has no songs.
            ctx.result("Artist not found or Artist has no songs");
        }
        else {
            ctx.json(songs);
            ctx.status(200);
        }
    };
    public Handler getSongsByArtistNameHandler = ctx -> {
        ArrayList<Song> songs = sDAO.getSongsFromArtist(ctx.pathParam("id"));
        if (songs == null) {
            ctx.status(400);    //bad request - Artist not found or Artist has no songs.
            ctx.result("Artist not found or Artist has no songs");
        }
        else {
            ctx.json(songs);
            ctx.status(200);
        }
    };
    public Handler insertSongHandler = ctx -> {
        Song insertedSong = sDAO.insertSong(ctx.bodyAsClass(Song.class));
        if (insertedSong == null) {
            ctx.status(400);
            ctx.result("failed to insert song!");
        } else {
            ctx.status(200);
            ctx.json(insertedSong);
        }
    };
    public Handler deleteSongHandler = ctx -> {
        int role_id = Integer.parseInt(ctx.pathParam("id"));
        sDAO.deleteSong(role_id);
        ctx.result("Song "+role_id+" no longer in database.");
        ctx.status(200);
    };

}
