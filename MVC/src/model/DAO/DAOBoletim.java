/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import util.ConexaoBD;

/**
 *
 * @author samuel
 */
public class DAOBoletim {

    private final Connection c;

    public DAOBoletim() throws SQLException, ClassNotFoundException {
        this.c = ConexaoBD.getConexaoMySQL();
    }

    public void criarBoletim(int idAluno, int idDisciplina) throws SQLException {
        String sql = "INSERT INTO boletim (id_aluno, id_disciplina, nota) VALUES (?, ?, 0.0)";
        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setInt(1, idAluno);
            stmt.setInt(2, idDisciplina);
            stmt.executeUpdate();
        }
    }

    public void excluirTodos(int idDisciplina) throws SQLException {
        String sql = "DELETE FROM boletim WHERE id_disciplina=?";
        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setInt(1, idDisciplina);
            stmt.executeUpdate();
        }
    }

    public void alterarNota(int idAluno, int idDisciplina, double novaNota) throws SQLException {
        String sql = "UPDATE boletim SET nota=? WHERE id_aluno=? AND id_disciplina=?";
        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setDouble(1, novaNota);
            stmt.setInt(2, idAluno);
            stmt.setInt(3, idDisciplina);
            stmt.executeUpdate();
        }
    }
    
    public boolean existeBoletim(int idAluno, int idDisciplina) throws SQLException {
    String sql = "SELECT COUNT(*) AS count FROM boletim WHERE id_aluno = ? AND id_disciplina = ?";
    try (PreparedStatement stmt = c.prepareStatement(sql)) {
        stmt.setInt(1, idAluno);
        stmt.setInt(2, idDisciplina);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt("count");
                return count > 0; // Retorna true se o boletim existir, caso contrário, false
            }
        }
    }
    return false;
}

    
}
