/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.group.utils.dto;

import java.util.Random;
import org.group.utils.qo.AddUserToGroup;
import org.group.utils.qo.GetGroupUser;

/**
 *
 * @author Aveek
 */
public class CreateGroups 
{
    int groupNo, totalGroups;
    
    public CreateGroups(int total)
    {        
        groupNo=1;
        totalGroups=total;
        formGroups();
        
    }
    
    public void formGroups()
    {
        int groupSize=0, userId;
        Random r=new Random();
        GetGroupUser ggu=new GetGroupUser();
        AddUserToGroup aug=new AddUserToGroup();
        
        for(int i=0;i<totalGroups;i++)
        {
            do
            {
                groupSize=r.nextInt(21);
            }while(groupSize<2);
            
            for(int j=0;j<groupSize;j++)
            {
                userId=ggu.getValidUserId();                
                aug.addUser(userId,groupNo);
                
            }
            groupNo++;
        }
    }
          
    public static void main(String args[])
    {
        CreateGroups cg=new CreateGroups(100);
    }
}
