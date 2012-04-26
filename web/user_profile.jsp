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
        <%FlowContext flowCtx = (FlowContext) request.getAttribute("flowContext");%>
        <table>
            <tr><td class="leftcol">    
                    <h1>Rate Some Movies</h1>
                    <form name ="movieSearchForm" method="POST" action="FlowManagerServlet">
                        <input type="text" name ="movieSearch" maxlength="50">
                        <%
                            session.setAttribute("flowCtx", flowCtx);
                        %>
                        <input type="submit" name ="action.movie.search" value="Search">
                    </form>
                </td>
                <td class="rightcol">
                    <h1>Create a new Group</h1>
                    <form name ="createGroupForm" method="POST" action="FlowManagerServlet">
                        <input type="text" name ="groupName" maxlength="20">        
                        <input type="submit" name ="action.create.group.submit" value="Create Group">
                    </form>
                </td>
            <tr><td class="leftcol">
                    <%if (flowCtx.get("similarMoviesList") != null) {
                    %>
                    <table><tr><td class="leftpadtablesmall"></td><td>
                                <table class="groupTable"><tr>

                                        <td>
                    <h2>Rate Movies</h2></td></tr>
                    <form name="ratingForm" method="POST" action="FlowManagerServlet">
                        <%

                            List<DataTransferObject> similarMoviesList = (List<DataTransferObject>) flowCtx.get("similarMoviesList");
                            for (DataTransferObject object : similarMoviesList) {
                                MoviesRatingsComposite movieObj = (MoviesRatingsComposite) object;
                        %>
                        <tr><td align="center" width="80%"><%=movieObj.getMovieObj().getMovieName()%>,
                            <%=movieObj.getMovieObj().getReleaseYear()%>
                            </td><td align="left"><input type="text" size="4" maxlength="4" name="movieId<%=movieObj.getRatingsObj().getMovieId()%>" value="<%=movieObj.getRatingsObj().getRating()%>">
                            </td></tr></td>
                        <%
            }%>
                        <tr><td><input type ="submit" name="action.user.ratings.submit" value="Submit Ratings"></tr></td>
                    </form>
                    <%}
                    %>
                                    </table>
                </td><td class="rightpadtablesmall"></td></tr></table>
                </td>
                <td class="rightcol">
                    <table><tr><td class="leftpadtable"></td><td>
                                <table class="groupTable"><tr>

                                        <td>
                                            <h2>Groups List</h2></td></tr>
                                            <%

                                                List<DataTransferObject> groupList = (List<DataTransferObject>) flowCtx.get("UserGroupsList");
                                                for (DataTransferObject object : groupList) {
                                                    GroupsTableObject groupObj = (GroupsTableObject) object;%>
                                    <tr><td>
                                            <a href="FlowManagerServlet?linkAction=action.group.recommendation
                                               &groupId=<%=groupObj.getGroupId()%>" >
                                                <%=groupObj.getGroupName()%></a>
                                    </tr></td>
                                    <%}%>
                    </table>
                </td><td class="rightpadtable"></td></tr></table>
    </td></tr>
<tr><td></td><td class="rightcol">
<table><tr><td class="leftpadtable"></td><td>
                                <table class="groupTable"><tr>

                                        <td>
        <h2>Available Groups List</h2></td></tr>

        <%

            List<DataTransferObject> avaibleGroupList = (List<DataTransferObject>) flowCtx.get("AvailableGroupsList");
            for (DataTransferObject object : avaibleGroupList) {
                GroupsTableObject availableGroupObj = (GroupsTableObject) object;%>
        <tr><td>
            <a href="FlowManagerServlet?linkAction=action.group.join
               &groupId=<%=availableGroupObj.getGroupId()%>" >
                <%=availableGroupObj.getGroupName()%></a>
        </tr></td>
        <%}%>
    </td></tr>
                                </table>
                </td><td class="rightpadtable"></td></tr></table>
    </td></tr>
<tr><td class="leftcol">
        <form name="logoutForm" method="POST" action="FlowManagerServlet">
            <input type ="submit" name="action.user.logout" value="Logout">
        </form>
    </td><td></td></tr>
</table>    
</body>
</html>
