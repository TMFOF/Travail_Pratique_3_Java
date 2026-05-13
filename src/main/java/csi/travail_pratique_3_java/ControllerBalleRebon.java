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
                verifierToutesLesCollisions();
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

    private void verifierToutesLesCollisions() {

        for (int i = 0; i < cercles.size(); i++) {
            for (int j = i + 1; j < cercles.size(); j++) {

                Circle a = cercles.get(i);
                Circle b = cercles.get(j);

                // Calculer la distance entre les deux centres (Pythagore)
                double distX = a.getCenterX() - b.getCenterX();
                double distY = a.getCenterY() - b.getCenterY();
                double dist  = Math.sqrt(distX * distX + distY * distY);

                // Si distance < 2 rayons → les balles se touchent
                if (dist < RAYON * 2) {

                    String typeA = types.get(i);
                    String typeB = types.get(j);

                    // Roche bat Ciseau
                    if (typeA.equals("roche") && typeB.equals("ciseau")) {
                        types.set(j, "roche");
                        images.get(j).setImage(imgRoche);

                        // Ciseau bat Papier
                    } else if (typeA.equals("ciseau") && typeB.equals("papier")) {
                        types.set(j, "ciseau");
                        images.get(j).setImage(imgCiseau);

                        // Papier bat Roche
                    } else if (typeA.equals("papier") && typeB.equals("roche")) {
                        types.set(j, "papier");
                        images.get(j).setImage(imgPapier);

                        // Roche bat Ciseau (B gagne)
                    } else if (typeB.equals("roche") && typeA.equals("ciseau")) {
                        types.set(i, "roche");
                        images.get(i).setImage(imgRoche);

                        // Ciseau bat Papier (B gagne)
                    } else if (typeB.equals("ciseau") && typeA.equals("papier")) {
                        types.set(i, "ciseau");
                        images.get(i).setImage(imgCiseau);

                        // Papier bat Roche (B gagne)
                    } else if (typeB.equals("papier") && typeA.equals("roche")) {
                        types.set(i, "papier");
                        images.get(i).setImage(imgPapier);
                    }
                    // Même type → égalité, rien ne change

                    // Rebond : les deux balles échangent leurs vitesses
                    double tmpVx = vitessesX.get(i);
                    double tmpVy = vitessesY.get(i);
                    vitessesX.set(i, vitessesX.get(j));
                    vitessesY.set(i, vitessesY.get(j));
                    vitessesX.set(j, tmpVx);
                    vitessesY.set(j, tmpVy);
                }
            }
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
