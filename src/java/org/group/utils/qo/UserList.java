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
public class UserList 
{
    ArrayList userList;
    Connection conn;
    
    public UserList()
    {
       conn = (Connection) ConnectionManager.getConnection(); 
    }
    
    public ArrayList getUserList()
    {
        StringBuilder queryString = new StringBuilder("SELECT USER_ID FROM USER_INFO ORDER BY USER_ID");
        PreparedStatement st = null;
        ResultSet rs = null;  
        userList=new ArrayList();
        
        try 
        {
            st=conn.prepareStatement(queryString.toString());
            rs = st.executeQuery();
                        
            while(rs.next())
            {
                userList.add(rs.getInt(1));
                    
            }            
        }catch (SQLException ex) 
        {
            System.out.println("Cannot get count");            
            Logger.getLogger(MovieRatingsDistinct.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return userList;        
    }
    
}
