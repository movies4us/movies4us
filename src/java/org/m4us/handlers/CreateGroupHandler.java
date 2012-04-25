/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.handlers;

import org.group.utils.qo.CreateGroup;
import org.m4us.controller.FlowContext;
import org.m4us.movielens.utils.dto.UserInfoTableObject;

/**
 *
 * @author Aveek
 */
public class CreateGroupHandler implements IHandler
{
    @Override
    public void handleRequest(FlowContext flowCtx) 
    {
        String groupName = (String)flowCtx.get("groupName");
        UserInfoTableObject userInfo = (UserInfoTableObject)flowCtx.get("userInfo");
        int userID=userInfo.getUserId();
        
        CreateGroup cg=new CreateGroup(userID,groupName);
        cg.create();
    }
    
}
