/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.handlers;

import org.m4us.controller.FlowContext;
import org.m4us.workers.MovieSearchWorker;

/**
 *
 * @author arka
 */
public class MovieSearchHandler implements IHandler{

    @Override
    public void handleRequest(FlowContext flowCtx) {
        String searchString = (String)flowCtx.get("movieSearch");
        flowCtx.put("similarMoviesList", new MovieSearchWorker().getSimilarMoviesList(searchString));
    }
    
}
