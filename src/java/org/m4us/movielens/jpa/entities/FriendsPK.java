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
public class FriendsPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_ID")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FRIEND_USER_ID")
    private int friendUserId;

    public FriendsPK() {
    }

    public FriendsPK(int userId, int friendUserId) {
        this.userId = userId;
        this.friendUserId = friendUserId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFriendUserId() {
        return friendUserId;
    }

    public void setFriendUserId(int friendUserId) {
        this.friendUserId = friendUserId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userId;
        hash += (int) friendUserId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FriendsPK)) {
            return false;
        }
        FriendsPK other = (FriendsPK) object;
        if (this.userId != other.userId) {
            return false;
        }
        if (this.friendUserId != other.friendUserId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.m4us.movielens.jpa.entities.FriendsPK[ userId=" + userId + ", friendUserId=" + friendUserId + " ]";
    }
    
}
