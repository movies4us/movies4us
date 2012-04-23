/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.group.utils.qo;

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
        profile=new double[GENRE_COUNT];
    }
    
    public double[] getUserProfile(int id)
    {
        initializeProfile(profile);
        
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
        return profile;
    }
    
    public void initializeProfile(double prof[])
    {
        for(int i=0;i<GENRE_COUNT;i++)
            prof[i]=0;
    }
    
    public void update(int user_id, double newProfile[])
    {
        StringBuilder queryString = new StringBuilder("SELECT COUNT(*) FROM USER_PROFILE WHERE USER_ID = "+user_id);
        PreparedStatement st = null;
        ResultSet rs = null;
        double currentProfile[];
        int count=0;
        try 
        {
            st=conn.prepareStatement(queryString.toString());
            rs = st.executeQuery();
            while(rs.next())
            {
                count=rs.getInt(1);
            }
            if(count==1)            
            {
                currentProfile=getUserProfile(user_id);
                for(int i=0;i<GENRE_COUNT;i++)
                    newProfile[i]+=currentProfile[i];
                queryString = new StringBuilder("DELETE FROM USER_PROFILE WHERE USER_ID = "+user_id);
                st = conn.prepareStatement(queryString.toString());
                st.executeUpdate();                
            }
            
            String values="";
            for(int i=0;i<GENRE_COUNT;i++)
                values+=","+newProfile[i];
            
            queryString = new StringBuilder("INSERT INTO USER_PROFILE VALUES ("+user_id+values+")");
            st = conn.prepareStatement(queryString.toString());
            st.executeUpdate();
        }catch (SQLException ex) 
        {
            System.out.println("Cannot generate user profile");            
            Logger.getLogger(MovieRatingsDistinct.class.getName()).log(Level.SEVERE, null, ex);            
        }    
    }
}
