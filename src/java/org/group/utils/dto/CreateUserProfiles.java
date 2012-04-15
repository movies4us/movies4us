/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.group.utils.dto;

import java.util.ArrayList;
import org.group.utils.qo.UserList;
import org.group.utils.qo.UserProfile;

/**
 *
 * @author Aveek
 */
public class CreateUserProfiles
{
    ArrayList userList;
    static int GENRE_COUNT=19;
    
    public void generateUserProfiles()
    {
        UserList uid=new UserList();
        userList=uid.getUserList();
        double profile[];
        for(int i=0;i<userList.size();i++)
        {
            UserProfile up=new UserProfile();
            profile=up.getUserProfile((Integer)userList.get(i));            
            up.update((Integer)userList.get(i),profile);
        }
    }
    
    public static void main(String args[])
    {
        CreateUserProfiles cup=new CreateUserProfiles();
        cup.generateUserProfiles();        
    }
}
