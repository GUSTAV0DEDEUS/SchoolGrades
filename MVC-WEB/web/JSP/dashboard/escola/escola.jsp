<%-- 
    Document   : escola
    Created on : May 1, 2024, 10:04:25 PM
    Author     : samuel
--%>

<%@page import="model.bean.Usuario"%>
<%@page import="model.bean.Aluno"%>
<%@page import="model.bean.Professor"%>
<%@page import="java.util.List"%>
<%@page import="controller.ControllerEscola"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    ControllerEscola controller = new ControllerEscola();
    HttpSession httpSession = request.getSession();
    Usuario user = (Usuario) httpSession.getAttribute("userOn");
%>
<!DOCTYPE html>
<html class="w-full">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard Escola</title>
        <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    </head>
    <body class="bg-gray-100 font-sans">
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
                <h2 class="text-xl font-semibold mb-4">Ações</h2>
                <div class="flex space-x-4 mb-8">
                    <a href="./selecionarMateria.jsp" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Criar Boletins</a>
                    <a href="./materias.jsp" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Matérias</a>
                </div>

                <div class="flex space-x-8">
                    <div class="w-1/2">
                        <h2 class="text-xl font-semibold mb-4">Lista de Alunos</h2>
                        <div class="overflow-x-auto">
                            <table class="min-w-full table-auto">
                                <thead>
                                    <tr class="bg-gray-200">
                                        <th class="px-4 py-2">ID Aluno</th>
                                        <th class="px-4 py-2">Nome Aluno</th>
                                        <th class="px-4 py-2">Ação</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%                                    try {
                                            List<Aluno> alunos = controller.listarAlunos(user.getIdUsuario());
                                            for (Aluno aluno : alunos) {
                                    %>
                                    <tr>
                                        <td class="border px-4 py-2"><%= aluno.getIdAluno()%></td>
                                        <td class="border px-4 py-2"><%= aluno.getNomeAluno()%></td>
                                        <td class="border px-4 py-2 text-center">
                                            <a href="./deleteAluno.jsp?idAluno=<%= aluno.getIdAluno()%>" class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded">Deletar</a>
                                        </td>
                                    </tr>
                                    <%
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <div class="w-1/2">
                        <h2 class="text-xl font-semibold mb-4">Lista de Professores</h2>
                        <div class="overflow-x-auto">
                            <table class="min-w-full table-auto">
                                <thead>
                                    <tr class="bg-gray-200">
                                        <th class="px-4 py-2">Nome Professor</th>
                                        <th class="px-4 py-2">Disciplina</th>
                                        <th class="px-4 py-2">Ação</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        try {
                                            List<Professor> professores = controller.listarProfessores(user.getIdUsuario());
                                            for (Professor professor : professores) {
                                    %>
                                    <tr>
                                        <td class="border px-4 py-2"><%= professor.getNomeProfessor()%></td>
                                        <td class="border px-4 py-2"><%= professor.getEspecialidade()%></td>
                                        <td class="border px-4 py-2 text-center">
                                            <a href="./deleteProfessor.jsp?idProfessor=<%=professor.getIdProfessor()%>" class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded">Deletar</a>
                                        </td>
                                    </tr>
                                    <%
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </body>
</html>