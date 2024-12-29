<%-- 
    Document   : professor
    Created on : May 1, 2024, 10:04:35 PM
    Author     : samuel
--%>

<%@page import="controller.ControllerAluno"%>
<%@page import="model.bean.Boletim"%>
<%@page import="model.bean.Professor"%>
<%@page import="model.bean.Aluno"%>
<%@page import="java.util.List"%>
<%@page import="controller.ControllerProfessor"%>
<%@page import="model.bean.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="w-full">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard Professor</title>
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
                <h2 class="text-xl font-semibold mb-4">Alunos e Boletins</h2>
                <div class="overflow-x-auto">
                    <table class="min-w-full table-auto">
                        <thead>
                            <tr class="bg-gray-200">
                                <th class="px-4 py-2">ID Aluno</th>
                                <th class="px-4 py-2">Nome Aluno</th>
                                <th class="px-4 py-2">Nota</th>
                                <th class="px-4 py-2">Ação</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% HttpSession httpSession = request.getSession();
                                Usuario user = (Usuario) httpSession.getAttribute("userOn");
                                ControllerProfessor controller = new ControllerProfessor();
                                Professor prof = controller.buscarProfessor(user.getIdUsuario());
                                if (prof != null) {
                                    List<Aluno> alunos = controller.listarAlunos(prof.getIdEscola());
                                    ControllerAluno contAlu = new ControllerAluno();
                                    for (Aluno aluno : alunos) {
                                        List<Boletim> boletins = contAlu.listarBoletinsPorIdAluno(aluno.getIdAluno());
                                        for (Boletim boletim : boletins) {
                                            if (boletim.getIdDisciplina() == prof.getIdDisciplina()) {
                            %>
                            <tr>
                                <td class="border px-4 py-2"><%= aluno.getIdAluno()%></td>
                                <td class="border px-4 py-2"><%= aluno.getNomeAluno()%></td>
                                <td class="border px-4 py-2"><%= boletim.getNota()%></td>
                                <td class="border px-4 py-2 text-center">
                                    <a href="editarNota.jsp?idAluno=<%=aluno.getIdAluno()%>&idDisciplina=<%=prof.getIdDisciplina()%>" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Editar Nota</a>
                                </td>
                            </tr>
                            <%          }
                                        }
                                    }
                                }
                            %>

                        </tbody>
                    </table>
                </div>
            </div>
        </main>
    </body>
</html>

