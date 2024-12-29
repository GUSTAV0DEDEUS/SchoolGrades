/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.sql.SQLException;
import java.util.List;
import model.bean.Disciplina;
import model.DAO.DAODisciplina;


/**
 *
 * @author samuel
 */
public class ControllerDisciplina {
    
    private final DAODisciplina daoDisciplina;

    public ControllerDisciplina() throws SQLException, ClassNotFoundException {
        this.daoDisciplina = new DAODisciplina();
    }

    public void criarDisciplina(Disciplina disciplina) throws SQLException {
        daoDisciplina.criarDisciplina(disciplina);
    }

    public void alterarDisciplina(Disciplina disciplina) throws SQLException {
        daoDisciplina.alterarDisciplina(disciplina);
    }

    public void excluirDisciplina(int idDisciplina) throws SQLException {
        daoDisciplina.excluirDisciplina(idDisciplina);
    }

    public Disciplina buscarDisciplinaPorId(int idDisciplina) throws SQLException {
        return daoDisciplina.buscarPorId(idDisciplina);
    }

    public List<Disciplina> listarDisciplinasPorIdEscola(int idEscola) throws SQLException {
        return daoDisciplina.listarPorIdEscola(idEscola);
    }
}
