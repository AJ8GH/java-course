package section8.linkedList;

import java.util.ArrayList;
import java.util.LinkedList;

public class Album {
    private String name;
    private String artist;
    private ArrayList<Song> songs;

    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        this.songs = new ArrayList<>();
    }

    public boolean addSong(String title, double duration) {
        if (findSong(title) != null) return false;
        return songs.add(new Song(title, duration));
    }

    private Song findSong(String songName) {
        for (Song song : songs) {
            if (song.getTitle().equals(songName)) return song;
        }
        return null;
    }

    public boolean addToPlayList(int trackNumber, LinkedList<Song> playList) {
        if (trackNumber > songs.size() || trackNumber < 1) return false;
        Song song = songs.get(trackNumber - 1);
        return playList.add(song);
    }

    public boolean addToPlayList(String title, LinkedList<Song> playList) {
        Song song = findSong(title);
        if (song == null) return false;
        return playList.add(song);
    }
}
