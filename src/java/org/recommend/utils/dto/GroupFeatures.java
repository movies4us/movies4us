/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.recommend.utils.dto;

import java.util.ArrayList;
import org.recommend.utils.qo.MovieActors;
import org.recommend.utils.qo.MovieDirectors;
import org.recommend.utils.qo.MovieLanguages;

/**
 *
 * @author Aveek
 */
public class GroupFeatures 
{
    int groupID;
    ArrayList groupMovies, groupActorSet, groupDirectorSet, groupLanguageSet;
    
    public GroupFeatures(ArrayList movies)
    {
        groupMovies=movies;
        groupActorSet=new ArrayList();
        groupDirectorSet=new ArrayList();
        groupLanguageSet=new ArrayList();
    }
    
    public ArrayList union(ArrayList groupSet, ArrayList featureSet)
    {
        ArrayList groupFeatureSet=groupSet;
                
        for(int i=0;i<featureSet.size();i++)
            groupFeatureSet.add(featureSet.get(i));
        return groupFeatureSet;
    }
    
    public ArrayList getGroupActorSet()
    {
        MovieActors ma=new MovieActors();
        ArrayList actors;
        for(int i=0;i<groupMovies.size();i++)
        {
            actors=ma.getMovieActors((Integer)groupMovies.get(i));
            groupActorSet=union(groupActorSet, actors);                        
        }
        return groupActorSet;
    }
    
    public void printActors()
    {
        String output="";
        for(int i=0;i<groupActorSet.size();i++)
            output+=groupActorSet.get(i)+", ";
        System.out.println(output);
    }
    
    public void printDirectors()
    {
        String output="";
        for(int i=0;i<groupDirectorSet.size();i++)
            output+=groupDirectorSet.get(i)+", ";
        System.out.println(output);
    }
    
    public ArrayList getGroupDirectorSet()
    {
        MovieDirectors md=new MovieDirectors();
        ArrayList directors;
        for(int i=0;i<groupMovies.size();i++)
        {
            directors=md.getMovieDirectors((Integer)groupMovies.get(i));
            groupDirectorSet=union(groupDirectorSet, directors);              
        }
        return groupDirectorSet;
    }
    
    public ArrayList getGroupLanguageSet()
    {
        MovieLanguages ml=new MovieLanguages();
        ArrayList languages;
        for(int i=0;i<groupMovies.size();i++)
        {
            languages=ml.getMovieLanguages((Integer)groupMovies.get(i));
            groupLanguageSet=union(groupLanguageSet, languages);                                    
        }
        return groupLanguageSet;
    }
    
    public void printLanguages()
    {
        String output="";
        for(int i=0;i<groupLanguageSet.size();i++)
            output+=groupLanguageSet.get(i)+", ";
        System.out.println(output);
    }
}
