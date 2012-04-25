/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.group.utils.qo;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.m4us.imdb.utils.qo.MovieRatingsDistinct;
import org.m4us.movielens.utils.ConnectionManager;
import org.m4us.movielens.utils.dto.RatingsTableObject;
import org.recommend.utils.GenreIndex;

/**
 *
 * @author Aveek
 */
public class UserProfileUpdate 
{
    public static int GENRE_COUNT=19;
    int profile[];
    int userId;
    Connection conn;
    
    public void updateProfile(List updateList)
    {
        conn = (Connection) ConnectionManager.getConnection();
        StringBuilder queryString=null;
        PreparedStatement st=null;
        ResultSet rs = null;
        
        profile=new int[GENRE_COUNT];
        for(int i=0;i<GENRE_COUNT;i++)
            profile[i]=0;
        
        try 
        {
            for(int i=0;i<updateList.size();i++)
            {
                RatingsTableObject obj=(RatingsTableObject)updateList.get(i);
                userId=obj.getUserId();
            
                queryString = new StringBuilder("SELECT GENRE FROM MOVIES_GENRE WHERE MOVIE_ID ="+obj.getMovieId());
                
                GenreIndex gi=new GenreIndex();
                int index;
            
                st=conn.prepareStatement(queryString.toString());
                rs = st.executeQuery();

                while(rs.next())
                {
                    String genre=rs.getString(1);
                    index=gi.getIndex(genre);
                    if(index>=0)
                        profile[index]++;
                }
            }
            queryString = new StringBuilder("SELECT * FROM USER_PROFILE WHERE USER_ID="+userId);
            st=conn.prepareStatement(queryString.toString());
            rs = st.executeQuery();

            while(rs.next())
            {
                for(int j=0;j<GENRE_COUNT;j++)
                    profile[j]+=rs.getInt(j+2);                
            }
            
            queryString = new StringBuilder("DELETE FROM USER_PROFILE WHERE USER_ID="+userId);
            st = conn.prepareStatement(queryString.toString());
            st.executeUpdate();
            
            String values=userId+",";
            for(int i=0;i<GENRE_COUNT;i++)
                values+=profile[i]+",";
            values=values.substring(0,values.length()-1);
            
            queryString = new StringBuilder("INSERT INTO USER_PROFILE VALUES("+values+")");
            st = conn.prepareStatement(queryString.toString());
            st.executeUpdate();
            
            conn.close();
        }catch (SQLException ex){
            System.out.println("Cannot generate user profile");            
            System.out.println(queryString.toString());
            Logger.getLogger(MovieRatingsDistinct.class.getName()).log(Level.SEVERE, null, ex);            
        }              
        
    }
}
