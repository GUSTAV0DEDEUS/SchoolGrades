/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.bean.Usuario;
import util.ConexaoBD;

/**
 *
 * @author samuel
 */
public class DAOUsuario {

    private final Connection c;

    public DAOUsuario() throws SQLException, ClassNotFoundException {
        this.c = ConexaoBD.getConexaoMySQL();
    }

    public void criarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (id_usuario, tipo_usuario, email, senha) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setInt(1, usuario.getIdUsuario());
            stmt.setString(2, usuario.getTipoUsuario());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getSenha());
            stmt.executeUpdate();
        }
    }

    public void alterarDados(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuario SET email=?, senha=? WHERE id_usuario=?";
        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());
            stmt.setInt(3, usuario.getIdUsuario());
            stmt.executeUpdate();
        }
    }

    public void deletarUsuario(int idUsuario) throws SQLException {
        String sql = "DELETE FROM usuario WHERE id_usuario=?";
        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.executeUpdate();
        }
    }

    public Usuario validar(String email, String senha) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE email=? AND senha=?";
        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int idUsuario = rs.getInt("id_usuario");
                    String tipoUsuario = rs.getString("tipo_usuario");
                    String userEmail = rs.getString("email");
                    String userSenha = rs.getString("senha");

                    return new Usuario(idUsuario, tipoUsuario, userEmail, userSenha);
                } else {

                    return null;
                }
            }
        }
    }

}
