/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.workers;

import java.util.List;
import org.m4us.movielens.utils.ConnectionManager;
import org.m4us.movielens.utils.dto.DataTransferObject;
import org.m4us.movielens.utils.qo.MoviesTableListQueryObject;

/**
 *
 * @author arka
 */
public class MovieSearchWorker {
    public List<DataTransferObject> getSimilarMoviesList(String searchString){
        return new MoviesTableListQueryObject().similarMoviesList(searchString, ConnectionManager.getConnection());
    }
}
