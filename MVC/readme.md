# Projeto MVC com Swing

Este projeto é uma implementação do padrão MVC (Model-View-Controller) utilizando Swing e MySQL para a gestão acadêmica.

## 📋 Descrição

O projeto MVC-Swing é uma aplicação desktop desenvolvida com Swing e MySQL, destinada à administração de notas escolares, disciplinas e informações acadêmicas dos alunos.

## 📂 Estrutura do Projeto

- **Model**: Contém as classes que representam a lógica de negócios e a interação com o banco de dados.
- **View**: Contém as interfaces gráficas desenvolvidas com Swing.
- **Controller**: Contém as classes que controlam o fluxo de dados entre o Model e a View.

## 🚀 Funcionalidades

- Cadastro de alunos
- Registro de notas
- Gestão de disciplinas
- Controle de turmas

## 🛠️ Tecnologias Utilizadas

- **Java 8**
- **Swing**
- **MySQL Database**

## 📦 Dependências

- **MySQL Connector/J**: Driver JDBC para conectar a aplicação ao banco de dados MySQL.

## 📖 Como Executar

1. **Clone o repositório**:
    ```bash
    git clone <URL_DO_REPOSITORIO>
    ```

2. **Configure o banco de dados MySQL**:
    - Crie um banco de dados chamado `ams_education`.
    - Importe o script SQL localizado em `MVC/script/banco.sql` para criar as tabelas necessárias.

3. **Configure o MySQL Connector/J**:
    - Certifique-se de que o MySQL Connector/J está no classpath do projeto.

4. **Compile e execute a aplicação**:
    - Utilize um IDE como Netbeans ou IntelliJ IDEA para compilar e executar a aplicação.
    - Certifique-se de que todas as dependências estão corretamente configuradas.

5. **Execute a aplicação**:
    - Inicie a aplicação e utilize a interface gráfica para gerenciar as informações acadêmicas.