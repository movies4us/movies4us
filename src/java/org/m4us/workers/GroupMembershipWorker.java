/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.workers;

import java.sql.Connection;
import java.util.List;
import org.m4us.movielens.utils.ConnectionManager;
import org.m4us.movielens.utils.dto.DataTransferObject;
import org.m4us.movielens.utils.dto.MembersTableObject;
import org.m4us.movielens.utils.qo.GroupMembershipQueryObject;
import org.m4us.movielens.utils.qo.GroupNonMembershipQueryObject;

/**
 *
 * @author arka
 */
public class GroupMembershipWorker {
    public List<DataTransferObject> getGroupMembershipList(int userId){
        Connection conn = ConnectionManager.getConnection();
        MembersTableObject memberObjectKey = new MembersTableObject();
        memberObjectKey.setUserId(userId);
        List<DataTransferObject> groupsList = new GroupMembershipQueryObject().retrieveList(memberObjectKey, conn);
        return groupsList;
    }
    
    public List<DataTransferObject> getGroupNonMembershipList(int userId){
        Connection conn = ConnectionManager.getConnection();
        MembersTableObject memberObjectKey = new MembersTableObject();
        memberObjectKey.setUserId(userId);
        List<DataTransferObject> groupsList = new GroupNonMembershipQueryObject().retrieveList(memberObjectKey, conn);
        return groupsList;
    }
}
