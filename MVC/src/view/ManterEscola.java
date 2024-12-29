/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.ControllerAluno;
import controller.ControllerDisciplina;
import controller.ControllerEscola;
import controller.ControllerProfessor;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Aluno;
import model.bean.Disciplina;
import model.bean.Escola;
import model.bean.Professor;

/**
 *
 * @author samuel
 */
public class ManterEscola {

    private Escola escola;

    public Escola inserirEscola() {
        String nome = JOptionPane.showInputDialog("Digite o nome da escola:");
        String endereco = JOptionPane.showInputDialog("Digite o endereço da escola:");
        String cidade = JOptionPane.showInputDialog("Digite a cidade da escola:");
        String estado = JOptionPane.showInputDialog("Digite o estado da escola (sigla):");

        Escola escolaEnt = new Escola(nome, endereco, cidade, estado);

        try {
            ControllerEscola contE = new ControllerEscola();
            return contE.criarEscola(escolaEnt);
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar a escola: " + e.getMessage());
            return null;
        }
    }

    public void menu() {
        int opcao = Integer.parseInt(JOptionPane.showInputDialog("### Menu de Escola ###\n"
                + "1. Alterar Escola\n"
                + "2. Deletar Escola\n"
                + "3. Listar Alunos\n"
                + "4. Listar Professores\n"
                + "5. Deletar Aluno\n"
                + "6. Deletar Professore\n"
                + "7. Listar Diciplinas\n"
                + "8. Criar Boletins\n"
                + "9. Criar diciplina\n"
                + "10. Alterar diciplina\n"
                + "11. Deletar diciplina\n"
                + "0. Sair\n"
                + "Escolha uma opção: "));
        switch (opcao) {
            case 1:
                alterarEscola();
                break;
            case 2:
                deletEscola();
                break;
            case 3:
                listarAlunos();
                break;
            case 4:
                listarProfessores();
                break;
            case 5:
                deletarAluno();
                break;
            case 6:
                deletarProfessor();
                break;
            case 7:
                listarDiciplinas();
                break;
            case 8:
                criarBoletins();
                break;
            case 9:
                criarDisciplina();
                break;
            case 10:
                alterarDisciplina();
                break;
            case 11:
                excluirDisciplina();
                break;

            case 0:
                System.out.println("Saindo...");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida!");
                break;
        }
    }

    public void alterarEscola() {
        try {
            ControllerEscola contE = new ControllerEscola();
            Escola escolaEnt = escola;
            if (escolaEnt != null) {
                String nome = JOptionPane.showInputDialog("Digite o novo nome da escola:");
                String endereco = JOptionPane.showInputDialog("Digite o novo endereço da escola:");
                String cidade = JOptionPane.showInputDialog("Digite a nova cidade da escola:");
                String estado = JOptionPane.showInputDialog("Digite o novo estado da escola (sigla):");

                escolaEnt.setNomeEscola(nome);
                escolaEnt.setEndereco(endereco);
                escolaEnt.setCidade(cidade);
                escolaEnt.setEstado(estado);

                contE.alterarEscola(escolaEnt);
                JOptionPane.showMessageDialog(null, "Escola alterada com sucesso.");
                escola = escolaEnt;
                menu();
            } else {
                JOptionPane.showMessageDialog(null, "Escola não encontrada!");
            }
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar a escola: " + e.getMessage());
        }
    }

    public void deletEscola() {
        try {
            int idEscola = escola.getIdEscola();
            ControllerEscola contE = new ControllerEscola();
            contE.deletarEscola(idEscola);
            JOptionPane.showMessageDialog(null, "Escola deletada com sucesso.");
            escola = null;
            System.exit(0);
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar a escola: " + e.getMessage());
        }
    }

