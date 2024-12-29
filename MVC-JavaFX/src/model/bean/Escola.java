/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

/**
 *
 * @author samuel
 */
public class Escola {

    private int idEscola;
    private String nomeEscola;
    private String endereco;
    private String cidade;
    private String estado;

    public Escola(int idEscola) {
        this.idEscola = idEscola;
    }

    public Escola(String nomeEscola, String endereco, String cidade, String estado) {
        this.nomeEscola = nomeEscola;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
    }

    public Escola(int idEscola, String nomeEscola, String endereco, String cidade, String estado) {
        this.idEscola = idEscola;
        this.nomeEscola = nomeEscola;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
    }

    public int getIdEscola() {
        return idEscola;
    }

    public void setIdEscola(int idEscola) {
        this.idEscola = idEscola;
    }

    public String getNomeEscola() {
        return nomeEscola;
    }

    public void setNomeEscola(String nomeEscola) {
        this.nomeEscola = nomeEscola;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        return idEscola + " " + nomeEscola;
    }

}
