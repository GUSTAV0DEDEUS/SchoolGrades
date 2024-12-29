/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

import java.util.Date;

/**
 *
 * @author gustavo
 */
public class Aluno {
    private int idAluno;
    private int idEscola;
    private String nomeAluno;
    private Date dataNascimento;

    public Aluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public Aluno(int idAluno, int idEscola, String nomeAluno, Date dataNascimento) {
        this.idAluno = idAluno;
        this.idEscola = idEscola;
        this.nomeAluno = nomeAluno;
        this.dataNascimento = dataNascimento;
    }
    
    public Aluno(int idEscola, String nomeAluno, Date dataNascimento) {
        this.idEscola = idEscola;
        this.nomeAluno = nomeAluno;
        this.dataNascimento = dataNascimento;
    }
    
    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public int getIdEscola() {
        return idEscola;
    }

    public void setIdEscola(int idEscola) {
        this.idEscola = idEscola;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
