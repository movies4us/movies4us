/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.imdb.utils.qo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arka
 */
public class MovieRatingsDistinct {

    public Map<String,Integer> retrieveList(Connection conn) {
        Map<String,Integer> distinctMoviesMap = new HashMap<String, Integer>();
        StringBuilder queryString = new StringBuilder("SELECT distinct b.MOVIE_ID,MOVIE_NAME,RELEASE_YEAR "
                                                       + "FROM movies4us.RATINGS a, movies4us.MOVIES b "
                                                        + "where a.MOVIE_ID = b.MOVIE_ID");
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st=conn.prepareStatement(queryString.toString());
            rs = st.executeQuery();
            while(rs.next()){
                distinctMoviesMap.put(rs.getString(2)+"|"+rs.getString(3), rs.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MovieRatingsDistinct.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(MovieRatingsDistinct.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return distinctMoviesMap;
    }
}
