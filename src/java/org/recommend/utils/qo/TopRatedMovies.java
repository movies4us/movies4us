/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.recommend.utils.qo;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.m4us.imdb.utils.qo.MovieRatingsDistinct;
import org.m4us.movielens.utils.ConnectionManager;

/**
 *
 * @author Aveek
 */
public class TopRatedMovies 
{
    Connection conn;
    ArrayList topRatedMovies;
    
    public TopRatedMovies()
    {
        topRatedMovies=new ArrayList();
        conn = (Connection) ConnectionManager.getConnection();
    }
    
    public ArrayList getTopRatedMovies(String seen, int number)
    {
        StringBuilder queryString = new StringBuilder("SELECT MOVIE_ID FROM MOVIES WHERE MOVIE_ID NOT IN ("+seen+") ORDER BY RATING DESC LIMIT "+number);
        PreparedStatement st = null;
        ResultSet rs = null;  
        
        try 
        {
            st=conn.prepareStatement(queryString.toString());
            rs = st.executeQuery();
                        
            while(rs.next())
            {
                topRatedMovies.add(rs.getInt(1));
            }            
        }catch (SQLException ex) 
        {
            System.out.println("Cannot get top rated");            
            Logger.getLogger(MovieRatingsDistinct.class.getName()).log(Level.SEVERE, null, ex);            
        }        
        return topRatedMovies;
    }
}
