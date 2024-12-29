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
import model.bean.Boletim;
import model.bean.Professor;
import util.ConexaoBD;
import java.sql.Statement;

/**
 *
 * @author samuel
 */
public class DAOProfessor {

    private final Connection c;

    public DAOProfessor() throws SQLException, ClassNotFoundException {
        this.c = ConexaoBD.getConexaoMySQL();
    }

    public Professor criarProfessor(Professor professor) throws SQLException {
        String sql = "INSERT INTO professor (id_escola, id_disciplina, nome_professor, especialidade) VALUES (?, ?, ?, ?)";

        // prepared statement para inserção
        try (PreparedStatement stmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, professor.getIdEscola());
            stmt.setInt(2, professor.getIdDisciplina());
            stmt.setString(3, professor.getNomeProfessor());
            stmt.setString(4, professor.getEspecialidade());

            // executa a inserção
            stmt.executeUpdate();

            // Obtém a chave gerada (id_professor)
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    professor.setIdProfessor(id);
                }
            }
        }
        return professor;
    }

    public void alterarProfessor(Professor professor) throws SQLException {
        String sql = "UPDATE professor SET nome_professor=?, especialidade=? WHERE id_professor=?";
        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setString(1, professor.getNomeProfessor());
            
            
            stmt.setString(2, professor.getEspecialidade());
            stmt.setInt(3, professor.getIdProfessor());
            stmt.executeUpdate();
        }
    }

    public List<Boletim> listarBoletins(int idDisciplina) throws SQLException {
        List<Boletim> boletins = new ArrayList<>();
        String sql = "SELECT id_boletim, id_aluno, nota FROM boletim WHERE id_disciplina=?";
        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setInt(1, idDisciplina);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Boletim boletim = new Boletim(
                            rs.getInt("id_boletim"),
                            rs.getInt("id_aluno"),
                            idDisciplina,
                            rs.getDouble("nota")
                    );
                    boletins.add(boletim);
                }
            }
        }
        return boletins;
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

    public Professor buscarProfessorPorId(int idProfessor) throws SQLException {
        String sql = "SELECT id_escola, id_disciplina, nome_professor, especialidade FROM professor WHERE id_professor=?";
        Professor professor = null;
        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setInt(1, idProfessor);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    professor = new Professor(
                            idProfessor,
                            rs.getInt("id_escola"),
                            rs.getInt("id_disciplina"),
                            rs.getString("nome_professor"),
                            rs.getString("especialidade")
                    );
                }
            }
        }
        return professor;
    }

    // public void alterarNota(int idProfessor, double novaNota) throws SQLException {
    // Implemente a lógica para alterar a nota de um professor se necessário
    // Essa lógica dependerá da estrutura de dados do seu sistema
    //}
}
