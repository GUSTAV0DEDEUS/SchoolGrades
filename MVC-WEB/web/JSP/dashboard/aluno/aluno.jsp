<%-- 
    Document   : aluno
    Created on : May 1, 2024, 10:04:16 PM
    Author     : samuel
--%>

<%@page import="controller.ControllerDisciplina"%>
<%@page import="model.bean.Disciplina"%>
<%@page import="model.bean.Usuario"%>
<%@page import="model.bean.Aluno"%>
<%@page import="controller.ControllerAluno"%>
<%@page import="model.bean.Boletim"%>
<%@page import="java.util.List"%>
<%-- 
    Document   : aluno
    Created on : May 1, 2024, 10:04:16 PM
    Author     : samuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="w-full">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard Aluno</title>
        <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    </head>
    <body class="bg-gray-100 h-screen">
        <header class="bg-white py-2 px-8 flex justify-between items-center">
            <a href="../fecharSession.jsp" class="text-3xl text-center">Dashboard Aluno</a>
            <div class="flex justify-center">
                <a href="#"> 
                    <img src="../../../imgs/default.jpg" alt="Avatar" class="rounded-full h-12 w-12 ">
                </a>
            </div>
        </header>
        <main class="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
            <div class="bg-white shadow-md rounded-lg p-8">

                <div class="space-y-8">
                    <h2 class="text-xl font-bold">Notas</h2>
                    <ul class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
                        <% HttpSession httpSession = request.getSession();
                            Usuario user = (Usuario) httpSession.getAttribute("userOn");
                            ControllerAluno aluCont = new ControllerAluno();
                            Aluno aluno = aluCont.buscarAlunoPorId(user.getIdUsuario());
                            ControllerDisciplina contDis = new ControllerDisciplina();
                            List<Boletim> boletins = aluCont.listarBoletinsPorIdAluno(aluno.getIdAluno());
                            for (Boletim boletim : boletins) {
                                Disciplina dis = contDis.buscarDisciplinaPorId(boletim.getIdDisciplina());
                        %>
                        <li class="bg-gray-50 border border-gray-200 p-4 rounded-lg shadow-md">
                            <div class="flex justify-between items-center">
                                <h3 class="font-semibold text-lg"><%= dis.getNomeDisciplina()%></h3>
                                <span class="text-gray-500"><%= boletim.getNota()%></span>
                            </div>
                        </li>
                        <%}%>
                    </ul>
                </div>
            </div>
        </main>

    </body>
</html>
