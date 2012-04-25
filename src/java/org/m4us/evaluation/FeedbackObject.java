/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.evaluation;

/**
 *
 * @author Aveek
 */
public class FeedbackObject 
{
    int movieID;
    double groupRating;
    int groupRank;
    int recommendedRank;

    public FeedbackObject(int movieID, double groupRating, int groupRank, int recommendedRank) {
        this.movieID = movieID;
        this.groupRating = groupRating;
        this.groupRank = groupRank;
        this.recommendedRank = recommendedRank;
    }

    public int getGroupRank() {
        return groupRank;
    }

    public void setGroupRank(int groupRank) {
        this.groupRank = groupRank;
    }

    public double getGroupRating() {
        return groupRating;
    }

    public void setGroupRating(double groupRating) {
        this.groupRating = groupRating;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public int getRecommendedRank() {
        return recommendedRank;
    }

    public void setRecommendedRank(int recommendedRank) {
        this.recommendedRank = recommendedRank;
    }        
}
