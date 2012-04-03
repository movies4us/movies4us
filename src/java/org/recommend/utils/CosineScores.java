/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.recommend.utils;

import java.util.ArrayList;
import org.recommend.utils.qo.MovieName;
import org.recommend.utils.qo.MovieProfile;

/**
 *
 * @author Aveek
 */
public class CosineScores 
{
    ArrayList championList;
    double groupProfile[], cosineScores[];
    static int GENRE_SIZE=19;    
    
    public CosineScores(ArrayList cList, double []profile)            
    {
        championList=cList;
        groupProfile=profile;
        cosineScores=new double[cList.size()];
        
        determineCosineScores();            
    }
    
    public void determineCosineScores()
    {
        double movieProfile[];
        MovieProfile mp=new MovieProfile();
        int movieID;
        double groupUnitVector=getUnitVector(groupProfile);
        double movieUnitVector;
        double numerator, denominator;
        
        for(int i=0;i<championList.size();i++)
        {
            movieID=(Integer)championList.get(i);
            movieProfile=mp.getMovieProfile(movieID);
            movieUnitVector=getUnitVector(movieProfile);
            
            denominator=movieUnitVector*groupUnitVector;
            numerator=0;
            for(int j=0;j<GENRE_SIZE;j++)
                numerator+=movieProfile[j]*groupProfile[j];
            
            cosineScores[i]=numerator/denominator;            
        }
    }
    
    public double getUnitVector(double vector[])
    {
        double unitVector=0;                
        for(int i=0;i<GENRE_SIZE;i++)
            unitVector+=Math.pow(vector[i],2);
                
        unitVector=Math.sqrt(unitVector);        
        return unitVector;
    }
        
    public double[] getCosineScores()
    {
        return cosineScores;
    }
}
