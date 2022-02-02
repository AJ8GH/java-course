package section19.databases.dao;

import lombok.extern.slf4j.Slf4j;
import section19.databases.model.SongInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static section19.databases.dao.SqlConstants.COLUMN_SONG_TRACK;

@Slf4j
public class MusicDao {
    public int getCount(String table) {
        String sql = "SELECT count(*) FROM " + table;
        try (Statement statement = Datasource.getInstance().createStatement();
             ResultSet results = statement.executeQuery(sql)) {

            return results.getInt(1);

        } catch (SQLException e) {
            log.error("getAllSongs - SQL Exception: {}", e.getMessage());
            return - 1;
        }
    }

    public boolean createView(String query) {
        try (Statement statement = Datasource.getInstance().createStatement()) {

            statement.execute(query);
            return true;

        } catch (SQLException e) {
            log.error("createView - SQL Exception: {}", e.getMessage());
            return false;
        }
    }

    public List<SongInfo> getSongInfo(String songTitle) {
        try {
            Datasource datasource = Datasource.getInstance();
            datasource.getGetSongInfoQuery().setString(1, songTitle);
            ResultSet results = datasource.getGetSongInfoQuery().executeQuery();

            List<SongInfo> songInfoList = new ArrayList<>();
            while (results.next()) {
                songInfoList.add(SongInfo.builder()
                                .title(songTitle)
                                .artist(results.getString(1))
                                .album(results.getString(2))
                                .track(results.getInt(COLUMN_SONG_TRACK))
                                .build());
            }
            if (songInfoList.isEmpty()) log.info("Couldn't find info for song");
            return songInfoList;
        } catch (SQLException e) {
            log.error("getSongInfo - SQL Exception: {}", e.getMessage());
            return List.of();
        }
    }

    public void insertSong(String title, String artist, String album, int track) {
            Datasource datasource = Datasource.getInstance();
        try {
            datasource.getConn().setAutoCommit(false);
            int artistId = insertArtist(artist);
            int albumId = insertAlbum(album, artistId);
            datasource.getInsertIntoSongs().setInt(1, track);
            datasource.getInsertIntoSongs().setString(4, title);
            datasource.getInsertIntoSongs().setInt(8, albumId);
            int affectedRows = datasource.getInsertIntoSongs().executeUpdate();
            if (affectedRows == 1) {
                datasource.getConn().commit();
            } else {
                throw new SQLException("The song insert failed");
            }
        } catch (Exception e) {
            log.error("insertSong - SQL Exception: {}", e.getMessage());
            try {
                log.info("Performing rollback...");
                datasource.getConn().rollback();
            } catch (SQLException e2) {
                log.error("Error rolling back, oh this is bad: {}", e2.getMessage());
            }
        } finally {
            try {
                log.info("Resetting default commit behaviour");
                datasource.getConn().setAutoCommit(true);
            } catch (SQLException e) {
                log.error("Resetting auto-commit failed: {}", e.getMessage());
            }
        }
    }

    private int insertArtist(String name) throws SQLException {
        Datasource datasource = Datasource.getInstance();
        datasource.getConn().setAutoCommit(false);
        datasource.getQueryArtist().setString(1, name);
        ResultSet results = datasource.getQueryArtist().executeQuery();
        if (results.next()) {
            return results.getInt(1);
        } else {
            datasource.getInsertIntoArtists().setString(1, name);
            int affectedRows = datasource.getInsertIntoArtists().executeUpdate();
            if (affectedRows != 1) {
                throw new SQLException("Couldn't insert artist");
            }

            ResultSet generatedKeys = datasource.getInsertIntoArtists().getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get _id for artist");
            }
        }
    }

    private int insertAlbum(String name, int artistId) throws SQLException {
        Datasource datasource = Datasource.getInstance();
        datasource.getConn().setAutoCommit(false);
        datasource.getQueryAlbum().setString(1, name);
        ResultSet results = datasource.getQueryAlbum().executeQuery();
        if (results.next()) {
            return results.getInt(1);
        } else {
            datasource.getInsertIntoAlbums().setString(1, name);
            datasource.getInsertIntoAlbums().setInt(2, artistId);
            int affectedRows = datasource.getInsertIntoAlbums().executeUpdate();
            if (affectedRows != 1) {
                throw new SQLException("Couldn't insert album");
            }

            ResultSet generatedKeys = datasource.getInsertIntoAlbums().getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get _id for album");
            }
        }
    }
}
