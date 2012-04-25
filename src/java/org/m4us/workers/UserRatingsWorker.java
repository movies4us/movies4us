/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.workers;

import java.util.List;
import org.m4us.movielens.utils.ConnectionManager;
import org.m4us.movielens.utils.dto.DataTransferObject;
import org.m4us.movielens.utils.qo.RatingsBulkInsert;

/**
 *
 * @author arka
 */
public class UserRatingsWorker {
    public void insertUserRatings(List<DataTransferObject> userRatings){
        new RatingsBulkInsert().bulkInsert(userRatings, ConnectionManager.getConnection());
    }
    
    public List<DataTransferObject> getRatedMovies(String userId){
        return null;
    }
}
