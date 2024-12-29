/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.ControllerAluno;
import controller.ControllerEscola;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Aluno;
import model.bean.Boletim;
import model.bean.Escola;

/**
 *
 * @author samuel
 */
public class ManterAluno {

    private Aluno aluno;

    private List<Escola> escolas;

    public Aluno inserirAluno() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        String nome = JOptionPane.showInputDialog("Digite o nome do aluno:");
        Date dataNascimento;
        try {
            dataNascimento = formatter.parse(JOptionPane.showInputDialog("Digite a data de nascimento do aluno (formato: dd-MM-yyyy):"));
            escolas = preencherLista();

            if (escolas != null && !escolas.isEmpty()) {
                Object[] escolaComIdNome = converterListaParaObject();
                String idEscolaSTR = JOptionPane.showInputDialog(
                        null,
                        "Escolha a escola do aluno:",
                        "Escolha",
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        escolaComIdNome,
                        escolaComIdNome[0]
                ).toString();
                int idEscola = Integer.parseInt(idEscolaSTR.split(" ")[0]);
                Aluno alunoEnt = new Aluno(idEscola, nome, dataNascimento);
                ControllerAluno contA = new ControllerAluno();
                return contA.criarAluno(alunoEnt);
            } else {
                JOptionPane.showMessageDialog(null, "Nenhuma escola encontrada!");
            }
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Data incorreta!");
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
        return null;
    }

    private List<Escola> preencherLista() {
        try {
            ControllerEscola contE = new ControllerEscola();
            return contE.listarTodasEscolas();
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar as escolas: " + e.getMessage());
            return null;
        }
    }

    private Object[] converterListaParaObject() {
        List<String> selectEscolas = new ArrayList<>();

        for (Escola escola : escolas) {
            selectEscolas.add(escola.toString());
        }

        return selectEscolas.toArray();
    }

    public void menu() {
        int opcao = Integer.parseInt(JOptionPane.showInputDialog("### Menu de Aluno ###\n"
                + "1. Alterar Aluno\n"
                + "2. Listar Boletins\n"
                + "Escolha uma opção: "));
        switch (opcao) {
            case 1:
                alterarAluno();
                break;
            case 2:
                listarBoletins();
                break;
            case 0:
                System.out.println("Saindo...");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida!");
                break;
        }
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void alterarAluno() {
        try {
            String novoNome = JOptionPane.showInputDialog("Digite o novo nome do aluno:");
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date novaDataNascimento = formatter.parse(JOptionPane.showInputDialog("Digite a nova data de nascimento do aluno (formato: dd-MM-yyyy):"));
            aluno.setNomeAluno(novoNome);
            aluno.setDataNascimento(novaDataNascimento);
            ControllerAluno controller = new ControllerAluno();
            controller.alterarAluno(aluno);
            JOptionPane.showMessageDialog(null, "Dados do aluno alterados com sucesso.");
            menu();
        } catch (SQLException | ClassNotFoundException | ParseException e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar os dados do aluno: " + e.getMessage());
        }
    }

    public void listarBoletins() {
        try {
            ControllerAluno controller = new ControllerAluno();
            List<Boletim> boletins = controller.listarBoletinsPorIdAluno(aluno.getIdAluno());
            StringBuilder sb = new StringBuilder();
            sb.append("Boletins:\n");
            for (Boletim boletim : boletins) {
                sb.append("ID: ").append(boletim.getIdBoletim()).append(", Nota: ").append(boletim.getNota()).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
            menu();
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar os boletins: " + e.getMessage());
        }
    }
}
