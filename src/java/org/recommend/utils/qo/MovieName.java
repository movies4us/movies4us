/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.recommend.utils.qo;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.m4us.imdb.utils.qo.MovieRatingsDistinct;
import org.m4us.movielens.utils.ConnectionManager;

/**
 *
 * @author Aveek
 */
public class MovieName 
{    
    Connection conn;
    
    public MovieName()
    {        
        conn = (Connection) ConnectionManager.getConnection();
    }
    
    public String getMovieName(int id)
    {
        String name="";
        StringBuilder queryString = new StringBuilder("SELECT MOVIE_NAME FROM MOVIES WHERE MOVIE_ID = "+id);
        PreparedStatement st = null;
        ResultSet rs = null;  
        
        try 
        {
            st=conn.prepareStatement(queryString.toString());
            rs = st.executeQuery();
                        
            while(rs.next())
            {
                name=rs.getString(1);
            }            
        }catch (SQLException ex) 
        {
            System.out.println("Cannot get movies in group");            
            Logger.getLogger(MovieRatingsDistinct.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return name;
    }
}
