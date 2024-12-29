/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.ControllerBoletim;
import controller.ControllerDisciplina;
import controller.ControllerEscola;
import controller.ControllerProfessor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Aluno;
import model.bean.Boletim;
import model.bean.Disciplina;
import model.bean.Escola;
import model.bean.Professor;

/**
 *
 * @author samuel
 */
public class ManterProfessor {

    private Professor professor;

    private List<Escola> escolas;
    private List<Disciplina> disciplinas;

    public Professor inserirProfessor() {
        try {
            String nome = JOptionPane.showInputDialog("Digite o nome do professor:");
            escolas = preencherListaEscolas();
            if (escolas != null && !escolas.isEmpty()) {
                Object[] escolaComIdNome = converterListaParaObject(escolas);
                String idEscolaSTR = JOptionPane.showInputDialog(
                        null,
                        "Escolha a escola do professor:",
                        "Escolha",
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        escolaComIdNome,
                        escolaComIdNome[0]
                ).toString();
                int idEscola = Integer.parseInt(idEscolaSTR.split(" ")[0]);

                disciplinas = preencherListaDisciplinasPorIdEscola(idEscola);
                if (disciplinas != null && !disciplinas.isEmpty()) {
                    Object[] disciplinaComIdNome = converterListaParaObject(disciplinas);
                    String idDisciplinaSTR = JOptionPane.showInputDialog(
                            null,
                            "Escolha a disciplina do professor:",
                            "Escolha",
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            disciplinaComIdNome,
                            disciplinaComIdNome[0]
                    ).toString();
                    int idDisciplina = Integer.parseInt(idDisciplinaSTR.split(" ")[0]);
                    ControllerDisciplina cd = new ControllerDisciplina();
                    Disciplina disciplina = cd.buscarDisciplinaPorId(idDisciplina);
                    ControllerProfessor controller = new ControllerProfessor();
                    Professor professorEnt = new Professor(idEscola, idDisciplina, nome, disciplina.getNomeDisciplina());
                    return controller.criarProfessor(professorEnt);
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhuma disciplina encontrada para esta escola!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nenhuma escola encontrada!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro: ID da escola deve ser um número inteiro.");
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar o professor: " + e.getMessage());
        }
        return null;
    }

    private Object[] converterListaParaObject(List<?> lista) {
        List<String> selectItens = new ArrayList<>();

        for (Object item : lista) {
            selectItens.add(item.toString());
        }

        return selectItens.toArray();
    }

    private List<Escola> preencherListaEscolas() {
        try {
            ControllerEscola contE = new ControllerEscola();
            return contE.listarTodasEscolas();
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar as escolas: " + e.getMessage());
            return null;
        }
    }

    private List<Disciplina> preencherListaDisciplinasPorIdEscola(int idEscola) {
        try {
            ControllerDisciplina contD = new ControllerDisciplina();
            return contD.listarDisciplinasPorIdEscola(idEscola);
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar as disciplinas: " + e.getMessage());
            return null;
        }
    }

    public void menu() {
        int opcao = Integer.parseInt(JOptionPane.showInputDialog("### Menu de Professor ###\n"
                + "1. Listar Alunos\n"
                + "2. Listar Boletins\n"
                + "3. Alterar Nota\n"
                + "4. Alterar Dados Professor\n"
                + "0. Sair\n"
                + "Escolha uma opção: "));
        switch (opcao) {
            case 1:
                listarAlunos();
                break;
            case 2:
                listarBoletins();
                break;
            case 3:
                alterarNota();
                break;
            case 4:
                alterarDadosProfessor();
                break;
            case 0:
                System.out.println("Saindo...");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida!");
                break;
        }
    }

    public void listarAlunos() {
        try {
            ControllerProfessor controller = new ControllerProfessor();
            List<Aluno> alunos = controller.listarAlunos(professor.getIdEscola());
            StringBuilder sb = new StringBuilder();
            sb.append("Alunos:\n");
            for (Aluno aluno : alunos) {
                sb.append("ID: ").append(aluno.getIdAluno()).append(", Nome: ").append(aluno.getNomeAluno()).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
            menu();
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar os alunos: " + e.getMessage());
        }
    }

    public void listarBoletins() {
        try {
            ControllerProfessor controller = new ControllerProfessor();
            System.out.println(professor.getIdDisciplina());
            List<Boletim> boletins = controller.listarBoletins(professor.getIdDisciplina());
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

    public void alterarNota() {
        try {
            int idAluno = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do aluno:"));
            double novaNota = Double.parseDouble(JOptionPane.showInputDialog("Digite a nova nota:"));
            ControllerBoletim controller = new ControllerBoletim();
            controller.alterarNotaBoletim(idAluno, professor.getIdDisciplina(), novaNota);
            JOptionPane.showMessageDialog(null, "Nota do boletim alterada com sucesso.");
            menu();
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar a nota do boletim: " + e.getMessage());
        }
    }

    public void alterarDadosProfessor() {
        try {
            String novoNome = JOptionPane.showInputDialog("Digite o novo nome do professor:");
            professor.setNomeProfessor(novoNome);
            ControllerProfessor controller = new ControllerProfessor();
            controller.alterarProfessor(professor);
            JOptionPane.showMessageDialog(null, "Dados do professor alterados com sucesso.");
            menu();
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar os dados do professor: " + e.getMessage());
        }
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

}
