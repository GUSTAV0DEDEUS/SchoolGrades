<%-- 
    Document   : alterar
    Created on : May 1, 2024, 11:17:07 PM
    Author     : samuel
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="controller.ControllerDisciplina"%>
<%@page import="java.sql.SQLException"%>
<%@page import="model.bean.Disciplina"%>

<%
    ControllerDisciplina controller = new ControllerDisciplina();
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Alterar Disciplina</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
<div class="container mx-auto h-screen flex justify-center items-center">
    <div class="bg-white p-8 rounded shadow-md">
        <% String idDisciplinaStr = request.getParameter("idDisciplina");
            if (idDisciplinaStr != null) {
                int idDisciplina = Integer.parseInt(idDisciplinaStr);
                try {
                    Disciplina disciplina = controller.buscarDisciplinaPorId(idDisciplina);
        %>
        <h1 class="text-2xl font-bold mb-4">Alterar Disciplina</h1>
        <form action="processarAlteracao.jsp" method="post">
            <input type="hidden" name="idDisciplina" value="<%= disciplina.getIdDisciplina()%>">
            <div class="mb-4">
                <label for="nomeDisciplina" class="block text-gray-700 font-bold mb-2">Nome da Disciplina:</label>
                <input type="text" id="nomeDisciplina" name="nomeDisciplina" value="<%= disciplina.getNomeDisciplina() %>" class="border border-gray-400 rounded px-4 py-2 w-full">
            </div>
            <div class="flex space-x-4">
                <button type="submit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Salvar</button>
                <a href="escola.jsp" class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded">Cancelar</a>
            </div>
        </form>
        <% } catch (SQLException e) {
        %>
        <h1 class="text-2xl font-bold mb-4">Erro ao buscar disciplina</h1>
        <p class="mb-4">Houve um erro ao buscar a disciplina. Por favor, tente novamente.</p>
        <div class="flex space-x-4">
            <a href="escola.jsp" class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded">voltar</a>
        </div>
        <%
            }

        } else { %>
        <p class="text-red-500">Erro: ID da disciplina não especificado.</p>
        <% }%>
    </div>
</div>
</body>
</html>
