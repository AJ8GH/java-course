package section19.databases.dao;

public class SqlConstants {

    public static final String ARTISTS_SONGS_ALBUMS_VIEW = "artists_songs_albums";
    public static final String ARTIST_VIEW = "artist";
    public static final String ALBUM_VIEW = "album";
    public static final String SONG_VIEW = "song";
    public static final String TRACK_VIEW = "track";

    public static final String TABLE_ARTISTS = "artists";
    public static final String COLUMN_ARTIST_ID = "_id";
    public static final String COLUMN_ARTIST_NAME = "name";

    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUM_ID = "_id";
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTIST = "artist";

    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONG_ID = "_id";
    public static final String COLUMN_SONG_TRACK = "track";
    public static final String COLUMN_SONG_TITLE = "title";
    public static final String COLUMN_SONG_ALBUM = "album";

    public static final String ALBUMS_BY_ARTIST_START = "SELECT " +
            TABLE_ALBUMS + "." +
            COLUMN_ALBUM_NAME + " FROM " +
            TABLE_ALBUMS + " INNER JOIN " +
            TABLE_ARTISTS + " ON " +
            TABLE_ALBUMS + "." +
            COLUMN_ALBUM_ARTIST + " = " +
            TABLE_ARTISTS + "." +
            COLUMN_ARTIST_ID + " WHERE " +
            TABLE_ARTISTS + "." +
            COLUMN_ARTIST_NAME + " = \"";

    public static final String ORDER_BY = " ORDER BY " +
            TABLE_ALBUMS + "." +
            COLUMN_ALBUM_NAME +
            " COLLATE NOCASE ";

    public static final String CREATE_SONGS_ARTISTS_ALBUMS_VIEW = "CREATE VIEW " +
            "IF NOT EXISTS " + ARTISTS_SONGS_ALBUMS_VIEW + " AS SELECT " +
            TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + " as " + ARTIST_VIEW + ", " +
            TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " as " + ALBUM_VIEW + ", " +
            TABLE_SONGS + "." + COLUMN_SONG_TRACK + " as " + TRACK_VIEW + ", " +
            TABLE_SONGS + "." + COLUMN_SONG_TITLE + " as " + SONG_VIEW + " FROM " +
            TABLE_SONGS + " INNER JOIN " + TABLE_ALBUMS + " ON " +
            TABLE_SONGS + "." + COLUMN_SONG_ALBUM + " = " +
            TABLE_ALBUMS + "." + COLUMN_ALBUM_ID + " INNER JOIN " +
            TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." +
            COLUMN_ALBUM_ARTIST + " = " + TABLE_ARTISTS + "." +
            COLUMN_ARTIST_ID + " ORDER BY " + TABLE_ARTISTS + "." +
            COLUMN_ARTIST_NAME + ", " + TABLE_ALBUMS + "." +
            COLUMN_ALBUM_NAME + ", " + TABLE_SONGS + "." + COLUMN_SONG_TRACK;

    public static final String GET_SONG_INFO = "SELECT " + ARTIST_VIEW + ", " +
            ALBUM_VIEW + ", " + TRACK_VIEW + " FROM " +
            ARTISTS_SONGS_ALBUMS_VIEW + " WHERE " + SONG_VIEW + " = ?";

    public static final String INSERT_SONG = "INSERT INTO " + TABLE_SONGS +
            " (" + COLUMN_SONG_TRACK + ", " + COLUMN_SONG_TITLE + ", " + COLUMN_SONG_ALBUM +
            ") VALUES (?, ?, ?)";

    public static final String INSERT_ALBUM = "INSERT INTO " + TABLE_ALBUMS +
            " (" + COLUMN_ALBUM_NAME + ", " + COLUMN_ALBUM_ARTIST + ") VALUES (?, ?)";

    public static final String INSERT_ARTIST = "INSERT INTO " + TABLE_ARTISTS +
            " (" + COLUMN_ARTIST_NAME + ") VALUES (?)";

    public static final String QUERY_ARTIST = "SELECT " + COLUMN_ARTIST_ID +
            " FROM " + TABLE_ARTISTS + " WHERE " + COLUMN_ARTIST_NAME + " = ?";

    public static final String QUERY_ALBUM = "SELECT " + COLUMN_ALBUM_ID +
            " FROM " + TABLE_ALBUMS + " WHERE " + COLUMN_ALBUM_NAME + " = ?";

}
