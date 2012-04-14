/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.movielens.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.m4us.movielens.utils.dto.DataTransferObject;
import org.m4us.movielens.utils.dto.MoviesTableObject;
import org.m4us.movielens.utils.qo.MoviesTableListQueryObject;

/**
 *
 * @author arka
 */
public class MoviesAndGroupsPorting {
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
        //moviesPort();
        groupsPort();
    }

    private static void moviesPort() {
        Connection m4usConn = ConnectionManager.getConnection(dbUrl, dbDriver, dbUser, dbPwd);
        List<DataTransferObject> moviesList = (new MoviesTableListQueryObject()).retrieveList(null, m4usConn);
        StringBuilder updateQuery = new StringBuilder("UPDATE MOVIES SET LANGUAGE = ?, RATING =?, RANK =?");
        updateQuery.append(", RUNTIME = ?, MPAA =?, IMDB_ID = ? where MOVIE_ID=?");
        int count=0; 
        try {
            m4usConn.setAutoCommit(false);
            for(DataTransferObject obj : moviesList){
                PreparedStatement ps = m4usConn.prepareStatement(updateQuery.toString());
                MoviesTableObject movieObj = (MoviesTableObject)obj;
                ps.setString(1, movieObj.getLanguage());
                ps.setInt(2, movieObj.getRating());
                ps.setInt(3, movieObj.getRank());
                ps.setInt(4, movieObj.getRuntime());
                ps.setString(5, movieObj.getMpaa());
                ps.setString(6, movieObj.getImdbId());
                ps.setInt(7, movieObj.getMovieId());
                ps.executeUpdate();
                
                count++;
                if(count>=500){
                    m4usConn.commit();
                    count=0;
                }
            }
            m4usConn.commit();
            m4usConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MoviesAndGroupsPorting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void groupsPort() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}