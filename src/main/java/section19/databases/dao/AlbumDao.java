package section19.databases.dao;

import lombok.extern.slf4j.Slf4j;
import section19.databases.model.Album;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AlbumDao {
    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUM_ID = "_id";
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTIST = "artist";

    public List<Album> getAll() {
        try (Datasource datasource = new Datasource();
             Statement statement = datasource.createStatement();
             ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_ALBUMS)) {

            List<Album> albums = new ArrayList<>();
            while (results.next()) {
                albums.add(Album.builder()
                        .id(results.getInt(COLUMN_ALBUM_ID))
                        .name(results.getString(COLUMN_ALBUM_NAME))
                        .artistId(results.getInt(COLUMN_ALBUM_ARTIST))
                        .build());
            }
            return albums;
        } catch (SQLException e) {
            log.error("#queryArtists() - SQL Exception: {}", e.getMessage());
            return null;
        }
    }
}
