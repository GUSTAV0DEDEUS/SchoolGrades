package viewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import padraomvc.PadraoMVCJavaFX;

public class RedirecionarPorTipoController implements Initializable {

    @FXML
    private ComboBox<String> tipoContaComboBox;

    @FXML
    private Label mensagemLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Adicionando os itens ao ComboBox
        tipoContaComboBox.setItems(FXCollections.observableArrayList("aluno", "professor", "escola"));
    }

    @FXML
    private void selecionarTipoConta() {
        String tipoContaSelecionado = tipoContaComboBox.getSelectionModel().getSelectedItem();
        if (tipoContaSelecionado != null) {
            mensagemLabel.setText("Tipo de usuário selecionado: " + tipoContaSelecionado);
        }
    }

    @FXML
    private void confirmarSelecao(ActionEvent e) {
        String tipoContaSelecionado = tipoContaComboBox.getSelectionModel().getSelectedItem();
        Parent newScreen;
        FXMLLoader loader = null;
        Stage stg;
        if (tipoContaSelecionado != null) {
            switch (tipoContaSelecionado) {
                case "aluno": {
                    try {
                        loader = new FXMLLoader(PadraoMVCJavaFX.class.getResource("/view/criarAluno.fxml"));
                        newScreen = loader.load();
                        stg = PadraoMVCJavaFX.getStage();
                        stg.setScene(new Scene(newScreen));
                        stg.show();
                    } catch (IOException ex) {
                        Logger.getLogger(RedirecionarPorTipoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                break;

                case "professor": {
                    try {
                        loader = new FXMLLoader(PadraoMVCJavaFX.class.getResource("/view/criarProfessor.fxml"));
                        newScreen = loader.load();

                        stg = PadraoMVCJavaFX.getStage();
                        stg.setScene(new Scene(newScreen));
                        stg.show();
                    } catch (IOException ex) {
                        Logger.getLogger(RedirecionarPorTipoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                break;

                case "escola":
                    try {
                        loader = new FXMLLoader(PadraoMVCJavaFX.class.getResource("/view/criarEscola.fxml"));
                        newScreen = loader.load();
                        stg = PadraoMVCJavaFX.getStage();
                        stg.setScene(new Scene(newScreen));
                        stg.show();
                    } catch (IOException ex) {
                        Logger.getLogger(RedirecionarPorTipoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                default:
                    System.exit(0);
                    break;
            }
        }
    }
}
