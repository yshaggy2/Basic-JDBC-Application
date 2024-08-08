package com.revature.DAO;

import com.revature.model.Artist;
import com.revature.model.Song;
import com.revature.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class songDAO implements songDAOInterface {


    @Override
    public ArrayList<Song> getSongs() {
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM songs";
            Statement cs = conn.createStatement();
            ResultSet rs = cs.executeQuery(sql);

            ArrayList<Song> songs = new ArrayList<>();
            while (rs.next()) {
                artistDAO aDao = new artistDAO();
                Artist songArtist = aDao.getArtistById(rs.getInt("artist_id_fk"));
                Song song = new Song(rs.getInt("song_id"),
                        rs.getString("song_name"),
                        rs.getInt("song_release_year"),
                        songArtist);
                song.setArtist_id_fk(rs.getInt("artist_id_fk"));
                songs.add(song);
            }
            return songs;
        } catch (SQLException e) {
            System.out.println("Exception caught for get Songs");
        }
        return null;
    }
    //If I have time, it'd be cool to do an overloaded getSongsFromArtist using the artist genre
    @Override
    public ArrayList<Song> getSongsFromArtist(int artist_id) {

        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM songs WHERE artist_id_fk = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, artist_id);
            ResultSet rs = ps.executeQuery();

            ArrayList<Song> songs = new ArrayList<>();
            while (rs.next()) {
                artistDAO aDao = new artistDAO();
                Artist songArtist = aDao.getArtistById(rs.getInt("artist_id_fk"));
                Song song = new Song(rs.getInt("song_id"),
                        rs.getString("song_name"),
                        rs.getInt("song_release_year"),
                        songArtist);
                song.setArtist_id_fk(rs.getInt("artist_id_fk"));
                songs.add(song);
            }
            return songs;
        } catch (SQLException e) {
            System.out.println("Exception caught for get Songs from Artist");
        }
        return null;
    };
    @Override
    public ArrayList<Song> getSongsFromArtist(String artist_name) {
            int artist_id = 0;
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT artist_id from artists WHERE artist_name = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, artist_name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                artist_id = rs.getInt("artist_id");
            } else {
                System.out.println("Artist not in database");
            }
        } catch (SQLException e) {
            System.out.println("Couldn't get song from Artist name");
        }
        return getSongsFromArtist(artist_id);
    };
    @Override
    public Song insertSong(Song song) {
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO songs (song_name, song_release_year, artist_id_fk) VALUES (?,?,?)";
            //Instantiate a Prepared statement to hold our SQL command and fill in the wildcards "?"
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, song.getSong_name());
            ps.setInt(2, song.getSong_release_year());
            ps.setInt(3, song.getArtist_id_fk());
            //Now that our SQL command is complete, we can execute it
            ps.executeUpdate();

            //Now we can return the emp to the user, assuming nothing went wrong
            return song;
        } catch (SQLException e) {
            System.out.println("Exception caught for insert Song");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteSong(int song_id) {
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "DELETE FROM songs WHERE song_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, song_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Exception caught for delete song");
            e.printStackTrace();
        }
    }
}
