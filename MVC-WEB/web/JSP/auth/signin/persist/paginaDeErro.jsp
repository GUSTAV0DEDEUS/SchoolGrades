<%-- 
    Document   : paginaDeErro
    Created on : May 1, 2024, 8:18:09 PM
    Author     : samuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Error Page | Academic</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-200 h-screen flex items-center justify-center">
    <div class="max-w-md w-full bg-white rounded-lg shadow-lg p-6">
        <h1 class="text-3xl font-bold mb-6 text-center">Ops, algo deu errado!</h1>
        <p class="text-gray-600 mb-6 text-center">Desculpe, ocorreu um erro inesperado.</p>
        <div class="flex items-center justify-center">
            <a href="../signin.jsp" class="bg-indigo-500 text-white px-4 py-2 rounded-md hover:bg-indigo-600 focus:outline-none focus:bg-indigo-600">Voltar para a página inicial</a>
        </div>
    </div>
</body>
</html>
