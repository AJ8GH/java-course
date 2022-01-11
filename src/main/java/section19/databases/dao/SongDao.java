package section19.databases.dao;

import lombok.extern.slf4j.Slf4j;
import section19.databases.model.Song;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SongDao {
    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONG_ID = "_id";
    public static final String COLUMN_SONG_TRACK = "track";
    public static final String COLUMN_SONG_TITLE = "title";
    public static final String COLUMN_SONG_ALBUM = "album";

    public List<Song> getAll() {
        try (Datasource datasource = new Datasource();
             Statement statement = datasource.createStatement();
             ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_SONGS)) {

            List<Song> songs = new ArrayList<>();
            while (results.next()) {
                songs.add(Song.builder()
                        .id(results.getInt(COLUMN_SONG_ID))
                        .track(results.getInt(COLUMN_SONG_TRACK))
                        .title(results.getString(COLUMN_SONG_TITLE))
                        .albumId(results.getInt(COLUMN_SONG_ALBUM))
                        .build());
            }
            return songs;
        } catch (SQLException e) {
            log.error("#queryArtists() - SQL Exception: {}", e.getMessage());
            return null;
        }
    }
}
