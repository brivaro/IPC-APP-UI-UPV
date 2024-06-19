/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import DBAccess.NavegacionDAOException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Navegacion;
import model.User;

/**
 *
 * @author Brian
 */
public class FXMLLoginController implements Initializable {

    private BooleanProperty validName;
    private BooleanProperty validPassword;
    private Button botonPrincipal;
    private User user;
    
    @FXML
    private Button bCancel;
    @FXML
    private Button bAccept;
    @FXML
    private PasswordField password;
    @FXML
    private TextField name;
    @FXML
    private Label errorname;
    @FXML
    private Label errorpass;
    @FXML
    private CheckBox mostrarpass;
    @FXML
    private TextField mostrarTextoContraseña;
    
    private Navegacion nav;
    private Image ima;
    @FXML
    private Label error;


    private void manageError(Label errorLabel, TextField textField, BooleanProperty boolProp )
    {
        boolProp.setValue(Boolean.FALSE);
        showErrorMessage(errorLabel,textField);
        textField.requestFocus();
        
    }
    /**
     * Updates the boolProp to true. Changes the background 
     * of the edit to the default value. Makes the error label invisible. 
     * @param errorLabel label added to alert the user
     * @param textField edit text added to allow user to introduce the value
     * @param boolProp property which stores if the value is correct or not
     */
    private void manageCorrect(Label errorLabel,TextField textField, BooleanProperty boolProp )
    {
        boolProp.setValue(Boolean.TRUE);
        hideErrorMessage(errorLabel,textField);   
    }
    /**
     * Changes to red the background of the edit and
     * makes the error label visible
     * @param errorLabel
     * @param textField 
     */
    private void showErrorMessage(Label errorLabel,TextField textField)
    {
        errorLabel.visibleProperty().set(true);
        textField.styleProperty().setValue("-fx-background-color: #FCE5E0");    
    }
    /**
     * Changes the background of the edit to the default value
     * and makes the error label invisible.
     * @param errorLabel
     * @param textField 
     */
    private void hideErrorMessage(Label errorLabel,TextField textField)
    {
        errorLabel.visibleProperty().set(false);
        textField.styleProperty().setValue("");
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            nav = Navegacion.getSingletonNavegacion();
                        
            validName = new SimpleBooleanProperty();
            validPassword = new SimpleBooleanProperty();
            validName.setValue(Boolean.FALSE);
            validPassword.setValue(Boolean.FALSE);
            
            this.password.textProperty().bindBidirectional(this.mostrarTextoContraseña.textProperty());
            this.password.visibleProperty().bind(Bindings.not(this.mostrarpass.selectedProperty()));
            this.mostrarTextoContraseña.visibleProperty().bind(this.mostrarpass.selectedProperty());
            
            //Check values when user leaves edits
            /*name.focusedProperty().addListener((observable, oldValue, newValue)->
            { if(!newValue){
                    if(!nav.exitsNickName(name.textProperty().getValueSafe())){
                        manageError(errorname, name,validName );
                    }
                    else{
                        manageCorrect(errorname, name,validName );
                        user = nav.getUser(name.getText());
                    }
            }
            
            });
            
            password.focusedProperty().addListener((observable, oldValue, newValue)->
            { if(!newValue){ 
                    if(!nav.getUser(name.textProperty().getValueSafe()).
                            getPassword().equals(password.textProperty().getValueSafe()))
                        manageError(errorpass,password,validPassword);
                    else
                        manageCorrect(errorpass,password,validPassword);
            }
            
            });*/
            
           
            BooleanBinding visible = Bindings.or(this.name.textProperty().isEmpty(), this.password.textProperty().isEmpty());
            this.bAccept.disableProperty().bind(visible);
            
            //comprueba q todos los campos estan bien y lo guarda en validFields
            //BooleanBinding validFields = Bindings.and(validName, validPassword);
            /*BooleanBinding validFields = Bindings.and(validName, validPassword);
            //crea enlace de apagar el boton de aceptar si validFields es false osea
            //q si los campos rellenados estan mal
            if (validFields.getValue() == Boolean.TRUE){
                bAccept.requestFocus();
            }
            else bAccept.disableProperty().bind(Bindings.not(validFields));
            */
            //si das a cancelar te cierra la app
            bCancel.setOnAction( (event)->{bCancel.getScene().getWindow().hide();});
        } catch (NavegacionDAOException ex) {
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void atras(ActionEvent event) {
        bCancel.getScene().getWindow().hide();
    }

    @FXML
    private void mano(MouseEvent event) {
        bAccept.setCursor(Cursor.HAND);
        bCancel.setCursor(Cursor.HAND);        
    }


    @FXML
    private void iniciarSesion(ActionEvent event) throws IOException, NavegacionDAOException {
        if(!nav.exitsNickName(name.textProperty().getValueSafe())){
                        manageError(errorname, name,validName );
                        error.setText("Asegúrese de que todos los campos sean correctos.");
                    }
        else{
                        manageCorrect(errorname, name,validName );
                        user = nav.getUser(name.getText());
                        
                        if(!user.getPassword().equals(password.textProperty().getValueSafe())){
                        manageError(errorpass,password,validPassword);
                        error.setText("Asegúrese de que todos los campos sean correctos.");
                        }
                        else {
                            manageCorrect(errorpass,password,validPassword);
                            
                            if(user.checkCredentials(name.getText(), password.getText())){
                            error.setText("");
                            user = nav.loginUser(name.getText(), password.getText());
                            Alert alert = new Alert(Alert.AlertType.INFORMATION, "content text");
                            //Cambia el icono por uno propio
                            Stage stagee= (Stage) alert.getDialogPane().getScene().getWindow();
                            stagee.getIcons().add(new Image(this.getClass().getResourceAsStream("/icons/efw.png")));
                            //Añade la hoja de estilo a la alerta
                            DialogPane dialogPane= alert.getDialogPane();
                            dialogPane.getStylesheets().add(
                            getClass().getResource("/icons/alert.css").toExternalForm());
                            //Asigna la clase .myAlertal contenedor principal del diálogo
                            alert.getDialogPane().getStyleClass().add("myAlert");
                            
                            alert.getDialogPane().getChildren().stream().filter(node -> node instanceof Label).forEach(node -> ((Label)node).setMinHeight(Region.USE_PREF_SIZE));
                            alert.setTitle("Inicio sesión completado.");
                            alert.setHeaderText("Bienvenido: " + name.getText());
                            alert.setContentText("¡Que comience la diversión!");
                            alert.showAndWait();
        
                            botonPrincipal.getScene().getWindow().hide();
                            bAccept.getScene().getWindow().hide();
        
                            //AHORA SALTAR AL CONTENEDOR DE LA APLICACIÓN TOCHA
                                FXMLLoader miCargador1 = new FXMLLoader(getClass().getResource("/Controllers/FXMLApp.fxml"));
                            Parent root = miCargador1.load();
                            FXMLAppController controladorApp = miCargador1.getController();
        
                            Scene scene = new Scene(root,730,450);
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.setTitle("KartoMapp");
                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.setResizable(false);
                            stage.show();
                            controladorApp.initUser(user,user.getAvatar());
                            controladorApp.initNombreUser(name.getText());
                            }
                        }
                        
                        
                        
        } 
    }

    void initVentana(Button b)
    {   
        botonPrincipal = b;
    }

    @FXML
    private void enter(KeyEvent event) throws IOException, NavegacionDAOException {
        presionar(event);
    }
    private void presionar(KeyEvent event) throws IOException, NavegacionDAOException {
        if(event.getCode() == KeyCode.ENTER){
         iniciarSesion(new ActionEvent());
        }
    }

    @FXML
    private void entername(KeyEvent event) {
        if(event.getCode()==KeyCode.ENTER)password.requestFocus();
    }

    
}
