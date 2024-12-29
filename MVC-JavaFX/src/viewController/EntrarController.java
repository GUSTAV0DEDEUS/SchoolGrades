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

public class EntrarController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField senhaField;

    private static Usuario usuarioOut;

    @FXML
    private void entrar() {
        String email = emailField.getText();
        String senha = senhaField.getText();

        try {
            ControllerUsuario contU = new ControllerUsuario();
            Usuario usuario = contU.validarCredenciais(email, senha);
            if (usuario != null) {
                usuarioOut = usuario;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Bem-vindo");
                alert.setHeaderText(null);
                alert.setContentText("Bem-vindo, " + usuario.getTipoUsuario());
                alert.showAndWait();

                Parent newScreen;
                FXMLLoader loader = null;
                Stage stg;
                switch (usuario.getTipoUsuario()) {

                    case "escola":
                        loader = new FXMLLoader(PadraoMVCJavaFX.class.getResource("/view/dashboard/dashboardEscola.fxml"));
                        newScreen = loader.load();
                        stg = PadraoMVCJavaFX.getStage();
                        stg.setScene(new Scene(newScreen));
                        stg.show();
                        break;
                    case "aluno":
                        loader = new FXMLLoader(PadraoMVCJavaFX.class.getResource("/view/dashboard/dashboardAluno.fxml"));
                        newScreen = loader.load();
                        stg = PadraoMVCJavaFX.getStage();
                        stg.setScene(new Scene(newScreen));
                        stg.show();
                        break;
                    case "professor":
                        loader = new FXMLLoader(PadraoMVCJavaFX.class.getResource("/view/dashboard/dashboardProfessor.fxml"));
                        newScreen = loader.load();
                        stg = PadraoMVCJavaFX.getStage();
                        stg.setScene(new Scene(newScreen));
                        stg.show();
                        break;
                    default:
                        mostrarAlerta("Erro", "Houve algum erro interno, tente criar uma nova conta ou contatar sua escola.");
                        break;
                }
            } else {
                mostrarAlerta("Erro", "Credenciais inválidas. Tente novamente.");
            }
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            mostrarAlerta("Erro", "Erro ao tentar entrar: \n" + ex.getMessage());
            System.out.println(ex);
        }
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public static Usuario getUsuarioOut() {
        return usuarioOut;
    }
}
