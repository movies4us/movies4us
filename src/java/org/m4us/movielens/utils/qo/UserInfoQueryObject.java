/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.movielens.utils.qo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.m4us.movielens.utils.ConnectionManager;
import org.m4us.movielens.utils.dto.TableObject;
import org.m4us.movielens.utils.dto.UserInfoTableObject;

/**
 *
 * @author arka
 */
public class UserInfoQueryObject implements CRUDQueryObjects{

    @Override
    public void create(TableObject object) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TableObject retrieve(TableObject object) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(TableObject object) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(TableObject object) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void retrieveByUserName(TableObject object){
        UserInfoTableObject userInfo = (UserInfoTableObject)object;
        Connection conn = ConnectionManager.getConnection();
        String queryString = "SELECT * FROM USER_INFO WHERE USERNAME=?";
        try {
            PreparedStatement ps = conn.prepareStatement(queryString);
            ps.setString(1, userInfo.getUsername());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                userInfo.setUserId(rs.getInt(1));
                userInfo.setPassword(rs.getString(3));
                userInfo.setJoinDate(rs.getTimestamp(4));
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(UserInfoQueryObject.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserInfoQueryObject.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
