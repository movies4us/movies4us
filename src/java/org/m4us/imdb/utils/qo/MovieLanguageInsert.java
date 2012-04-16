/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.imdb.utils.qo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.m4us.movielens.utils.ConnectionInfo;
import org.m4us.movielens.utils.ConnectionManager;
import org.m4us.imdb.utils.dto.GetIMDBInformation;
import org.m4us.imdb.utils.dto.MovieInformation;

/**
 *
 * @author Aveek
 */
public class MovieLanguageInsert {
    String movieName, movieYear;
    Connection conn;
    int movieID;
    
    public MovieLanguageInsert(ConnectionInfo connInfo)
    {                
        if(connInfo!=null)
            conn = ConnectionManager.getConnection(connInfo);
        else
            conn = ConnectionManager.getConnection();
        
        getLanguage(conn);
    }
    
    public void getLanguage(Connection conn)
    {
        StringBuilder queryString = new StringBuilder("SELECT MOVIE_ID, MOVIE_NAME,RELEASE_YEAR FROM movies4us.MOVIES");
        PreparedStatement st = null;
        PreparedStatement personsStmt=null;
        ResultSet rs = null;        
        
        try 
        {
            st=conn.prepareStatement(queryString.toString());
            rs = st.executeQuery();            
            
            while(rs.next())
            {
               movieID=rs.getInt(1);
               movieName=rs.getString(2);
               movieYear=rs.getString(3);          
               
               MovieInformation mi= getURLInformation(movieID,movieName,movieYear);
               
               if(mi!=null)
                    System.out.println(mi.getMovieID()+"\t"+mi.getMovieName()+"\t"+mi.getMovieYear()+"\t"+mi.getMPAARating()+"\t"+mi.getPlot()+"\t"+mi.getPosterLink()+"\t"+mi.getRuntime()+"\t"+mi.getRating()+"\t"+mi.getMovieLanguage()+"\t"+mi.getIMDBID());
               else
                   System.out.println(movieID+" "+movieName+" "+movieYear);

               if(mi!=null)
               {
                   String query="UPDATE movies4us.MOVIES SET RATING ="+mi.getRating()+", RUNTIME ="+mi.getRuntime()+", MPAA ='"+mi.getMPAARating()+"' , LANGUAGE ='"+mi.getMovieLanguage()+"', IMDB_ID ='"+mi.getIMDBID()+"' WHERE MOVIE_ID ="+movieID;               
                   queryString=new StringBuilder(query);
                   personsStmt = conn.prepareStatement(queryString.toString());
                   personsStmt.executeUpdate();
               }
            }
        }catch (SQLException ex) 
        {
            System.out.println(movieName+" "+movieYear);
            Logger.getLogger(MovieRatingsDistinct.class.getName()).log(Level.SEVERE, null, ex);
        }                        
    }
    
    public MovieInformation getURLInformation(int ID, String movieName, String movieYear)
    {
        GetIMDBInformation gi=new GetIMDBInformation(movieName,movieYear);
        if((gi.getData()).equals("{\"Response\":\"Parse Error\"}"))
            return null;
        MovieInformation mi=new MovieInformation(ID, movieName, movieYear, gi.getData());
        
        return mi;
    }    
}
