<%-- 
    Document   : CYA
    Created on : May 1, 2024, 7:06:30 PM
    Author     : samuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Escolha o tipo da sua conta | Academic</title>
        <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    </head>
    <body class="bg-gray-200 flex items-center justify-center h-screen">
        <div class="max-w-md w-full bg-white rounded-lg shadow-lg p-6">
            <h1 class="text-2xl font-bold mb-6">Escolha o tipo da sua conta</h1>
            <div class="space-y-4">
                <label for="tipoConta" class="block font-medium text-gray-700">Tipo de conta:</label>
                <div class="relative">
                    <select id="tipoConta" name="tipoConta" class="block appearance-none w-full bg-gray-200 border border-gray-200 text-gray-700 py-3 px-4 pr-8 rounded-md leading-tight focus:outline-none focus:bg-white focus:border-gray-500">
                        <option value="aluno">Aluno</option>
                        <option value="professor">Professor</option>
                        <option value="escola">Escola</option>
                    </select>
                    <div class="pointer-events-none absolute inset-y-0 right-0 flex items-center px-2 text-gray-700">
                        <svg class="fill-current h-4 w-4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20"><path d="M10 12L0 2h20z"/></svg>
                    </div>
                </div>
                <div class="flex items-center justify-between mt-4">
                    <button id="btnSelecionar" class="w-full bg-indigo-500 text-white px-4 py-2 rounded-md hover:bg-indigo-600 focus:outline-none focus:bg-indigo-600">Selecionar</button>
                </div>
            </div>
        </div>

        <script>
            document.getElementById('btnSelecionar').addEventListener('click', function () {
                var tipoContaSelecionado = document.getElementById('tipoConta').value;
                switch (tipoContaSelecionado) {
                    case 'aluno':
                        window.location.href = 'aluno.jsp';
                        break;
                    case 'professor':
                        window.location.href = 'professor.jsp';
                        break;
                    case 'escola':
                        window.location.href = 'escola.jsp';
                        break;
                    default:
                        window.location.href = 'CYA.jsp';
                        break;
                }
            });
        </script>
    </body>
</html>
