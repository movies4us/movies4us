/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.handlers;

import java.sql.Timestamp;
import org.m4us.controller.FlowContext;
import org.m4us.movielens.utils.dto.UserInfoTableObject;
import org.m4us.workers.SignUpWorker;

/**
 *
 * @author arka
 */
public class SignUpHandler implements IHandler{

    @Override
    public void handleRequest(FlowContext flowCtx) {
        String userName = (String)flowCtx.get("username");
        String password = (String)flowCtx.get("password");
        UserInfoTableObject userInfo = new SignUpWorker().addNewUser(userName, password);
        flowCtx.put("userInfo", userInfo);
    }
    
}
