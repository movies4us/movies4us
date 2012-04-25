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
public class RejectedCount 
{
    Connection conn;
    int movieID;
    int groupID;
    
    public RejectedCount(int gid, int mid)
    {
        groupID=gid;
        movieID=mid;
        conn = (Connection) ConnectionManager.getConnection();        
    }
    
    public int getRejectedCount()
    {
        StringBuilder queryString = new StringBuilder("SELECT COUNT FROM REJECTED_MOVIES WHERE GROUP_ID="+groupID+" AND MOVIE_ID="+movieID);
        PreparedStatement st = null;
        ResultSet rs = null; 
        int count=0;
        
        try 
        {
            st=conn.prepareStatement(queryString.toString());
            rs = st.executeQuery();
                        
            while(rs.next())
            {
                count=rs.getInt(1);
            }        
            conn.close();
        }catch (SQLException ex) 
        {
            System.out.println("Cannot get users in group");            
            Logger.getLogger(MovieRatingsDistinct.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return count;
    }
}
