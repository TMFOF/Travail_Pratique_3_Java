package csi.travail_pratique_3_java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class ControllerBalleRebon {

    @FXML
    private Button boutonAjouterDesBalles;
    @FXML
    private Button boutonAjouterUneBalle;
    @FXML
    private Button boutonRetirerUneBalle;
    @FXML
    private Pane zoneJeu;

    private ArrayList<Circle> cercles   = new ArrayList<>();
    private ArrayList<ImageView> images    = new ArrayList<>();
    private ArrayList<Double>    vitessesX = new ArrayList<>();
    private ArrayList<Double>    vitessesY = new ArrayList<>();
    private ArrayList<String>    types     = new ArrayList<>();


    private int    compteur = 0;
    private Random random   = new Random();
    private Image imgRoche;
    private Image imgPapier;
    private Image imgCiseau;

    private static final int RAYON = 25;

    @FXML
    void onBoutonAjouterDesBalles(ActionEvent event) {
        ajouterUneBalle();
        ajouterUneBalle();
        ajouterUneBalle();

    }

    @FXML
    void onBoutonAjouterUneBalle(ActionEvent event) {
        ajouterUneBalle();
    }

    @FXML
    void onBoutonRetirerUneBalle(ActionEvent event) {

    }

    private void ajouterUneBalle() {

        // Choisir le type selon l'ordre roche → papier → ciseau → roche → ...
        String type;
        if      (compteur % 3 == 0) type = "roche";
        else if (compteur % 3 == 1) type = "papier";
        else                        type = "ciseau";
        compteur++;

        // Position aléatoire
        double x = RAYON + random.nextDouble() * (zoneJeu.getWidth()  - RAYON * 2);
        double y = RAYON + random.nextDouble() * (zoneJeu.getHeight() - RAYON * 2);

        // Vitesse aléatoire (positive = droite/bas, négative = gauche/haut)
        double vx;
        if (random.nextBoolean()) {
            vx = 2.0;
        } else {
            vx = -2.0;
        }

        double vy;
        if (random.nextBoolean()) {
            vy = 2.0;
        } else {
            vy = -2.0;
        }

        // Créer le cercle (rond noir transparent dedans)
        Circle cercle = new Circle(x, y, RAYON);
        cercle.setFill(Color.TRANSPARENT);
        cercle.setStroke(Color.BLACK);
        cercle.setStrokeWidth(2);

        // Créer l'image à l'intérieur
        ImageView imageView = new ImageView(obtenirImage(type));
        imageView.setFitWidth(RAYON * 2);
        imageView.setFitHeight(RAYON * 2);
        imageView.setLayoutX(x - RAYON);
        imageView.setLayoutY(y - RAYON);

        // Sauvegarder dans les listes
        cercles.add(cercle);
        images.add(imageView);
        vitessesX.add(vx);
        vitessesY.add(vy);
        types.add(type);

        // Afficher dans la zone de jeu (image en dessous, cercle par-dessus)
        zoneJeu.getChildren().addAll(imageView, cercle);
    }

    private Image obtenirImage(String type) {
        if (type.equals("roche"))  return imgRoche;
        if (type.equals("papier")) return imgPapier;
        return imgCiseau;
    }


    private HelloController helloController;
    public void setHelloController(HelloController helloController) {
        this.helloController = helloController;
    }

}
