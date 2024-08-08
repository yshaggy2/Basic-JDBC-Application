package com.revature.model;

public class Artist {
    private int artist_id;
    private String artist_name;
    private String artist_genre;
    private boolean _alive;

    public Artist() {
    }

    public Artist(int artist_id, String artist_name, String artist_genre, boolean is_alive) {
        this.artist_id = artist_id;
        this.artist_name = artist_name;
        this.artist_genre = artist_genre;
        this._alive = is_alive;
    }

    public int getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public String getArtist_genre() {
        return artist_genre;
    }

    public void setArtist_genre(String artist_genre) {
        this.artist_genre = artist_genre;
    }

    public boolean is_alive() {
        return _alive;
    }

    public void setIs_alive(boolean is_alive) {
        this._alive = is_alive;
    }
}
