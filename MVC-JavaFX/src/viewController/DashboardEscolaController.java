package viewController;

import controller.ControllerDisciplina;
import controller.ControllerEscola;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.bean.Aluno;
import model.bean.Disciplina;
import model.bean.Escola;
import model.bean.Professor;
import model.bean.Usuario;

public class DashboardEscolaController implements Initializable {

    @FXML
    private TextField textFieldNovoNomeEscola;

    @FXML
    private TextField textFieldNovoEndereco;

    @FXML
    private TextField textFieldNovaCidade;

    @FXML
    private TextField textFieldNovoEstado;

    @FXML
    private ComboBox<String> comboBoxAlunosDeletar;

    @FXML
    private ComboBox<String> comboBoxProfessoresDeletar;

    @FXML
    private ComboBox<String> comboBoxDisciplinasExcluir;

    @FXML
    private ComboBox<String> comboBoxIdDisciplinaAlterar;

    @FXML
    private ComboBox<String> comboBoxIdDisciplinaCriarBoletins;

    @FXML
    private TextField textFieldNomeDisciplina;

    @FXML
    private TextField textFieldNovoNomeDisciplina;

    @FXML
    private TextArea textAreaAlunos;

    @FXML
    private TextArea textAreaProfessores;

    @FXML
    private TextArea textAreaDisciplinas;

    private Escola escola;
    private Usuario user;

    @FXML
    private void alterarEscola() {
        String novoNome = textFieldNovoNomeEscola.getText();
        String novoEndereco = textFieldNovoEndereco.getText();
        String novaCidade = textFieldNovaCidade.getText();
        String novoEstado = textFieldNovoEstado.getText();

        escola.setNomeEscola(novoNome);
        escola.setEndereco(novoEndereco);
        escola.setCidade(novaCidade);
        escola.setEstado(novoEstado);

        try {
            ControllerEscola controllerEscola = new ControllerEscola();
            controllerEscola.alterarEscola(escola);
            mostrarMensagem("Escola alterada com sucesso.");
        } catch (SQLException | ClassNotFoundException e) {
            mostrarErro("Erro ao alterar a escola: " + e.getMessage());
        }
    }

    @FXML
    private void deletarEscola() {
        try {
            ControllerEscola controllerEscola = new ControllerEscola();
            controllerEscola.deletarEscola(escola.getIdEscola());
            mostrarMensagem("Escola deletada com sucesso.");
        } catch (SQLException | ClassNotFoundException e) {
            mostrarErro("Erro ao deletar a escola: " + e.getMessage());
        }
    }

    @FXML
    private void listarAlunos() {
        try {
            ControllerEscola controllerEscola = new ControllerEscola();
            List<Aluno> alunos = controllerEscola.listarAlunos(escola.getIdEscola());
            StringBuilder sb = new StringBuilder();
            sb.append("Alunos da Escola:\n");
            for (Aluno aluno : alunos) {
                sb.append("ID: ").append(aluno.getIdAluno()).append(", Nome: ").append(aluno.getNomeAluno()).append("\n");
            }
            textAreaAlunos.setText(sb.toString());
        } catch (SQLException | ClassNotFoundException e) {
            mostrarErro("Erro ao listar os alunos: " + e.getMessage());
        }
    }

    @FXML
    private void listarProfessores() {
        try {
            ControllerEscola controllerEscola = new ControllerEscola();
            List<Professor> professores = controllerEscola.listarProfessores(escola.getIdEscola());
            StringBuilder sb = new StringBuilder();
            sb.append("Professores da Escola:\n");
            for (Professor professor : professores) {
                sb.append("ID: ").append(professor.getIdProfessor()).append(", Nome: ").append(professor.getNomeProfessor()).append("\n");
            }
            textAreaProfessores.setText(sb.toString());
        } catch (SQLException | ClassNotFoundException e) {
            mostrarErro("Erro ao listar os professores: " + e.getMessage());
        }
    }

    @FXML
    private void deletarAluno() {
        int alunoSelecionado = -1;
        alunoSelecionado = Integer.parseInt(comboBoxAlunosDeletar.getValue().split(" ")[0]);
        if (alunoSelecionado != -1) {
            try {
                ControllerEscola controllerEscola = new ControllerEscola();
                controllerEscola.deletarAluno(alunoSelecionado);
                mostrarMensagem("Aluno deletado com sucesso.");
                popularComboBoxes();
            } catch (SQLException | ClassNotFoundException e) {
                mostrarErro("Erro ao deletar aluno: " + e.getMessage());
            }
        } else {
            mostrarErro("Selecione um aluno para deletar.");
        }
    }

    @FXML
    private void deletarProfessor() {

        int professorSelecionado = -1;
        professorSelecionado = Integer.parseInt(comboBoxProfessoresDeletar.getValue().split(" ")[0]);
        if (professorSelecionado != -1) {
            try {
                ControllerEscola controllerEscola = new ControllerEscola();
                controllerEscola.deletarProfessor(professorSelecionado);
                mostrarMensagem("Professor deletado com sucesso.");
                popularComboBoxes();
            } catch (SQLException | ClassNotFoundException e) {
                mostrarErro("Erro ao deletar professor: " + e.getMessage());
            }
        } else {
            mostrarErro("Selecione um professor para deletar.");
        }
    }

    @FXML
    private void excluirDisciplina() {
        int disciplinaSelecionada = -1;
        disciplinaSelecionada = Integer.parseInt(comboBoxDisciplinasExcluir.getValue().split(" ")[0]);
        if (disciplinaSelecionada != -1) {
            try {
                ControllerDisciplina controllerDisciplina = new ControllerDisciplina();
                controllerDisciplina.excluirDisciplina(disciplinaSelecionada);
                mostrarMensagem("Disciplina excluída com sucesso.");
                popularComboBoxes();
            } catch (SQLException | ClassNotFoundException e) {
                mostrarErro("Erro ao excluir disciplina: " + e.getMessage());
            }
        } else {
            mostrarErro("Selecione uma disciplina para excluir.");
        }
    }

