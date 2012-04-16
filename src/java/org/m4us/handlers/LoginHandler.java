/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.handlers;

import org.m4us.controller.FlowContext;
import org.m4us.movielens.utils.dto.UserInfoTableObject;
import org.m4us.workers.LoginWorker;

/**
 *
 * @author arka
 */
public class LoginHandler implements IHandler{

    @Override
    public void handleRequest(FlowContext flowCtx) {
        String userName = (String)flowCtx.get("username");
        String password = (String)flowCtx.get("password");
        
        UserInfoTableObject userInfo = new LoginWorker().getUserInfo(userName);
        if(!userInfo.getPassword().equalsIgnoreCase(password))
            flowCtx.put("SUCCESS",false);
        else
            flowCtx.put("userInfo", userInfo);
            
    }
    
}
