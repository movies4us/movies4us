/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.movielens.utils.qo;

import java.util.List;
import org.m4us.movielens.utils.dto.DataTransferObject;
import java.sql.Connection;
/**
 *
 * @author arka
 */
public interface BulkInsertObject extends QueryObjects{
    public void bulkInsert(List<DataTransferObject> objectList, Connection conn);
}
