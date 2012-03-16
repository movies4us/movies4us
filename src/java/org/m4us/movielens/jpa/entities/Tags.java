/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.movielens.jpa.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author arka
 */
@Entity
@Table(name = "TAGS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tags.findAll", query = "SELECT t FROM Tags t"),
    @NamedQuery(name = "Tags.findByUserId", query = "SELECT t FROM Tags t WHERE t.tagsPK.userId = :userId"),
    @NamedQuery(name = "Tags.findByMovieId", query = "SELECT t FROM Tags t WHERE t.tagsPK.movieId = :movieId"),
    @NamedQuery(name = "Tags.findByTag", query = "SELECT t FROM Tags t WHERE t.tag = :tag"),
    @NamedQuery(name = "Tags.findByTagDate", query = "SELECT t FROM Tags t WHERE t.tagDate = :tagDate")})
public class Tags implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TagsPK tagsPK;
    @Size(max = 45)
    @Column(name = "TAG")
    private String tag;
    @Column(name = "TAG_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tagDate;
    @JoinColumn(name = "MOVIE_ID", referencedColumnName = "MOVIE_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Movies movies;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private UserInfo userInfo;

    public Tags() {
    }

    public Tags(TagsPK tagsPK) {
        this.tagsPK = tagsPK;
    }

    public Tags(int userId, int movieId) {
        this.tagsPK = new TagsPK(userId, movieId);
    }

    public TagsPK getTagsPK() {
        return tagsPK;
    }

    public void setTagsPK(TagsPK tagsPK) {
        this.tagsPK = tagsPK;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Date getTagDate() {
        return tagDate;
    }

    public void setTagDate(Date tagDate) {
        this.tagDate = tagDate;
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
        hash += (tagsPK != null ? tagsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tags)) {
            return false;
        }
        Tags other = (Tags) object;
        if ((this.tagsPK == null && other.tagsPK != null) || (this.tagsPK != null && !this.tagsPK.equals(other.tagsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.m4us.movielens.jpa.entities.Tags[ tagsPK=" + tagsPK + " ]";
    }
    
}
