/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.movielens.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.m4us.movielens.utils.dto.DataTransferObject;
import org.m4us.movielens.utils.dto.UserInfoTableObject;
import org.m4us.movielens.utils.qo.UserInfoBulkInsert;

/**
 *
 * @author arka
 */
public class MovieLensDataSetup {
    
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
    
    public static void main(String[] args) {
        System.out.println("current dir: " + (new File(".")).getAbsolutePath());
//        createUsers();
//        processMoviesFile();
//        processTagsFile();
//        processRatingsFile();
    }

    private static void createUsers() {
        Connection m4usConn = ConnectionManager.getConnection(dbUrl, dbDriver, dbUser, dbPwd);
        List<DataTransferObject> objectList = new ArrayList<DataTransferObject>();
        for(int i=1;i<=100000;i++){
            UserInfoTableObject object = new UserInfoTableObject();
            object.setUserId(i);
            object.setUsername((new StringBuilder("USER")).append(i).toString());
            object.setPassword((new StringBuilder("USER")).append(i).toString());
            object.setJoinDate(new Timestamp(System.currentTimeMillis()));
            objectList.add(object);
        }
        
        (new UserInfoBulkInsert()).bulkInsert(objectList, m4usConn);
    }

    private static void processMoviesFile() {
        try {
            ParserFactory.getInstance(ParserFactory.MOVIELENS_MOVIES_FILE)
                    .parse((new BufferedReader(new FileReader
                    (ParserFactory.MOVIELENS_MOVIES_FILE))), connInfo);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MovieLensDataSetup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void processTagsFile() {
        try {
            ParserFactory.getInstance(ParserFactory.MOVIELENS_TAGS_FILE)
                    .parse((new BufferedReader(new FileReader
                    (ParserFactory.MOVIELENS_TAGS_FILE))), connInfo);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MovieLensDataSetup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void processRatingsFile() {
        try {
            ParserFactory.getInstance(ParserFactory.MOVIELENS_RATINGS_FILE)
                    .parse((new BufferedReader(new FileReader
                    (ParserFactory.MOVIELENS_RATINGS_FILE))), connInfo);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MovieLensDataSetup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
