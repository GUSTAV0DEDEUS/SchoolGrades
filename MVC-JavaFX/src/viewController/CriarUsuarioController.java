package viewController;

import controller.ControllerUsuario;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.bean.Usuario;
import padraomvc.PadraoMVCJavaFX;

public class CriarUsuarioController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField senhaField;

    private static String tipoUsuario;
    private static int idUsuario;

    CriarProfessorController tipoProfessor = new CriarProfessorController();
    CriarAlunoController tipoAluno = new CriarAlunoController();
    CriarEscolaController tipoEscola = new CriarEscolaController();

    private void refresh() {
        if ("professor".equals(tipoProfessor.getTipoUsuario())) {
            tipoUsuario = tipoProfessor.getTipoUsuario();
            idUsuario = tipoProfessor.getIdProfessor();
        } else if ("aluno".equals(tipoAluno.getTipoUsuario())) {
            tipoUsuario = tipoAluno.getTipoUsuario();
            idUsuario = CriarAlunoController.getIdAluno();
        } else if ("escola".equals(tipoEscola.getTipoUsuario())) {
            tipoUsuario = tipoEscola.getTipoUsuario();
            idUsuario = CriarEscolaController.getIdEscola();
        } else {
            mostrarAlerta("Erro", "Nenhum dado");
        }
    }

    @FXML
    private void cadastrar() {
        try {
            if (tipoUsuario == null || idUsuario == 0) {
                mostrarAlerta("Erro", "Tipo de usuário ou ID não especificado.");
                return;
            }

            String email = emailField.getText();
            String senha = senhaField.getText();

            if (email.isEmpty() || senha.isEmpty()) {
                mostrarAlerta("Erro", "Por favor, preencha todos os campos.");
                return;
            }

            ControllerUsuario contU = new ControllerUsuario();
            Usuario usuario = new Usuario(idUsuario, tipoUsuario, email, senha);

            contU.criarUsuario(usuario);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sucesso");
            alert.setHeaderText(null);
            alert.setContentText("Usuário criado com sucesso");
            alert.showAndWait();

            FXMLLoader loader = new FXMLLoader(PadraoMVCJavaFX.class.getResource("/view/entrar.fxml"));
            Parent  newScreen = loader.load();
            Stage stg = PadraoMVCJavaFX.getStage();
            stg.setScene(new Scene(newScreen));
            stg.show();
        } catch (ClassNotFoundException | SQLException | IOException ex) {
            mostrarAlerta("Erro", "Erro ao criar usuário. Por favor, tente novamente.");
        }
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML
    public void initialize() {
        refresh();
    }
}
