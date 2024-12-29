<%-- 
    Document   : processarAlteracao
    Created on : May 2, 2024, 9:04:54 AM
    Author     : samuel
--%>

<%@ page import="model.bean.Usuario"%>
<%@ page import="controller.ControllerDisciplina"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="model.bean.Disciplina"%>

<%
    HttpSession httpSession = request.getSession();
    Usuario user = (Usuario) httpSession.getAttribute("userOn");
    if (user == null) {
        response.sendRedirect("../fecharSession.jsp");
        return;
    }

    String idDisciplinaStr = request.getParameter("idDisciplina");
    String idEscolaStr = Integer.toString(user.getIdUsuario());

    if (idDisciplinaStr == null || idDisciplinaStr.isEmpty() || idEscolaStr == null || idEscolaStr.isEmpty()) {
        response.sendRedirect("../fecharSession.jsp");
        return;
    }

    int idDisciplina;
    int idEscola;

    try {
        idDisciplina = Integer.parseInt(idDisciplinaStr);
        idEscola = Integer.parseInt(idEscolaStr);
    } catch (NumberFormatException e) {
        response.sendRedirect("../fecharSession.jsp");
        return;
    }

    String nomeDisciplina = request.getParameter("nomeDisciplina");
    Disciplina disciplina = new Disciplina(idDisciplina, nomeDisciplina, idEscola);

    ControllerDisciplina controller = new ControllerDisciplina();

    try {
        controller.alterarDisciplina(disciplina);
        response.sendRedirect("materias.jsp");
    } catch (SQLException e) {
        e.printStackTrace();
        response.sendRedirect("alterarMateria.jsp?idDisciplina=" + idDisciplina);
    }
%>
