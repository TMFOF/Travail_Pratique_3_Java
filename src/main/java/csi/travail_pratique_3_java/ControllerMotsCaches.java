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
        String motsPige = mots[index];

        String motCache = motsPige.charAt(0) + " ";

        for (int i = 1; i < motsPige.length(); i++) {
            motCache = motCache + "* ";
        }

        // Mettre à jour l'affichage du mot et de l'indice
        labelMotCache.setText(motCache.trim());

        message.set("");
        essaisRestants.set(3);

        // Vider le champ et remettre le curseur
        champReponse.clear();
        champReponse.requestFocus();

        // Activer Valider et desactiver Suivant
        boutonValider.setDisable(false);
        boutonSuivant.setDisable(true);
    }




    @FXML void onBoutonJouerMusique(ActionEvent event) {

    }

    @FXML void onBoutonJouerVideo(ActionEvent event) {

    }

    @FXML void onBoutonMotSuivant(ActionEvent event) {

    }

    @FXML void onBoutonValider(ActionEvent event) {

    }

}
