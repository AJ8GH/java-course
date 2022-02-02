package section19.databases.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import section19.databases.model.Artist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static section19.databases.dao.SqlConstants.COLUMN_ARTIST_ID;
import static section19.databases.dao.SqlConstants.COLUMN_ARTIST_NAME;
import static section19.databases.dao.SqlConstants.TABLE_ARTISTS;

@Slf4j
@RequiredArgsConstructor
public class ArtistDao {


    public List<Artist> getAll() {
        return getAll(OrderBy.ORDER_BY_NONE, null);
    }

    public List<Artist> getAll(OrderBy sortOrder, String sortColumn) {
        StringBuilder sb = new StringBuilder("SELECT * FROM ")
                .append(TABLE_ARTISTS);

        if (sortOrder != OrderBy.ORDER_BY_NONE) {
            sb.append(" ORDER BY ")
                    .append(sortColumn)
                    .append(" COLLATE NOCASE ")
                    .append(sortOrder == OrderBy.ORDER_BY_DESC ? "DESC" : "ASC");
        }
        try (Statement statement = Datasource.getInstance().createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            List<Artist> artists = new ArrayList<>();
            while (results.next()) {
                artists.add(Artist.builder()
                        .id(results.getInt(COLUMN_ARTIST_ID))
                        .name(results.getString(COLUMN_ARTIST_NAME))
                        .build());
            }
            return artists;
        } catch (SQLException e) {
            log.error("#queryArtists() - SQL Exception: {}", e.getMessage());
            return null;
        }
    }

    public Artist get(int id) {
        return null;
    }

    public boolean delete(Artist artist) {
        return false;
    }

    public boolean update(Artist artist, String[] params) {
        return false;
    }

    public boolean save(Artist artist) {
        return false;
    }
}
