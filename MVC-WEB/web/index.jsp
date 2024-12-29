<%-- 
    Document   : index
    Created on : May 1, 2024, 6:39:22 PM
    Author     : samuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Seja bem vindo | Academic</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-200">
    <div class="flex items-center justify-center h-screen">
        <div class="max-w-4xl w-full bg-white rounded-lg shadow-lg p-6">
            <h1 class="text-4xl font-bold mb-6">Bem-vindo ao Academic!</h1>
            <p class="text-lg text-gray-700 mb-8">Faça login ou crie uma conta para acessar todo o conteúdo.</p>
            <div class="flex items-center justify-between space-x-4">
                <a href="./JSP/auth/signin/signin.jsp" class="w-1/2">
                    <div class="bg-gray-300 rounded-lg overflow-hidden shadow-lg">
                        <div class="p-4">
                            <h2 class="text-xl font-bold mb-2">Faça login</h2>
                            <p class="text-gray-700">Já tem uma conta? Faça login para continuar.</p>
                        </div>
                    </div>
                </a>
                <a href="./JSP/auth/signup/CYA.jsp" class="w-1/2">
                    <div class="bg-gray-300 rounded-lg overflow-hidden shadow-lg">
                        <div class="p-4">
                            <h2 class="text-xl font-bold mb-2">Criar uma conta</h2>
                            <p class="text-gray-700">Ainda não tem uma conta? Crie uma agora mesmo.</p>
                        </div>
                    </div>
                </a>
            </div>
        </div>
    </div>
</body>
</html>
