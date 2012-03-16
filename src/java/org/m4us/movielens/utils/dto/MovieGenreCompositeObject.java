/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.movielens.utils.dto;

import java.util.List;

/**
 *
 * @author arka
 */
public class MovieGenreCompositeObject implements DataTransferObject{
    private MoviesTableObject movieObject;
    private List<MoviesGenreTableObject> genreObjectList;

    public List<MoviesGenreTableObject> getGenreObjectList() {
        return genreObjectList;
    }

    public void setGenreObjectList(List<MoviesGenreTableObject> genreObjectList) {
        this.genreObjectList = genreObjectList;
    }

    
    
    public MoviesTableObject getMovieObject() {
        return movieObject;
    }

    public void setMovieObject(MoviesTableObject movieObject) {
        this.movieObject = movieObject;
    }

    @Override
    public String toString() {
        return "MovieGenreCompositeObject{" + "movieObject=" + movieObject + ", genreObjectList=" + genreObjectList + '}';
    }

    
    
}
