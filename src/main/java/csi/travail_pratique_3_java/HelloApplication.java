package csi.travail_pratique_3_java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private static Stage stage;
    private static final int LARGEUR_APPLICATION = 600;
    private static final int HAUTEUR_APPLICATION = 400;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), LARGEUR_APPLICATION, HAUTEUR_APPLICATION);

        HelloApplication.stage = stage;
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
