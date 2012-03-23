/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.imdb.utils;

import java.sql.Connection;
import java.util.List;
import org.m4us.imdb.utils.qo.MoviesPersonsBulkInsert;
import org.m4us.movielens.utils.ConnectionInfo;
import org.m4us.movielens.utils.ConnectionManager;
import org.m4us.movielens.utils.dto.DataTransferObject;

/**
 *
 * @author arka
 */
public class PersonsProcessor implements Runnable{

    private List<DataTransferObject> personsList;
    private ConnectionInfo connInfo;

    public PersonsProcessor(List<DataTransferObject> personsList, ConnectionInfo connInfo) {
        this.personsList = personsList;
        this.connInfo = connInfo;
    }
    
    
    @Override
    public void run() {
        Connection conn;
        if(connInfo!=null)
            conn = ConnectionManager.getConnection(connInfo);
        else
            conn = ConnectionManager.getConnection();
        
        new MoviesPersonsBulkInsert().bulkInsert(personsList, conn);
    }
    
}
