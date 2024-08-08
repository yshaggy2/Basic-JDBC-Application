package com.revature;

import com.revature.controller.ArtistController;
import com.revature.controller.AuthController;
import com.revature.controller.SongController;
import com.revature.util.ConnectionUtil;
import io.javalin.Javalin;
import io.javalin.http.Handler;

import java.sql.Connection;
import java.sql.SQLException;
//TODO: FIGURE OUT WHY RETURNS don't return serial ids or artists object
//Should maybe make a document with body text for live demo preparation

public class Main {
    public static void main(String[] args) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            System.out.println("CONNECTION SUCCESSFUL");
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("connection failed");
        }
        //typical Javalin setup syntax
        var app = Javalin.create(/*any extra configs would go here*/).start(3000)
                .get("/", ctx -> ctx.result("Hello Postman!"));

        //This will check for login authentication


        //initialize controllers
        ArtistController ac = new ArtistController();
        SongController sc = new SongController();
        AuthController tc = new AuthController();
        //create HTTP protocol commands

        //Middleware Handler should check to make sure user is logged in
        //The following line applies Middleware to all paths.
        //AuthController paths:
        //Login and Register should bypass Middleware

        app.before("/*", tc.beforeHandler);
        app.post("/login", tc.loginHandler);
        app.post("/register", tc.registerHandler);
        app.post("/logout", tc.logoutHandler);

        //ArtistController paths:
        //Get All Artists
        app.get("/artists", ac.getArtistsHandler);
        //Insert Artist
        app.post("/artists", ac.insertArtistHandler);
        //Update Artist Genre
        app.patch("/artists/updateGenre/{id}", ac.updateArtistGenreHandler);
        //Note: for updating Artist genre, we will take the "id" variable from the path to find which row to change genre for,
        //and the body of the http request will contain the string with the updated genre name.

        //SongController paths:

        app.get("/songs", sc.getSongsHandler);

        app.get("/songs/{id}", sc.getSongsbyArtistHandler);
        app.get("/songsBy/{id}", sc.getSongsByArtistNameHandler);
        app.post("/songs", sc.insertSongHandler);
        app.delete("/songs/{id}", sc.deleteSongHandler);



    }

}