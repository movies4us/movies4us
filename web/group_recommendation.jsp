<%-- 
    Document   : group_recommendation
    Created on : Apr 23, 2012, 11:34:49 PM
    Author     : arka
--%>

<%@page import="org.m4us.movielens.utils.dto.MoviesTableObject"%>
<%@page import="org.m4us.movielens.utils.dto.DataTransferObject"%>
<%@page import="java.util.List"%>
<%@page import="org.m4us.controller.FlowContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Group Recommendation</title>
    </head>
    <body>
        <h1>Group Recommendation</h1>
        <%
        FlowContext flowCtx = (FlowContext)request.getAttribute("flowContext");
        List<DataTransferObject> recommendations = 
                (List<DataTransferObject>)flowCtx.get("RecommendationList");
        for(DataTransferObject object : recommendations){
            MoviesTableObject obj = (MoviesTableObject)object;
        %><h4><a href="FlowManagerServlet?linkAction=action.movie.select
              &movieId=<%=obj.getMovieId()%>" ><%=obj.getMovieName()%></a>
                        ,<%=obj.getReleaseYear()%>
        </h4>        
        <%
        }
        %>
    </body>
</html>
