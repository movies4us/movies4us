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
public class MovieDirectors 
{
    ArrayList directors;
    Connection conn;
    
    public MovieDirectors()
    {
        conn = (Connection) ConnectionManager.getConnection();
    }
    
    public ArrayList getMovieDirectors(int movieID)
    {
        StringBuilder queryString = new StringBuilder("SELECT PERSON_NAME FROM MOVIES_PERSONS WHERE MOVIE_ID ="+movieID+" AND PERSON_ROLE ='DI'");
        PreparedStatement st = null;
        ResultSet rs = null;  
        directors=new ArrayList();
        
        try 
        {
            st=conn.prepareStatement(queryString.toString());
            rs = st.executeQuery();
                        
            while(rs.next())
            {
                directors.add(rs.getString(1));
            }            
        }catch (SQLException ex) 
        {
            System.out.println("Cannot get director set");            
            Logger.getLogger(MovieRatingsDistinct.class.getName()).log(Level.SEVERE, null, ex);            
        }                
        return directors;
    }
}
