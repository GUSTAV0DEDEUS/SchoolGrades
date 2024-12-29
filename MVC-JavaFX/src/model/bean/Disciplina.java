/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

/**
 *
 * @author samuel
 */
public class Disciplina {

    private int idDisciplina;
    private String nomeDisciplina;
    private int idEscola;

    public Disciplina(int idDisciplina, String nomeDisciplina, int idEscola) {
        this.idDisciplina = idDisciplina;
        this.nomeDisciplina = nomeDisciplina;
        this.idEscola = idEscola;
    }

    public Disciplina(String nomeDisciplina, int idEscola) {
        this.nomeDisciplina = nomeDisciplina;
        this.idEscola = idEscola;
    }

    public int getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public int getIdEscola() {
        return idEscola;
    }

    public void setIdEscola(int idEscola) {
        this.idEscola = idEscola;
    }

    @Override
    public String toString() {
        return idDisciplina + " " + nomeDisciplina;
    }
}
