package section19.databases.dao;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static section19.databases.dao.SqlConstants.GET_SONG_INFO;
import static section19.databases.dao.SqlConstants.INSERT_ALBUM;
import static section19.databases.dao.SqlConstants.INSERT_ARTIST;
import static section19.databases.dao.SqlConstants.INSERT_SONG;
import static section19.databases.dao.SqlConstants.QUERY_ALBUM;
import static section19.databases.dao.SqlConstants.QUERY_ARTIST;

@Slf4j
@Data
public class Datasource implements AutoCloseable {
    private static final Datasource INSTANCE = new Datasource();

    public static final String DB_NAME = "music.db";
    public static final String PATH = "jdbc:sqlite:./db/";
    public static final String CONNECTION_STRING = PATH + DB_NAME;

    private Connection conn;
    private PreparedStatement getSongInfoQuery;
    private PreparedStatement insertIntoSongs;
    private PreparedStatement insertIntoArtists;
    private PreparedStatement insertIntoAlbums;
    private PreparedStatement queryArtist;
    private PreparedStatement queryAlbum;

    public static Datasource getInstance() {
        return INSTANCE;
    }

    private Datasource() {
    }

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            getSongInfoQuery = conn.prepareStatement(GET_SONG_INFO);
            queryArtist = conn.prepareStatement(QUERY_ARTIST);
            queryAlbum = conn.prepareStatement(QUERY_ALBUM);
            insertIntoSongs = conn.prepareStatement(INSERT_SONG);
            insertIntoArtists = conn.prepareStatement(INSERT_ARTIST, Statement.RETURN_GENERATED_KEYS);
            insertIntoAlbums = conn.prepareStatement(INSERT_ALBUM, Statement.RETURN_GENERATED_KEYS);
            return true;
        } catch (SQLException e) {
            log.info("#open() - SQL Exception: {}", e.getMessage());
            return false;
        }
    }

    @Override
    public void close() {
        try {
            if (getSongInfoQuery != null) getSongInfoQuery.close();
            if (insertIntoAlbums != null) insertIntoAlbums.close();
            if (insertIntoSongs != null) insertIntoSongs.close();
            if (insertIntoArtists != null) insertIntoArtists.close();
            if (queryArtist != null) queryArtist.close();
            if (queryAlbum != null) queryAlbum.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            log.info("#close() - SQL Exception: {}", e.getMessage());
        }
    }

    public Statement createStatement() {
        try {
            return conn.createStatement();
        } catch (SQLException e) {
            log.info("#createStatement() - SQL Exception: {}", e.getMessage());
            return null;
        }
    }
}
