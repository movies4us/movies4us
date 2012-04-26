/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.handlers;

import java.util.ArrayList;
import org.m4us.controller.FlowContext;
import org.m4us.movielens.utils.dto.MoviesTableObject;
import org.recommend.utils.qo.RejectMovie;

/**
 *
 * @author Aveek
 */
public class MovieSelectHandler implements IHandler{
    
    @Override
    public void handleRequest(FlowContext flowCtx) 
    {
        
        Integer groupId = Integer.parseInt((String)flowCtx.get("groupId"));
        Integer movieId = Integer.parseInt((String)flowCtx.get("movieId"));
        ArrayList movieList=(ArrayList)flowCtx.get("RecommendationList");
        
        for(int i=0;i<movieList.size();i++)
        {
            MoviesTableObject obj = (MoviesTableObject)movieList.get(i);
            if(obj.getMovieId().equals(movieId))                
                break;
            else
            {
                RejectMovie rm=new RejectMovie(groupId, obj.getMovieId());
                rm.updateRejects();
            }                
        }
        
    }
}
