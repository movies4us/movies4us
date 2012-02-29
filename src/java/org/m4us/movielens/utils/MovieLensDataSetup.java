/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.movielens.utils;

import java.io.File;
import java.sql.Connection;

/**
 *
 * @author arka
 */
public class MovieLensDataSetup {
    
    private static final String dbUrl = "jdbc:mysql://localhost:3306/movies4us";
    private static final String dbDriver = "com.mysql.jdbc.Driver";
    
    public static void main(String[] args) {
        System.out.println("current dir: " + (new File(".")).getAbsolutePath());
        createUsers();
        processMoviesFile();
        processTagsFile();
        processRatingsFile();
    }

    private static void createUsers() {
        Connection m4usConn = ConnectionManager.getConnection(dbUrl, dbDriver);
        
    }

    private static void processMoviesFile() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private static void processTagsFile() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private static void processRatingsFile() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    
}
