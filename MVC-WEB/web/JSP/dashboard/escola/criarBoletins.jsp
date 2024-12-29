<%-- 
    Document   : boletins
    Created on : May 2, 2024, 8:16:40 AM
    Author     : samuel
--%>

<%@page import="model.bean.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <title>Criar Boletins</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
<div class="container mx-auto h-screen flex justify-center items-center">
    <div class="bg-white p-8 rounded shadow-md">
        <%
            try {
                Usuario user = (Usuario) httpSession.getAttribute("userOn");
                int idEscola = user.getIdUsuario();
                int idDisciplina = Integer.parseInt(request.getParameter("idDisciplina"));
                controller.criarBoletinsParaTodosAlunos(idEscola, idDisciplina);
        %>
                <h1 class="text-2xl font-bold mb-4">Boletins criados com sucesso!</h1>
                <p class="mb-4">Deseja voltar?</p>
                <div class="flex space-x-4">
                    <a href="escola.jsp" class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded">Voltar</a>
                </div>
        <%
            } catch (Exception e) {
        %>
                <h1 class="text-2xl font-bold mb-4">Erro ao criar boletins</h1>
                <p class="mb-4">Houve um erro ao criar os boletins. Por favor, tente novamente.</p>
                <div class="flex space-x-4">
                    <a href="escola.jsp" class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded">Voltar</a>
                </div>
        <%
            }
        %>
    </div>
</div>
</body>
</html>
