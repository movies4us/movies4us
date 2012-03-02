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
import javax.validation.constraints.Size;

/**
 *
 * @author arka
 */
@Embeddable
public class MoviesGenrePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "MOVIE_ID")
    private int movieId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "GENRE")
    private String genre;

    public MoviesGenrePK() {
    }

    public MoviesGenrePK(int movieId, String genre) {
        this.movieId = movieId;
        this.genre = genre;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) movieId;
        hash += (genre != null ? genre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MoviesGenrePK)) {
            return false;
        }
        MoviesGenrePK other = (MoviesGenrePK) object;
        if (this.movieId != other.movieId) {
            return false;
        }
        if ((this.genre == null && other.genre != null) || (this.genre != null && !this.genre.equals(other.genre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.m4us.movielens.jpa.entities.MoviesGenrePK[ movieId=" + movieId + ", genre=" + genre + " ]";
    }
    
}
