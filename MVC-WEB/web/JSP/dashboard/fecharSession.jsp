<%-- 
    Document   : fecharSession
    Created on : May 1, 2024, 11:14:59 PM
    Author     : samuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    session.invalidate();
    response.sendRedirect("../auth/signin/signin.jsp");
%>
