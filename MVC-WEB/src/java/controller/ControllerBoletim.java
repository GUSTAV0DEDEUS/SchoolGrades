/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.SQLException;
import model.DAO.DAOBoletim;

/**
 *
 * @author samuel
 */
public class ControllerBoletim {
    
    private final DAOBoletim daoBoletim;

    public ControllerBoletim() throws SQLException, ClassNotFoundException {
        this.daoBoletim = new DAOBoletim();
    }

    public void criarBoletim(int idAluno, int idDisciplina) throws SQLException {
        daoBoletim.criarBoletim(idAluno, idDisciplina);
    }

    public void excluirTodosBoletins(int idDisciplina) throws SQLException {
        daoBoletim.excluirTodos(idDisciplina);
    }

    public void alterarNotaBoletim(int idAluno, int idDisciplina,double novaNota) throws SQLException {
        daoBoletim.alterarNota(idAluno, idDisciplina, novaNota);
    }
}