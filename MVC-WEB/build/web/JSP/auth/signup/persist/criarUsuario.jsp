<%-- 
    Document   : criarUsuario
    Created on : May 1, 2024, 7:59:32 PM
    Author     : samuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.bean.Usuario"%>
<%@page import="controller.ControllerUsuario"%>
<%@page import="java.io.IOException"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="javax.servlet.ServletException"%>
<%@page import="javax.servlet.http.HttpSession"%>

<%
    String tipoUsuario = null;
    int idUsuario = 0;

    HttpSession httpSession = request.getSession();

    if (httpSession.getAttribute("aluno") != null) {
        tipoUsuario = "aluno";
        idUsuario = ((model.bean.Aluno) httpSession.getAttribute("aluno")).getIdAluno();
    } 
    else if (httpSession.getAttribute("professor") != null) {
        tipoUsuario = "professor";
        idUsuario = ((model.bean.Professor) httpSession.getAttribute("professor")).getIdProfessor();
    } 
    else if (httpSession.getAttribute("escola") != null) {
        tipoUsuario = "escola";
        idUsuario = ((model.bean.Escola) httpSession.getAttribute("escola")).getIdEscola();
    } 
  
    else {
        response.sendRedirect("paginaDeErro.jsp");
        return;
    }

    String email = request.getParameter("email");
    String senha = request.getParameter("senha");
    // Verifica se o email ou a senha está vazio
    if (email == null || senha == null || email.isEmpty() || senha.isEmpty()) {
        response.sendRedirect("paginaDeErro.jsp");
        return;
    }

    Usuario usuario = new Usuario(idUsuario, tipoUsuario, email, senha);

    try {
        ControllerUsuario contU = new ControllerUsuario();
        contU.criarUsuario(usuario);
        
        response.sendRedirect("../../signin/signin.jsp");
    } 
    catch (ClassNotFoundException | SQLException | IOException ex) {
        Logger.getLogger(ControllerUsuario.class.getName()).log(Level.SEVERE, null, ex);
        response.sendRedirect("paginaDeErro.jsp");
    }
%>
