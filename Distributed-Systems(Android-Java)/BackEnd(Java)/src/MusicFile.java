import java.io.*;

public class MusicFile implements Serializable {

    //Class Variables
    private String trackName;
    private String artistName;
    private String albumInfo;
    private String genre;
    private byte[] musicFileExtract;

    //Cpmstructors

    //Default Constructor
    public MusicFile() {
    }

    //Constructor_1
    public MusicFile(String trackName) {
        this.trackName = trackName;
    }

    //Constructor_2
    public MusicFile(String trackName, String artistName) {
        this.trackName = trackName;
        this.artistName = artistName;
    }

    //Constructor_3
    public MusicFile(String trackName, String artistName, String albumInfo) {
        this.trackName = trackName;
        this.artistName = artistName;
        this.albumInfo = albumInfo;
    }

    //Constructor_4
    public MusicFile(String trackName, String artistName, String albumInfo, String genre) {
        this.trackName = trackName;
        this.artistName = artistName;
        this.albumInfo = albumInfo;
        this.genre = genre;
    }

    //Constructor_5
    public MusicFile(String trackName, String artistName, String albumInfo, String genre, byte[] musicFileExtract) {
        this.trackName = trackName;
        this.artistName = artistName;
        this.albumInfo = albumInfo;
        this.genre = genre;
        this.musicFileExtract = musicFileExtract;
    }

    //Constructor_6
    public MusicFile(byte[] musicFileExtract) {
        this.musicFileExtract = musicFileExtract;
    }

    //Setters and Getters
    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumInfo() {
        return albumInfo;
    }

    public void setAlbumInfo(String albumInfo) {
        this.albumInfo = albumInfo;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public byte[] getMusicFileExtract() {
        return musicFileExtract;
    }

    public void setMusicFileExtract(byte[] musicFileExtract) {
        this.musicFileExtract = musicFileExtract;
    }
    
}//end of MusicFile