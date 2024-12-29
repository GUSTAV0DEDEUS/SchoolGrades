/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

/**
 *
 * @author samuel
 */
public class Professor {

    private int idProfessor;
    private int idEscola;
    private int idDisciplina;
    private String nomeProfessor;
    private String especialidade;

    public Professor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    public Professor(int idEscola, int idDisciplina, String nomeProfessor, String especialidade) {
        this.idEscola = idEscola;
        this.idDisciplina = idDisciplina;
        this.nomeProfessor = nomeProfessor;
        this.especialidade = especialidade;
    }

    public Professor(int idProfessor, int idEscola, int idDisciplina, String nomeProfessor, String especialidade) {
        this.idProfessor = idProfessor;
        this.idEscola = idEscola;
        this.idDisciplina = idDisciplina;
        this.nomeProfessor = nomeProfessor;
        this.especialidade = especialidade;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public int getIdDisciplina() {
        return idDisciplina;
    }

    public int getIdEscola() {
        return idEscola;
    }

    public void setIdEscola(int idEscola) {
        this.idEscola = idEscola;
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
