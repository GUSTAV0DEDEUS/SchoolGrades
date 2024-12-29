package viewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.bean.Usuario;
import padraomvc.PadraoMVCJavaFX;

/**
 * FXML Controller class
 *
 * @author samuel
 */
public class HomeTelaController implements Initializable {

    static Usuario usuario;
    static Object usuarioTipo;

    @FXML
    private TextField indexMenu;

    @FXML
    private ComboBox<String> menuOptions;

    @FXML
    private void confirmButtonAction(ActionEvent event) {
        // Lógica para lidar com a ação do botão de confirmação
        String selectedItem = menuOptions.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            System.out.println("Opção selecionada: " + selectedItem);
            try {
                switchScene(Integer.parseInt(selectedItem.substring(0, 1)));
            } catch (IOException e) {
                System.out.println("Erro ao carregar a tela: " + e.getMessage());
            }
        } else {
            openAlert("Nenhuma opção selecionada");
        }
    }

    private void switchScene(int index) throws IOException {
        Parent newScreen;
        FXMLLoader loader = null;
        Stage stg;
        switch (index) {
            case 1:
                loader = new FXMLLoader(PadraoMVCJavaFX.class.getResource("/view/redirecionarPorTipo.fxml"));
                newScreen = loader.load();
                stg = PadraoMVCJavaFX.getStage();
                stg.setScene(new Scene(newScreen));
                stg.show();
                break;
            case 2:
                loader = new FXMLLoader(PadraoMVCJavaFX.class.getResource("/view/entrar.fxml"));
                newScreen = loader.load();
                stg = PadraoMVCJavaFX.getStage();
                stg.setScene(new Scene(newScreen));
                stg.show();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                openAlert("Selecione uma opção válida");
                break;
        }
    }

    private void openAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setContentText(message);
        alert.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializa o ComboBox com os itens desejados
        menuOptions.setItems(FXCollections.observableArrayList("1- Cadastrar", "2- Entrar", "0- Sair"));
    }

}
