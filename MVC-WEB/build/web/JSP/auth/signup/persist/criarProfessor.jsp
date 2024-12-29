<%-- 
    Document   : criarProfessor
    Created on : May 1, 2024, 7:59:41 PM
    Author     : samuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.time.LocalDate"%>
<%@page import="model.bean.Professor"%>
<%@page import="model.bean.Disciplina"%>
<%@page import="controller.ControllerProfessor"%>
<%@page import="java.io.IOException"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="javax.servlet.ServletException"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="controller.ControllerDisciplina"%>


<%
    String nome = request.getParameter("nomeProfessor");
    String escolaStr = request.getParameter("idEscola");
    String disciplinaStr = request.getParameter("disciplina");

    // Verifica se todos os parâmetros necessários foram recebidos
    if (nome == null || nome.isEmpty()
            || escolaStr == null || escolaStr.isEmpty()
            || disciplinaStr == null || disciplinaStr.isEmpty()) {
        response.sendRedirect("paginaDeErro.jsp");
        return;
    }

    int idEscola = Integer.parseInt(escolaStr);
    int idDisciplina = Integer.parseInt(disciplinaStr);
    ControllerDisciplina contD = new ControllerDisciplina();
    Disciplina dis = contD.buscarDisciplinaPorId(idDisciplina);
    
    Professor professor = new Professor(idEscola, idDisciplina, nome, dis.getNomeDisciplina());

    try {
        ControllerProfessor contP = new ControllerProfessor();
        Professor professorCriado = contP.criarProfessor(professor);

        if (professorCriado != null) {
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("professor", professorCriado);

            response.sendRedirect("../signup.jsp");
        }
    } catch (SQLException | ClassNotFoundException | IOException ex) {
        Logger.getLogger(ControllerProfessor.class.getName()).log(Level.SEVERE, null, ex);
        response.sendRedirect("paginaDeErro.jsp");
    }
%>
