package csi.travail_pratique_3_java;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.util.Random;

public class ControllerMotsCaches {

    @FXML private Button boutonSuivant;
    @FXML private Button boutonValider;
    @FXML private TextField champReponse;
    @FXML private ComboBox<?> comboTheme;
    @FXML private Label labelEssais;
    @FXML private Label labelIndice;
    @FXML private Label labelMessage;
    @FXML private Label labelMotCache;
    @FXML private Label labelScore;
    @FXML private MediaView mediaView;
    @FXML private BorderPane panePrincipal;
    String motsPige;


    private IntegerProperty score          = new SimpleIntegerProperty(0);
    private IntegerProperty essaisRestants = new SimpleIntegerProperty(3);
    private StringProperty message        = new SimpleStringProperty("");

    private String[] mots = {
            "CLIC", "ECRAN", "SOURIS", "CLAVIER", "IMPRIMANTE", "DISQUE",
            "CABLE", "WIFI", "RESEAU", "INTERNET", "SITE", "PAGE",
            "LIEN", "IMAGE", "PHOTO", "VIDEO", "SON", "FICHIER", "DOSSIER",
            "BUREAU", "FENETRE", "ICONE", "MENU", "BOUTON", "CURSEUR",
            "TEXTE", "POLICE", "COULEUR", "FOND", "COPIER", "COLLER",
            "COUPER", "SAUVER", "OUVRIR", "FERMER", "QUITTER", "INSTALLER",
            "LANCER", "CHARGER", "ENVOYER", "RECEVOIR", "PARTAGER", "IMPRIMER",
            "SCANNER", "ZOOMER", "PIVOTER", "GLISSER", "CLIQUER", "TAPER",
            "EFFACER", "ANNULER", "VALIDER", "CHERCHER", "TROUVER", "TRIER",
            "FILTRER", "BLOQUER", "ACTIVER", "DESACTIVER", "CONNECTER", "DECONNECTER",
            "ALLUMER", "ETEINDRE", "REDEMARRER", "RESTAURER", "SUPPRIMER", "RENOMMER",
            "DEPLACER", "COMPRESSER", "EXTRAIRE", "CRYPTER", "DECRYPTER", "FORMATER",
            "PARTITIONNER", "IDENTIFIANT", "COMPTE", "PROFIL", "AVATAR", "PSEUDO",
            "EMAIL", "MESSAGE", "CHAT", "FORUM", "BLOG", "SERVEUR", "CLOUD", "MEMOIRE",
            "PROCESSEUR", "BATTERIE", "CHARGEUR", "CASQUE", "WEBCAM",
            "MICROPHONE", "TABLETTE", "TELEPHONE", "ORDINATEUR"
    };

    @FXML
    public void initialize()
    {
        labelScore.textProperty().bind(score.asString("Score : %d"));
        labelEssais.textProperty().bind(essaisRestants.asString("Essais restants : %d"));
        labelMessage.textProperty().bind(message);

        afficherMot();
    }

    // Affiche le mot cache et son indice
    private void afficherMot() {

        Random aleatoire = new Random();
        int index = aleatoire.nextInt(mots.length);
        motsPige = mots[index];

        String motCache = motsPige.charAt(0) + " ";

        for (int i = 1; i < motsPige.length(); i++) {
            motCache = motCache + "* ";
        }

        // Calcul du nombre de lettres a afficher dans l'indice (3 ou 4)
        int nbLettres;

        if (motsPige.length() >= 6) {
            nbLettres = 4;
        } else {
            nbLettres = 3;
        }

        // Construction de l'indice avec les premieres lettres
        String indice = "";
        boolean[] positions = new boolean[motsPige.length()];

        int compteur = 0;

        while (compteur < nbLettres) {
            int position = aleatoire.nextInt(motsPige.length());
            if (!positions[position]) {
                positions[position] = true;
                compteur++;
            }
        }

        for (int i = 0; i < motsPige.length(); i++) {

            if (positions[i]) {

                indice = indice + motsPige.charAt(i);

            } else {
                indice = indice + "*";
            }
        }

        labelMotCache.setText(motCache.trim());
        labelIndice.setText("Indice : " + indice.trim());

        message.set("");
        essaisRestants.set(3);

        champReponse.clear();
        champReponse.requestFocus();

        boutonValider.setDisable(false);
        boutonSuivant.setDisable(true);

        System.out.println(motsPige);
    }



    @FXML void onBoutonJouerMusique(ActionEvent event) {
        Media audio = new Media(this.getClass().getResource("/audio/audio1.mp3").toExternalForm());
        MediaPlayer lecteurMultimedia = new MediaPlayer(audio);
        lecteurMultimedia.play();
    }

    @FXML void onBoutonJouerVideo(ActionEvent event) {
        Media video = new Media(this.getClass().getResource("/video/video.mp4").toExternalForm());
        MediaPlayer lecteurMultimedia = new MediaPlayer(video);
        mediaView.setMediaPlayer(lecteurMultimedia);
    }

    @FXML void onBoutonMotSuivant(ActionEvent event) {
        afficherMot();
    }

    @FXML void onBoutonValider(ActionEvent event) {

        boolean lettreTrouve = true;
        String motTrouve = "";
        String reponse = champReponse.getText().trim().toUpperCase();

        for(int i = 0; i < motsPige.length(); i++)
        {
            if(i < reponse.length() && reponse.charAt(i) == motsPige.charAt(i))
            {
                motTrouve =  motTrouve + motsPige.charAt(i);
            }
            else
            {
                motTrouve = motTrouve + "* ";
                lettreTrouve = false;
            }
        }

        labelMotCache.setText(motTrouve.trim());
        if (lettreTrouve) {
            score.set(score.get() + 10);
            message.set("Bravo !");
            boutonValider.setDisable(true);
            boutonSuivant.setDisable(false);
        } else {
            essaisRestants.set(essaisRestants.get() - 1);
            if (essaisRestants.get() == 0) {
                message.set("Perdu ! Le mot etait : " + motsPige);
                boutonValider.setDisable(true);
                boutonSuivant.setDisable(false);
            } else {
                message.set("Essayez encore !");
            }


        }
    }

}
