/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.movielens.utils.dto;

import java.sql.Timestamp;

/**
 *
 * @author arka
 */
public class UserInfoTableObject implements TableObject{

    private Integer userId;
    private String username;
    private String password;
    private Timestamp joinDate;

    public Timestamp getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Timestamp joinDate) {
        this.joinDate = joinDate;
    }
    
    
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserInfo{" + "userId=" + userId + ", username=" + username + ", password=" + password + ", joinDate=" + joinDate + '}';
    }
}
