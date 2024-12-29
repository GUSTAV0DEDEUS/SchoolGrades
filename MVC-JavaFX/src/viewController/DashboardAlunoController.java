package viewController;

import controller.ControllerAluno;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Aluno;
import model.bean.Boletim;
import model.bean.Usuario;

public class DashboardAlunoController implements Initializable {

    @FXML
    private TextField textFieldNome;

    @FXML
    private DatePicker datePickerDataNascimento;

    @FXML
    private Button buttonAlterar;

    @FXML
    private ListView<String> listViewBoletins;

    private ObservableList<String> boletinsObservableList;

    private ControllerAluno controllerAluno;
    private Usuario usuEnt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            controllerAluno = new ControllerAluno();
            datePickerDataNascimento.setValue(LocalDate.now());
            usuEnt = EntrarController.getUsuarioOut();
            carregarBoletins();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DashboardAlunoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void alterarAluno() {
        String novoNome = textFieldNome.getText();
        LocalDate novaDataNascimento = datePickerDataNascimento.getValue();
        if (!novoNome.isEmpty() && novaDataNascimento != null) {
            try {
                Aluno alunoEnt = controllerAluno.buscarAlunoPorId(usuEnt.getIdUsuario());
                alunoEnt.setDataNascimento(java.sql.Date.valueOf(novaDataNascimento));
                alunoEnt.setNomeAluno(novoNome);
                controllerAluno.alterarAluno(alunoEnt);
                carregarBoletins();

                mostrarMensagem("Dados do aluno alterados com sucesso.");
            } catch (SQLException e) {
                mostrarErro("Erro ao alterar os dados do aluno: " + e.getMessage());
            }
        } else {
            mostrarErro("Por favor, preencha todos os campos.");
        }
    }

    private void carregarBoletins() {
        try {
            List<Boletim> boletimL = controllerAluno.listarBoletinsPorIdAluno(usuEnt.getIdUsuario());
            List<String> stringBoletim = new ArrayList<>();
            for (Boletim boletim : boletimL) {
                stringBoletim.add(boletim.toString());
            }
            boletinsObservableList = FXCollections.observableArrayList(stringBoletim);
            listViewBoletins.setItems(boletinsObservableList);
        } catch (SQLException e) {
            mostrarErro("Erro ao carregar os boletins do aluno: " + e.getMessage());
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
}
