/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.group.utils.qo;

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
public class UserFeatures 
{
    ArrayList actors, languages, directors;
    Connection conn;
    
    public UserFeatures()
    {
        conn = (Connection) ConnectionManager.getConnection();
    }
    
    public ArrayList getActors(int userId)
    {   
        StringBuilder queryString = new StringBuilder("SELECT PERSON_NAME FROM MOVIES_PERSONS WHERE PERSON_ROLE ='AR' AND MOVIE_ID IN (SELECT MOVIE_ID FROM RATINGS WHERE USER_ID ="+userId+")");
        PreparedStatement st = null;
        ResultSet rs = null;  
        actors=new ArrayList();
        
        try 
        {
            st=conn.prepareStatement(queryString.toString());
            rs = st.executeQuery();
                        
            while(rs.next())
            {
                actors.add(rs.getString(1));
            }            
        }catch (SQLException ex) 
        {
            System.out.println("Cannot get actor set");            
            Logger.getLogger(MovieRatingsDistinct.class.getName()).log(Level.SEVERE, null, ex);            
        }        
        return actors;
    }
    
    public ArrayList getDirectors(int userId)
    {
        StringBuilder queryString = new StringBuilder("SELECT PERSON_NAME FROM MOVIES_PERSONS WHERE PERSON_ROLE ='DI' AND MOVIE_ID IN (SELECT MOVIE_ID FROM RATINGS WHERE USER_ID ="+userId+")");
        PreparedStatement st = null;
        ResultSet rs = null;  
        directors=new ArrayList();
        
        try 
        {
            st=conn.prepareStatement(queryString.toString());
            rs = st.executeQuery();
                        
            while(rs.next())
            {
                directors.add(rs.getString(1));
            }            
        }catch (SQLException ex) 
        {
            System.out.println("Cannot get director set");            
            Logger.getLogger(MovieRatingsDistinct.class.getName()).log(Level.SEVERE, null, ex);            
        }        
        return directors;
    }
    
    public ArrayList getLanguages(int userId)
    {
        StringBuilder queryString = new StringBuilder("SELECT LANGUAGE FROM MOVIES WHERE MOVIE_ID IN (SELECT MOVIE_ID FROM RATINGS WHERE USER_ID ="+userId+")");
        PreparedStatement st = null;
        ResultSet rs = null;  
        languages=new ArrayList();
        
        try 
        {
            st=conn.prepareStatement(queryString.toString());
            rs = st.executeQuery();
                        
            while(rs.next())
            {
                languages.add(rs.getString(1));
            }            
        }catch (SQLException ex) 
        {
            System.out.println("Cannot get language set");            
            Logger.getLogger(MovieRatingsDistinct.class.getName()).log(Level.SEVERE, null, ex);            
        }        
        return languages;
    }
    
    public String formatString(String str)
    {        
        String featureName=str;
        for(int i=0;i<featureName.length();i++)
        {
            if(featureName.charAt(i)=='\'')
            {
                if(i==0)
                    featureName="\\"+featureName;
                else if(featureName.charAt(i-1)!='\\')
                {
                    featureName=featureName.substring(0,i)+"\\"+featureName.substring(i,featureName.length());                                    
                }
                i++;
            }
        }
        return featureName;
    }
    
