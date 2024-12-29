<%-- 
    Document   : salvarNota
    Created on : May 1, 2024, 11:49:00 PM
    Author     : samuel
--%>

<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="controller.ControllerBoletim"%>
<%@page import="model.bean.Boletim"%>
<%@ page import="controller.ControllerAluno" %>
<%@ page import="model.bean.Aluno" %>
<%@ page import="java.io.*,java.util.*,javax.servlet.*" %>

<%
    int alunoId = Integer.parseInt(request.getParameter("alunoId"));
    double novaNota = Double.parseDouble(request.getParameter("nota"));
    int disId = Integer.parseInt(request.getParameter("disId"));

    ControllerBoletim controllerBoletim = new ControllerBoletim();
    try {
        controllerBoletim.alterarNotaBoletim(alunoId, disId, novaNota);
        response.sendRedirect("professor.jsp");

    } catch (SQLException e) {
%>
<p>Erro ao processar a nota. Por favor, tente novamente.</p>
<%
        e.printStackTrace();
        response.sendRedirect("professor.jsp");
    }
%>
