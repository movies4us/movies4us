/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.group.utils.dto;

import java.util.ArrayList;
import org.group.utils.qo.GroupDetails;
import org.group.utils.qo.GroupsInformation;

/**
 *
 * @author Aveek
 */
public class UpdateGroups 
{
    ArrayList groups;
    public void update()
    {
        GroupsInformation gi=new GroupsInformation();
        groups=gi.getGroups();
        
        for(int i=0;i<groups.size();i++)
        {
            GroupDetails gd=new GroupDetails((Integer)groups.get(i));
            gd.addAdmin();
        }
    }
    
    public static void main(String args[])
    {
        UpdateGroups up=new UpdateGroups();
        up.update();
    }
}
