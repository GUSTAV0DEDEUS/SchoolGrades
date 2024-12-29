/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.bean.Aluno;
import model.bean.Disciplina;
import model.bean.Escola;
import model.bean.Professor;
import util.ConexaoBD;
import java.sql.Statement;

/**
 *
 * @author samuel
 */
public class DAOEscola {

    private final Connection c;

    public DAOEscola() throws SQLException, ClassNotFoundException {
        this.c = ConexaoBD.getConexaoMySQL();
    }

    public Escola criarEscola(Escola escola) throws SQLException {
        String sql = "INSERT INTO escola (nome_escola, endereco, cidade, estado) VALUES (?, ?, ?, ?)";

        // prepared statement para inserção
        try (PreparedStatement stmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, escola.getNomeEscola());
            stmt.setString(2, escola.getEndereco());
            stmt.setString(3, escola.getCidade());
            stmt.setString(4, escola.getEstado());

            // executa a inserção
            stmt.executeUpdate();

            // Obtém a chave gerada (id_escola)
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    escola.setIdEscola(id);
                }
            }
        }
        return escola;
    }

    public void alterarEscola(Escola escola) throws SQLException {
        String sql = "UPDATE escola SET nome_escola=?, endereco=?, cidade=?, estado=? WHERE id_escola=?";
        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setString(1, escola.getNomeEscola());
            stmt.setString(2, escola.getEndereco());
            stmt.setString(3, escola.getCidade());
            stmt.setString(4, escola.getEstado());
            stmt.setInt(5, escola.getIdEscola());
            stmt.executeUpdate();
        }
    }

    public void deletarEscola(int idEscola) throws SQLException {
        // delete alunos
        String sqlDeleteAlunos = "DELETE FROM aluno WHERE id_escola=?";
        try (PreparedStatement stmtDeleteAlunos = c.prepareStatement(sqlDeleteAlunos)) {
            stmtDeleteAlunos.setInt(1, idEscola);
            stmtDeleteAlunos.executeUpdate();
        }

        // delete professores
        String sqlDeleteProfessores = "DELETE FROM professor WHERE id_escola=?";
        try (PreparedStatement stmtDeleteProfessores = c.prepareStatement(sqlDeleteProfessores)) {
            stmtDeleteProfessores.setInt(1, idEscola);
            stmtDeleteProfessores.executeUpdate();
        }

        // delete escola
        String sqlDeleteEscola = "DELETE FROM escola WHERE id_escola=?";
        try (PreparedStatement stmtDeleteEscola = c.prepareStatement(sqlDeleteEscola)) {
            stmtDeleteEscola.setInt(1, idEscola);
            stmtDeleteEscola.executeUpdate();
        }
    }

    public List<Escola> listarTodasEscolas() throws SQLException {
        List<Escola> escolas = new ArrayList<>();
        String sql = "SELECT id_escola, nome_escola, endereco, cidade, estado FROM escola";
        try (PreparedStatement stmt = c.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Escola escola = new Escola(
                        rs.getInt("id_escola"),
                        rs.getString("nome_escola"),
                        rs.getString("endereco"),
                        rs.getString("cidade"),
                        rs.getString("estado")
                );
                escolas.add(escola);
            }
        }
        return escolas;
    }

    public Escola buscarPorId(int id) throws SQLException {
        String sql = "SELECT id_escola, nome_escola, endereco, cidade, estado FROM escola WHERE id_escola=?";
        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Escola(
                            rs.getInt("id_escola"),
                            rs.getString("nome_escola"),
                            rs.getString("endereco"),
                            rs.getString("cidade"),
                            rs.getString("estado")
                    );
                }
            }
        }
        return null;
    }

    public List<Aluno> listarAlunos(int idEscola) throws SQLException {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT id_aluno, nome_aluno, data_nascimento FROM aluno WHERE id_escola=?";
        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setInt(1, idEscola);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Aluno aluno = new Aluno(
                            rs.getInt("id_aluno"),
                            idEscola,
                            rs.getString("nome_aluno"),
                            rs.getDate("data_nascimento")
                    );
                    alunos.add(aluno);
                }
            }
        }
        return alunos;
    }

    public List<Professor> listarProfessores(int idEscola) throws SQLException {
        List<Professor> professores = new ArrayList<>();
        String sql = "SELECT id_professor, id_disciplina, nome_professor, especialidade FROM professor WHERE id_escola=?";
        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setInt(1, idEscola);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Professor professor = new Professor(
                            rs.getInt("id_professor"),                            
                            rs.getInt("id_disciplina"),
                            idEscola,
                            rs.getString("nome_professor"),
                            rs.getString("especialidade")
                    );
                    professores.add(professor);
                }
            }
        }
        return professores;
    }

    public void deletarAluno(int idAluno) throws SQLException {
        String sql = "DELETE FROM aluno WHERE id_aluno=?";
        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setInt(1, idAluno);
            stmt.executeUpdate();
        }
    }

    public void deletarProfessor(int idProfessor) throws SQLException {
        String sql = "DELETE FROM professor WHERE id_professor=?";
        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setInt(1, idProfessor);
            stmt.executeUpdate();
        }
    }

    public List<Disciplina> listarDisciplinas(int idEscola) throws SQLException {
        List<Disciplina> disciplinas = new ArrayList<>();
        String sql = "SELECT id_disciplina, nome_disciplina FROM disciplina WHERE id_escola=?";
        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setInt(1, idEscola);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Disciplina disciplina = new Disciplina(
                            rs.getInt("id_disciplina"),
                            rs.getString("nome_disciplina"),
                            idEscola
                    );
                    disciplinas.add(disciplina);
                }
            }
        }
        return disciplinas;
    }

    public void criarBoletinsParaTodosAlunos(int idEscola, int idDisciplina) throws SQLException, ClassNotFoundException {
        List<Aluno> alunos = listarAlunos(idEscola);
        DAOBoletim daoBoletim = new DAOBoletim();
        for (Aluno aluno : alunos) {
            if (!daoBoletim.existeBoletim(aluno.getIdAluno(), idDisciplina)) {
                daoBoletim.criarBoletim(aluno.getIdAluno(), idDisciplina);
            }
        }
    }

}
