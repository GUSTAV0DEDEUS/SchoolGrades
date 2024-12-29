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
import model.bean.Disciplina;
import util.ConexaoBD;

/**
 *
 * @author samuel
 */
public class DAODisciplina {

    private final Connection c;

    public DAODisciplina() throws SQLException, ClassNotFoundException {
        this.c = ConexaoBD.getConexaoMySQL();
    }

    public void criarDisciplina(Disciplina disciplina) throws SQLException {
        String sql = "INSERT INTO disciplina (nome_disciplina, id_escola) VALUES (?, ?)";
        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setString(1, disciplina.getNomeDisciplina());
            stmt.setInt(2, disciplina.getIdEscola());
            stmt.executeUpdate();
        }
    }

    public void alterarDisciplina(Disciplina disciplina) throws SQLException {
        String sql = "UPDATE disciplina SET nome_disciplina=? WHERE id_disciplina=?";
        try (PreparedStatement stmt = c.prepareStatement(sql)) {

            stmt.setString(1, disciplina.getNomeDisciplina());
            stmt.setInt(2, disciplina.getIdDisciplina());
            stmt.executeUpdate();
        }
    }

    public void excluirDisciplina(int idDisciplina) throws SQLException {
        String sql = "DELETE FROM disciplina WHERE id_disciplina=?";
        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setInt(1, idDisciplina);
            stmt.executeUpdate();
        }
    }

    public Disciplina buscarPorId(int idDisciplina) throws SQLException {
        String sql = "SELECT nome_disciplina, id_escola FROM disciplina WHERE id_disciplina=?";
        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setInt(1, idDisciplina);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Disciplina(idDisciplina, rs.getString("nome_disciplina"), rs.getInt("id_escola"));
                }
            }
        }
        return null;
    }

    public List<Disciplina> listarPorIdEscola(int idEscola) throws SQLException {
        List<Disciplina> disciplinas = new ArrayList<>();
        String sql = "SELECT id_disciplina, nome_disciplina FROM disciplina WHERE id_escola=?";
        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setInt(1, idEscola);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Disciplina disciplina = new Disciplina(rs.getInt("id_disciplina"), rs.getString("nome_disciplina"), idEscola);
                    disciplinas.add(disciplina);
                }
            }
        }
        return disciplinas;
    }
}
