/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasiperpustakaan;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author ASUS
 */
public class Controller implements Initializable {
    
    //FXML//    
    @FXML private Pane MenuAkun;
    @FXML private Pane MenuTambah;
    @FXML private Pane MenuBuku;
    @FXML private Pane MenuExit;
    
    @FXML
    void AktifMenuAkun(MouseEvent event) {
        MenuAkun.setBackground(new Background(new BackgroundFill(Color.valueOf("#013a63"), CornerRadii.EMPTY, Insets.EMPTY)));
        MenuTambah.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        MenuBuku.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        MenuExit.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    
    @FXML
    void AktifMenuTambah(MouseEvent event) {
        MenuAkun.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        MenuTambah.setBackground(new Background(new BackgroundFill(Color.valueOf("#013a63"), CornerRadii.EMPTY, Insets.EMPTY)));
        MenuBuku.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        MenuExit.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    
    @FXML
    void AktifMenuBuku(MouseEvent event) {
        MenuAkun.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        MenuTambah.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        MenuBuku.setBackground(new Background(new BackgroundFill(Color.valueOf("#013a63"), CornerRadii.EMPTY, Insets.EMPTY)));
        MenuExit.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    
    @FXML
    void exit(MouseEvent event) {
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
