/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.movielens.utils.dto;

/**
 *
 * @author arka
 */
public class GroupsTableObject implements TableObject{
    private int groupId;
    private int userId;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "GroupsTableObject{" + "groupId=" + groupId + ", userId=" + userId + '}';
    }

    
}
