/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.movielens.jpa.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author arka
 */
@Embeddable
public class RatingsPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_ID")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MOVIE_ID")
    private int movieId;

    public RatingsPK() {
    }

    public RatingsPK(int userId, int movieId) {
        this.userId = userId;
        this.movieId = movieId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userId;
        hash += (int) movieId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RatingsPK)) {
            return false;
        }
        RatingsPK other = (RatingsPK) object;
        if (this.userId != other.userId) {
            return false;
        }
        if (this.movieId != other.movieId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.m4us.movielens.jpa.entities.RatingsPK[ userId=" + userId + ", movieId=" + movieId + " ]";
    }
    
}
