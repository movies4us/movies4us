/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.recommend.utils.dto;

import java.util.ArrayList;
import org.recommend.utils.qo.UserProfile;

/**
 *
 * @author Aveek
 */
public class GroupProfile 
{
    ArrayList userList;
    double userProfiles[][];
    double groupProfile[];
    static int GENRE_COUNT=19;
    
    public GroupProfile(ArrayList user)
    {        
        userList=user;                      
        groupProfile=new double[GENRE_COUNT];
        userProfiles=new double[userList.size()][GENRE_COUNT];
        getUserProfiles();
    }
    
    public void getUserProfiles()
    {
        double profile[];
        UserProfile userProfile=new UserProfile();
        for(int i=0;i<userList.size();i++)
        {
            int id=(Integer)userList.get(i);            
            profile=userProfile.getUserProfile(id);
            
            for(int j=0;j<GENRE_COUNT;j++)            
                userProfiles[i][j]=profile[j];            
        }        
    }
    
    public void printUserProfiles()
    {
        String output="";
        double sum;
        for(int i=0;i<userList.size();i++)
        {
            sum=0;
            output+="User "+i+": ";
            for(int j=0;j<GENRE_COUNT;j++)
            {
                sum+=userProfiles[i][j];
                output+=userProfiles[i][j]+"\t";
            }
            output+="= "+sum+"\n";
        }
        System.out.println(output);
    }
    
    public void printGroupProfile()
    {
        double sum=0;
        String output="Group: ";
        String val;
        for(int i=0;i<GENRE_COUNT;i++)
        {
            val=""+groupProfile[i];
            if(val.length()>5)
                output+=val.substring(0,5)+"\t";
            else
                output+=groupProfile[i]+"\t";
            sum+=groupProfile[i];
        }
        
        System.out.println(output+sum);
    }
    
    public double[] getGroupProfile()
    {
        for(int i=0;i<GENRE_COUNT;i++)
            groupProfile[i]=0;
        
        for(int i=0;i<userList.size();i++)
            for(int j=0;j<GENRE_COUNT;j++)
                groupProfile[j]+=userProfiles[i][j];
        
        return groupProfile;
    }    
}
