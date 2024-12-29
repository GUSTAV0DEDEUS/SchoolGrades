/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

/**
 *
 * @author samuel
 */
public class Usuario {
    private int idLogin;
    private int idUsuario;
    private String tipoUsuario;
    private String email;
    private String senha;

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario(int idLogin, int idUsuario, String tipoUsuario, String email, String senha) {
        this.idLogin = idLogin;
        this.idUsuario = idUsuario;
        this.tipoUsuario = tipoUsuario;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(int idUsuario, String tipoUsuario, String email, String senha) {
        this.idUsuario = idUsuario;
        this.tipoUsuario = tipoUsuario;
        this.email = email;
        this.senha = senha;
    }
    
    

    public int getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(int idLogin) {
        this.idLogin = idLogin;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
