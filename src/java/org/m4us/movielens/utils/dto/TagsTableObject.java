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
public class TagsTableObject implements TableObject{

    private int userId;
    private int movieId;
    private String tag;
    private Timestamp tagDate;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Timestamp getTagDate() {
        return tagDate;
    }

    public void setTagDate(Timestamp tagDate) {
        this.tagDate = tagDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "TagsTableObject{" + "userId=" + userId + ", movieId=" + movieId + ", tag=" + tag + ", tagDate=" + tagDate + '}';
    }
    
    
}
