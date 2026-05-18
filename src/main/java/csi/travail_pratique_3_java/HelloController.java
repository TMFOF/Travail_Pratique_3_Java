package csi.travail_pratique_3_java;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.Node;

import java.io.IOException;

public class HelloController {

    @FXML
    private Button boutonBalleRebon;

    @FXML
    private Button boutonMotsCache;

    @FXML
    private Button boutonQuitter;

    @FXML
    private Button boutonReglement;

    @FXML
    private Pane contenuCentre;

    private ControllerBalleRebon controllerBalleRebon = new ControllerBalleRebon();

    @FXML
    public void initialize() {
        controllerBalleRebon.setHelloController(this);
    }

    @FXML
    void onBoutonBalleRebonClic(ActionEvent event) throws IOException {
        changerContenu("ballesRebondissantes");

    }

    @FXML
    void onBoutonMotsCacheClic(ActionEvent event) throws IOException {
        changerContenu("motsCaches");
    }

    @FXML
    void onBoutonQuitter(ActionEvent event) throws IOException {
        Platform.exit();
    }

    @FXML
    void onBoutonReglements(ActionEvent event) throws IOException {
        changerContenu("reglements");
    }


    private void changerContenu(String fichierFxml) throws IOException {

        FXMLLoader loader = new FXMLLoader(
                HelloApplication.class.getResource(fichierFxml + ".fxml")
        );
        Node vue = loader.load();
        contenuCentre.getChildren().setAll(vue);
    }


}
