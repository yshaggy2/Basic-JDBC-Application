package com.revature.DAO;

import com.revature.model.Artist;
import com.revature.model.Song;
import com.revature.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class artistDAO implements artistDAOInterface {
    public Artist getArtistById(int id){
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM artists WHERE artist_id = ?";
            //write query
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            //prepare statement and set variable to id parameter
            ResultSet rs = ps.executeQuery();
            //execute
            if (rs.next()) {
                return new Artist(
                        rs.getInt("artist_id"),
                        rs.getString("artist_name"),
                        rs.getString("artist_genre"),
                        rs.getBoolean("artist_isalive")
                        );
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to get RoleBYID");
        }
        return null;
    }
    @Override
    public ArrayList<Artist> getArtists() {

        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM artists";
            Statement cs = conn.createStatement();
            ResultSet rs = cs.executeQuery(sql);

            ArrayList<Artist> artists = new ArrayList<>();
            while (rs.next()) {

                artists.add(new Artist(
                        rs.getInt("artist_id"),
                        rs.getString("artist_name"),
                        rs.getString("artist_genre"),
                        rs.getBoolean("artist_isalive")
                        )
                );
            }
            return artists;
        } catch (SQLException e) {
            System.out.println("Exception caught for get Artists");
        }
        return null;
    }

    @Override
    public Artist insertArtist(Artist artist) {
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO artists (artist_name, artist_genre , artist_isalive) VALUES (?,?,?)";
            //Instantiate a Prepared statement to hold our SQL command and fill in the wildcards "?"
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, artist.getArtist_name());
            ps.setString(2, artist.getArtist_genre());
            ps.setBoolean(3, artist.is_alive());
            //Now that our SQL command is complete, we can execute it
            ps.executeUpdate();
            //Now we can return the artist to the user, assuming nothing went wrong
            return artist;
        } catch (SQLException e) {
            System.out.println("Exception caught for insert artist");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String updateArtistGenre(int artist_id, String newGenre) {
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "UPDATE artists SET artist_genre = ? WHERE artist_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, newGenre);
            ps.setInt(2,artist_id);
            ps.executeUpdate();
            return newGenre;
        } catch (SQLException e) {
            System.out.println("Exception caught for get Artists");
        }
        return null;
    }
}
