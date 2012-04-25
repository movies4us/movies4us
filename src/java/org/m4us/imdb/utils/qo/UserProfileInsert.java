/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.imdb.utils.qo;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.m4us.movielens.utils.ConnectionManager;
import org.m4us.movielens.utils.dto.UserInfoTableObject;

/**
 *
 * @author Aveek
 */
public class UserProfileInsert 
{
    static int GENRE_COUNT=19;
    public void createProfile(UserInfoTableObject object)
    {
        String values=object.getUserId()+",";
        Connection conn=(Connection) ConnectionManager.getConnection();
        for(int i=0;i<GENRE_COUNT;i++)
            values+="0,";
        values=values.substring(0,values.length()-1);
        
        StringBuilder queryString = new StringBuilder("INSERT INTO USER_PROFILE VALUES ("+values+")");
        PreparedStatement st = null;  
        
        try 
        {
            st = conn.prepareStatement(queryString.toString());
            st.executeUpdate();
            
            conn.close();
        }catch (SQLException ex) 
        {
            System.out.println("Cannot Insert into user_profile"+"\n"+queryString.toString());
            Logger.getLogger(MovieRatingsDistinct.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
