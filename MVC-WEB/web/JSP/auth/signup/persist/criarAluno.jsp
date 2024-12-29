<%-- 
    Document   : criarAlunno
    Created on : May 1, 2024, 7:59:17 PM
    Author     : samuel
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.time.LocalDate"%>
<%@page import="model.bean.Aluno"%>
<%@page import="java.io.IOException"%>
<%@page import="controller.ControllerAluno"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="javax.servlet.ServletException"%>
<%@page import="javax.servlet.http.HttpSession"%>

<%
    String nome = request.getParameter("nome");
    String dataNascimentoStr = request.getParameter("dataNascimento");
    String escolaStr = request.getParameter("escola");
    if (nome == null || nome.isEmpty()
            || dataNascimentoStr == null || dataNascimentoStr.isEmpty()
            || escolaStr == null || escolaStr.isEmpty()) {
        response.sendRedirect("paginaDeErro.jsp");
        return; 
    }

    LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr);
    int idEscola = Integer.parseInt(escolaStr);

    Aluno aluno = new Aluno(idEscola, nome, java.sql.Date.valueOf(dataNascimento));

    try {
        ControllerAluno contA = new ControllerAluno();
        Aluno alunoCriado = contA.criarAluno(aluno);

        if (alunoCriado != null) {
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("aluno", alunoCriado);

            response.sendRedirect("../signup.jsp");
        }
    } catch (SQLException | ClassNotFoundException | IOException ex) {
        Logger.getLogger(ControllerAluno.class.getName()).log(Level.SEVERE, null, ex);
        response.sendRedirect("paginaDeErro.jsp");
    }
%>
