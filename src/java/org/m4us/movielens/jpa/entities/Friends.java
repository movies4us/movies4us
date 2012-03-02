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
@Table(name = "FRIENDS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Friends.findAll", query = "SELECT f FROM Friends f"),
    @NamedQuery(name = "Friends.findByUserId", query = "SELECT f FROM Friends f WHERE f.friendsPK.userId = :userId"),
    @NamedQuery(name = "Friends.findByFriendUserId", query = "SELECT f FROM Friends f WHERE f.friendsPK.friendUserId = :friendUserId")})
public class Friends implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FriendsPK friendsPK;

    public Friends() {
    }

    public Friends(FriendsPK friendsPK) {
        this.friendsPK = friendsPK;
    }

    public Friends(int userId, int friendUserId) {
        this.friendsPK = new FriendsPK(userId, friendUserId);
    }

    public FriendsPK getFriendsPK() {
        return friendsPK;
    }

    public void setFriendsPK(FriendsPK friendsPK) {
        this.friendsPK = friendsPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (friendsPK != null ? friendsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Friends)) {
            return false;
        }
        Friends other = (Friends) object;
        if ((this.friendsPK == null && other.friendsPK != null) || (this.friendsPK != null && !this.friendsPK.equals(other.friendsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.m4us.movielens.jpa.entities.Friends[ friendsPK=" + friendsPK + " ]";
    }
    
}
