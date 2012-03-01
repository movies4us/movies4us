/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.movielens.utils.dto;

import java.sql.Timestamp;

/**
 *
 * @author arka
 */
public class RatingsTableObject implements TableObject{
 
    private int userId;
    private int movieId;
    private float rating;
    private Timestamp ratingDate;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public Timestamp getRatingDate() {
        return ratingDate;
    }

    public void setRatingDate(Timestamp ratingDate) {
        this.ratingDate = ratingDate;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float ratings) {
        this.rating = ratings;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "RatingsTableObject{" + "userId=" + userId + ", movieId=" + movieId + ", ratings=" + rating + ", ratingDate=" + ratingDate + '}';
    }
    
    
}
