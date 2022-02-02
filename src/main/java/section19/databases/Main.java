package section19.databases;

import lombok.extern.slf4j.Slf4j;
import section19.databases.dao.AlbumDao;
import section19.databases.dao.ArtistDao;
import section19.databases.dao.Datasource;
import section19.databases.dao.MusicDao;
import section19.databases.dao.OrderBy;
import section19.databases.dao.SongDao;
import section19.databases.dao.SqlConstants;

import java.util.Scanner;

import static section19.databases.dao.SqlConstants.COLUMN_ARTIST_NAME;

@Slf4j
public class Main {
    private static final ArtistDao ARTIST_DAO = new ArtistDao();
    private static final AlbumDao ALBUM_DAO = new AlbumDao();
    private static final SongDao SONG_DAO = new SongDao();
    private static final MusicDao MUSIC_DAO = new MusicDao();

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        Datasource datasource = Datasource.getInstance();
        datasource.open();

        var artists = ARTIST_DAO.getAll(OrderBy.ORDER_BY_DESC, COLUMN_ARTIST_NAME);
        artists.forEach(System.out::println);

        var albums = ALBUM_DAO.getByArtistName("Iron Maiden", OrderBy.ORDER_BY_DESC);
        albums.forEach(System.out::println);

        SONG_DAO.getSongsMetadata();

        if (MUSIC_DAO.createView(SqlConstants.CREATE_SONGS_ARTISTS_ALBUMS_VIEW)) {
            log.info("View created successfully");
        } else {
            log.info("Problem creating view");
        }

        var songInfoList = MUSIC_DAO.getSongInfo("Go Your Own Way");
        songInfoList.forEach(System.out::println);

//        System.out.println("Enter a song title: ");
//        songInfoList = MUSIC_DAO.getSongInfo(SCANNER.nextLine());
//        songInfoList.forEach(System.out::println);

        MUSIC_DAO.insertSong("sdfs", "sdfsdf", "sdfsd", 1);
        MUSIC_DAO.getSongInfo("Song 2");

        datasource.close();
    }
}
