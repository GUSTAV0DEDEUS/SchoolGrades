<%-- 
    Document   : deletarMateria
    Created on : May 2, 2024, 8:57:49 AM
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
    <title>Delete Disciplina</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
<div class="container mx-auto h-screen flex justify-center items-center">
    <div class="bg-white p-8 rounded shadow-md">
        <%-- Verificar se o parâmetro idDisciplina foi passado --%>
        <% String idDisciplinaStr = request.getParameter("idDisciplina");
            if (idDisciplinaStr != null) {
                int idDisciplina = Integer.parseInt(idDisciplinaStr);
                try {
                    controller.excluirDisciplina(idDisciplina);
        %>
        <h1 class="text-2xl font-bold mb-4">Disciplina deletada</h1>
        <p class="mb-4">Deseja voltar?</p>
        <div class="flex space-x-4">
            <a href="escola.jsp" class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded">voltar</a>
        </div>
        <% } catch (SQLException e) {
        %>
        <h1 class="text-2xl font-bold mb-4">Disciplina não deletada</h1>
        <p class="mb-4">Houve um erro ao deletar a disciplina. Por favor, tente novamente.</p>
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
