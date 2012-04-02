/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.recommend.utils;

import java.util.ArrayList;
import org.recommend.utils.dto.ChampionList;
import org.recommend.utils.dto.GroupProfile;
import org.recommend.utils.qo.GetUserList;

/**
 *
 * @author Aveek
 */
public class RecommendMovies 
{
    int groupID;
    ArrayList users, championList;
    double groupProfile[];
    
    RecommendMovies(int id)
    {        
        groupID=id;
        System.out.println("Getting user list.....");
        GetUserList userList=new GetUserList(groupID);                
        users=userList.getUsersList();
        
        System.out.println("Creating group profile......");
        GroupProfile profile=new GroupProfile(users);
        groupProfile=profile.getGroupProfile();
        
        System.out.println("Creating champion list....");
        ChampionList cl=new ChampionList(groupID);
        championList=cl.getChampionList();
                
    }
    
    public static void main(String ags[])
    {
        RecommendMovies rm=new RecommendMovies(1);
    }
}
