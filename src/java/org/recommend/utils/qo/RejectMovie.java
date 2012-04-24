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
public class RejectMovie 
{
    int groupID, movieID;
    ArrayList recommendedList;    
    Connection conn;

    public RejectMovie(int groupID, int movieID) {
        this.groupID = groupID;
        this.movieID = movieID;
        recommendedList=new ArrayList();
        conn = (Connection) ConnectionManager.getConnection();
    }
    
    public void updateRejects()
    {
        int count=0;
        StringBuilder queryString = new StringBuilder("SELECT COUNT FROM REJECTED_MOVIES WHERE MOVIE_ID ="+movieID+" AND GROUP_ID= "+groupID);
        PreparedStatement st = null;
        ResultSet rs = null;  
        
        try 
        {
            st=conn.prepareStatement(queryString.toString());
            rs = st.executeQuery();
                        
            while(rs.next())
            {
                count=rs.getInt(1);
            }
            if(count==0)
                queryString = new StringBuilder("INSERT INTO REJECTED_MOVIES VALUES("+groupID+","+movieID+",1)");
            else
                queryString = new StringBuilder("UPDATE REJECTED_MOVIES SET COUNT="+(count+1)+" WHERE MOVIE_ID ="+movieID+" AND GROUP_ID= "+groupID);
            
            st = conn.prepareStatement(queryString.toString());
            st.executeUpdate();
            
            conn.close();
        }catch (SQLException ex) 
        {
            System.out.println("Cannot add rejected movies");   
            System.out.println(queryString);
            Logger.getLogger(MovieRatingsDistinct.class.getName()).log(Level.SEVERE, null, ex);            
        }
    }
    
    
}
