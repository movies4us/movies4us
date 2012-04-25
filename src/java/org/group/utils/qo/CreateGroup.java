/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.group.utils.qo;

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
public class CreateGroup {
    int groupID;
    int userID;
    String groupName;
    Connection conn;
    
    public CreateGroup(int uID, String gName)
    {
        userID=uID;
        groupName=gName;
        conn = (Connection) ConnectionManager.getConnection();
    }
    
    public void create()
    {
        StringBuilder queryString = new StringBuilder("SELECT MAX(GROUP_ID) FROM GROUPS");
        PreparedStatement st = null;
        ResultSet rs = null;  
        
        try 
        {
            st=conn.prepareStatement(queryString.toString());
            rs = st.executeQuery();
                        
            while(rs.next())
            {
                groupID=rs.getInt(1)+1;
            }
            queryString = new StringBuilder("INSERT INTO GROUPS VALUES("+groupID+" , '"+groupName+"',"+userID+")");
            st = conn.prepareStatement(queryString.toString());
            st.executeUpdate();
            
            queryString = new StringBuilder("INSERT INTO FRIENDS VALUES("+userID+" , "+groupID+")");
            st = conn.prepareStatement(queryString.toString());
            st.executeUpdate();
            
            conn.close();
        }catch (SQLException ex) 
        {
            System.out.println("Cannot create group");   
            System.out.println(queryString);
            Logger.getLogger(MovieRatingsDistinct.class.getName()).log(Level.SEVERE, null, ex);            
        }
    }
    
    
}
