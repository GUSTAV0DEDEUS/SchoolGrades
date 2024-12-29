<%-- 
    Document   : editarNota
    Created on : May 1, 2024, 11:36:58 PM
    Author     : samuel
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="controller.ControllerAluno"%>
<%@page import="model.bean.Aluno"%>
<%@page import="controller.ControllerBoletim"%>
<%@page import="model.bean.Usuario"%>
<%@ page import="controller.ControllerProfessor" %>
<%@ page import="model.bean.Professor" %>
<%
    String alunoId = request.getParameter("idAluno");
    String disId = request.getParameter("idDisciplina");
    ControllerAluno controllerAluno = new ControllerAluno();
    Aluno aluno = controllerAluno.buscarAlunoPorId(Integer.parseInt(alunoId));
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Editar Nota</title>
        <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    </head>
    <body class="bg-gray-100 font-sans">
        <nav class="bg-white shadow-md">
            <div class="container mx-auto px-4">
                <div class="flex justify-between items-center py-4">
                    <a href="../fecharSession.jsp" class="text-3xl text-center">Dashboard Professor</a>
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
                <h2 class="text-xl font-semibold mb-4">Editar Nota do Aluno <%= aluno.getNomeAluno()%></h2>
                <form action="processarNota.jsp" method="post">
                    <input type="hidden" name="alunoId" value="<%= alunoId%>">
                    <input type="hidden" name="disId" value="<%= disId%>">
                    <div class="mb-4">
                        <label class="block text-gray-700 text-sm font-bold mb-2" for="nota">Nota:</label>
                        <input step="any" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="nota" name="nota" type="number" min="0" max="10"  placeholder="Insira a nova nota">
                    </div>
                    <button type="submit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mt-4">Salvar Nota</button>
                </form>
            </div>
        </main>
    </body>
</html>
