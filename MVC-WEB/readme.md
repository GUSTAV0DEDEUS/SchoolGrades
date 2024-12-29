# MVC-WEB

Este projeto é uma implementação do padrão MVC (Model-View-Controller) utilizando JSP e MySQL para a gestão acadêmica.

## 📋 Descrição

O projeto MVC-WEB é uma aplicação web desenvolvida com JSP (JavaServer Pages) e MySQL, destinada à administração de notas escolares, disciplinas e informações acadêmicas dos alunos.

## 📂 Estrutura do Projeto

- **Model**: Contém as classes que representam a lógica de negócios e a interação com o banco de dados.
- **View**: Contém as páginas JSP que são responsáveis pela interface com o usuário.
- **Controller**: Contém os servlets que controlam o fluxo de dados entre o Model e a View.

## 🚀 Funcionalidades

- Cadastro de alunos
- Registro de notas
- Gestão de disciplinas
- Controle de turmas

## 🛠️ Tecnologias Utilizadas

- **Java 11+**
- **JSP/Servlets**
- **MySQL Database**

## 📦 Dependências

- **Apache Tomcat**: Servidor de aplicação para rodar a aplicação JSP.
- **MySQL Connector/J**: Driver JDBC para conectar a aplicação ao banco de dados MySQL.

## 📖 Como Executar

1. **Clone o repositório**:
    ```bash
    git clone <URL_DO_REPOSITORIO>
    ```

2. **Configure o banco de dados MySQL**:
    - Crie um banco de dados chamado `ams_education`.
    - Importe o script SQL localizado em `MVC/script/banco.sql` para criar as tabelas necessárias.

3. **Configure o servidor Tomcat**:
    - Adicione o projeto ao servidor Tomcat no seu IDE (Netbeans, Eclipse, etc).
    - Certifique-se de que o MySQL Connector/J está no classpath do projeto.

4. **Execute a aplicação**:
    - Inicie o servidor Tomcat e acesse a aplicação via navegador em `http://localhost:8080/MVC-WEB`.
