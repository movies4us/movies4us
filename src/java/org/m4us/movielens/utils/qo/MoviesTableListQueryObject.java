/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.movielens.utils.qo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.m4us.movielens.utils.dto.DataTransferObject;
import org.m4us.movielens.utils.dto.MoviesTableObject;

/**
 *
 * @author arka
 */
public class MoviesTableListQueryObject implements ListQueryObject{

    @Override
    public List<DataTransferObject> retrieveList(DataTransferObject object, Connection conn) {
        /*select * query fired right now. change to key based query later*/
        StringBuilder queryString = new StringBuilder("SELECT * FROM MOVIES1");
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<DataTransferObject> moviesList = new ArrayList<DataTransferObject>();
        try {
            ps=conn.prepareStatement(queryString.toString());
            rs = ps.executeQuery();
            while(rs.next()){
                MoviesTableObject movieObject = new MoviesTableObject();
                movieObject.setMovieId(rs.getInt(1));
                movieObject.setMovieName(rs.getString(2));
                movieObject.setReleaseYear(rs.getString(3));
                movieObject.setLanguage(rs.getString(4));
                movieObject.setRating(rs.getInt(5));
                movieObject.setRank(rs.getInt(6));
                movieObject.setRuntime(rs.getInt(7));
                movieObject.setMpaa(rs.getString(8));
                movieObject.setImdbId(rs.getString(9));
                moviesList.add(movieObject);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MoviesTableListQueryObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return moviesList;
    }
    public List<DataTransferObject> retrieveMovieDetailsList(List movieIdList, Connection conn) {
        StringBuilder queryString = new StringBuilder("SELECT * FROM MOVIES WHERE MOVIE_ID IN (");
        for(int i=0;i<movieIdList.size();i++){
            queryString.append(" ").append((Integer)movieIdList.get(i)).append(",");
        }
        queryString.deleteCharAt(queryString.length()-1).append(")");
        List<DataTransferObject> movieDetailsList = new ArrayList<DataTransferObject>();
        try {
            PreparedStatement ps = conn.prepareStatement(queryString.toString());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                MoviesTableObject movieObj = new MoviesTableObject();
                movieObj.setMovieId(rs.getInt(1));
                movieObj.setMovieName(rs.getString(2));
                movieObj.setReleaseYear(rs.getString(3));
                movieObj.setLanguage(rs.getString(4));
                movieObj.setRating(rs.getInt(5));
                movieObj.setRank(rs.getInt(6));
                movieObj.setRuntime(rs.getInt(7));
                movieObj.setMpaa(rs.getString(8));
                movieObj.setImdbId(rs.getString(9));
                movieDetailsList.add(movieObj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MoviesTableListQueryObject.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(MoviesTableListQueryObject.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return movieDetailsList;
    }
    
    public List<DataTransferObject> similarMoviesList(String searchString, Connection conn) {
        StringBuilder queryString = new StringBuilder("SELECT * FROM MOVIES WHERE MOVIE_NAME LIKE");
        queryString.append(" '%").append(searchString).append("%' ");
        List<DataTransferObject> movieSearchList = new ArrayList<DataTransferObject>();
        try {
            PreparedStatement ps = conn.prepareStatement(queryString.toString());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                MoviesTableObject movieObj = new MoviesTableObject();
                movieObj.setMovieId(rs.getInt(1));
                movieObj.setMovieName(rs.getString(2));
                movieObj.setReleaseYear(rs.getString(3));
                movieObj.setLanguage(rs.getString(4));
                movieObj.setRating(rs.getInt(5));
                movieObj.setRank(rs.getInt(6));
                movieObj.setRuntime(rs.getInt(7));
                movieObj.setMpaa(rs.getString(8));
                movieObj.setImdbId(rs.getString(9));
                movieSearchList.add(movieObj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MoviesTableListQueryObject.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(MoviesTableListQueryObject.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return movieSearchList;
    }
}
