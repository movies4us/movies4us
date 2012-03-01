/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.movielens.utils.qo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.m4us.movielens.utils.dto.DataTransferObject;
import org.m4us.movielens.utils.dto.UserInfoTableObject;

/**
 *
 * @author arka
 */
public class UserInfoBulkInsert implements BulkInsertObject{

    
            
    @Override
    public void bulkInsert(List<DataTransferObject> objectList, Connection conn) {
        int paramNo = 1;
        int count = 0;
        StringBuilder queryString = new StringBuilder("INSERT INTO USER_INFO VALUES (");
        queryString.append(" ? , ? , ?, ? )");
        PreparedStatement st=null;
        try{
        conn.setAutoCommit(false);
        st = conn.prepareStatement(queryString.toString());
        for(DataTransferObject obj:objectList){
            
            UserInfoTableObject object = (UserInfoTableObject)obj;
            st.setInt(paramNo++, object.getUserId());
            st.setString(paramNo++, object.getUsername());
            st.setString(paramNo++, object.getPassword());
            st.setTimestamp(paramNo++, object.getJoinDate());
            
            st.executeUpdate();
            count++;
            paramNo=1;
            if(count>=100){
                conn.commit();
                count=0;
            }
        }
        conn.commit();    // commit the last batch of records that may not 
                          //be multiple of 100
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try {
                st.close();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserInfoBulkInsert.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
