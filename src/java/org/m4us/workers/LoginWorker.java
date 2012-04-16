/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.workers;

import org.m4us.movielens.utils.dto.UserInfoTableObject;
import org.m4us.movielens.utils.qo.UserInfoQueryObject;

/**
 *
 * @author arka
 */
public class LoginWorker{

    public UserInfoTableObject getUserInfo(String userName) {
        UserInfoTableObject userInfo = new UserInfoTableObject();
        userInfo.setUsername(userName);
        new UserInfoQueryObject().retrieveByUserName(userInfo);
        
        return userInfo;
    }

        
}
