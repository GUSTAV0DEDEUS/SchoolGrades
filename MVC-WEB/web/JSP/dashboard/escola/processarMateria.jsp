<%-- 
    Document   : processarMateria
    Created on : May 2, 2024, 9:24:40 AM
    Author     : samuel
--%>

<%@page import="model.bean.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="controller.ControllerDisciplina"%>
<%@page import="java.sql.SQLException"%>
<%@page import="model.bean.Disciplina"%>
<%@page import="java.io.IOException" %>

<%
    HttpSession httpSession = request.getSession();
    Usuario user = (Usuario) httpSession.getAttribute("userOn");
    int idEscola = user.getIdUsuario();
    String nomeDisciplina = request.getParameter("nomeDisciplina");

    if (nomeDisciplina != null && !nomeDisciplina.isEmpty()) {
        Disciplina disciplina = new Disciplina(nomeDisciplina, idEscola);
        ControllerDisciplina controller = new ControllerDisciplina();

        try {
            controller.criarDisciplina(disciplina);

            response.sendRedirect("materias.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("criarMateria.jsp");
        }
    } else {
        response.sendRedirect("criarMateria.jsp?error=true");
    }
%>
