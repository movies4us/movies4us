/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.movielens.utils.qo;

import java.util.List;
import org.m4us.movielens.utils.dto.DataTransferObject;

/**
 *
 * @author arka
 */
public interface BulkInsertObject extends QueryObjects{
    public void BulkInsert(List<DataTransferObject> objectList);
}
