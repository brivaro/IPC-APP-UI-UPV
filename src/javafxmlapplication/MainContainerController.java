/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import DBAccess.NavegacionDAOException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import static javafx.application.Application.STYLESHEET_CASPIAN;
import static javafx.application.Application.launch;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Navegacion;
import model.User;

/**
 * FXML Controller class
 *
 * @author Brian
 */
public class MainContainerController implements Initializable {

    @FXML
    private Button iniciarSesion;
    @FXML
    private Button registrarse;
    private Navegacion nav;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            nav = Navegacion.getSingletonNavegacion();
            Application.setUserAgentStylesheet(STYLESHEET_CASPIAN);
        } catch (NavegacionDAOException ex) {
            Logger.getLogger(MainContainerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    

    @FXML
    private void iniciarSesion(ActionEvent event) throws IOException {
        FXMLLoader miCargador1 = new FXMLLoader(getClass().getResource("/Controllers/FXMLLogin.fxml"));
        Parent root = miCargador1.load();
        FXMLLoginController controladorIniciar = miCargador1.getController();
        
        Scene scene = new Scene(root,834,390);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Iniciar sesión estudiante");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
        controladorIniciar.initVentana(iniciarSesion);
    }

    @FXML
    private void registrarse(ActionEvent event) throws IOException {
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/Controllers/FXMLSignUp.fxml"));
        Parent root = miCargador.load();
        FXMLSignUpController controladorRegistrar = miCargador.getController();
        
        Scene scene = new Scene(root,840,551);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Registrar nuevo estudiante");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
        controladorRegistrar.initButton(registrarse);
    }

    @FXML
    private void manoRaton(MouseEvent event) {
        registrarse.setCursor(Cursor.HAND);
        iniciarSesion.setCursor(Cursor.HAND);
    }

    @FXML
    private void timon(KeyEvent event) throws IOException {
        if(event.getCode()==KeyCode.LEFT) iniciarSesion(new ActionEvent());
        if(event.getCode()==KeyCode.RIGHT) registrarse(new ActionEvent());
    }

}
