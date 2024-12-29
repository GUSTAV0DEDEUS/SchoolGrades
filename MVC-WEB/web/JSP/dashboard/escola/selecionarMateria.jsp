<%-- 
    Document   : selecionarMateria
    Created on : May 2, 2024, 8:44:10 AM
    Author     : samuel
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.bean.Usuario"%>
<%@ page import="java.util.List" %>
<%@ page import="model.bean.Disciplina" %>
<%@ page import="controller.ControllerEscola" %>
<%
    ControllerEscola controller = new ControllerEscola();
    HttpSession httpSession = request.getSession();
    Usuario user = (Usuario) httpSession.getAttribute("userOn");
    int idEscola = user.getIdUsuario();
    List<Disciplina> disciplinas = controller.listarDisciplinas(idEscola);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Selecionar Matéria</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
<div class="container mx-auto h-screen flex justify-center items-center">
    <div class="bg-white p-8 rounded shadow-md">
        <h1 class="text-2xl font-bold mb-4">Selecione a disciplina</h1>
        <form action="criarBoletins.jsp" method="get">
            <div class="mb-4">
                <label for="idDisciplina" class="block text-gray-700 font-bold mb-2">Disciplina:</label>
                <select name="idDisciplina" id="idDisciplina" class="border border-gray-400 rounded px-4 py-2 w-full">
                    <option value="" selected disabled>Selecione a disciplina</option>
                    <% for(Disciplina disciplina : disciplinas) { %>
                        <option value="<%= disciplina.getIdDisciplina() %>"><%= disciplina.getNomeDisciplina() %></option>
                    <% } %>
                </select>
            </div>
            <div class="flex space-x-4">
                <button type="submit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Criar Boletins</button>
                <a href="escola.jsp" class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded">Cancelar</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>
