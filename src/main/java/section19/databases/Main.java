package section19.databases;

import lombok.extern.slf4j.Slf4j;
import section19.databases.dao.ArtistDao;
import section19.databases.dao.OrderBy;

@Slf4j
public class Main {
    private static final ArtistDao ARTIST_DAO  = new ArtistDao();

    public static void main(String[] args) {

        var artists = ARTIST_DAO.getAll(OrderBy.ORDER_BY_DESC,
                ArtistDao.COLUMN_ARTIST_NAME);
        artists.forEach(System.out::println);
    }
}
