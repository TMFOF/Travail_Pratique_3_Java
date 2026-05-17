package csi.travail_pratique_3_java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private static Stage stage;
    private static final int LARGEUR_APPLICATION = 900;
    private static final int HAUTEUR_APPLICATION = 6700;

    @Override
    public void start(Stage stage) throws IOException {

        stage.setMinWidth(900);
        stage.setMinHeight(700);
        stage.setMaxWidth(1200);
        stage.setMaxHeight(700);

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), LARGEUR_APPLICATION, HAUTEUR_APPLICATION);

        HelloApplication.stage = stage;
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getStage() {
        return stage;}
}
