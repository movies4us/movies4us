/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.handlers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.m4us.controller.FlowContext;
import org.m4us.movielens.utils.dto.DataTransferObject;
import org.m4us.movielens.utils.dto.MoviesTableObject;
import org.m4us.movielens.utils.dto.RatingsTableObject;
import org.m4us.movielens.utils.dto.UserInfoTableObject;

/**
 *
 * @author arka
 */
public class UserRatingsHandler implements IHandler{

    @Override
    public void handleRequest(FlowContext flowCtx) {
        UserInfoTableObject userInfo = (UserInfoTableObject)flowCtx.get("userInfo");
        List<DataTransferObject> ratingsList = new ArrayList<DataTransferObject>();
        List<DataTransferObject> similarMoviesList = 
                (List<DataTransferObject>)flowCtx.get("similarMoviesList");
        for(DataTransferObject obj: similarMoviesList){
            MoviesTableObject movieObj = (MoviesTableObject)obj;
            String movieRatingStr;
            if((movieRatingStr=(String)flowCtx.get("movieId"+movieObj.getMovieId()))==null)
                continue;
            RatingsTableObject ratingObj = new RatingsTableObject();
            ratingObj.setMovieId(movieObj.getMovieId());
            ratingObj.setUserId(userInfo.getUserId());
            ratingObj.setRatingDate(new Timestamp(System.currentTimeMillis()));
            ratingObj.setRating(Float.parseFloat(movieRatingStr));
            ratingsList.add(ratingObj);
        }
        flowCtx.remove("similarMoviesList");
    }
    
}
