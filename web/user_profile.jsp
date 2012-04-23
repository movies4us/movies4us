<%-- 
    Document   : user_profile
    Created on : Apr 15, 2012, 3:11:32 PM
    Author     : arka
--%>

<%@page import="org.m4us.movielens.utils.dto.GroupsTableObject"%>
<%@page import="org.m4us.movielens.utils.dto.DataTransferObject"%>
<%@page import="java.util.List"%>
<%@page import="org.m4us.controller.FlowContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Groups List</h1>
        <%
            FlowContext flowCtx = (FlowContext)request.getAttribute("flowContext");
            List<DataTransferObject> groupList = (List<DataTransferObject>)flowCtx.get("UserGroupsList");
            for(DataTransferObject object : groupList){
                GroupsTableObject groupObj = (GroupsTableObject)object;%>
                <h2>
                    <a href="FlowManagerServlet?linkAction=action.group.recommendation
                       &groupId=<%=groupObj.getGroupName()%>" >
                        <%=groupObj.getGroupName()%></a>
                </h2><br/>
        <%}%>
        
    </body>
</html>
