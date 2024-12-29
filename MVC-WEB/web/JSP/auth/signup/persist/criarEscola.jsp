<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.bean.Escola"%>
<%@page import="controller.ControllerEscola"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="java.io.IOException"%>
<%@page import="java.sql.SQLException"%>
<%@page import="javax.servlet.ServletException"%>

<%
    String nome = request.getParameter("nome");
    String endereco = request.getParameter("endereco");
    String cidade = request.getParameter("cidade");
    String estado = request.getParameter("estado");
    
    if (nome == null || nome.isEmpty()
            || endereco == null || endereco.isEmpty()
            || cidade == null || cidade.isEmpty()
            || estado == null || estado.isEmpty()) {

        response.sendRedirect("paginaDeErro.jsp");
        return; 
    }

    Escola escolaEnt = new Escola(nome, endereco, cidade, estado);

    try {
        ControllerEscola contE = new ControllerEscola();
        Escola escolaCriada = contE.criarEscola(escolaEnt);

        if (escolaCriada != null) {
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("escola", escolaCriada);

            response.sendRedirect("../signup.jsp");
        } else {
            response.sendRedirect("paginaDeErro.jsp");
        }

    } catch (SQLException | ClassNotFoundException | IOException e) {
        e.printStackTrace();
        response.sendRedirect("paginaDeErro.jsp");
    }
%>
