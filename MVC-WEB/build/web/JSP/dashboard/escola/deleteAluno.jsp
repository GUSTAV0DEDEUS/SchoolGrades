<%-- 
    Document   : deleteAluno
    Created on : May 2, 2024, 8:00:03 AM
    Author     : samuel
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.bean.Usuario"%>
<%@page import="model.bean.Aluno"%>
<%@page import="java.util.List"%>
<%@page import="controller.ControllerEscola"%>

<%
    ControllerEscola controller = new ControllerEscola();
    HttpSession httpSession = request.getSession();
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Deletar Aluno</title>
        <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    </head>
    <body class="bg-gray-100">
        <div class="container mx-auto h-screen flex justify-center items-center">
            <div class="bg-white p-8 rounded shadow-md">
                <%-- Verificar se o parâmetro idAluno foi passado --%>
                <% String idAlunoStr = request.getParameter("idAluno");
                    if (idAlunoStr != null) {
                        int idAluno = Integer.parseInt(idAlunoStr);
                        try {
                            controller.deletarAluno(idAluno);

                %>
                <h1 class="text-2xl font-bold mb-4">Aluno deletado</h1>
                <p class="mb-4">Deseja voltar?</p>
                <div class="flex space-x-4">
                    <a href="escola.jsp" class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded">voltar</a>
                </div>
                <% } catch (Exception e) {
                %>
                <h1 class="text-2xl font-bold mb-4">Aluno nao deletado</h1>
                <p class="mb-4">O aluno possui boletins abertos</p>
                <div class="flex space-x-4">
                    <a href="escola.jsp" class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded">voltar</a>
                </div>
                <%
                    }

                } else { %>
                <p class="text-red-500">Erro: ID do aluno não especificado.</p>
                <% }%>
            </div>
        </div>
    </body>
</html>
