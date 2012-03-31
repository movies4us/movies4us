/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.imdb.utils.qo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.m4us.imdb.utils.IMDBUtils;
import org.m4us.movielens.utils.ConnectionInfo;
import org.m4us.movielens.utils.ConnectionManager;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.StringTokenizer;
import org.m4us.imdb.utils.dto.GetIMDBInformation;
import org.m4us.imdb.utils.dto.MovieInformation;
/**
 *
 * @author Aveek
 */
public class MovieRankInsert 
{
    String movieName, movieYear;
    int movieRank;
    
    public MovieRankInsert(String name, String year, int rank)
    {
        movieName=formatName(name);
        movieYear=year;
        movieRank=rank;
        
        insertRank(ConnectionManager.getConnection());
    }
    
    public String formatName(String str)
    {
        for(int i=0;i<str.length();i++)
            if(str.charAt(i)=='\'')
            {
                str=str.substring(0,i+1)+str.substring(i,str.length());
                i++;
            }
        return str;
    }
    
    public void insertRank(Connection conn)
    {
        
        StringBuilder queryString = new StringBuilder("UPDATE MOVIES SET RANK ="+movieRank+" WHERE MOVIE_NAME ='"+movieName+"' AND RELEASE_YEAR ='"+movieYear+"'");
        PreparedStatement st = null;
        PreparedStatement personsStmt=null;
        ResultSet rs = null;        
        
        try 
        {                               
            System.out.println(movieName+" "+movieYear+" "+movieRank);
            personsStmt = conn.prepareStatement(queryString.toString());
            personsStmt.executeUpdate();                           
        }catch (SQLException ex) 
        {
            System.out.println(movieName+" "+movieYear);
            Logger.getLogger(MovieRatingsDistinct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
