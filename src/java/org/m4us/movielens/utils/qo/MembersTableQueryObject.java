/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.movielens.utils.qo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.m4us.movielens.utils.ConnectionManager;
import org.m4us.movielens.utils.dto.MembersTableObject;
import org.m4us.movielens.utils.dto.TableObject;

/**
 *
 * @author arka
 */
public class MembersTableQueryObject implements CRUDQueryObjects {

    @Override
    public void create(TableObject object) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TableObject retrieve(TableObject object) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(TableObject object) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(TableObject object) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