    public void listarAlunos() {
        try {
            int idEscola = escola.getIdEscola();
            ControllerEscola contE = new ControllerEscola();
            List<Aluno> alunos = contE.listarAlunos(idEscola);
            StringBuilder sb = new StringBuilder();
            sb.append("Alunos da Escola:\n");
            for (Aluno aluno : alunos) {
                sb.append("ID: ").append(aluno.getIdAluno()).append(", Nome: ").append(aluno.getNomeAluno()).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
            menu();
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar os alunos da escola: " + e.getMessage());
        }
    }

    public void listarProfessores() {
        try {
            int idEscola = escola.getIdEscola();
            ControllerEscola contE = new ControllerEscola();
            List<Professor> professores = contE.listarProfessores(idEscola);
            StringBuilder sb = new StringBuilder();
            sb.append("Professores da Escola:\n");
            for (Professor professor : professores) {
                sb.append("ID: ").append(professor.getIdProfessor()).append(", Nome: ").append(professor.getNomeProfessor()).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
            menu();
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar os professores da escola: " + e.getMessage());
        }
    }

    public void deletarAluno() {
        try {
            if (escola != null) {
                int idAluno = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do aluno que deseja deletar:"));
                ControllerAluno contA = new ControllerAluno();
                ControllerEscola contE = new ControllerEscola();

                Aluno aluno = contA.buscarAlunoPorId(idAluno);
                if (aluno != null && aluno.getIdEscola() == escola.getIdEscola()) {
                    contE.deletarAluno(idAluno);
                    JOptionPane.showMessageDialog(null, "Aluno deletado com sucesso.");
                } else {
                    JOptionPane.showMessageDialog(null, "O aluno não foi encontrado ou não pertence a esta escola.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nenhuma escola selecionada.");
            }
            menu();
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar o aluno: " + e.getMessage());
        }
    }

    public void deletarProfessor() {
        try {
            if (escola != null) {
                int idProfessor = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do professor que deseja deletar:"));
                ControllerProfessor contP = new ControllerProfessor();
                ControllerEscola contE = new ControllerEscola();

                Professor professor = contP.buscarProfessor(idProfessor);
                if (professor != null && professor.getIdEscola() == escola.getIdEscola()) {
                    contE.deletarProfessor(idProfessor);
                    JOptionPane.showMessageDialog(null, "Professor deletado com sucesso.");
                } else {
                    JOptionPane.showMessageDialog(null, "O professor não foi encontrado ou não pertence a esta escola.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nenhuma escola selecionada.");
            }
            menu();
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar o professor: " + e.getMessage());
        }
    }

    public void listarDiciplinas() {
        try {
            int idEscola = escola.getIdEscola();
            ControllerEscola contE = new ControllerEscola();
            List<Disciplina> disciplinas = contE.listarDisciplinas(idEscola);
            StringBuilder sb = new StringBuilder();
            sb.append("Disciplinas da Escola:\n");
            for (Disciplina disciplina : disciplinas) {
                sb.append("ID: ").append(disciplina.getIdDisciplina()).append(", Nome: ").append(disciplina.getNomeDisciplina()).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
            menu();
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar as disciplinas da escola: " + e.getMessage());
        }
    }

    public void criarBoletins() {
        try {
            if (escola != null) {
                int idEscola = escola.getIdEscola();
                int idDisciplina = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da disciplina para criar boletins:"));

                // Verifica se a disciplina pertence à escola atual
                ControllerDisciplina contD = new ControllerDisciplina();
                Disciplina disciplina = contD.buscarDisciplinaPorId(idDisciplina);
                if (disciplina != null && disciplina.getIdEscola() == idEscola) {
                    ControllerEscola contE = new ControllerEscola();
                    contE.criarBoletinsParaTodosAlunos(idEscola, idDisciplina);
                    JOptionPane.showMessageDialog(null, "Boletins criados com sucesso.");
                } else {
                    JOptionPane.showMessageDialog(null, "A disciplina não foi encontrada ou não pertence a esta escola.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nenhuma escola selecionada.");
            }
            menu();
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar os boletins: " + e.getMessage());
        }
    }

    public void criarDisciplina() {
        try {
            String nomeDisciplina = JOptionPane.showInputDialog("Digite o nome da disciplina:");
            int idEscola = escola.getIdEscola();
            Disciplina disciplina = new Disciplina(nomeDisciplina, idEscola);
            ControllerDisciplina contD = new ControllerDisciplina();
            contD.criarDisciplina(disciplina);
            JOptionPane.showMessageDialog(null, "Disciplina criada com sucesso.");
            menu();
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar a disciplina: " + e.getMessage());
        }
    }

    public void alterarDisciplina() {
        try {
            ControllerDisciplina cd = new ControllerDisciplina();
            int idDisciplina = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da disciplina para alterar:"));
            Disciplina disciplinaExistente = cd.buscarDisciplinaPorId(idDisciplina);

            if (disciplinaExistente != null && disciplinaExistente.getIdEscola() == escola.getIdEscola()) {
                String novoNome = JOptionPane.showInputDialog("Digite o novo nome da disciplina:");
                Disciplina disciplina = new Disciplina(idDisciplina, novoNome, escola.getIdEscola());

                ControllerDisciplina contD = new ControllerDisciplina();
                contD.alterarDisciplina(disciplina);
                JOptionPane.showMessageDialog(null, "Disciplina alterada com sucesso.");
                menu();
            } else {
                JOptionPane.showMessageDialog(null, "A disciplina não foi encontrada ou não pertence a esta escola.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar a disciplina: " + e.getMessage());
        }
    }

    public void excluirDisciplina() {
        try {
            ControllerDisciplina cd = new ControllerDisciplina();
            int idDisciplina = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da disciplina para excluir:"));
            Disciplina disciplinaExistente = cd.buscarDisciplinaPorId(idDisciplina);

            if (disciplinaExistente != null && disciplinaExistente.getIdEscola() == escola.getIdEscola()) {
                ControllerDisciplina contD = new ControllerDisciplina();
                contD.excluirDisciplina(idDisciplina);
                JOptionPane.showMessageDialog(null, "Disciplina excluída com sucesso.");
                menu();
            } else {
                JOptionPane.showMessageDialog(null, "A disciplina não foi encontrada ou não pertence a esta escola.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir a disciplina: " + e.getMessage());
        }
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

}
