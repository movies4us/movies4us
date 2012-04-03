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
public class MovieActors 
{
    ArrayList actors;
    Connection conn;
    
    public MovieActors()
    {
        conn = (Connection) ConnectionManager.getConnection();
    }
    
    public ArrayList getMovieActors(int movieID)
    {
        StringBuilder queryString = new StringBuilder("SELECT PERSON_NAME FROM MOVIES_PERSONS WHERE MOVIE_ID ="+movieID+" AND PERSON_ROLE ='AR'");
        PreparedStatement st = null;
        ResultSet rs = null;  
        actors=new ArrayList();
        
        try 
        {
            st=conn.prepareStatement(queryString.toString());
            rs = st.executeQuery();
                        
            while(rs.next())
            {
                actors.add(rs.getString(1));
            }            
        }catch (SQLException ex) 
        {
            System.out.println("Cannot get actor set");            
            Logger.getLogger(MovieRatingsDistinct.class.getName()).log(Level.SEVERE, null, ex);            
        }        
        return actors;
    }
}
