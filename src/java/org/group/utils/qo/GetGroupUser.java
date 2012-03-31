/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.group.utils.qo;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.m4us.imdb.utils.qo.MovieRatingsDistinct;
import org.m4us.movielens.utils.ConnectionManager;

/**
 *
 * @author Aveek
 */
public class GetGroupUser 
{
    int maxID, minID;
    Connection conn;
    
    public GetGroupUser()
    {
        conn = (Connection) ConnectionManager.getConnection();
        maxID=getMaxUserID();
        minID=getMinUserID();        
    }
    
    public int getValidUserId()
    {
        int id=0;
        Random r=new Random();
        boolean valid;
        
        do
        {
            do
            {
                id=r.nextInt(maxID+1);
            }while(id<minID);
            valid=checkID(id);
        }while(!valid);
        
        return id;
    }
    
    public boolean checkID(int id)
    {
        StringBuilder queryString = new StringBuilder("SELECT COUNT(*) FROM RATINGS WHERE USER_ID="+id);
        PreparedStatement st = null;
        ResultSet rs = null;  
        
        try 
        {
            st=conn.prepareStatement(queryString.toString());
            rs = st.executeQuery();
                        
            while(rs.next())
            {
                if(rs.getInt(1)>0)
                    return true;
            }            
        }catch (SQLException ex) 
        {
            System.out.println("Cannot get count");            
            Logger.getLogger(MovieRatingsDistinct.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
        return false;
    }
    
    public int getMaxUserID()
    {
        StringBuilder queryString = new StringBuilder("SELECT MAX(USER_ID) FROM RATINGS");
        PreparedStatement st = null;
        ResultSet rs = null;
        int count=-1;
        
        try 
        {
            st=conn.prepareStatement(queryString.toString());
            rs = st.executeQuery();
            while(rs.next())
            {
                count=rs.getInt(1);
            }            
            return count;
        }catch (SQLException ex) 
        {
            System.out.println("Cannot get count");            
            Logger.getLogger(MovieRatingsDistinct.class.getName()).log(Level.SEVERE, null, ex);            
        }   
        return -1;
    }
    
    public int getMinUserID()
    {
        StringBuilder queryString = new StringBuilder("SELECT MIN(USER_ID) FROM RATINGS");
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
            return count;
        }catch (SQLException ex) 
        {
            System.out.println("Cannot get count");            
            Logger.getLogger(MovieRatingsDistinct.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return -1;
    }
}
