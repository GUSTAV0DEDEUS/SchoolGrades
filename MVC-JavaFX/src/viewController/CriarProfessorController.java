/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package viewController;

import controller.ControllerDisciplina;
import controller.ControllerEscola;
import controller.ControllerProfessor;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.bean.Disciplina;
import model.bean.Escola;
import model.bean.Professor;

import java.sql.SQLException;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import padraomvc.PadraoMVCJavaFX;

/**
 * FXML Controller class
 *
 * @author samuel
 */
public class CriarProfessorController {

    @FXML
    private TextField nomeField;

    @FXML
    private ComboBox<Escola> escolasComboBox;

    @FXML
    private ComboBox<Disciplina> disciplinasComboBox;

    private static int idProfessor;
    private static String tipoUsuario;

    private ControllerEscola contE;
    private ControllerDisciplina contD;
    private ControllerProfessor contP;

    @FXML
    public void initialize() {
        try {
            contE = new ControllerEscola();
            contD = new ControllerDisciplina();
            carregarEscolas();
            escolasComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                escolaSelecionada();
            });
        } catch (SQLException | ClassNotFoundException e) {
            exibirAlertaErro("Erro ao inicializar", "Falha ao carregar dados", e.getMessage());
        }
    }

    private void carregarEscolas() throws SQLException, ClassNotFoundException {
        List<Escola> escolas = contE.listarTodasEscolas();
        escolasComboBox.getItems().addAll(escolas);
    }

    @FXML
    private void escolaSelecionada() {
        try {
            Escola escolaSelecionada = escolasComboBox.getSelectionModel().getSelectedItem();
            if (escolaSelecionada != null) {
                int idEscola = escolaSelecionada.getIdEscola();
                List<Disciplina> disciplinas = contD.listarDisciplinasPorIdEscola(idEscola);
                disciplinasComboBox.getItems().clear();
                disciplinasComboBox.getItems().addAll(disciplinas);
            }
        } catch (SQLException e) {
            exibirAlertaErro("Erro ao carregar disciplinas", "Falha ao carregar dados", e.getMessage());
        }
    }

    @FXML
    private void inserirProfessor() {
        try {
            String nome = nomeField.getText();
            Escola escolaSelecionada = escolasComboBox.getValue();
            Disciplina disciplinaSelecionada = disciplinasComboBox.getValue();

            if (nome.isEmpty() || escolaSelecionada == null || disciplinaSelecionada == null) {
                exibirAlertaErro("Dados incompletos", "Por favor, preencha todos os campos", null);
                return;
            }

            Professor professor = new Professor(escolaSelecionada.getIdEscola(), disciplinaSelecionada.getIdDisciplina(), nome, disciplinaSelecionada.getNomeDisciplina());
            contP = new ControllerProfessor();
            Professor professorOut = contP.criarProfessor(professor);

            exibirAlertaSucesso("Sucesso", "Professor criado com sucesso!");

            idProfessor = professorOut.getIdProfessor();
            tipoUsuario = "professor";

            FXMLLoader loader = new FXMLLoader(PadraoMVCJavaFX.class.getResource("/view/criarUsuario.fxml"));
            Stage stg = PadraoMVCJavaFX.getStage();
            Parent newScreen = loader.load();
            stg.setScene(new Scene(newScreen));
            stg.show();

        } catch (SQLException | ClassNotFoundException | IOException e) {
            exibirAlertaErro("Erro ao criar professor", "Falha ao criar professor", e.getMessage());
        }
    }

    private void exibirAlertaErro(String titulo, String cabecalho, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void exibirAlertaSucesso(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

}
