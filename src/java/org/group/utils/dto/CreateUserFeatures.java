/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.group.utils.dto;

import java.util.ArrayList;
import org.group.utils.qo.UserFeatures;
import org.group.utils.qo.UserList;

/**
 *
 * @author Aveek
 */
public class CreateUserFeatures 
{
     ArrayList userList, actors, languages, directors;
     
     public void generateUserFeatures()
     {
         UserList uid=new UserList();
         userList=uid.getUserList();
         
         for(int i=0;i<userList.size();i++)
         {
             if((Integer)userList.get(i)>3817)
             {
                System.out.println((Integer)userList.get(i));             
                UserFeatures uf=new UserFeatures();
                actors=uf.getActors((Integer)userList.get(i));

                for(int j=0;j<actors.size();j++)
                {                 
                    uf.addActor((Integer)userList.get(i), (String)actors.get(j));   
                    if(j>14000)
                        break;
                }             
                actors=null;

                directors=uf.getDirectors((Integer)userList.get(i));             

                for(int j=0;j<directors.size();j++)
                {
                    uf.addDirector((Integer)userList.get(i), (String)directors.get(j));                                  
                }
                directors=null;

                languages=uf.getLanguages((Integer)userList.get(i));             
                for(int j=0;j<languages.size();j++)
                {
                    uf.addLanguage((Integer)userList.get(i), (String)languages.get(j));                 
                }
                languages=null;
             }
         }
     }
     
     public static void main(String args[])
    {
        CreateUserFeatures cuf=new CreateUserFeatures();
        cuf.generateUserFeatures();        
    }
}
