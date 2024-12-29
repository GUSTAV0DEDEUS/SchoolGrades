<%-- 
    Document   : entrar
    Created on : May 1, 2024, 9:58:50 PM
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

    HttpSession httpSession = request.getSession();

    String email = request.getParameter("email");
    String senha = request.getParameter("senha");

    if (email == null || senha == null || email.isEmpty() || senha.isEmpty()) {
        response.sendRedirect("paginaDeErro.jsp");
        return;
    }

    try {
        ControllerUsuario contU = new ControllerUsuario();
        Usuario usuario = contU.validarCredenciais(email, senha);
        
        if (usuario == null) {
            response.sendRedirect("paginaDeErro.jsp");
            return;
        }
        
        tipoUsuario = usuario.getTipoUsuario();
        httpSession.setAttribute("userOn", usuario);
        
        if ("aluno".equals(tipoUsuario)) {
            response.sendRedirect("../../../dashboard/aluno/aluno.jsp");
        } else if ("escola".equals(tipoUsuario)) {
            response.sendRedirect("../../../dashboard/escola/escola.jsp");
        } else if ("professor".equals(tipoUsuario)) {
            response.sendRedirect("../../../dashboard/professor/professor.jsp");
        } else {
            response.sendRedirect("paginaDeErro.jsp");
        }
    } catch (ClassNotFoundException | SQLException | IOException ex) {
        Logger.getLogger(ControllerUsuario.class.getName()).log(Level.SEVERE, null, ex);
        response.sendRedirect("paginaDeErro.jsp");
    }
%>
