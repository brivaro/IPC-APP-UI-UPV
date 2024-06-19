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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.STYLESHEET_CASPIAN;
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
import javafx.scene.control.RadioMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
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
public class FXMLAppController implements Initializable {

    @FXML
    private MenuItem cerrar;
    @FXML
    private MenuItem perfil;
    @FXML
    private MenuItem bcerrarSesion;
    @FXML
    private Button partida;
    @FXML
    private Button estudiante;
    @FXML
    private Button partida1;
    @FXML
    private ImageView avatar;
    
    private String name;
    private User user;
    private Navegacion nav;
    @FXML
    private MenuItem resultados;
    @FXML
    private Button ayuda;
    @FXML
    private Button ayuda2;
    @FXML
    private ImageView newrecord;
    @FXML
    private ImageView rifa;
    @FXML
    private ImageView emblema;
    @FXML
    private ImageView emblema1;
    @FXML
    private MenuItem acerca;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            nav = Navegacion.getSingletonNavegacion();
          
        } catch (NavegacionDAOException ex) {
            Logger.getLogger(FXMLAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        /*ayuda.getScene().getWindow().setOnCloseRequest(()->{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            //Cambia el icono por uno propio
            Stage stage= (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/icons/efw.png")));
            alert.setTitle("Aviso!");
            alert.setHeaderText("El programa se va a cerrar sin guardar la sesion.");
            alert.setContentText("¿Seguro que desea continuar? \n Si no quiere perder su progreso, cierre sesión o la app \n desde el Menú KartoMapp destinado a ello.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK){
            System.out.println("OK"); 
            System.exit(0);
            } else {
            System.out.println("CANCEL");
            } 
        });*/
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
                FXMLLoader miCargadorX = new FXMLLoader(getClass().getResource("/Controllers/FXMLMapa.fxml"));
                Parent rootX = miCargadorX.load();
                FXMLMapaController controladorIniciarX = miCargadorX.getController();
                
                LocalDateTime d = LocalDateTime.now();
                Session s = new Session(d,controladorIniciarX.getHit(),controladorIniciarX.getFault());
                user.addSession(s);
            System.out.println("OK"); 
            System.exit(0);
            } else {
            System.out.println("CANCEL");
            } 
        }
        
    }


    @FXML
    private void verPerfil(ActionEvent event) throws IOException {
        FXMLLoader miCargador1 = new FXMLLoader(getClass().getResource("/Controllers/FXMLPerfil_1.fxml"));
        Parent root = miCargador1.load();
        Perfil_1Controller controladorIniciar = miCargador1.getController();
        
        Scene scene = new Scene(root,825,400);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Perfil estudiante");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
        controladorIniciar.initPerfil(user,partida);
        controladorIniciar.initPerfil(user.getNickName(),user.getEmail(),user.getPassword(),user.getBirthdate().toString(),avatar.getImage());
    }

    @FXML
    private void cerrarSesion(ActionEvent event) throws IOException, NavegacionDAOException {
        if(!bcerrarSesion.isDisable()){
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
                partida.getScene().getWindow().hide();
                FXMLLoader miCargador1 = new FXMLLoader(getClass().getResource("/Controllers/MainContainer.fxml"));
                Parent root = miCargador1.load();
                MainContainerController controladorIniciar = miCargador1.getController();
                
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

    void initNombreUser(String text) { //del sign up
        estudiante.setText(estudiante.getText() + " " + user.getNickName());
        name = user.getNickName();
    }

    void initUser(User us, Image ima) throws IOException { //del sign up
        this.user = us;
        avatar.setImage(ima);
            FXMLLoader miCargadorX = new FXMLLoader(getClass().getResource("/Controllers/FXMLMapa.fxml"));
            Parent rootX = miCargadorX.load();
            FXMLMapaController controladorIniciarX = miCargadorX.getController();
            Boolean ima0 = false; //medalla
            Boolean ima1 = false; //boleto
            Boolean ima2 = false; //ballena
            Boolean ima3 = false; //medalla
            Boolean i1 = false; //emblema1ballena
            Boolean i2 = false; //emblema2ballena
            int num = user.getSessions().size();
            List<Integer> l = new ArrayList<Integer>();
            List<Integer> l2 = new ArrayList<Integer>();
          if(num > 0){
            for(int i = 0; i<num; i++){
                l.add(user.getSessions().get(i).getHits());
            }
            for(int i = 0; i<num; i++){
                l2.add(user.getSessions().get(i).getFaults());
            }
            int maxPuntuacion = Collections.max(l);
            int maxPuntuacionFallos = Collections.max(l2);
            
            //////////////comprobaciones para las medallas y emblemas
                if(maxPuntuacion < user.getSessions().get(num-1).getHits()){
                   ima0 = true;
                }
                else{
                  if(maxPuntuacionFallos < user.getSessions().get(num-1).getFaults()){
                     ima1 = true;
                  }
                }
                if(user.getSessions().size() == 7) {ima2 = true;}
                else if(user.getSessions().size() > 7){i1 = true;}
                if(user.getSessions().size() == 14) {ima3 = true;}
                else if(user.getSessions().size() > 14){i2 = true;}

            if(ima0){
                   rifa.setVisible(false);newrecord.setVisible(true);
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   //Cambia el icono por uno propio
                   Stage stage= (Stage) alert.getDialogPane().getScene().getWindow();
                   stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/icons/efw.png")));
                  alert.getDialogPane().getChildren().stream().filter(node -> node instanceof Label).forEach(node -> ((Label)node).setMinHeight(Region.USE_PREF_SIZE));
                  alert.setTitle("Nuevo record de aciertos!");
                  alert.setHeaderText("Nos complace anunciarle que ha sido premiado con la mismísima medalla KartoMapp navegante PRO.");
                  alert.setContentText("Continúe así! No obstante, revise sus fallos que no han sido pocos...");
                  alert.showAndWait();
                }
            if(ima1){
                   newrecord.setVisible(false);rifa.setVisible(true);
                   Alert alert = new Alert(Alert.AlertType.WARNING);
                   //Cambia el icono por uno propio
                   Stage stage= (Stage) alert.getDialogPane().getScene().getWindow();
                   stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/icons/efw.png")));
                  alert.getDialogPane().getChildren().stream().filter(node -> node instanceof Label).forEach(node -> ((Label)node).setMinHeight(Region.USE_PREF_SIZE));
                  alert.setTitle("Revise los temas!");
                  alert.setHeaderText("Ha superado el número de fallos de sesiones anteriores. "
                          + "Le dejamos un boleto de rifa por si tiene más suerte...");
                  alert.setContentText("No se olvide de estudiar, los temas no se leen solos!");
                  alert.showAndWait();
            }
            if(ima2){
                   emblema.setVisible(true);
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   //Cambia el icono por uno propio
                   Stage stage= (Stage) alert.getDialogPane().getScene().getWindow();
                   stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/icons/efw.png")));
                  alert.getDialogPane().getChildren().stream().filter(node -> node instanceof Label).forEach(node -> ((Label)node).setMinHeight(Region.USE_PREF_SIZE));
                  alert.setTitle("Emblema 1 desbloqueado");
                  alert.setHeaderText("Navegante principiante. "
                          + "Aquí tiene un pin de nuestro club KartoMapp!");
                  alert.setContentText("Siga jugando, aún puede ganar más recompensas!");
                  alert.showAndWait();
            }
            if(ima3){
                   emblema1.setVisible(true);
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   //Cambia el icono por uno propio
                   Stage stage= (Stage) alert.getDialogPane().getScene().getWindow();
                   stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/icons/efw.png")));
                  alert.getDialogPane().getChildren().stream().filter(node -> node instanceof Label).forEach(node -> ((Label)node).setMinHeight(Region.USE_PREF_SIZE));
                  alert.setTitle("Emblema 2 desbloqueado");
                  alert.setHeaderText("Navegante experto."
                          + " Aquí tiene nuestra medalla KartoMapp!");
                  alert.setContentText("Siga jugando y busque ese 10 tan deseado en el examen!");
                  alert.showAndWait();
            }
            if(i1){
                   emblema.setVisible(true);
            }
            if(i2){
                   emblema1.setVisible(true);
            }
          }
    }
    
    void initUser(Image ima) {
        this.avatar.setImage(ima);
    }

    void initAvatar(Image image) {
        this.avatar.setImage(image);
    }

    @FXML
    private void hacerProblemaAleatorio(ActionEvent event) throws IOException {
        List<Problem> problemas = nav.getProblems();
        Random n = new Random();
        int valor = n.nextInt(18);
        String enunciado = problemas.get(valor).getText();
        Problem p = problemas.get(valor);
        FXMLLoader miCargador1 = new FXMLLoader(getClass().getResource("/Controllers/FXMLMapa.fxml"));
        Parent root = miCargador1.load();
        FXMLMapaController controladorIniciar = miCargador1.getController();
        controladorIniciar.initProblema(enunciado,p,ayuda,user.getAvatar());
        controladorIniciar.initAvatar(user.getAvatar(),null, name);
        controladorIniciar.initButton(ayuda);
        Scene scene = new Scene(root,1181,731);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Carta Náutica");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void verListaProblemas(ActionEvent event) throws IOException {
        FXMLLoader miCargador1 = new FXMLLoader(getClass().getResource("/Controllers/FXMLListaProblemas.fxml"));
        Parent root = miCargador1.load();
        FXMLListaProblemasController controladorIniciar = miCargador1.getController();
        controladorIniciar.initButton(ayuda);
        controladorIniciar.initUser(name,avatar.getImage());
        Scene scene = new Scene(root,865,536);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Carta Náutica");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void verResultados(ActionEvent event) throws IOException {
        FXMLLoader miCargador1 = new FXMLLoader(getClass().getResource("/Controllers/FXMLVerResultados.fxml"));
        Parent root = miCargador1.load();
        FXMLVerResultadosController controladorResultados = miCargador1.getController();
        controladorResultados.initUser(user,name,ayuda);
        Scene scene = new Scene(root,730,450);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Aciertos y fallos");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }


    @FXML
    private void mano(MouseEvent event) {
        partida.setCursor(Cursor.HAND);
        partida1.setCursor(Cursor.HAND);
        ayuda.setCursor(Cursor.HAND);
        estudiante.setCursor(Cursor.HAND);   
        
    }

    @FXML
    private void verMapa(ActionEvent event) throws IOException {
        FXMLLoader miCargador1 = new FXMLLoader(getClass().getResource("/Controllers/FXMLVista.fxml"));
        Parent root = miCargador1.load();
        FXMLVistaController controladorIniciar = miCargador1.getController();
        Scene scene = new Scene(root,600,650);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Carta Náutica");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public Button conseguirButton() {
        return partida;
    }

    @FXML
    private void verAyuda(ActionEvent event) throws IOException {
        FXMLLoader miCargador1 = new FXMLLoader(getClass().getResource("/Controllers/FXMLAyudaGeneral.fxml"));
        Parent root = miCargador1.load();
        FXMLAyudaGeneralController controladorIniciar = miCargador1.getController();
        
        Scene scene = new Scene(root,866,536);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Ayuda General");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
        
    }

    @FXML
    private void inf(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        //Cambia el icono por uno propio
            Stage stage= (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/icons/efw.png")));
                  alert.getDialogPane().getChildren().stream().filter(node -> node instanceof Label).forEach(node -> ((Label)node).setMinHeight(Region.USE_PREF_SIZE));
                  alert.setTitle("Emblema 1");
                  alert.setHeaderText("Navegante principiante. "
                          + "Pin de nuestro club KartoMapp!");
                  alert.setContentText("Aún puede ganar más recompensas!");
                  alert.showAndWait();
        
    }

    @FXML
    private void inf2(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        //Cambia el icono por uno propio
            Stage stage= (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/icons/efw.png")));
                  alert.getDialogPane().getChildren().stream().filter(node -> node instanceof Label).forEach(node -> ((Label)node).setMinHeight(Region.USE_PREF_SIZE));
                  alert.setTitle("Emblema 2");
                  alert.setHeaderText("Navegante experto."
                          + " Medalla de nuestro club KartoMapp!");
                  alert.setContentText("Siga estudiando y busque ese 10 tan deseado en el examen!");
                  alert.showAndWait();
    }

    @FXML
    private void acercaDe(ActionEvent event) {
        Alert mensaje= new Alert(Alert.AlertType.INFORMATION);
        mensaje.setTitle("Acerca de");
        mensaje.setHeaderText("IPC_Entregable - 2022");
        mensaje.setContentText("Realizado por: Brian Valiente, Álvaro López, Francisco Lozano.");
        mensaje.showAndWait();

    }

    
}
