/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.recommend.utils;

import java.util.ArrayList;
import org.recommend.utils.dto.ChampionList;
import org.recommend.utils.dto.GroupFeatures;
import org.recommend.utils.dto.GroupProfile;
import org.recommend.utils.qo.GetUserList;
import org.recommend.utils.qo.GroupMovies;
import org.recommend.utils.qo.MovieName;

/**
 *
 * @author Aveek
 */
public class RecommendMovies 
{
    int groupID;
    ArrayList users, championList, groupMovies;
    ArrayList championActorSet, championDirectorSet, championLanguageSet;
    ArrayList groupActorSet, groupDirectorSet, groupLanguageSet;
    double groupProfile[];
    double cosineScores[], jaccardScores[], totalScores[];
    static int RESULT_SIZE=10;
    ArrayList movieDetails;
    
    RecommendMovies(int id)
    {        
        groupID=id;
        System.out.println("Getting user list.....");
        GetUserList userList=new GetUserList(groupID);                
        users=userList.getUsersList();
        
        System.out.println("Getting group movies.....");
        GroupMovies gm=new GroupMovies(groupID);
        groupMovies=gm.getGroupMovies();
                
        System.out.println("Creating group profile......");
        GroupProfile profile=new GroupProfile(users);
        groupProfile=profile.getGroupProfile();         
        
        System.out.println("Creating champion list....");
        ChampionList cl=new ChampionList(groupID);
        championList=cl.getChampionList();
        
        System.out.println("Getting cosine scores....");
        CosineScores cScores=new CosineScores(championList, groupProfile);
        cosineScores=cScores.getCosineScores();                
        
        System.out.println("Getting Jaccard coefficients....");
        GroupFeatures grFeatures=new GroupFeatures(groupMovies);
        groupActorSet=grFeatures.getGroupActorSet();        
        groupDirectorSet=grFeatures.getGroupDirectorSet();
        groupLanguageSet=grFeatures.getGroupLanguageSet();
        JaccardScores jScores=new JaccardScores(championList, groupActorSet, groupDirectorSet, groupLanguageSet);
        jaccardScores=jScores.getJaccardScores();        
                
        System.out.println("Calculating total scores....");
        totalScores=new double[championList.size()];
        movieDetails=new ArrayList();
        for(int i=0;i<championList.size();i++)
        {
            totalScores[i]=cosineScores[i]+jaccardScores[i];            
        }
                
        sortTotalScore();        
        printTop(RESULT_SIZE);        
    }
    
    public void printTop(int n)
    {
        MovieName mn=new MovieName();        
        System.out.println("\nRecommended Top Movies");
        for(int i=0;i<n;i++)
        {
            System.out.println(championList.get(i)+"\t"+mn.getMovieName((Integer)championList.get(i))+"\t\t"+totalScores[i]+"\t"+cosineScores[i]+"\t"+jaccardScores[i]);
            //System.out.println(championList.get(i)+"\t"+mn.getMovieName((Integer)championList.get(i))+jaccardScores[i]);
        }
    }        
        
    public void sortTotalScore()
    {
        int id;
        double score;
        for(int i=0;i<championList.size();i++)
            for(int j=0;j<championList.size()-i-1;j++)            
                if(totalScores[j]<totalScores[j+1])
                //if(jaccardScores[j]<jaccardScores[j+1])
                {
                    score=totalScores[j];
                    totalScores[j]=totalScores[j+1];
                    totalScores[j+1]=score;
                    
                    score=cosineScores[j];
                    cosineScores[j]=cosineScores[j+1];
                    cosineScores[j+1]=score;
                    
                    score=jaccardScores[j];
                    jaccardScores[j]=jaccardScores[j+1];
                    jaccardScores[j+1]=score;
                    
                    id=(Integer)championList.get(j);
                    championList.add(j,championList.get(j+1));
                    championList.remove(j+1);
                    championList.remove(j+1);
                    championList.add(j+1,id);
                }            
    }
    
    private class MovieDetails
    {
        int movieID;
        double cosScore, jaccScore, totScore;
        String name;
        
        public MovieDetails(int id, double cScore, double jScore, double tScore)                
        {
            movieID=id;
            cosScore=cScore;
            jaccScore=jScore;
            totScore=tScore;
            MovieName mn=new MovieName();
            name=mn.getMovieName(movieID);
        }
        
        public int getID()
        {
            return movieID;
        }
        
        public double getCosineScore()
        {
            return cosScore;
        }
        
        public double getJaccardScore()
        {
            return jaccScore;
        }
        
        public double getTotalScore()
        {
            return totScore;
        }
        
        public String getName()
        {
            return name;
        }
    }
    
    public static void main(String ags[])
    {
        new RecommendMovies(1);
    }
}
