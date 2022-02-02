package section19.databases.dao;

import lombok.extern.slf4j.Slf4j;
import section19.databases.model.Song;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static section19.databases.dao.SqlConstants.COLUMN_SONG_ALBUM;
import static section19.databases.dao.SqlConstants.COLUMN_SONG_ID;
import static section19.databases.dao.SqlConstants.COLUMN_SONG_TITLE;
import static section19.databases.dao.SqlConstants.COLUMN_SONG_TRACK;
import static section19.databases.dao.SqlConstants.TABLE_SONGS;

@Slf4j
public class SongDao {

    public List<Song> getAll() {
        try (Statement statement = Datasource.getInstance().createStatement();
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
            log.error("getAllSongs - SQL Exception: {}", e.getMessage());
            return null;
        }
    }

    public ResultSetMetaData getSongsMetadata() {
        String sql = "SELECT * FROM " + TABLE_SONGS;
        try (Statement statement = Datasource.getInstance().createStatement();
             ResultSet results = statement.executeQuery(sql)) {

            ResultSetMetaData metaData = results.getMetaData();
            printMetadata(metaData);
            return metaData;

        } catch (SQLException e) {
            log.error("getSongsMetadata - SQL Exception: {}", e.getMessage());
            return null;
        }
    }

    private void printMetadata(ResultSetMetaData metaData) {
        try {
            log.info("Metadata: {}", metaData.getTableName(1));
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                log.info("Column {}: {} ({})", i,
                        metaData.getColumnName(i),
                        metaData.getColumnTypeName(i));
            }
        } catch (SQLException e) {
            log.error("printMetadata - SQL Exception: {}", e.getMessage());
        }
    }
}
