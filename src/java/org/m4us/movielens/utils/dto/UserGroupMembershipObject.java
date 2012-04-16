/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.movielens.utils.dto;

import java.util.List;

/**
 *
 * @author arka
 */
public class UserGroupMembershipObject implements DataTransferObject{
    private int userId;
    private List<GroupsTableObject> groupsList;

    public List<GroupsTableObject> getGroupsList() {
        return groupsList;
    }

    public void setGroupsList(List<GroupsTableObject> groupsList) {
        this.groupsList = groupsList;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserGroupMembershipObject{" + "userId=" + userId + ", groupsList=" + groupsList + '}';
    }
    
    
}
