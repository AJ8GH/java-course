package section19.databases.dao;

import lombok.extern.slf4j.Slf4j;
import section19.databases.model.Album;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static section19.databases.dao.SqlConstants.ALBUMS_BY_ARTIST_START;
import static section19.databases.dao.SqlConstants.COLUMN_ALBUM_ARTIST;
import static section19.databases.dao.SqlConstants.COLUMN_ALBUM_ID;
import static section19.databases.dao.SqlConstants.COLUMN_ALBUM_NAME;
import static section19.databases.dao.SqlConstants.ORDER_BY;
import static section19.databases.dao.SqlConstants.TABLE_ALBUMS;

@Slf4j
public class AlbumDao {

    public List<Album> getAll() {
        try (Statement statement = Datasource.getInstance().createStatement();
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
            log.error("getAllAlbums - SQL Exception: {}", e.getMessage());
            return null;
        }
    }

    public List<Album> getByArtistName(String artist, OrderBy sortOrder) {
        StringBuilder query = new StringBuilder(ALBUMS_BY_ARTIST_START)
                .append(artist).append("\"");

        if (sortOrder != OrderBy.ORDER_BY_NONE) {
            query.append(ORDER_BY)
                    .append(sortOrder == OrderBy.ORDER_BY_DESC ? "DESC" : "ASC");
        }

        log.info("SQL Query: {}", query);

        try (Statement statement = Datasource.getInstance().createStatement();
             ResultSet results = statement.executeQuery(query.toString())) {

            List<Album> albums = new ArrayList<>();
            while (results.next()) {
                albums.add(Album.builder()
                        .name(results.getString(1))
                        .build());
            }
            return albums;
        } catch (SQLException e) {
            log.error("getAlbumsByArtistName - SQL Exception: {}", e.getMessage());
            return null;
        }
    }
}
