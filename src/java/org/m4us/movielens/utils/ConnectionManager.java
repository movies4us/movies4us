/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.movielens.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arka
 */
public class ConnectionManager {
    
    private static final String dbUrl = "jdbc:mysql://localhost:3306/movies4us";
    private static final String dbDriver = "com.mysql.jdbc.Driver";
    private static final String dbUser = "arka";
    private static final String dbPwd = "admin";
    private static final ConnectionInfo connInfo;
    
    static{
        connInfo = new ConnectionInfo();
        connInfo.setDbDriver(dbDriver);
        connInfo.setDbUrl(dbUrl);
        connInfo.setDbUser(dbUser);
        connInfo.setDbPwd(dbPwd);
    }
    
    public static Connection getConnection(String url, String driver, String userName, String password){
        
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, userName,password);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
    public static Connection getConnection(ConnectionInfo connInfo){
        
        Connection conn = null;
        try {
            Class.forName(connInfo.getDbDriver());
            conn = DriverManager.getConnection(connInfo.getDbUrl(), connInfo.getDbUser(),connInfo.getDbPwd());
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
    public static Connection getConnection(){
        
        Connection conn = null;
        try {
            Class.forName(connInfo.getDbDriver());
            conn = DriverManager.getConnection(connInfo.getDbUrl(), connInfo.getDbUser(),connInfo.getDbPwd());
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
}
