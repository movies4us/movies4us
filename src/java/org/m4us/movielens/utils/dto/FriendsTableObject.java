/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.movielens.utils.dto;

/**
 *
 * @author arka
 */
public class FriendsTableObject implements TableObject{
    private int userId;
    private int friendUserId;

    public int getFriendUserId() {
        return friendUserId;
    }

    public void setFriendUserId(int friendUserId) {
        this.friendUserId = friendUserId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "FriendsTableObject{" + "userId=" + userId + ", friendUserId=" + friendUserId + '}';
    }
    
}
