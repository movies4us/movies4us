/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.handlers;

import java.util.ArrayList;
import org.group.utils.qo.AddUserToGroup;
import org.m4us.controller.FlowContext;
import org.m4us.movielens.utils.dto.GroupsTableObject;
import org.m4us.movielens.utils.dto.UserInfoTableObject;
import org.m4us.workers.GroupMembershipWorker;

/**
 *
 * @author Aveek
 */
public class JoinGroupHandler implements IHandler
{
    @Override
    public void handleRequest(FlowContext flowCtx) 
    {
        Integer groupId = Integer.parseInt((String)flowCtx.get("groupId"));
        UserInfoTableObject userInfo = (UserInfoTableObject)flowCtx.get("userInfo");
        int userId=userInfo.getUserId();
        
        AddUserToGroup add=new AddUserToGroup();
        add.addUser(userId, groupId);
        
        ArrayList belongToGroups=(ArrayList)flowCtx.get("UserGroupsList");
        ArrayList availableGroups=(ArrayList)flowCtx.get("AvailableGroupsList");
        
        GroupsTableObject obj=new GroupsTableObject();
        obj.setGroupId(groupId);
        
        for(int i=0;i<availableGroups.size();i++)
        {
            GroupsTableObject groupObject=(GroupsTableObject)availableGroups.get(i);
            if(groupObject.getGroupId()==groupId)
            {
                obj.setGroupName(groupObject.getGroupName());
                availableGroups.remove(i);
                break;
            }
        }        
        belongToGroups.add(obj);
        flowCtx.put("UserGroupsList", belongToGroups);
        flowCtx.put("AvailableGroupsList", availableGroups);
    }
}
