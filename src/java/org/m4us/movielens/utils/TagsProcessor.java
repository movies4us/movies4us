/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.movielens.utils;

import java.util.List;
import org.m4us.movielens.utils.dto.DataTransferObject;
import org.m4us.movielens.utils.qo.TagsBulkInsert;

/**
 *
 * @author arka
 */
public class TagsProcessor implements Runnable{

    private List<DataTransferObject> tagsList;
    private ConnectionInfo connInfo;

    public TagsProcessor(List<DataTransferObject> tagsList, ConnectionInfo connInfo) {
        this.tagsList = tagsList;
        this.connInfo = connInfo;
    }
    
    
        
    @Override
    public void run() {
        (new TagsBulkInsert()).bulkInsert(tagsList, ConnectionManager.getConnection(connInfo));
    }

}
