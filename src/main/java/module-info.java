module csi.travail_pratique_3_java {
    requires javafx.controls;
    requires javafx.fxml;


    opens csi.travail_pratique_3_java to javafx.fxml;
    exports csi.travail_pratique_3_java;
}