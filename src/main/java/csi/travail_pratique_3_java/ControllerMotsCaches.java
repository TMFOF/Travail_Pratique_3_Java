package csi.travail_pratique_3_java;

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

    @FXML
    private Button boutonSuivant;
    @FXML
    private Button boutonValider;
    @FXML
    private TextField champReponse;
    @FXML
    private ComboBox<String> comboTheme;
    @FXML
    private Label labelEssais;
    @FXML
    private Label labelIndice;
    @FXML
    private Label labelMessage;
    @FXML
    private Label labelMotCache;
    @FXML
    private Label labelScore;
    @FXML
    private MediaView mediaView;
    @FXML
    private BorderPane panePrincipal;

    @FXML
    void onBoutonJouerMusique(ActionEvent event) {

    }

    @FXML
    void onBoutonJouerVideo(ActionEvent event) {
        labelIndice.setText(motsCache);


    }

    @FXML
    void onBoutonMotSuivant(ActionEvent event) {
        labelMessage.setText("hello");
        labelEssais.setText("essais");

    }

    @FXML
    void onBoutonValider(ActionEvent event) {
        String ok;
        ok = champReponse.getText();
        labelScore.setText(ok);

    }

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
    Random aleatoire = new Random();
    int index = aleatoire.nextInt(mots.length);
    String motsCache = mots[index];


}
