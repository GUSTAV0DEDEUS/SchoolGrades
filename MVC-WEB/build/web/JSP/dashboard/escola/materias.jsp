<%-- 
    Document   : materias
    Created on : May 2, 2024, 7:53:27 AM
    Author     : samuel
--%>

<%@page import="model.bean.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.bean.Disciplina" %>
<%@ page import="controller.ControllerEscola" %>
<%
    ControllerEscola controller = new ControllerEscola();
    HttpSession httpSession = request.getSession();
    Usuario user = (Usuario) httpSession.getAttribute("userOn");
    int idEscola = user.getIdUsuario();
    List<Disciplina> disciplinas = controller.listarDisciplinas(idEscola);
    boolean isEmpty = disciplinas.isEmpty();
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Disciplinas</title>
        <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    </head>
    <body class="bg-gray-100">
        <nav class="bg-white shadow-md">
            <div class="container mx-auto px-4">
                <div class="flex justify-between items-center py-4">
                    <a href="../fecharSession.jsp" class="text-3xl text-center">Dashboard Escola</a>
                    <div class="flex space-x-4">
                        <a href="#"> 
                            <img src="../../../imgs/default.jpg" alt="Avatar" class="rounded-full h-12 w-12 ">
                        </a>
                    </div>
                </div>
            </div>
        </nav>
        <main class="container mx-auto px-4 py-8">
            <div class="bg-white shadow-md rounded-lg p-8">
                <h1 class="text-2xl font-bold mb-4">Lista de Disciplinas</h1>
                <div class="mb-4">
                    <a href="criarMateria.jsp" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Criar Disciplina</a>
                </div>
                <% if (!isEmpty) { %>
                <div class="overflow-x-auto">
                    <table class="min-w-full table-auto">
                        <thead>
                            <tr class="bg-gray-200">
                                <th class="px-4 py-2">Nome da Disciplina</th>
                                <th class="px-4 py-2">Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Disciplina disciplina : disciplinas) {%>
                            <tr>
                                <td class="border px-4 py-2"><%= disciplina.getNomeDisciplina()%></td>
                                <td class="border px-4 py-2 flex justify-center space-x-2">
                                    <a href="alterarMateria.jsp?idDisciplina=<%= disciplina.getIdDisciplina()%>" class="bg-yellow-500 hover:bg-yellow-700 text-white font-bold py-2 px-4 rounded">Alterar</a>
                                    <a href="deletarMateria.jsp?idDisciplina=<%= disciplina.getIdDisciplina()%>" class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded">Deletar</a>
                                </td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
                <% } else { %>
                <p>Não há disciplinas cadastradas.</p>
                <% }%>
            </div>
        </main>
    </body>
</html>
