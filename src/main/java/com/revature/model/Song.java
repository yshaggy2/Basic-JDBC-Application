package com.revature.model;

public class Song {
    private int song_id;
    private String song_name;
    private int song_release_year;
    private Artist artist;
    private int artist_id_fk;

    public Song() {
    }

    public Song(int song_id, String song_name, int song_release_year, Artist artist) {
        this.song_id = song_id;
        this.song_name = song_name;
        this.song_release_year = song_release_year;
        this.artist = artist;
    }
    public Song(String song_name, int song_release_year, int artist_id_fk) {
        this.song_name = song_name;
        this.song_release_year = song_release_year;
        this.artist_id_fk = artist_id_fk;
    }

    public int getSong_id() {
        return song_id;
    }

    public void setSong_id(int song_id) {
        this.song_id = song_id;
    }

    public int getSong_release_year() {
        return song_release_year;
    }

    public void setSong_release_year(int song_release_year) {
        this.song_release_year = song_release_year;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public int getArtist_id_fk() {
        return artist_id_fk;
    }

    public void setArtist_id_fk(int artist_id_fk) {
        this.artist_id_fk = artist_id_fk;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }
}
