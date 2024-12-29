/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.SQLException;
import java.util.List;
import model.DAO.DAOProfessor;
import model.bean.Aluno;
import model.bean.Boletim;
import model.bean.Professor;

/**
 *
 * @author samuel
 */
public class ControllerProfessor {
    
    private final DAOProfessor daoProfessor;

    public ControllerProfessor() throws SQLException, ClassNotFoundException {
        this.daoProfessor = new DAOProfessor();
    }

    public Professor criarProfessor(Professor professor) throws SQLException {
        return daoProfessor.criarProfessor(professor);
    }

    public void alterarProfessor(Professor professor) throws SQLException {
        daoProfessor.alterarProfessor(professor);
    }

    public List<Boletim> listarBoletins(int idDisciplina) throws SQLException {
        return daoProfessor.listarBoletins(idDisciplina);
    }

    public List<Aluno> listarAlunos(int idEscola) throws SQLException {
        return daoProfessor.listarAlunos(idEscola);
    }
    
    public Professor buscarProfessor(int idProfessor) throws SQLException{
        return  daoProfessor.buscarProfessorPorId(idProfessor);
    }
}