    @FXML
    private void criarDisciplina() {
        String nomeDisciplina = textFieldNomeDisciplina.getText();
        Disciplina disciplina = new Disciplina(nomeDisciplina, escola.getIdEscola());

        try {
            ControllerDisciplina controllerDisciplina = new ControllerDisciplina();
            controllerDisciplina.criarDisciplina(disciplina);
            mostrarMensagem("Disciplina criada com sucesso.");
            popularComboBoxes();
        } catch (SQLException | ClassNotFoundException e) {
            mostrarErro("Erro ao criar a disciplina: " + e.getMessage());
        }
    }

    @FXML
    private void alterarDisciplina() {
        String novoNome = textFieldNovoNomeDisciplina.getText();
        int disciplinaSelecionada = -1;
        disciplinaSelecionada = Integer.parseInt(comboBoxIdDisciplinaAlterar.getValue().split(" ")[0]);
        if (disciplinaSelecionada != -1) {
            Disciplina newDisciplina = new Disciplina(disciplinaSelecionada, novoNome, escola.getIdEscola());
            try {
                ControllerDisciplina controllerDisciplina = new ControllerDisciplina();
                controllerDisciplina.alterarDisciplina(newDisciplina);
                mostrarMensagem("Nome da disciplina alterado com sucesso.");
                popularComboBoxes();
            } catch (SQLException | ClassNotFoundException e) {
                mostrarErro("Erro ao alterar o nome da disciplina: " + e.getMessage());
            }
        } else {
            mostrarErro("Selecione uma disciplina para alterar.");
        }
    }

    private void popularComboBoxes() {
        try {
            ControllerEscola controllerEscola = new ControllerEscola();

            // Popular ComboBox de Alunos
            List<Aluno> alunos = controllerEscola.listarAlunos(escola.getIdEscola());
            comboBoxAlunosDeletar.getItems().clear();
            for (Aluno aluno : alunos) {
                comboBoxAlunosDeletar.getItems().add(aluno.getIdAluno() + " - " + aluno.getNomeAluno());
            }

            // Popular ComboBox de Professores
            List<Professor> professores = controllerEscola.listarProfessores(escola.getIdEscola());
            comboBoxProfessoresDeletar.getItems().clear();
            for (Professor professor : professores) {
                comboBoxProfessoresDeletar.getItems().add(professor.getIdProfessor() + " - " + professor.getNomeProfessor());
            }

            // Popular ComboBox de Disciplinas para excluir
            List<Disciplina> disciplinas = controllerEscola.listarDisciplinas(escola.getIdEscola());
            comboBoxDisciplinasExcluir.getItems().clear();
            for (Disciplina disciplina : disciplinas) {
                comboBoxDisciplinasExcluir.getItems().add(disciplina.getIdDisciplina() + " - " + disciplina.getNomeDisciplina());
            }

            // Popular ComboBox de Disciplinas para alterar nome
            comboBoxIdDisciplinaAlterar.getItems().clear();
            for (Disciplina disciplina : disciplinas) {
                comboBoxIdDisciplinaAlterar.getItems().add(disciplina.getIdDisciplina() + " - " + disciplina.getNomeDisciplina());
            }

            // Popular ComboBox de Disciplinas para criar boletins
            comboBoxIdDisciplinaCriarBoletins.getItems().clear();
            for (Disciplina disciplina : disciplinas) {
                comboBoxIdDisciplinaCriarBoletins.getItems().add(disciplina.getIdDisciplina() + " - " + disciplina.getNomeDisciplina());
            }
        } catch (SQLException | ClassNotFoundException e) {
            mostrarErro("Erro ao popular os ComboBoxes: " + e.getMessage());
        }
    }

    @FXML
    private void listarDisciplinas() {
        try {
            ControllerEscola controllerEscola = new ControllerEscola();
            List<Disciplina> disciplinas = controllerEscola.listarDisciplinas(escola.getIdEscola());
            StringBuilder sb = new StringBuilder();
            sb.append("Disciplinas da Escola:\n");
            for (Disciplina disciplina : disciplinas) {
                sb.append("ID: ").append(disciplina.getIdDisciplina()).append(", Nome: ").append(disciplina.getNomeDisciplina()).append("\n");
            }
            textAreaDisciplinas.setText(sb.toString());
        } catch (SQLException | ClassNotFoundException e) {
            mostrarErro("Erro ao listar as disciplinas: " + e.getMessage());
        }
    }

    @FXML
    private void criarBoletins() {
        int idDisciplinaSelecionada = Integer.parseInt(comboBoxIdDisciplinaCriarBoletins.getValue().split(" ")[0]);
        try {
            ControllerEscola controllerEscola = new ControllerEscola();
            controllerEscola.criarBoletinsParaTodosAlunos(escola.getIdEscola(), idDisciplinaSelecionada);
            mostrarMensagem("Boletins criados com sucesso.");
            popularComboBoxes();
        } catch (SQLException | ClassNotFoundException e) {
            mostrarErro("Erro ao criar os boletins: " + e.getMessage());
        }
    }

    private void mostrarMensagem(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void mostrarErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user = EntrarController.getUsuarioOut();
        ControllerEscola escolaCont;
        try {
            escolaCont = new ControllerEscola();
            escola = escolaCont.buscarPorId(user.getIdUsuario());
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DashboardEscolaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        popularComboBoxes();
    }
}
