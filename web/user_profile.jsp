<%-- 
    Document   : user_profile
    Created on : Apr 15, 2012, 3:11:32 PM
    Author     : arka
--%>

<%@page import="org.m4us.movielens.utils.dto.MoviesRatingsComposite"%>
<%@page import="org.m4us.movielens.utils.dto.MoviesTableObject"%>
<%@page import="org.m4us.movielens.utils.dto.GroupsTableObject"%>
<%@page import="org.m4us.movielens.utils.dto.DataTransferObject"%>
<%@page import="java.util.List"%>
<%@page import="org.m4us.controller.FlowContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="movies4us.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <%FlowContext flowCtx = (FlowContext)request.getAttribute("flowContext");%>
        <table>
            <tr><td>    
        <h1>Rate Some Movies</h1>
        <form name ="movieSearchForm" method="POST" action="FlowManagerServlet">
        <input type="text" name ="movieSearch" maxlength="50">
        <%
        session.setAttribute("flowCtx", flowCtx);
        %>
        <input type="submit" name ="action.movie.search" value="Search">
        </form>
        </td>
        <td>
        <h4>Create a new Group</h4>
        <form name ="createGroupForm" method="POST" action="FlowManagerServlet">
        <input type="text" name ="groupName" maxlength="20">        
        <input type="submit" name ="action.create.group.submit" value="Create Group">
        </form>
        </td>
            <tr><td>
                    <%if(flowCtx.get("similarMoviesList")!=null){
        %>
        <h2>Rate Movies</h2>
        <form name="ratingForm" method="POST" action="FlowManagerServlet">
        <%
        
        List<DataTransferObject> similarMoviesList = (List<DataTransferObject>)flowCtx.get("similarMoviesList");
        for(DataTransferObject object : similarMoviesList){
                MoviesRatingsComposite movieObj = (MoviesRatingsComposite)object;
        %>
        <h4><%=movieObj.getMovieObj().getMovieName()%>,
            <%=movieObj.getMovieObj().getReleaseYear()%>
            <input type="text" maxlength="4" name="movieId<%=movieObj.getRatingsObj().getMovieId()%>" value="<%=movieObj.getRatingsObj().getRating()%>">
        </h4>
        <%
        }%>
        <input type ="submit" name="action.user.ratings.submit" value="Submit Ratings">
        </form>
        <%}
        %>
                </td>
                <td>
        <h2>Groups List</h2>
        <%
            
            List<DataTransferObject> groupList = (List<DataTransferObject>)flowCtx.get("UserGroupsList");
            for(DataTransferObject object : groupList){
                GroupsTableObject groupObj = (GroupsTableObject)object;%>
                <h4>
                    <a href="FlowManagerServlet?linkAction=action.group.recommendation
                       &groupId=<%=groupObj.getGroupId()%>" >
                        <%=groupObj.getGroupName()%></a>
                </h4><br/>
        <%}%>
                </td></tr>
            <tr><td></td><td>
        <h2>Available Groups List</h2>
        <%
            
            List<DataTransferObject> avaibleGroupList = (List<DataTransferObject>)flowCtx.get("AvailableGroupsList");
            for(DataTransferObject object : avaibleGroupList){
                GroupsTableObject availableGroupObj = (GroupsTableObject)object;%>
                <h4>
                    <a href="FlowManagerServlet?linkAction=action.group.join
                       &groupId=<%=availableGroupObj.getGroupId()%>" >
                        <%=availableGroupObj.getGroupName()%></a>
                </h4><br/>
        <%}%>
                </td></tr>
            <tr><td>
        <form name="logoutForm" method="POST" action="FlowManagerServlet">
            <input type ="submit" name="action.user.logout" value="Logout">
        </form>
                </td><td></td></tr>
        </table>    
    </body>
</html>
