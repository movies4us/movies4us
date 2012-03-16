/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.movielens.jpa.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author arka
 */
@Entity
@Table(name = "MOVIES_GENRE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MoviesGenre.findAll", query = "SELECT m FROM MoviesGenre m"),
    @NamedQuery(name = "MoviesGenre.findByMovieId", query = "SELECT m FROM MoviesGenre m WHERE m.moviesGenrePK.movieId = :movieId"),
    @NamedQuery(name = "MoviesGenre.findByGenre", query = "SELECT m FROM MoviesGenre m WHERE m.moviesGenrePK.genre = :genre")})
public class MoviesGenre implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MoviesGenrePK moviesGenrePK;
    @JoinColumn(name = "MOVIE_ID", referencedColumnName = "MOVIE_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Movies movies;

    public MoviesGenre() {
    }

    public MoviesGenre(MoviesGenrePK moviesGenrePK) {
        this.moviesGenrePK = moviesGenrePK;
    }

    public MoviesGenre(int movieId, String genre) {
        this.moviesGenrePK = new MoviesGenrePK(movieId, genre);
    }

    public MoviesGenrePK getMoviesGenrePK() {
        return moviesGenrePK;
    }

    public void setMoviesGenrePK(MoviesGenrePK moviesGenrePK) {
        this.moviesGenrePK = moviesGenrePK;
    }

    public Movies getMovies() {
        return movies;
    }

    public void setMovies(Movies movies) {
        this.movies = movies;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (moviesGenrePK != null ? moviesGenrePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MoviesGenre)) {
            return false;
        }
        MoviesGenre other = (MoviesGenre) object;
        if ((this.moviesGenrePK == null && other.moviesGenrePK != null) || (this.moviesGenrePK != null && !this.moviesGenrePK.equals(other.moviesGenrePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.m4us.movielens.jpa.entities.MoviesGenre[ moviesGenrePK=" + moviesGenrePK + " ]";
    }
    
}
