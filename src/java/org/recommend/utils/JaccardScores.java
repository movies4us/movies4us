/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.recommend.utils;

import java.util.ArrayList;
import org.recommend.utils.qo.MovieActors;
import org.recommend.utils.qo.MovieDirectors;
import org.recommend.utils.qo.MovieLanguages;
import org.recommend.utils.qo.MovieName;

/**
 *
 * @author Aveek
 */
public class JaccardScores 
{
    ArrayList championList, groupActorSet, groupDirectorSet, groupLanguageSet;
    double jaccardScores[];
    
    public JaccardScores(ArrayList champList, ArrayList actorSet, ArrayList directorSet, ArrayList languageSet)
    {
        championList=champList;
        groupActorSet=actorSet;
        groupDirectorSet=directorSet;
        groupLanguageSet=languageSet;
        jaccardScores=new double[championList.size()];
        
        determineJaccardScores();
    }
    
    public void determineJaccardScores()
    {
        ArrayList movieActors, movieDirectors, movieLanguages;
        MovieActors ma=new MovieActors();
        MovieDirectors md=new MovieDirectors();
        MovieLanguages ml=new MovieLanguages();
        
        for(int i=0;i<championList.size();i++)
        {
            movieActors=ma.getMovieActors((Integer)championList.get(i));
            movieDirectors=md.getMovieDirectors((Integer)championList.get(i));
            movieLanguages=ml.getMovieLanguages((Integer)championList.get(i));
            
            jaccardScores[i]=getJaccardScore(movieActors, movieDirectors, movieLanguages);
        }
    }
    
    public double getJaccardScore(ArrayList movieActors, ArrayList movieDirectors, ArrayList movieLanguages)
    {
        double score, directorsScore, actorsScore, languageScore;
        
        actorsScore=getScore(movieActors, groupActorSet);
        directorsScore=getScore(movieDirectors, groupDirectorSet);
        languageScore=getScore(movieLanguages, groupLanguageSet);
        score= (0.45*actorsScore)+(0.35*directorsScore)+(0.2*languageScore);
        
        return score;
    }
    
    public double getScore(ArrayList movieFeature, ArrayList groupFeature)
    {
        double score, numerator=0, denominator;
        denominator=movieFeature.size()+groupFeature.size();
        int index;
        String feature;
        for(int i=0;i<movieFeature.size();i++)
        {
            feature=(String)movieFeature.get(i);
            index=groupFeature.indexOf(feature);
            if(index>=0)
            {
                numerator++;
                groupFeature.remove(index);                
            }
        }
        score=numerator/denominator;
        return score;
    }
    
    public double[] getJaccardScores()            
    {
        return jaccardScores;
    }
    
    
}
