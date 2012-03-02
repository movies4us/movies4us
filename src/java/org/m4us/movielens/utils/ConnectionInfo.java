/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.movielens.utils;

/**
 *
 * @author arka
 */
public class ConnectionInfo {
    private String dbUrl;
    private String dbDriver;
    private String dbUser;
    private String dbPwd;

    public String getDbDriver() {
        return dbDriver;
    }

    public void setDbDriver(String dbDriver) {
        this.dbDriver = dbDriver;
    }

    public String getDbPwd() {
        return dbPwd;
    }

    public void setDbPwd(String dbPwd) {
        this.dbPwd = dbPwd;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    @Override
    public String toString() {
        return "ConnectionInfo{" + "dbUrl=" + dbUrl + ", dbDriver=" + dbDriver + ", dbUser=" + dbUser + ", dbPwd=" + dbPwd + '}';
    }
    
    
}
