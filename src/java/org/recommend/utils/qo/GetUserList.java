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
public class GetUserList 
{
    int groupID;
    ArrayList users;
    Connection conn;
    
    public GetUserList(int id)
    {
        groupID=id;
        users=new ArrayList();
        conn = (Connection) ConnectionManager.getConnection();
        
        getUsers();
    }
    
    public void getUsers()
    {
        StringBuilder queryString = new StringBuilder("SELECT USER_ID FROM FRIENDS WHERE GROUP_ID="+groupID);
        PreparedStatement st = null;
        ResultSet rs = null;  
        
        try 
        {
            st=conn.prepareStatement(queryString.toString());
            rs = st.executeQuery();
                        
            while(rs.next())
            {
                users.add(rs.getInt(1));
            }            
        }catch (SQLException ex) 
        {
            System.out.println("Cannot get users in group");            
            Logger.getLogger(MovieRatingsDistinct.class.getName()).log(Level.SEVERE, null, ex);            
        }
    }
    
    public ArrayList getUsersList()
    {
        return users;
    }
}
