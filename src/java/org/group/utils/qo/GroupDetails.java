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
public class GroupDetails 
{
    int adminID, groupID;
    Connection conn;
    public GroupDetails(int gID)
    {
        groupID=gID;
        conn = (Connection) ConnectionManager.getConnection();
    }
    
    public void addAdmin()
    {
        StringBuilder queryString = new StringBuilder("SELECT USER_ID FROM FRIENDS WHERE GROUP_ID="+groupID+" ORDER BY RAND() LIMIT 1");
        PreparedStatement st = null;
        ResultSet rs = null;  
        
        try 
        {
            st=conn.prepareStatement(queryString.toString());
            rs = st.executeQuery();
                        
            while(rs.next())
            {
                adminID=rs.getInt(1)+1;
            }
            
            queryString = new StringBuilder("UPDATE GROUPS SET GROUP_ADMIN ="+adminID+" WHERE GROUP_ID="+groupID);
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
