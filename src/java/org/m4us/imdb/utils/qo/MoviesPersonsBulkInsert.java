/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.imdb.utils.qo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.m4us.imdb.utils.dto.MoviesPersonsTableObject;
import org.m4us.movielens.utils.dto.DataTransferObject;
import org.m4us.movielens.utils.qo.BulkInsertObject;
import org.m4us.movielens.utils.qo.UserInfoBulkInsert;

/**
 *
 * @author arka
 */
public class MoviesPersonsBulkInsert implements BulkInsertObject{

    @Override
    public void bulkInsert(List<DataTransferObject> objectList, Connection conn) {
        int paramNo = 1;
        int count = 0;
        StringBuilder queryStringPersons = new StringBuilder("INSERT INTO MOVIES_PERSONS VALUES (");
        queryStringPersons.append(" ? , ? , ? )");
        PreparedStatement personsStmt=null;
        MoviesPersonsTableObject object = null;
        
        try{
        conn.setAutoCommit(false);
        personsStmt = conn.prepareStatement(queryStringPersons.toString());
        
        for(DataTransferObject obj:objectList){
            
            object = (MoviesPersonsTableObject)obj;
            if(object.getPersonName().length()>45)
                continue;
            personsStmt.setInt(paramNo++, object.getMovieId());
            personsStmt.setString(paramNo++, object.getPersonName());
            personsStmt.setString(paramNo++, object.getPersonRole());
            try{
            personsStmt.executeUpdate();
            }catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e){
                System.out.println("object details"+object.toString());
                paramNo=1;
                continue;
            }
            paramNo=1;
            count++;
            if(count>=100){
                conn.commit();
                count=0;
            }
        }
        conn.commit();    // commit the last batch of records that may not 
                          //be multiple of 100
        }catch(SQLException e){
            System.out.println("Current object"+object.toString());
            e.printStackTrace();
        }finally{
            try {
                personsStmt.close();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserInfoBulkInsert.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
