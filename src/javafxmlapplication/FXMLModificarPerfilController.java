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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Navegacion;
import model.User;

/**
 *
 * @author Brian
 */
public class FXMLModificarPerfilController implements Initializable {

    @FXML
    private Button bCancel;
    @FXML
    private Button bAccept;
    @FXML
    private DatePicker date;
    @FXML
    private Label errordate;
    @FXML
    private TextField email;
    @FXML
    private Label erroremail;
    @FXML
    private PasswordField password;
    @FXML
    private Label errorpass;
    @FXML
    private PasswordField repeatpassword;
    @FXML
    private Label errorpass2;
    @FXML
    private Button bAvatar;
    @FXML
    private ImageView avatar;
    @FXML
    private Text perfilde;
    
    private Image avatarElegido;
    private Button cerrarVentana;
    private User user;
    private Button bb;
    private Button botonReseteaApp;
    
    private BooleanProperty validDate;
    private BooleanProperty validPassword;
    private BooleanProperty validEmail;
    private BooleanProperty equalPasswords;
    private final int EQUALS = 0; 
    private Navegacion nav;
    @FXML
    private TextField mostrarTextoContraseña;
    @FXML
    private TextField mostrarTextPass2;
    @FXML
    private CheckBox mostrarpass;
    @FXML
    private Label error;
    
    private void manageError(Label errorLabel, TextField textField, BooleanProperty boolProp ){
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
    private void manageCorrect(Label errorLabel,TextField textField, BooleanProperty boolProp ){
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
            
            //avatarElegido = avatar.getImage();
            validDate = new SimpleBooleanProperty();
            validEmail = new SimpleBooleanProperty();
            validPassword = new SimpleBooleanProperty();
            equalPasswords = new SimpleBooleanProperty();
            
            validDate.setValue(Boolean.FALSE);
            validPassword.setValue(Boolean.FALSE);
            validEmail.setValue(Boolean.FALSE);
            equalPasswords.setValue(Boolean.FALSE);
            
            this.password.textProperty().bindBidirectional(this.mostrarTextoContraseña.textProperty());
            this.password.visibleProperty().bind(Bindings.not(this.mostrarpass.selectedProperty()));
            this.mostrarTextoContraseña.visibleProperty().bind(this.mostrarpass.selectedProperty());
            
            this.repeatpassword.textProperty().bindBidirectional(this.mostrarTextPass2.textProperty());
            this.repeatpassword.visibleProperty().bind(Bindings.not(this.mostrarpass.selectedProperty()));
            this.mostrarTextPass2.visibleProperty().bind(this.mostrarpass.selectedProperty());

            date.focusedProperty().addListener((observable, oldValue, newValue)->
            { if(!newValue){ //focuslost.
                LocalDate ahora = LocalDate.now();
                LocalDate fechaNac = date.getValue();
                int edad = ahora.getYear() - fechaNac.getYear();
                
                if(edad < 16){
                    //Incorrect email
                    validDate.setValue(Boolean.FALSE);
                    errordate.visibleProperty().set(true);
                    date.styleProperty().setValue("-fx-background-color: #FCE5E0");
                    date.requestFocus();}
                else{
                    validDate.setValue(Boolean.TRUE);
                    errordate.visibleProperty().set(false);
                    date.styleProperty().setValue("");
                
                }
            }
            });
            
            //email.setText(user.getEmail());
            email.focusedProperty().addListener((observable, oldValue, newValue)->
            { if(!newValue){ //focuslost.
                if(!User.checkEmail(email.textProperty().getValueSafe()))
                    //Incorrect email
                    manageError(erroremail, email,validEmail );
                else
                    manageCorrect(erroremail, email,validEmail );
            }
            
            });
            
            //password.setText(user.getPassword());
            password.focusedProperty().addListener((observable, oldValue, newValue)->
            { if(!newValue){ //focuslost.
                if(!User.checkPassword(password.textProperty().getValueSafe()))
                    manageError(errorpass,password,validPassword);
                else
                    manageCorrect(errorpass,password,validPassword);
            }
            
            });
            
            //password.setText(user.getPassword());
            repeatpassword.focusedProperty().addListener((observable, oldValue, newValue)->
            { if(!newValue){ //focuslost.
                checkEquals();
            }
            
            });
            
            //si das a cancelar te cierra la app
            bCancel.setOnAction( (event)->{bCancel.getScene().getWindow().hide();});
        } catch (NavegacionDAOException ex) {
            Logger.getLogger(FXMLModificarPerfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    private void checkEquals(){
        if(password.textProperty().getValueSafe().compareTo(
                    repeatpassword.textProperty().getValueSafe()) != EQUALS){
                showErrorMessage(errorpass2,repeatpassword);
                equalPasswords.setValue(Boolean.FALSE);
                password.textProperty().setValue("");
                repeatpassword.textProperty().setValue("");
                password.requestFocus();
        }else
            manageCorrect(errorpass2,repeatpassword,equalPasswords);
        
    }

    @FXML
    private void atras(ActionEvent event) {
        bCancel.getScene().getWindow().hide();
    }

    @FXML
    private void mano(MouseEvent event) {
        bAccept.setCursor(Cursor.HAND);
        bCancel.setCursor(Cursor.HAND);
        bAvatar.setCursor(Cursor.HAND);
        
    }

    @FXML
    private void cambiarAvatar(ActionEvent event) throws IOException {
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/Controllers/FXMLAvatar.fxml"));
        Parent root = miCargador.load();
        FXMLAvatarController controladorAvatar = miCargador.getController();
        controladorAvatar.resetDefaultLabel("");

        Scene scene = new Scene(root,820,576);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Avatar");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait(); //falta la funcion de la foto de tu pc
       
        if (controladorAvatar.getChanged() == true) {
         avatarElegido = controladorAvatar.getImage();
         avatar.setImage(avatarElegido);
        }
    }

    void initPerfil(User user, Button b, Button bbb) {
        this.user = user;  
        bb = b;
        botonReseteaApp = bbb;
    }

    @FXML
    private void guardar(ActionEvent event) throws NavegacionDAOException, IOException {
        if(email.getText().isEmpty() || password.getText().isEmpty() || repeatpassword.getText().isEmpty()){
            error.setText("Error, hay campos sin rellenar.");
            
        }
        else{
        error.setText("");
        user.setEmail(email.getText());
        user.setPassword(password.getText());
        user.setAvatar(avatar.getImage());
        FXMLLoader miCargador1 = new FXMLLoader(getClass().getResource("/Controllers/FXMLApp.fxml"));
        Parent root = miCargador1.load();
        FXMLAppController controladorApp = miCargador1.getController();
       
        controladorApp.initAvatar(avatar.getImage());
        
        Scene scene = new Scene(root,730,450);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("KartoMapp");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
        controladorApp.initUser(user, user.getAvatar());
        String[] aux = perfilde.getText().split(" ");
        String name = aux[1];
        controladorApp.initNombreUser(name);
        
        botonReseteaApp.getScene().getWindow().hide();
        bb.getScene().getWindow().hide();
        bAccept.getScene().getWindow().hide();
        }
    }

    void initPerfil(String nick, String emai, String pass, String toString, Image ima) {
        perfilde.setText(perfilde.getText() + nick);
        date.setPromptText(toString);
        password.setText(pass);
        repeatpassword.setText(pass);
        email.setText(emai);
        avatar.setImage(ima);
        
    }

    
}
