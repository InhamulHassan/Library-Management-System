package com.municipal.cmb.models;

/**
 * Created by RazR on 7/1/2018.
 */

public class GenreDataModel {
    private int GenreID;
    private String Genre;

    public GenreDataModel(int genreID, String genre) {
        GenreID = genreID;
        Genre = genre;
    }

    public int getGenreID() {
        return GenreID;
    }

    public void setGenreID(int genreID) {
        GenreID = genreID;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }
}
