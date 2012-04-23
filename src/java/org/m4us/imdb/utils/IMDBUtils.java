/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.imdb.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arka
 */
public class IMDBUtils {
    public static String stripAliasInfo(String movieNameWithAlias){
        String movieNameWithoutAlias="";
        if(movieNameWithAlias.contains("("))
            movieNameWithoutAlias = movieNameWithAlias.substring(0,movieNameWithAlias.indexOf("(")).trim();
        else
            movieNameWithoutAlias = movieNameWithAlias;
        return movieNameWithoutAlias;
    }
    
    public static int getNextUserId(Connection conn){
        String query = "SELECT count(*) FROM USER_INFO";
        int currentId=0;
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            currentId = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(IMDBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(IMDBUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return currentId+1;
    }
}
