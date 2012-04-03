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
public class MovieLanguages 
{
    ArrayList languages;
    Connection conn;
    
    public MovieLanguages()
    {
        conn = (Connection) ConnectionManager.getConnection();
    }
    
    public ArrayList getMovieLanguages(int movieID)
    {
        StringBuilder queryString = new StringBuilder("SELECT LANGUAGE FROM MOVIES WHERE MOVIE_ID="+movieID);
        PreparedStatement st = null;
        ResultSet rs = null;  
        languages=new ArrayList();
        
        try 
        {
            st=conn.prepareStatement(queryString.toString());
            rs = st.executeQuery();
                        
            while(rs.next())
            {
                String lang=rs.getString(1);                
                if(lang!=null)
                    if(!lang.equals("null"))
                        languages.add(lang);
            }            
        }catch (SQLException ex) 
        {
            System.out.println("Cannot get director set");            
            Logger.getLogger(MovieRatingsDistinct.class.getName()).log(Level.SEVERE, null, ex);            
        }                
        return languages;
    }
}
