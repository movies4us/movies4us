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
public interface ListQueryObject extends QueryObjects{
    public List<DataTransferObject> retrieveList(DataTransferObject object,Connection conn);
}
