/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.SQLException;
import java.util.List;
import model.DAO.DAOEscola;
import model.bean.Aluno;
import model.bean.Disciplina;
import model.bean.Escola;
import model.bean.Professor;

/**
 *
 * @author samuel
 */
public class ControllerEscola {
    
    private final DAOEscola daoEscola;

    public ControllerEscola() throws SQLException, ClassNotFoundException {
        this.daoEscola = new DAOEscola();
    }

    public Escola criarEscola(Escola escola) throws SQLException {
        return daoEscola.criarEscola(escola);
    }

    public void alterarEscola(Escola escola) throws SQLException {
        daoEscola.alterarEscola(escola);
    }

    public void deletarEscola(int idEscola) throws SQLException {
        daoEscola.deletarEscola(idEscola);
    }

    public List<Escola> listarTodasEscolas() throws SQLException {
        return daoEscola.listarTodasEscolas();
    }

    public Escola buscarPorId(int id) throws SQLException {
        return daoEscola.buscarPorId(id);
    }

    public List<Aluno> listarAlunos(int idEscola) throws SQLException {
        return daoEscola.listarAlunos(idEscola);
    }

    public List<Professor> listarProfessores(int idEscola) throws SQLException {
        return daoEscola.listarProfessores(idEscola);
    }

    public void deletarAluno(int idAluno) throws SQLException {
        daoEscola.deletarAluno(idAluno);
    }

    public void deletarProfessor(int idProfessor) throws SQLException {
        daoEscola.deletarProfessor(idProfessor);
    }

    public List<Disciplina> listarDisciplinas(int idEscola) throws SQLException {
        return daoEscola.listarDisciplinas(idEscola);
    }

    public void criarBoletinsParaTodosAlunos(int idEscola, int idDisciplina) throws SQLException, ClassNotFoundException {
        daoEscola.criarBoletinsParaTodosAlunos(idEscola, idDisciplina);
    }
}