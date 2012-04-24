/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.handlers;

import java.util.List;
import org.m4us.controller.FlowContext;
import org.m4us.movielens.utils.dto.DataTransferObject;
import org.m4us.workers.GroupDetailsWorker;
import org.recommend.utils.RecommendMovies;

/**
 *
 * @author arka
 */
public class GroupDetailsHandler implements IHandler{

    @Override
    public void handleRequest(FlowContext flowCtx) {
        Integer groupId = Integer.parseInt((String)flowCtx.get("groupId"));
        List<DataTransferObject> groupRecommendation = new GroupDetailsWorker().getRecommendedMovies(groupId);
        flowCtx.put("RecommendationList", groupRecommendation);
    }
    
}
