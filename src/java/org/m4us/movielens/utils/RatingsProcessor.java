/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.movielens.utils;

import java.util.List;
import org.m4us.movielens.utils.dto.DataTransferObject;
import org.m4us.movielens.utils.qo.RatingsBulkInsert;

/**
 *
 * @author arka
 */
public class RatingsProcessor implements Runnable{

    private List<DataTransferObject> ratingsList;
    private ConnectionInfo connInfo;

    public RatingsProcessor(List<DataTransferObject> ratingsList, ConnectionInfo connInfo) {
        this.ratingsList = ratingsList;
        this.connInfo = connInfo;
    }
    
    
    
    @Override
    public void run() {
        (new RatingsBulkInsert()).bulkInsert(ratingsList, ConnectionManager.getConnection(connInfo));
    }
    
}
