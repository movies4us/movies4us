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
public class UserProfile 
{
    Connection conn;
    double profile[];
    static int GENRE_COUNT=19;
    
    public UserProfile()
    {
        conn = (Connection) ConnectionManager.getConnection();
        profile=new double[24];
    }
    
    public double[] getUserProfile(int id)
    {
        initializeProfile();
        
        StringBuilder queryString = new StringBuilder("SELECT GENRE FROM MOVIES_GENRE WHERE MOVIE_ID IN (SELECT MOVIE_ID FROM RATINGS WHERE USER_ID = "+id+")");
        PreparedStatement st = null;
        ResultSet rs = null;
        GenreIndex gi=new GenreIndex();
        int index;
        
        try 
        {
            st=conn.prepareStatement(queryString.toString());
            rs = st.executeQuery();
                        
            while(rs.next())
            {
                String genre=rs.getString(1);
                index=gi.getIndex(genre);
                if(index>=0)
                    profile[index]++;
            }            
        }catch (SQLException ex) 
        {
            System.out.println("Cannot generate user profile");            
            Logger.getLogger(MovieRatingsDistinct.class.getName()).log(Level.SEVERE, null, ex);            
        }                
        normalize();
        return profile;
    }
    
    public void normalize()
    {
        int sum=0;
        for(int i=0;i<GENRE_COUNT;i++)
        {
            sum+=profile[i];
        }
        
        for(int i=0;i<GENRE_COUNT;i++)
        {
            profile[i]/=sum;
        }
    }
    
    public void initializeProfile()
    {
        for(int i=0;i<24;i++)
            profile[i]=0;
    }
}
