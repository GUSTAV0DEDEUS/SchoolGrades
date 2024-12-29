/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.SQLException;
import model.bean.Usuario;
import model.DAO.DAOUsuario;

/**
 *
 * @author samuel
 */
public class ControllerUsuario {
    
    private final DAOUsuario daoUsuario;

    public ControllerUsuario() throws SQLException, ClassNotFoundException {
        this.daoUsuario = new DAOUsuario();
    }

    public void criarUsuario(Usuario usuario) throws SQLException {
        daoUsuario.criarUsuario(usuario);
    }

    public void alterarDadosUsuario(Usuario usuario) throws SQLException {
        daoUsuario.alterarDados(usuario);
    }

    public void deletarUsuario(int idUsuario) throws SQLException {
        daoUsuario.deletarUsuario(idUsuario);
    }

    public Usuario validarCredenciais(String email, String senha) throws SQLException {
        return daoUsuario.validar(email, senha);
    }
}
