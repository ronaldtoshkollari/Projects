package com.example.srv_spotify;

import java.io.Serializable;

public class ArtistName implements Serializable {

    //Class Variables
    private String artistName;

    //Default Constructor
    public ArtistName() {
    }

    //Constructor_1
    public ArtistName(String artistName) {
        this.artistName = artistName;
    }

    //Setters and Getters
    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

}//end of ArtistName