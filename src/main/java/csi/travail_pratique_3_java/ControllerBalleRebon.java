package csi.travail_pratique_3_java;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javax.naming.InitialContext;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class ControllerBalleRebon implements Initializable {

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


    public void initialize( URL url, ResourceBundle rb) {

        // Charger les 3 images
        imgRoche  = new Image(getClass().getResourceAsStream("/images/roche.png"));
        imgPapier = new Image(getClass().getResourceAsStream("/images/papier.png"));
        imgCiseau = new Image(getClass().getResourceAsStream("/images/ciseau.png"));

        // Démarrer la boucle de jeu (60 fois par seconde)
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long maintenant) {
                deplacerToutesLesBalles();
            }
        };
        timer.start();

    }

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

        if (cercles.isEmpty()) return;

        int i = cercles.size() - 1; // index de la dernière balle

        zoneJeu.getChildren().remove(cercles.get(i));
        zoneJeu.getChildren().remove(images.get(i));
        cercles.remove(i);
        images.remove(i);
        vitessesX.remove(i);
        vitessesY.remove(i);
        types.remove(i);

        if (compteur > 0) compteur--;
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


    private void deplacerToutesLesBalles() {

        for (int i = 0; i < cercles.size(); i++) {

            Circle cercle = cercles.get(i);
            double x  = cercle.getCenterX();
            double y  = cercle.getCenterY();
            double vx = vitessesX.get(i);
            double vy = vitessesY.get(i);

            // Rebond mur gauche ou droit → inverser la vitesse horizontale
            if (x - RAYON <= 0 || x + RAYON >= zoneJeu.getWidth()) {
                vx = -vx;
                vitessesX.set(i, vx);
            }

            // Rebond mur haut ou bas → inverser la vitesse verticale
            if (y - RAYON <= 0 || y + RAYON >= zoneJeu.getHeight()) {
                vy = -vy;
                vitessesY.set(i, vy);
            }

            // Déplacer le cercle
            cercle.setCenterX(x + vx);
            cercle.setCenterY(y + vy);

            // Déplacer l'image au même endroit
            images.get(i).setLayoutX(cercle.getCenterX() - RAYON);
            images.get(i).setLayoutY(cercle.getCenterY() - RAYON);
        }
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
