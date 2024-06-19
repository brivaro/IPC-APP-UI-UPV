/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import DBAccess.NavegacionDAOException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Navegacion;
import model.Problem;
import model.Session;
import model.User;

/**
 * FXML Controller class
 *
 * @author Brian
 */
public class FXMLListaProblemasController implements Initializable {

    @FXML
    private MenuItem cerrar;
    private Navegacion nav;
    @FXML
    private Label label1;
    @FXML
    private Button select1;
    @FXML
    private Label label11;
    @FXML
    private Button select11;
    @FXML
    private Label label12;
    @FXML
    private Button select12;
    @FXML
    private Label label13;
    @FXML
    private Button select13;
    private List<Problem> problemas;
    private Button buton;
    private String name;
    private Image image;
    @FXML
    private Button atras;
    private Button b;
    private User user;
    private int aciertos;
    private int fallos;
    private Image ima;
    @FXML
    private MenuItem cerrarsesion;
    @FXML
    private Label label131;
    @FXML
    private Button select131;
    @FXML
    private Label label132;
    @FXML
    private Button select132;
    @FXML
    private Label label133;
    @FXML
    private Button select133;
    @FXML
    private Label label134;
    @FXML
    private Button select134;
    @FXML
    private Label label135;
    @FXML
    private Button select135;
    @FXML
    private Label label136;
    @FXML
    private Button select136;
    @FXML
    private Label label137;
    @FXML
    private Button select137;
    @FXML
    private Label label138;
    @FXML
    private Button select138;
    @FXML
    private Label label139;
    @FXML
    private Button select139;
    @FXML
    private Label label1310;
    @FXML
    private Button select1310;
    @FXML
    private Label label1311;
    @FXML
    private Button select1311;
    @FXML
    private Label label1312;
    @FXML
    private Button select1312;
    @FXML
    private Label label1313;
    @FXML
    private Button select1313;
    @FXML
    private Label label1314;
    @FXML
    private Button select1314;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            nav = Navegacion.getSingletonNavegacion();
        } catch (NavegacionDAOException ex) {
            Logger.getLogger(FXMLListaProblemasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        problemas = nav.getProblems();
        label1.setText(problemas.get(0).getText());
        label11.setText(problemas.get(1).getText());
        label12.setText(problemas.get(2).getText());
        label13.setText(problemas.get(3).getText());
        
        ///////FALTA HACER LOS METODOS DE ENUNCIADO 5... ENUNCIADO 17
        label131.setText(problemas.get(4).getText());;
        label132.setText(problemas.get(5).getText());;
        label133.setText(problemas.get(6).getText());;
        label134.setText(problemas.get(7).getText());;
        label135.setText(problemas.get(8).getText());;
        label136.setText(problemas.get(9).getText());;
        label137.setText(problemas.get(10).getText());;
        label138.setText(problemas.get(11).getText());;
        label139.setText(problemas.get(12).getText());;
        label1310.setText(problemas.get(13).getText());;
        label1311.setText(problemas.get(14).getText());;
        label1312.setText(problemas.get(15).getText());;
        label1313.setText(problemas.get(16).getText());;
        label1314.setText(problemas.get(17).getText());;
        
    }    

    @FXML
    private void cerrarApp(ActionEvent event) throws NavegacionDAOException, IOException {
        if(!cerrar.isDisable()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            //Cambia el icono por uno propio
            Stage stage= (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/icons/efw.png")));
            alert.setTitle("Aviso");
            alert.setHeaderText("El programa se va a cerrar.");
            alert.setContentText("¿Seguro que desea continuar?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK){
            System.out.println("OK"); 
                FXMLLoader miCargadorX = new FXMLLoader(getClass().getResource("/Controllers/FXMLMapa.fxml"));
                Parent rootX = miCargadorX.load();
                FXMLMapaController controladorIniciarX = miCargadorX.getController();
                
                LocalDateTime d = LocalDateTime.now();
                Session s = new Session(d,controladorIniciarX.getHit(),controladorIniciarX.getFault());
                user.addSession(s);
            System.exit(0);
            } else {
            System.out.println("CANCEL");
            } 
        }
    }

    @FXML
    private void enunciado1(ActionEvent event) throws IOException {
        System.out.println(label1.getText());
        FXMLLoader miCargador1 = new FXMLLoader(getClass().getResource("/Controllers/FXMLMapa.fxml"));
        Parent root = miCargador1.load();
        FXMLMapaController controladorIniciar = miCargador1.getController();
        controladorIniciar.initProblema(label1.getText(),problemas.get(0),select1,image);
        controladorIniciar.initUser(name,ima);
        controladorIniciar.initButton(b);
        Scene scene = new Scene(root,1181,731);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Carta Náutica");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);//tiene q redimensionarse
        stage.show();
        select1.getScene().getWindow().hide();
    
    }

    @FXML
    private void enunciado2(ActionEvent event) throws IOException {
        System.out.println(label11.getText());
        FXMLLoader miCargador1 = new FXMLLoader(getClass().getResource("/Controllers/FXMLMapa.fxml"));
        Parent root = miCargador1.load();
        FXMLMapaController controladorIniciar = miCargador1.getController();
        controladorIniciar.initProblema(label11.getText(),problemas.get(1),select1,image);
        controladorIniciar.initUser(name,ima);
        controladorIniciar.initButton(b);
        Scene scene = new Scene(root,1181,731);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Carta Náutica");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);//tiene q redimensionarse
        stage.show();
        select1.getScene().getWindow().hide();
    }

    @FXML
    private void enunciado3(ActionEvent event) throws IOException {
        System.out.println(label12.getText());
        FXMLLoader miCargador1 = new FXMLLoader(getClass().getResource("/Controllers/FXMLMapa.fxml"));
        Parent root = miCargador1.load();
        FXMLMapaController controladorIniciar = miCargador1.getController();
        controladorIniciar.initProblema(label12.getText(),problemas.get(2),select1,image);
        controladorIniciar.initUser(name,ima);
        controladorIniciar.initButton(b);
        Scene scene = new Scene(root,1181,731);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Carta Náutica");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);//tiene q redimensionarse
        stage.show();
        select1.getScene().getWindow().hide();
    }

    @FXML
    private void enunciado4(ActionEvent event) throws IOException {
        System.out.println(label13.getText());
        FXMLLoader miCargador1 = new FXMLLoader(getClass().getResource("/Controllers/FXMLMapa.fxml"));
        Parent root = miCargador1.load();
        FXMLMapaController controladorIniciar = miCargador1.getController();
        controladorIniciar.initProblema(label13.getText(),problemas.get(3),select1,image);
        controladorIniciar.initUser(name,ima);
        controladorIniciar.initButton(b);
        Scene scene = new Scene(root,1181,731);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Carta Náutica");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);//tiene q redimensionarse
        stage.show();
        select1.getScene().getWindow().hide();
    }

    void initAvatar(Image i) {
        image = i;
    }

    @FXML
    private void atras(ActionEvent event) {
        atras.getScene().getWindow().hide();
    }

    void initButton(Button ayuda) {
        b = ayuda;
    }

    void initUser(String n, Image i) {
        name = n;
        user = nav.getUser(name);
        ima = i;
    }

    @FXML
    private void mano(MouseEvent event) {
        atras.setCursor(Cursor.HAND); 
        select1.setCursor(Cursor.HAND);
        select11.setCursor(Cursor.HAND); 
        select12.setCursor(Cursor.HAND); 
        select13.setCursor(Cursor.HAND); 
    }

    @FXML
    private void cerrarSesion(ActionEvent event) throws IOException, NavegacionDAOException {
        if(!cerrarsesion.isDisable()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            //Cambia el icono por uno propio
            Stage stagee= (Stage) alert.getDialogPane().getScene().getWindow();
            stagee.getIcons().add(new Image(this.getClass().getResourceAsStream("/icons/efw.png")));
            alert.setTitle("Aviso");
            alert.setHeaderText("La sesión de " + user.getNickName() + " va a cerrarse.");
            alert.setContentText("¿Seguro que desea continuar?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK){
                System.out.println("OK"); 
                FXMLLoader miCargador1 = new FXMLLoader(getClass().getResource("/Controllers/MainContainer.fxml"));
                Parent root = miCargador1.load();
                MainContainerController controladorIniciar = miCargador1.getController();
                b.getScene().getWindow().hide();
                atras.getScene().getWindow().hide();
                FXMLLoader miCargadorX = new FXMLLoader(getClass().getResource("/Controllers/FXMLMapa.fxml"));
                Parent rootX = miCargadorX.load();
                FXMLMapaController controladorIniciarX = miCargadorX.getController();
                
                LocalDateTime d = LocalDateTime.now();
                Session s = new Session(d,controladorIniciarX.getHit(),controladorIniciarX.getFault());
                user.addSession(s);
                
                Scene scene = new Scene(root,790,528);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("KartoMapp");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(false);
                stage.show();
            } else {
                System.out.println("CANCEL");
            } 
        }
        
    }

    @FXML
    private void enunciado5(ActionEvent event) throws IOException {
        System.out.println(label131.getText());
        FXMLLoader miCargador1 = new FXMLLoader(getClass().getResource("/Controllers/FXMLMapa.fxml"));
        Parent root = miCargador1.load();
        FXMLMapaController controladorIniciar = miCargador1.getController();
        controladorIniciar.initProblema(label131.getText(),problemas.get(4),select131,image);
        controladorIniciar.initUser(name,ima);
        controladorIniciar.initButton(b);
        Scene scene = new Scene(root,1181,731);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Carta Náutica");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);//tiene q redimensionarse
        stage.show();
        select131.getScene().getWindow().hide();
    }

    @FXML
    private void enunciado6(ActionEvent event) throws IOException {
        System.out.println(label132.getText());
        FXMLLoader miCargador1 = new FXMLLoader(getClass().getResource("/Controllers/FXMLMapa.fxml"));
        Parent root = miCargador1.load();
        FXMLMapaController controladorIniciar = miCargador1.getController();
        controladorIniciar.initProblema(label132.getText(),problemas.get(5),select132,image);
        controladorIniciar.initUser(name,ima);
        controladorIniciar.initButton(b);
        Scene scene = new Scene(root,1181,731);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Carta Náutica");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);//tiene q redimensionarse
        stage.show();
        select132.getScene().getWindow().hide();
    }

    @FXML
    private void enunciado7(ActionEvent event) throws IOException {
        System.out.println(label133.getText());
        FXMLLoader miCargador1 = new FXMLLoader(getClass().getResource("/Controllers/FXMLMapa.fxml"));
        Parent root = miCargador1.load();
        FXMLMapaController controladorIniciar = miCargador1.getController();
        controladorIniciar.initProblema(label133.getText(),problemas.get(6),select133,image);
        controladorIniciar.initUser(name,ima);
        controladorIniciar.initButton(b);
        Scene scene = new Scene(root,1181,731);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Carta Náutica");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);//tiene q redimensionarse
        stage.show();
        select133.getScene().getWindow().hide();
        
    }

    @FXML
    private void enunciado8(ActionEvent event) throws IOException {
        System.out.println(label134.getText());
        FXMLLoader miCargador1 = new FXMLLoader(getClass().getResource("/Controllers/FXMLMapa.fxml"));
        Parent root = miCargador1.load();
        FXMLMapaController controladorIniciar = miCargador1.getController();
        controladorIniciar.initProblema(label134.getText(),problemas.get(7),select134,image);
        controladorIniciar.initUser(name,ima);
        controladorIniciar.initButton(b);
        Scene scene = new Scene(root,1181,731);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Carta Náutica");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);//tiene q redimensionarse
        stage.show();
        select134.getScene().getWindow().hide();
    }

    @FXML
    private void enunciado9(ActionEvent event) throws IOException {
        System.out.println(label135.getText());
        FXMLLoader miCargador1 = new FXMLLoader(getClass().getResource("/Controllers/FXMLMapa.fxml"));
        Parent root = miCargador1.load();
        FXMLMapaController controladorIniciar = miCargador1.getController();
        controladorIniciar.initProblema(label135.getText(),problemas.get(8),select135,image);
        controladorIniciar.initUser(name,ima);
        controladorIniciar.initButton(b);
        Scene scene = new Scene(root,1181,731);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Carta Náutica");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);//tiene q redimensionarse
        stage.show();
        select135.getScene().getWindow().hide();
    }

    @FXML
    private void enunciado10(ActionEvent event) throws IOException {
        System.out.println(label136.getText());
        FXMLLoader miCargador1 = new FXMLLoader(getClass().getResource("/Controllers/FXMLMapa.fxml"));
        Parent root = miCargador1.load();
        FXMLMapaController controladorIniciar = miCargador1.getController();
        controladorIniciar.initProblema(label136.getText(),problemas.get(9),select136,image);
        controladorIniciar.initUser(name,ima);
        controladorIniciar.initButton(b);
        Scene scene = new Scene(root,1181,731);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Carta Náutica");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);//tiene q redimensionarse
        stage.show();
        select136.getScene().getWindow().hide();
    }

    @FXML
    private void enunciado11(ActionEvent event) throws IOException {
        System.out.println(label137.getText());
        FXMLLoader miCargador1 = new FXMLLoader(getClass().getResource("/Controllers/FXMLMapa.fxml"));
        Parent root = miCargador1.load();
        FXMLMapaController controladorIniciar = miCargador1.getController();
        controladorIniciar.initProblema(label137.getText(),problemas.get(10),select137,image);
        controladorIniciar.initUser(name,ima);
        controladorIniciar.initButton(b);
        Scene scene = new Scene(root,1181,731);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Carta Náutica");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);//tiene q redimensionarse
        stage.show();
        select137.getScene().getWindow().hide();
        
    }

    @FXML
    private void enunciado12(ActionEvent event) throws IOException {
        System.out.println(label138.getText());
        FXMLLoader miCargador1 = new FXMLLoader(getClass().getResource("/Controllers/FXMLMapa.fxml"));
        Parent root = miCargador1.load();
        FXMLMapaController controladorIniciar = miCargador1.getController();
        controladorIniciar.initProblema(label138.getText(),problemas.get(11),select138,image);
        controladorIniciar.initUser(name,ima);
        controladorIniciar.initButton(b);
        Scene scene = new Scene(root,1181,731);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Carta Náutica");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);//tiene q redimensionarse
        stage.show();
        select138.getScene().getWindow().hide();
    }

    @FXML
    private void enunciado13(ActionEvent event) throws IOException {
        System.out.println(label139.getText());
        FXMLLoader miCargador1 = new FXMLLoader(getClass().getResource("/Controllers/FXMLMapa.fxml"));
        Parent root = miCargador1.load();
        FXMLMapaController controladorIniciar = miCargador1.getController();
        controladorIniciar.initProblema(label139.getText(),problemas.get(12),select139,image);
        controladorIniciar.initUser(name,ima);
        controladorIniciar.initButton(b);
        Scene scene = new Scene(root,1181,731);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Carta Náutica");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);//tiene q redimensionarse
        stage.show();
        select139.getScene().getWindow().hide();
    }

    @FXML
    private void enunciado14(ActionEvent event) throws IOException {
        System.out.println(label1310.getText());
        FXMLLoader miCargador1 = new FXMLLoader(getClass().getResource("/Controllers/FXMLMapa.fxml"));
        Parent root = miCargador1.load();
        FXMLMapaController controladorIniciar = miCargador1.getController();
        controladorIniciar.initProblema(label1310.getText(),problemas.get(13),select1310,image);
        controladorIniciar.initUser(name,ima);
        controladorIniciar.initButton(b);
        Scene scene = new Scene(root,1181,731);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Carta Náutica");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);//tiene q redimensionarse
        stage.show();
        select1310.getScene().getWindow().hide();
    }

    @FXML
    private void enunciado15(ActionEvent event) throws IOException {
        System.out.println(label1311.getText());
        FXMLLoader miCargador1 = new FXMLLoader(getClass().getResource("/Controllers/FXMLMapa.fxml"));
        Parent root = miCargador1.load();
        FXMLMapaController controladorIniciar = miCargador1.getController();
        controladorIniciar.initProblema(label1311.getText(),problemas.get(14),select1311,image);
        controladorIniciar.initUser(name,ima);
        controladorIniciar.initButton(b);
        Scene scene = new Scene(root,1181,731);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Carta Náutica");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);//tiene q redimensionarse
        stage.show();
        select1311.getScene().getWindow().hide();
        
    }

    @FXML
    private void enunciado16(ActionEvent event) throws IOException {
        System.out.println(label1312.getText());
        FXMLLoader miCargador1 = new FXMLLoader(getClass().getResource("/Controllers/FXMLMapa.fxml"));
        Parent root = miCargador1.load();
        FXMLMapaController controladorIniciar = miCargador1.getController();
        controladorIniciar.initProblema(label1312.getText(),problemas.get(16),select1312,image);
        controladorIniciar.initUser(name,ima);
        controladorIniciar.initButton(b);
        Scene scene = new Scene(root,1181,731);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Carta Náutica");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);//tiene q redimensionarse
        stage.show();
        select1312.getScene().getWindow().hide();
        
    }

    @FXML
    private void enunciado17(ActionEvent event) throws IOException {
        System.out.println(label1313.getText());
        FXMLLoader miCargador1 = new FXMLLoader(getClass().getResource("/Controllers/FXMLMapa.fxml"));
        Parent root = miCargador1.load();
        FXMLMapaController controladorIniciar = miCargador1.getController();
        controladorIniciar.initProblema(label1313.getText(),problemas.get(16),select1313,image);
        controladorIniciar.initUser(name,ima);
        controladorIniciar.initButton(b);
        Scene scene = new Scene(root,1181,731);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Carta Náutica");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);//tiene q redimensionarse
        stage.show();
        select1313.getScene().getWindow().hide();
    }

    @FXML
    private void enunciado18(ActionEvent event) throws IOException {
        System.out.println(label1314.getText());
        FXMLLoader miCargador1 = new FXMLLoader(getClass().getResource("/Controllers/FXMLMapa.fxml"));
        Parent root = miCargador1.load();
        FXMLMapaController controladorIniciar = miCargador1.getController();
        controladorIniciar.initProblema(label1314.getText(),problemas.get(17),select1314,image);
        controladorIniciar.initUser(name,ima);
        controladorIniciar.initButton(b);
        Scene scene = new Scene(root,1181,731);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Carta Náutica");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);//tiene q redimensionarse
        stage.show();
        select1314.getScene().getWindow().hide();
    }
    
    
}

