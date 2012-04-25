/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.workers;

import java.sql.Timestamp;
import org.m4us.imdb.utils.qo.UserProfileInsert;
import org.m4us.movielens.utils.dto.UserInfoTableObject;
import org.m4us.movielens.utils.qo.UserInfoQueryObject;

/**
 *
 * @author arka
 */
public class SignUpWorker {
    
    public UserInfoTableObject addNewUser(String userName,String password) {
        UserInfoTableObject userInfo = new UserInfoTableObject();
        userInfo.setUsername(userName);
        userInfo.setPassword(password);
        userInfo.setUserId(null);
        userInfo.setJoinDate(new Timestamp(System.currentTimeMillis()));
        new UserInfoQueryObject().create(userInfo);
        new UserInfoQueryObject().retrieveByUserName(userInfo);
        UserProfileInsert upi=new UserProfileInsert();
        upi.createProfile(userInfo);
        return userInfo;
    }
}
