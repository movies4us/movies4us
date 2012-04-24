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
public class GroupMovies 
{
    int groupID;
    ArrayList groupMovies;
    Connection conn;   
    
    public GroupMovies(int id)
    {
        groupID=id;
        groupMovies=new ArrayList();
        conn = (Connection) ConnectionManager.getConnection();
    }
    
    public ArrayList getGroupMovies()
    {
        StringBuilder queryString = new StringBuilder("SELECT MOVIE_ID FROM RATINGS WHERE USER_ID IN (SELECT USER_ID FROM FRIENDS WHERE GROUP_ID = "+groupID+")");
        PreparedStatement st = null;
        ResultSet rs = null;  
        
        try 
        {
            st=conn.prepareStatement(queryString.toString());
            rs = st.executeQuery();
                        
            while(rs.next())
            {
                groupMovies.add(rs.getInt(1));
            }
            conn.close();
        }catch (SQLException ex) 
        {
            System.out.println("Cannot get movies in group");            
            Logger.getLogger(MovieRatingsDistinct.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
        return groupMovies;
    }
}
