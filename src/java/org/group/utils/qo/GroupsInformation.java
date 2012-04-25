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
public class GroupsInformation {

    ArrayList groups;
    Connection conn;
    public GroupsInformation()
    {
        conn = (Connection) ConnectionManager.getConnection();
        groups=new ArrayList();
    }
    
    public ArrayList getGroups()
    {
        StringBuilder queryString = new StringBuilder("SELECT GROUP_ID FROM GROUPS");
        PreparedStatement st = null;
        ResultSet rs = null;  
        
        try 
        {
            st=conn.prepareStatement(queryString.toString());
            rs = st.executeQuery();
                        
            while(rs.next())
            {
               groups.add(rs.getInt(1));
            }
            conn.close();
        }catch (SQLException ex) 
        {
            System.out.println("Cannot update groups");   
            System.out.println(queryString);
            Logger.getLogger(MovieRatingsDistinct.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return groups;
    }
}
