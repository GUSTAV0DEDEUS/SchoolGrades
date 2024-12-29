<%-- 
    Document   : deleteProfessor
    Created on : May 2, 2024, 8:00:13 AM
    Author     : samuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="controller.ControllerEscola"%>
<%@page import="java.sql.SQLException"%>
<%@page import="model.bean.Usuario"%>

<%
    String idProfessorStr = request.getParameter("idProfessor");
    if (idProfessorStr == null || idProfessorStr.isEmpty()) {
        response.sendRedirect("paginaDeErro.jsp");
        return;
    }      

    int idProfessor = Integer.parseInt(idProfessorStr);

    ControllerEscola controller = new ControllerEscola();

    try {
        controller.deletarProfessor(idProfessor);
        response.sendRedirect("escola.jsp");
    } catch (SQLException e) {
        e.printStackTrace();
        response.sendRedirect("paginaDeErro.jsp");
    }
%>
