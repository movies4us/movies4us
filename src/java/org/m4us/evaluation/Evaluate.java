/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.evaluation;

import java.util.ArrayList;

/**
 *
 * @author Aveek
 */
public class Evaluate 
{
    ArrayList feedback;
    
    public Evaluate(ArrayList feedbackObjects)
    {
        feedback=feedbackObjects;
    }
    
    public double getRMSE()
    {
        double RMSE=0;
        int error;
        for(int i=0;i<feedback.size();i++)
        {
            FeedbackObject fObj=(FeedbackObject)feedback.get(i);
            error=fObj.getGroupRank()-fObj.getRecommendedRank();
            RMSE+=error*error;
        }
        RMSE=RMSE/feedback.size();
        RMSE=Math.sqrt(RMSE);
        return RMSE;
    }
    
    public double getRankScore()
    {
        double rankScoreP=0, rankScoreMax=0;
        for(int i=0;i<feedback.size();i++)
        {
            FeedbackObject fObj=(FeedbackObject)feedback.get(i);
            rankScoreMax+=Math.pow(2,1-i);
            if(fObj.getGroupRating()>=3)
                rankScoreP+=Math.pow(2,1-fObj.getGroupRank());
        }
        
        return rankScoreP/rankScoreMax;
    }
    
    public double getNDCGScore()
    {
        double NDCGScore=0;
        double discount;
        double gainP[]=new double[feedback.size()];
        double sortedRating[]=new double[feedback.size()];
        double maxGainP[]=new double[feedback.size()];
        double NDCG[]=new double[feedback.size()];        
        
        for(int i=0;i<feedback.size();i++)
        {
            FeedbackObject fObj=(FeedbackObject)feedback.get(i);
            sortedRating[i]=fObj.getGroupRating();
        }
        
        for(int i=0;i<feedback.size();i++)
            for(int j=0;j<feedback.size()-i-1;j++)
                if(sortedRating[j]<sortedRating[j+1])
                {
                    sortedRating[j]=sortedRating[j+1];                    
                }
        
        for(int i=0;i<feedback.size();i++)
        {
            FeedbackObject fObj=(FeedbackObject)feedback.get(i);
            gainP[i]=Math.pow(2,Math.ceil(fObj.getGroupRating()))-1;
            discount=Math.log(2)/Math.log(1+fObj.getRecommendedRank());
            gainP[i]*=discount;
            
            maxGainP[i]=Math.pow(2,Math.ceil(sortedRating[i]))-1;            
            maxGainP[i]*=discount;
            
            for(int j=i;j>0;j--)
            {                            
                gainP[i]+=gainP[j];
                maxGainP[i]+=maxGainP[j];
            }
            NDCG[i]=gainP[i]/maxGainP[i];
            NDCGScore+=NDCG[i];
        }
        
        return NDCGScore/feedback.size();
    }
}
