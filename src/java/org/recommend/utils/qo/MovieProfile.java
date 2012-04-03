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
import org.recommend.utils.GenreIndex;

/**
 *
 * @author Aveek
 */
public class MovieProfile 
{
    Connection conn;
    int movieID;
    double movieProfile[];
    static int GENRE_SIZE=19;
    
    public MovieProfile()
    {
        conn = (Connection) ConnectionManager.getConnection();        
        movieProfile=new double[GENRE_SIZE];        
    }
    
    public double[] getMovieProfile(int id)
    {
        movieID=id;
        StringBuilder queryString = new StringBuilder("SELECT GENRE FROM MOVIES_GENRE WHERE MOVIE_ID ="+movieID);
        PreparedStatement st = null;
        ResultSet rs = null;  
        GenreIndex gi=new GenreIndex();
        int index;
        
        for(int i=0;i<19;i++)
            movieProfile[i]=0;
        
        try 
        {
            st=conn.prepareStatement(queryString.toString());
            rs = st.executeQuery();
                        
            while(rs.next())
            {
                String genre=rs.getString(1);
                index=gi.getIndex(genre);                
                movieProfile[index]++;
            }            
        }catch (SQLException ex) 
        {
            System.out.println("Cannot get movie profile");            
            Logger.getLogger(MovieRatingsDistinct.class.getName()).log(Level.SEVERE, null, ex);            
        }         
        return movieProfile;
    }
}
