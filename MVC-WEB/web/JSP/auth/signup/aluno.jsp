<%-- 
    Document   : aluno
    Created on : May 1, 2024, 7:06:55 PM
    Author     : samuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.bean.Escola"%>
<%@page import="controller.ControllerEscola"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Aluno | Academic</title>
        <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    </head>
    <body class="bg-gray-200 h-screen flex items-center justify-center">
        <div class="max-w-md w-full bg-white rounded-lg shadow-lg p-6">
            <h1 class="text-3xl font-bold mb-6 text-center">Criar Aluno</h1>
            <form action="./persist/criarAluno.jsp" method="post">
                <div class="mb-4">
                    <label for="nome" class="block text-gray-700">Nome:</label>
                    <input type="text" id="nome" name="nome" class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50">
                </div>
                <div class="mb-4">
                    <label for="dataNascimento" class="block text-gray-700">Data de Nascimento:</label>
                    <input type="date" id="dataNascimento" name="dataNascimento" class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50">
                </div>
                <div class="mb-4">
                    <label for="escola" class="block text-gray-700">Escola:</label>
                    <select id="escola" name="escola" class="mt-1 p-4 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50">
                        <%
                            ControllerEscola cont = new ControllerEscola();
                            List<Escola> escolas = cont.listarTodasEscolas();
                            Iterator<Escola> iterator = escolas.iterator();
                            while (iterator.hasNext()) {
                                Escola escola = iterator.next();
                        %>
                        <option value="<%=escola.getIdEscola()%>"><%=escola.getNomeEscola()%></option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <div class="flex items-center justify-center">
                    <input type="submit" value="Criar Aluno" class="bg-indigo-500 text-white px-4 py-2 rounded-md hover:bg-indigo-600 focus:outline-none focus:bg-indigo-600">
                </div>
            </form>
        </div>
    </body>
</html>
