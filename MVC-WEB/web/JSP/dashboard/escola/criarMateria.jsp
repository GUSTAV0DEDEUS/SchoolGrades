<%-- 
    Document   : criarMateria
    Created on : May 2, 2024, 8:58:04 AM
    Author     : samuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Criar Disciplina</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
    <div class="container mx-auto h-screen flex justify-center items-center">
        <div class="bg-white p-8 rounded shadow-md">
            <h1 class="text-2xl font-bold mb-4">Criar Disciplina</h1>
            <form action="processarMateria.jsp" method="post">
                <div class="mb-4">
                    <label for="nomeDisciplina" class="block text-gray-700 font-bold mb-2">Nome da Disciplina:</label>
                    <input type="text" id="nomeDisciplina" name="nomeDisciplina" class="border border-gray-400 rounded px-4 py-2 w-full">
                </div>
                <div class="flex space-x-4">
                    <button type="submit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Criar</button>
                    <a href="escola.jsp" class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded">Cancelar</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
