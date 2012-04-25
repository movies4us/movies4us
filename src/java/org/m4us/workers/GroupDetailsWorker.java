/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.workers;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.m4us.movielens.utils.ConnectionManager;
import org.m4us.movielens.utils.dto.DataTransferObject;
import org.m4us.movielens.utils.dto.MoviesTableObject;
import org.m4us.movielens.utils.qo.MoviesTableListQueryObject;
import org.recommend.utils.RecommendMovies;

/**
 *
 * @author arka
 */
public class GroupDetailsWorker {
    public List<DataTransferObject> getRecommendedMovies(int groupId){
        List recommendationList = RecommendMovies.getGroupRecommendations(groupId);
        Connection conn = ConnectionManager.getConnection();
        List<DataTransferObject> movieDetailsList = new MoviesTableListQueryObject().
                retrieveMovieDetailsList(recommendationList, conn);
        HashMap<Integer,MoviesTableObject> moviesMap = new HashMap<Integer, MoviesTableObject>();
        List<DataTransferObject> sortedMoviesList = new ArrayList<DataTransferObject>();
        for(DataTransferObject obj:movieDetailsList){
            moviesMap.put(((MoviesTableObject)obj).getMovieId(), (MoviesTableObject)obj);
        }
        for(int i=0;i<recommendationList.size();i++){
            int movieId = (Integer)recommendationList.get(i);
            sortedMoviesList.add(moviesMap.get(movieId));
        }
        return sortedMoviesList;
    }
}
