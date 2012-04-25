/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.movielens.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.m4us.movielens.utils.dto.DataTransferObject;
import org.m4us.movielens.utils.dto.RatingsTableObject;
import org.m4us.movielens.utils.qo.TagsBulkInsert;

/**
 *
 * @author Aveek
 */
public class RatingsBulkUpdate 
{
    public void bulkUpdate(List<DataTransferObject> objectList, Connection conn)
    {
        StringBuilder queryString;        
        PreparedStatement st = null;
        
        try
        {
            for(DataTransferObject obj : objectList)
            {
                RatingsTableObject object = (RatingsTableObject) obj;
                queryString=new StringBuilder("UPDATE RATINGS SET RATINGS = "+object.getRating()+" WHERE USER_ID ="+object.getUserId()+" AND MOVIE_ID="+object.getMovieId());
                st.executeUpdate();
            }
            conn.commit();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try {
                st.close();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(TagsBulkInsert.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
