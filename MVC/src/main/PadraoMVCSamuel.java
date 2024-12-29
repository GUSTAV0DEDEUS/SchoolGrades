package main;

import javax.swing.JOptionPane;
import view.ManterUsuario;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *
 * @author gustavo
 */
public class PadraoMVCSamuel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ManterUsuario mu = new ManterUsuario();
        boolean sair = false;

        while (!sair) {
            int opcao = introduction();

            switch (opcao) {
                case 1 :
                   mu.cadastrar();
                case 2 :
                    mu.entrar();
                case 0 :
                    sair = true;
                default :
                    System.exit(0);
            }
        }
    }

    private static int introduction() {
        String msg = " 1 - Cadastrar \n 2 - Entrar \n 0 - Sair";
        return Integer.parseInt(JOptionPane.showInputDialog(msg));
    }

}