    public void addActor(int user_id, String actor)
    {
        actor=formatString(actor);
        StringBuilder queryString = new StringBuilder("SELECT COUNT(*) FROM USER_FEATURES WHERE USER_ID = "+user_id+" AND FEATURE_ID = 1 AND FEATURE_NAME ='"+actor+"'");
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
            if(count==0)
                count=1;
            else
            {
                queryString = new StringBuilder("SELECT COUNT FROM USER_FEATURES WHERE USER_ID = "+user_id+" AND FEATURE_ID = 1 AND FEATURE_NAME ='"+actor+"'");
                st=conn.prepareStatement(queryString.toString());
                rs = st.executeQuery();
                while(rs.next())
                {
                    count=rs.getInt(1);
                }
                count++;
                
                queryString = new StringBuilder("DELETE FROM USER_FEATURES WHERE USER_ID = "+user_id+" AND FEATURE_ID = 1 AND FEATURE_NAME ='"+actor+"'");
                st=conn.prepareStatement(queryString.toString());
                st.executeUpdate(); 
            }    
            queryString = new StringBuilder("INSERT INTO USER_FEATURES VALUES ("+user_id+",1, '"+actor+"',"+count+")");
            //System.out.println(queryString.toString());
            st = conn.prepareStatement(queryString.toString());
            st.executeUpdate();
            
        }catch (SQLException ex) 
        {
            System.out.println(queryString.toString());
            System.out.println("Cannot generate user feature set");            
            Logger.getLogger(MovieRatingsDistinct.class.getName()).log(Level.SEVERE, null, ex);            
        }                   
    }
    
    public void addDirector(int user_id, String director)
    {
        director=formatString(director);
        StringBuilder queryString = new StringBuilder("SELECT COUNT(*) FROM USER_FEATURES WHERE USER_ID = "+user_id+" AND FEATURE_ID = 2 AND FEATURE_NAME ='"+director+"'");
        PreparedStatement st = null;
        ResultSet rs = null;
        int count=0;
        director=formatString(director);
        
        try 
        {
            st=conn.prepareStatement(queryString.toString());
            rs = st.executeQuery();
            while(rs.next())
            {
                count=rs.getInt(1);
            }
            if(count==0)
                count=1;
            else
            {
                queryString = new StringBuilder("SELECT COUNT FROM USER_FEATURES WHERE USER_ID = "+user_id+" AND FEATURE_ID = 2 AND FEATURE_NAME ='"+director+"'");
                st=conn.prepareStatement(queryString.toString());
                rs = st.executeQuery();
                while(rs.next())
                {
                    count=rs.getInt(1);
                }
                count++;
                
                queryString = new StringBuilder("DELETE FROM USER_FEATURES WHERE USER_ID = "+user_id+" AND FEATURE_ID = 2 AND FEATURE_NAME ='"+director+"'");
                st=conn.prepareStatement(queryString.toString());
                st.executeUpdate(); 
            }
            queryString = new StringBuilder("INSERT INTO USER_FEATURES VALUES ("+user_id+",2, '"+director+"',"+count+")");
            st = conn.prepareStatement(queryString.toString());
            st.executeUpdate();
            
        }catch (SQLException ex) 
        {
            System.out.println(queryString.toString());
            System.out.println("Cannot generate user feature set");            
            Logger.getLogger(MovieRatingsDistinct.class.getName()).log(Level.SEVERE, null, ex);            
        }                          
    }
    
    public void addLanguage(int user_id, String language)
    {
        StringBuilder queryString = new StringBuilder("SELECT COUNT(*) FROM USER_FEATURES WHERE USER_ID = "+user_id+" AND FEATURE_ID = 3 AND FEATURE_NAME ='"+language+"'");
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
            if(count==0)
                count=1;
            else
            {
                queryString = new StringBuilder("SELECT COUNT FROM USER_FEATURES WHERE USER_ID = "+user_id+" AND FEATURE_ID = 3 AND FEATURE_NAME ='"+language+"'");
                st=conn.prepareStatement(queryString.toString());
                rs = st.executeQuery();
                while(rs.next())
                {
                    count=rs.getInt(1);
                }
                count++;
                
                queryString = new StringBuilder("DELETE FROM USER_FEATURES WHERE USER_ID = "+user_id+" AND FEATURE_ID = 3 AND FEATURE_NAME ='"+language+"'");
                st=conn.prepareStatement(queryString.toString());
                st.executeUpdate(); 
            }    
            queryString = new StringBuilder("INSERT INTO USER_FEATURES VALUES ("+user_id+",3, '"+language+"' ,"+count+")");
            st = conn.prepareStatement(queryString.toString());
            st.executeUpdate();            
        }catch (SQLException ex) 
        {
            System.out.println("Cannot generate user feature set");            
            Logger.getLogger(MovieRatingsDistinct.class.getName()).log(Level.SEVERE, null, ex);            
        }                      
    }
}
