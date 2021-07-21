package section9.innerclasses;

import section8.linkedList.Song;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Album {
    private String name;
    private String artist;
    private SongList songs;

    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        this.songs = new SongList();
    }

    public boolean addSong(String title, double duration) {
        if (songs.findSong(title) != null) return false;
        return songs.add(new Song(title, duration));
    }

    public boolean addToPlayList(int trackNumber, LinkedList<Song> playList) {
        if (trackNumber > songs.songs.size() || trackNumber < 1) return false;
        Song song = songs.findSong(trackNumber - 1);
        return playList.add(song);
    }

    public boolean addToPlayList(String title, LinkedList<Song> playList) {
        Song song = songs.findSong(title);
        if (song == null) return false;
        return playList.add(song);
    }

    public static class SongList {
        private ArrayList<Song> songs;

        private SongList() {
            this.songs = new ArrayList<>();
        }

        private boolean add(Song song) {
            if (findSong(song.getTitle()) != null) return false;
            return songs.add(song);
        }

        private Song findSong(String title) {
            for (Song song : songs) {
                if (song.getTitle().equals(title)) return song;
            }
            return null;
        }

        private Song findSong(int trackNumber) {
            if (trackNumber < 1 || trackNumber > songs.size()) return null;
            return songs.get(trackNumber - 1);
        }
    }
}
