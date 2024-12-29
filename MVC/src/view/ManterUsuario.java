/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.ControllerAluno;
import controller.ControllerEscola;
import controller.ControllerProfessor;
import controller.ControllerUsuario;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.bean.Aluno;
import model.bean.Escola;
import model.bean.Professor;
import model.bean.Usuario;

/**
 *
 * @author samuel
 */
public class ManterUsuario {

    public void entrar() {
        String email = JOptionPane.showInputDialog("Email");
        String senha = JOptionPane.showInputDialog("Senha");

        try {
            ControllerUsuario contU = new ControllerUsuario();
            Usuario usuario = contU.validarCredenciais(email, senha);

            if (usuario != null) {
                JOptionPane.showMessageDialog(null, "Bem-vindo, " + usuario.getTipoUsuario());
                switch (usuario.getTipoUsuario()) {
                    case "escola":
                        ManterEscola me = new ManterEscola();
                        ControllerEscola ce = new ControllerEscola();
                        Escola escola = ce.buscarPorId(usuario.getIdUsuario());
                        me.setEscola(escola);
                        me.menu();
                        break;
                    case "aluno":
                        ManterAluno ma = new ManterAluno();
                        ControllerAluno ca = new ControllerAluno();
                        Aluno aluno = ca.buscarAlunoPorId(usuario.getIdUsuario());
                        ma.setAluno(aluno);
                        ma.menu();
                        break;
                    case "professor":
                        ManterProfessor mp = new ManterProfessor();
                        ControllerProfessor cp = new ControllerProfessor();
                        Professor professor = cp.buscarProfessor(usuario.getIdUsuario());
                        mp.setProfessor(professor);
                        mp.menu();

                        break;
                    default:
                        JOptionPane.showMessageDialog(
                                null,
                                "Houve algum erro interno, tente criar uma nova conta ou contatar sua escola"
                        );
                        System.exit(0);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Credenciais inválidas. Tente novamente.");
                System.exit(0);
            }
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar entrar: \n" + e.getMessage());
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    public void cadastrar() {
        String tipo = tipoDeUsario();
        int usuarioId = 0;
        switch (tipo) {
            case "aluno":
                ManterAluno ma = new ManterAluno();
                Aluno aluno = ma.inserirAluno();
                ma.setAluno(aluno);
                usuarioId = aluno.getIdAluno();
                break;
            case "professor":
                ManterProfessor mp = new ManterProfessor();
                Professor professor = mp.inserirProfessor();
                mp.setProfessor(professor);
                usuarioId = professor.getIdProfessor();
                break;
            case "escola":
                ManterEscola me = new ManterEscola();
                Escola escola = me.inserirEscola();
                me.setEscola(escola);
                usuarioId = escola.getIdEscola();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Tente novamente!");
                cadastrar();
                break;
        }
        String email = JOptionPane.showInputDialog("Email");
        String senha = JOptionPane.showInputDialog("Senha");
        Usuario usuEnt = new Usuario(usuarioId, tipo, email, senha);
        try {
            ControllerUsuario contU = new ControllerUsuario();
            contU.criarUsuario(usuEnt);
            JOptionPane.showMessageDialog(null, "Usuario Criado com sucesso");
            entrar();
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error, tente novamente \n" + e.getMessage());
            System.out.println(e.getMessage());
            System.exit(0);
        }

    }

    private static String tipoDeUsario() {
        Object[] tiposO = {"aluno", "professor", "escola"};
        String tipo = JOptionPane.showInputDialog(
                null,
                "Seja bem vindo \n Escolha qual tipo de conta.",
                "Opções",
                JOptionPane.INFORMATION_MESSAGE,
                null,
                tiposO,
                tiposO[0]
        ).toString();
        return tipo;
    }
}
