package viewController;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.bean.Escola;
import controller.ControllerEscola;
import java.io.IOException;

import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import padraomvc.PadraoMVCJavaFX;

public class CriarEscolaController {

    @FXML
    private TextField nomeField;

    @FXML
    private TextField enderecoField;

    @FXML
    private TextField cidadeField;

    @FXML
    private TextField estadoField;

    private static int idEscola;
    private static String tipoUsuario;

    public static int getIdEscola() {
        return idEscola;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    @FXML
    private void inserirEscola() {
        String nome = nomeField.getText();
        String endereco = enderecoField.getText();
        String cidade = cidadeField.getText();
        String estado = estadoField.getText();

        Escola escolaEnt = new Escola(nome, endereco, cidade, estado);

        try {
            ControllerEscola contE = new ControllerEscola();
            Escola escolaCriada = contE.criarEscola(escolaEnt);

            if (escolaCriada != null) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso");
                alert.setHeaderText(null);
                alert.setContentText("Escola criada com sucesso!");
                alert.showAndWait();
                idEscola = escolaCriada.getIdEscola();
                tipoUsuario = "escola";
                FXMLLoader loader = new FXMLLoader(PadraoMVCJavaFX.class.getResource("/view/criarUsuario.fxml"));
                Parent newScreen = loader.load();
                Stage stg = PadraoMVCJavaFX.getStage();
                stg.setScene(new Scene(newScreen));
                stg.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("Falha ao criar a escola. Verifique os dados e tente novamente.");
                alert.showAndWait();
            }

        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

}
