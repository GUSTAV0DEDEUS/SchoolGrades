package viewController;

import controller.ControllerBoletim;
import controller.ControllerProfessor;
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
import model.bean.Boletim;
import model.bean.Professor;
import model.bean.Usuario;

/**
 * FXML Controller class
 *
 * @author samuel
 */
public class DashboardProfessorController implements Initializable {

    @FXML
    private TextArea textAreaAlunos;

    @FXML
    private TextArea textAreaBoletins;

    @FXML
    private ComboBox<Aluno> comboBoxAlunos;

    @FXML
    private TextField textFieldNovaNota;

    @FXML
    private TextField textFieldNovoNome;

    private int idProfessor;
    private int idEscola;
    private int idDisciplina;

    private Professor prof;
    private Usuario user;

    @FXML
    private void listarAlunos() {
        try {
            ControllerProfessor controller = new ControllerProfessor();
            List<Aluno> alunos = controller.listarAlunos(idEscola);
            StringBuilder sb = new StringBuilder();
            sb.append("Alunos:\n");
            for (Aluno aluno : alunos) {
                sb.append("ID: ").append(aluno.getIdAluno()).append(", Nome: ").append(aluno.getNomeAluno()).append("\n");
            }
            textAreaAlunos.setText(sb.toString());
        } catch (SQLException | ClassNotFoundException e) {
            mostrarErro("Erro ao listar os alunos: " + e.getMessage());
        }
    }

    @FXML
    private void listarBoletins() {
        try {
            ControllerProfessor controller = new ControllerProfessor();
            List<Boletim> boletins = controller.listarBoletins(idDisciplina);
            StringBuilder sb = new StringBuilder();
            sb.append("Boletins:\n");
            for (Boletim boletim : boletins) {
                sb.append("ID: ").append(boletim.getIdBoletim()).append(", Nota: ").append(boletim.getNota()).append("\n");
            }
            textAreaBoletins.setText(sb.toString());
        } catch (SQLException | ClassNotFoundException e) {
            mostrarErro("Erro ao listar os boletins: " + e.getMessage());
        }
    }

    @FXML
    private void alterarNota() {
        try {
            Aluno alunoSelecionado = comboBoxAlunos.getValue();
            if (alunoSelecionado != null) {
                int idAluno = alunoSelecionado.getIdAluno();
                double novaNota = Double.parseDouble(textFieldNovaNota.getText());
                ControllerBoletim controller = new ControllerBoletim();
                controller.alterarNotaBoletim(idAluno, idDisciplina, novaNota);
                mostrarMensagem("Nota do boletim alterada com sucesso.");
                listarAlunos();
                listarBoletins();
            } else {
                mostrarErro("Selecione um aluno para alterar a nota.");
            }
        } catch (SQLException | ClassNotFoundException | NumberFormatException e) {
            mostrarErro("Erro ao alterar a nota do boletim: " + e.getMessage());
        }
    }

    @FXML
    private void alterarDadosProfessor() {
        try {
            String novoNome = textFieldNovoNome.getText();
            ControllerProfessor controller = new ControllerProfessor();
            Professor prof = controller.buscarProfessor(idProfessor);
            prof.setNomeProfessor(novoNome);
            controller.alterarProfessor(prof);
            mostrarMensagem("Dados do professor alterados com sucesso.");
            listarAlunos();
            listarBoletins();
        } catch (SQLException | ClassNotFoundException e) {
            mostrarErro("Erro ao alterar os dados do professor: " + e.getMessage());
        }
    }

    private void popularComboBoxAlunos() {
        try {
            ControllerProfessor controller = new ControllerProfessor();
            List<Aluno> alunos = controller.listarAlunos(idDisciplina);
            comboBoxAlunos.getItems().addAll(alunos);
        } catch (SQLException | ClassNotFoundException e) {
            mostrarErro("Erro ao preencher o ComboBox de alunos: " + e.getMessage());
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
        try {
            user = EntrarController.getUsuarioOut();
            ControllerProfessor profCont = new ControllerProfessor();
            idProfessor = user.getIdUsuario();
            prof = profCont.buscarProfessor(idProfessor);
            idEscola = prof.getIdEscola();
            idDisciplina = prof.getIdDisciplina();
            popularComboBoxAlunos();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DashboardProfessorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
