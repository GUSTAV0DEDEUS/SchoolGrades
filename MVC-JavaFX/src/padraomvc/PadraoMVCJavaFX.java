/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package padraomvc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author samuel
 */
public class PadraoMVCJavaFX extends Application {

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        PadraoMVCJavaFX.stage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/home.fxml"));
        Parent root = loader.load();
        primaryStage.centerOnScreen();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(PadraoMVCJavaFX.class, args);
    }

    public static Stage getStage() {
        return stage;
    }

}
