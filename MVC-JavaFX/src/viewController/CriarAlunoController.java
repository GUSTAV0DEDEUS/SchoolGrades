package viewController;

import controller.ControllerAluno;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.bean.Aluno;
import model.bean.Escola;
import model.DAO.DAOEscola;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import padraomvc.PadraoMVCJavaFX;

public class CriarAlunoController {

    @FXML
    private TextField nomeField;

    @FXML
    private DatePicker dataNascimentoPicker;

    @FXML
    private ComboBox<Escola> escolasComboBox;

    private List<Escola> escolas;

    private DAOEscola escolaDAO;

    private Aluno alunoCriado;

    private static int idAluno;
    private static String tipoUsuario;

    public static int getIdAluno() {
        return idAluno;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public Aluno getAlunoCriado() {
        return alunoCriado;
    }

    @FXML
    private void initialize() {
        try {
            escolaDAO = new DAOEscola();
            carregarEscolas();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CriarAlunoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void carregarEscolas() {
        try {
            escolas = escolaDAO.listarTodasEscolas();
            escolasComboBox.setItems(FXCollections.observableArrayList(escolas));
        } catch (SQLException ex) {
            Logger.getLogger(CriarAlunoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleCancelar() {
        System.exit(0);
    }

    @FXML
    private void handleSalvar() {
        try {
            String nome = nomeField.getText();
            LocalDate dataNascimento = dataNascimentoPicker.getValue();

            Integer idEscola = null;
            Escola escolaSelecionada = escolaSelecionada();
            if (escolaSelecionada != null) {
                idEscola = escolaSelecionada.getIdEscola();
            }

            if (nome == null || nome.isEmpty() || dataNascimento == null || idEscola == null) {
                return;
            }

            Aluno aluno = new Aluno(idEscola, nome, java.sql.Date.valueOf(dataNascimento));

            ControllerAluno contA = new ControllerAluno();
            alunoCriado = contA.criarAluno(aluno);

            if (alunoCriado != null) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Sucesso");
                alert.setHeaderText(null);
                alert.setContentText("Aluno criado com sucesso!");

                alert.showAndWait();
                idAluno = alunoCriado.getIdAluno();
                tipoUsuario = "aluno";
                FXMLLoader loader = new FXMLLoader(PadraoMVCJavaFX.class.getResource("/view/criarUsuario.fxml"));
                Parent newScreen = loader.load();
                Stage stg = PadraoMVCJavaFX.getStage();
                stg.setScene(new Scene(newScreen));
                stg.show();

            }
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(CriarAlunoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private Escola escolaSelecionada() throws NumberFormatException {
        Escola escola = escolasComboBox.getSelectionModel().getSelectedItem();
        if (escola == null) {
            return null;
        }
        return escola;
    }
}
