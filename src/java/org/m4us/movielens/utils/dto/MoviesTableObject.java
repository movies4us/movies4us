/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.movielens.utils.dto;

/**
 *
 * @author arka
 */
public class MoviesTableObject implements TableObject{
    private Integer movieId;
    private String movieName;
    private String releaseYear;

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public String toString() {
        return "Movies{" + "movieId=" + movieId + ", movieName=" + movieName + ", releaseYear=" + releaseYear + '}';
    }
    
    
}
