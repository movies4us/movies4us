/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.movielens.utils.qo;

import org.m4us.movielens.utils.dto.TableObject;

/**
 *
 * @author arka
 */
public interface CRUDQueryObjects extends QueryObjects{
    public void create(TableObject object);
    public TableObject retrieve(TableObject object);
    public void update(TableObject object);
    public void delete(TableObject object);
}
