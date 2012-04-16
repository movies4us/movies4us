/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.handlers;

import org.m4us.controller.FlowContext;
import org.m4us.movielens.utils.dto.UserInfoTableObject;
import org.m4us.workers.GroupMembershipWorker;

/**
 *
 * @author arka
 */
public class UserGroupMembershipHandler implements IHandler{

    @Override
    public void handleRequest(FlowContext flowCtx) {
        UserInfoTableObject userInfo = (UserInfoTableObject)flowCtx.get("userInfo");
        flowCtx.put("UserGroupsList", new GroupMembershipWorker().getGroupMembershipList(userInfo.getUserId()));
        flowCtx.put("AvailableGroupsList", new GroupMembershipWorker().getGroupNonMembershipList(userInfo.getUserId()));
    }
    
}
