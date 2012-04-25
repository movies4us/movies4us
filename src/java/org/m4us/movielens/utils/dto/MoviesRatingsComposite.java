/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.movielens.utils.dto;

/**
 *
 * @author arka
 */
public class MoviesRatingsComposite implements DataTransferObject{
    private MoviesTableObject movieObj;
    private RatingsTableObject ratingsObj;

    public MoviesTableObject getMovieObj() {
        return movieObj;
    }

    public void setMovieObj(MoviesTableObject movieObj) {
        this.movieObj = movieObj;
    }

    public RatingsTableObject getRatingsObj() {
        return ratingsObj;
    }

    public void setRatingsObj(RatingsTableObject ratingsObj) {
        this.ratingsObj = ratingsObj;
    }

    @Override
    public String toString() {
        return "MoviesRatingsComposite{" + "movieObj=" + movieObj + ", ratingsObj=" + ratingsObj + '}';
    }
    
    
}
