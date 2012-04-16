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
import org.m4us.movielens.utils.dto.DataTransferObject;
import org.m4us.movielens.utils.dto.GroupsTableObject;
import org.m4us.movielens.utils.dto.MembersTableObject;

/**
 *
 * @author arka
 */
public class GroupNonMembershipQueryObject implements ListQueryObject{

    @Override
    public List<DataTransferObject> retrieveList(DataTransferObject object, Connection conn) {
        MembersTableObject memberObjectKey = (MembersTableObject)object;
        //conn = ConnectionManager.getConnection();
        StringBuilder queryString = new StringBuilder("select * from GROUPS where GROUP_ID not in ");
        queryString.append(" (select GROUP_ID from FRIENDS where USER_ID=?)");
        
        Integer userId = memberObjectKey.getUserId();
        //UserGroupMembershipObject groupsListObject = new UserGroupMembershipObject();
        List<DataTransferObject> groupsList = new ArrayList<DataTransferObject>();
        //groupsListObject.setUserId(userId);  
        
        try {
            PreparedStatement ps = conn.prepareStatement(queryString.toString());
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                GroupsTableObject groupObject = new GroupsTableObject();
                groupObject.setGroupId(rs.getInt(1));
                groupObject.setGroupName(rs.getString(2));
                groupsList.add(groupObject);
            }
            //groupsListObject.setGroupsList(groupsList);
        } catch (SQLException ex) {
            Logger.getLogger(MembersTableQueryObject.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(GroupNonMembershipQueryObject.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return groupsList;
    }
    
}
