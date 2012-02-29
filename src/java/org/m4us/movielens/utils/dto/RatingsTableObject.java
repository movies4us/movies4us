/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.movielens.utils.dto;

import java.util.Date;

/**
 *
 * @author arka
 */
public class RatingsTableObject implements TableObject{
 
    private int userId;
    private int movieId;
    private Integer ratings;
    private Date ratingDate;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public Date getRatingDate() {
        return ratingDate;
    }

    public void setRatingDate(Date ratingDate) {
        this.ratingDate = ratingDate;
    }

    public Integer getRatings() {
        return ratings;
    }

    public void setRatings(Integer ratings) {
        this.ratings = ratings;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "RatingsTableObject{" + "userId=" + userId + ", movieId=" + movieId + ", ratings=" + ratings + ", ratingDate=" + ratingDate + '}';
    }
    
    
}
