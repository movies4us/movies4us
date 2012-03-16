/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.movielens.jpa.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author arka
 */
@Entity
@Table(name = "RATINGS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ratings.findAll", query = "SELECT r FROM Ratings r"),
    @NamedQuery(name = "Ratings.findByUserId", query = "SELECT r FROM Ratings r WHERE r.ratingsPK.userId = :userId"),
    @NamedQuery(name = "Ratings.findByMovieId", query = "SELECT r FROM Ratings r WHERE r.ratingsPK.movieId = :movieId"),
    @NamedQuery(name = "Ratings.findByRatings", query = "SELECT r FROM Ratings r WHERE r.ratings = :ratings"),
    @NamedQuery(name = "Ratings.findByRatingDate", query = "SELECT r FROM Ratings r WHERE r.ratingDate = :ratingDate")})
public class Ratings implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RatingsPK ratingsPK;
    @Column(name = "RATINGS")
    private Integer ratings;
    @Column(name = "RATING_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ratingDate;
    @JoinColumn(name = "MOVIE_ID", referencedColumnName = "MOVIE_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Movies movies;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private UserInfo userInfo;

    public Ratings() {
    }

    public Ratings(RatingsPK ratingsPK) {
        this.ratingsPK = ratingsPK;
    }

    public Ratings(int userId, int movieId) {
        this.ratingsPK = new RatingsPK(userId, movieId);
    }

    public RatingsPK getRatingsPK() {
        return ratingsPK;
    }

    public void setRatingsPK(RatingsPK ratingsPK) {
        this.ratingsPK = ratingsPK;
    }

    public Integer getRatings() {
        return ratings;
    }

    public void setRatings(Integer ratings) {
        this.ratings = ratings;
    }

    public Date getRatingDate() {
        return ratingDate;
    }

    public void setRatingDate(Date ratingDate) {
        this.ratingDate = ratingDate;
    }

    public Movies getMovies() {
        return movies;
    }

    public void setMovies(Movies movies) {
        this.movies = movies;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ratingsPK != null ? ratingsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ratings)) {
            return false;
        }
        Ratings other = (Ratings) object;
        if ((this.ratingsPK == null && other.ratingsPK != null) || (this.ratingsPK != null && !this.ratingsPK.equals(other.ratingsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.m4us.movielens.jpa.entities.Ratings[ ratingsPK=" + ratingsPK + " ]";
    }
    
}
