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
import util.ConexaoBD;
import java.sql.Statement;


/**
 *
 * @author gustavo
 */
public class DAOAluno {
   private final Connection c;
   
   public DAOAluno() throws SQLException, ClassNotFoundException {
       this.c = ConexaoBD.getConexaoMySQL();
   }
   
   // create student
  public Aluno inserir(Aluno aluno) throws SQLException {
        String sql = "INSERT INTO aluno (id_escola, nome_aluno, data_nascimento) VALUES (?, ?, ?)";
        
        try (PreparedStatement stmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, aluno.getIdEscola());
            stmt.setString(2, aluno.getNomeAluno());
            stmt.setDate(3, new java.sql.Date(aluno.getDataNascimento().getTime()));
            
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    aluno.setIdAluno(id);
                }
            }
        }
        return aluno;
    }
   
   // change student
    public void alterarAluno(Aluno aluno) throws SQLException {
        String sql = "UPDATE aluno SET id_escola=?, nome_aluno=?, data_nascimento=? WHERE id_aluno=?";
        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setInt(1, aluno.getIdEscola());
            stmt.setString(2, aluno.getNomeAluno());
            stmt.setDate(3, new java.sql.Date(aluno.getDataNascimento().getTime()));
            stmt.setInt(4, aluno.getIdAluno());
            stmt.executeUpdate();
        }
    }
    
    // search
    public Aluno buscarPorId(int id) throws SQLException {
        String sql = "SELECT id_aluno, id_escola, nome_aluno, data_nascimento FROM aluno WHERE id_aluno=?";
        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Aluno(
                            rs.getInt("id_aluno"),
                            rs.getInt("id_escola"),
                            rs.getString("nome_aluno"),
                            rs.getDate("data_nascimento")
                    );
                }
            }
        }
        return null; 
    }
    // Boletim
    public List<Boletim> listarBoletins(int idAluno) throws SQLException {
        List<Boletim> boletins = new ArrayList<>();
        String sql = "SELECT id_boletim, id_disciplina, nota FROM boletim WHERE id_aluno=?";
        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setInt(1, idAluno);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    boletins.add(new Boletim(
                            rs.getInt("id_boletim"),
                            idAluno,
                            rs.getInt("id_disciplina"),
                            rs.getDouble("nota")
                    ));
                }
            }
        }
        return boletins;
    }
    // boletim 
    public Boletim obterUltimoBoletim(int idAluno) throws SQLException {
        String sql = "SELECT id_boletim, id_disciplina, nota FROM boletim WHERE id_aluno=? ORDER BY id_boletim DESC LIMIT 1";
        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setInt(1, idAluno);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Boletim(
                            rs.getInt("id_boletim"),
                            idAluno,
                            rs.getInt("id_disciplina"),
                            rs.getDouble("nota")
                    );
                }
            }
        }
        return null; 
    }
   
}
