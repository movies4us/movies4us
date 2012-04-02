/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.recommend.utils.dto;

import java.util.ArrayList;
import org.recommend.utils.qo.SeenMovies;
import org.recommend.utils.qo.TopRankedMovies;
import org.recommend.utils.qo.TopRatedMovies;

/**
 *
 * @author Aveek
 */
public class ChampionList 
{
    ArrayList championList, topRated, topRanked, seenMovies;
    int groupID;
    static int CHAMPION_SIZE=250;
    
    public ChampionList(int id)
    {
        groupID=id;
        TopRankedMovies trm=new TopRankedMovies();        
        topRanked=trm.getTopRankedMovies();
        
        SeenMovies seen=new SeenMovies(groupID);
        seenMovies=seen.getSeenMovies();
        populateChampionList();
    }        
    
    public void populateChampionList()
    {
        championList=topRanked;
        String seen=getCurrentList();
        
        TopRatedMovies trm=new TopRatedMovies();
        int number=CHAMPION_SIZE-championList.size();
        topRated=trm.getTopRatedMovies(seen,number);      
        
        for(int i=0;i<seenMovies.size();i++)
            if(!championList.contains(seenMovies.get(i)))
                championList.add(seenMovies.get(i));        
    }
    
    public String getCurrentList()
    {
        String currentList="";        
        for(int i=0;i<seenMovies.size();i++)
            currentList+=seenMovies.get(i)+",";
        for(int i=0;i<topRanked.size();i++)
            currentList+=topRanked.get(i)+",";
        
        currentList=currentList.substring(0,currentList.length()-1);        
        
        return currentList;
    }
    
    public ArrayList getChampionList()
    {
        return championList;
    }
}
