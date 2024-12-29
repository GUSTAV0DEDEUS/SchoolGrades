/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.SQLException;
import java.util.List;
import model.DAO.DAOAluno;
import model.bean.Aluno;
import model.bean.Boletim;

/**
 *
 * @author samuel
 */
public class ControllerAluno {
    
    private final DAOAluno daoAluno;

    public ControllerAluno() throws SQLException, ClassNotFoundException {
        this.daoAluno = new DAOAluno();
    }

    public Aluno criarAluno(Aluno aluno) throws SQLException {
        return daoAluno.inserir(aluno);
    }

    public void alterarAluno(Aluno aluno) throws SQLException {
        daoAluno.alterarAluno(aluno);
    }

    public Aluno buscarAlunoPorId(int id) throws SQLException {
        return daoAluno.buscarPorId(id);
    }

    public List<Boletim> listarBoletinsPorIdAluno(int idAluno) throws SQLException {
        return daoAluno.listarBoletins(idAluno);
    }

    public Boletim obterUltimoBoletim(int idAluno) throws SQLException {
        return daoAluno.obterUltimoBoletim(idAluno);
    }
}
