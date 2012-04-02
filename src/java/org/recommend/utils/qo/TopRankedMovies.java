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
public class TopRankedMovies 
{
    Connection conn;
    ArrayList topRankedMovies;
    
    public TopRankedMovies()
    {
        conn = (Connection) ConnectionManager.getConnection();
        topRankedMovies=new ArrayList();
        
        StringBuilder queryString = new StringBuilder("SELECT MOVIE_ID FROM MOVIES WHERE RANK > 0");
        PreparedStatement st = null;
        ResultSet rs = null;  
        
        try 
        {
            st=conn.prepareStatement(queryString.toString());
            rs = st.executeQuery();
                        
            while(rs.next())
            {
                topRankedMovies.add(rs.getInt(1));
            }            
        }catch (SQLException ex) 
        {
            System.out.println("Cannot get top ranked");            
            Logger.getLogger(MovieRatingsDistinct.class.getName()).log(Level.SEVERE, null, ex);            
        }             
    }
    
    public ArrayList getTopRankedMovies()
    {
        return topRankedMovies;
    }
    
    public void printTopRankedMovies()
    {
        for(int i=0;i<topRankedMovies.size();i++)
            System.out.println(topRankedMovies.get(i));
    }
}
