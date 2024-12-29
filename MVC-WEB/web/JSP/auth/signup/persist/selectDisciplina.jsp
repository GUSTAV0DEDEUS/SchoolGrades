<%-- 
    Document   : select disciplina
    Created on : May 1, 2024, 8:49:48 PM
    Author     : samuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.bean.Disciplina"%>
<%@page import="controller.ControllerDisciplina"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Selecionar Disciplina | Academic</title>
        <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    </head>
    <body class="bg-gray-200 h-screen flex items-center justify-center">
        <div class="max-w-md w-full bg-white rounded-lg shadow-lg p-6">
            <h1 class="text-3xl font-bold mb-6 text-center">Selecionar Disciplina</h1>
            <form action="./criarProfessor.jsp" method="post">
                <input type="hidden" id="idEscola" name="idEscola" value="<%= request.getParameter("escola")%>">
                <input type="hidden" id="nomeProfessor" name="nomeProfessor" value="<%= request.getParameter("nome")%>">
                <div class="mb-4">
                    <label for="disciplina" class="block text-gray-700">Disciplina:</label>
                    <select id="disciplina" name="disciplina" class="mt-1 p-4 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50">
                        <%
                            ControllerDisciplina contDisciplina = new ControllerDisciplina();
                            int idEscola = Integer.parseInt(request.getParameter("escola"));
                            List<Disciplina> disciplinas = contDisciplina.listarDisciplinasPorIdEscola(idEscola);
                            Iterator<Disciplina> iteratorDisciplina = disciplinas.iterator();
                            while (iteratorDisciplina.hasNext()) {
                                Disciplina disciplina = iteratorDisciplina.next();
                        %>
                        <option value="<%=disciplina.getIdDisciplina()%>"><%=disciplina.getNomeDisciplina()%></option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <div class="flex items-center justify-center">
                    <input type="submit" value="Continuar" class="bg-indigo-500 text-white px-4 py-2 rounded-md hover:bg-indigo-600 focus:outline-none focus:bg-indigo-600">
                </div>
            </form>
        </div>
    </body>
</html>
