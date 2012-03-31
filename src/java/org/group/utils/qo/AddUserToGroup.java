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
public class AddUserToGroup 
{
    Connection conn;
    
    public AddUserToGroup()
    {
        conn = (Connection) ConnectionManager.getConnection();
    }
    
    public void addUser(int userID, int groupID)
    {
        StringBuilder queryString = new StringBuilder("INSERT INTO FRIENDS VALUES ("+userID+","+groupID+")");
        PreparedStatement st = null;                
        
        try 
        {
            st = conn.prepareStatement(queryString.toString());
            st.executeUpdate();
        }catch (SQLException ex) 
        {
            System.out.println("Cannot Insert");
            Logger.getLogger(MovieRatingsDistinct.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
