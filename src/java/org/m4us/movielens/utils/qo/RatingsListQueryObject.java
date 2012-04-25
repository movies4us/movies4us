/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.movielens.utils.qo;

import java.sql.Connection;
import java.util.List;
import org.m4us.movielens.utils.dto.DataTransferObject;

/**
 *
 * @author arka
 */
public class RatingsListQueryObject implements ListQueryObject{

    @Override
    public List<DataTransferObject> retrieveList(DataTransferObject object, Connection conn) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
