/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import DBAccess.NavegacionDAOException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.User;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import javafx.scene.image.ImageView;
import model.Navegacion;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.DateCell;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Brian
 */
public class FXMLSignUpController implements Initializable {
    
    private BooleanProperty validName;
    private BooleanProperty validDate;
    private BooleanProperty validPassword;
    private BooleanProperty validEmail;
    private BooleanProperty equalPasswords;
    private final int EQUALS = 0; 
    private Navegacion nav;
    
    @FXML
    private Button bCancel;
    @FXML
    private Button bAccept;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private TextField name;
    @FXML
    private PasswordField repeatpassword;
    @FXML
    private Label errorname;
    @FXML
    private Label erroremail;
    @FXML
    private Label errorpass;
    @FXML
    private Label errorpass2;
    @FXML
    private Label errordate;
    @FXML
    private DatePicker date;
    @FXML
    private ImageView avatar;
    @FXML
    private Button bAvatar;
    
    private Image avatarElegido;
    private Button cerrarVentana;
    private User user;
    @FXML
    private TextField mostrarTextoContraseña;
    @FXML
    private CheckBox mostrarpass;
    @FXML
    private TextField mostrarTextPass2;
    @FXML
    private Label error;
    @FXML
    private Label errorname2;
    @FXML
    private Label defecto;

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
            name.requestFocus();
            
            avatarElegido = avatar.getImage();
            validName = new SimpleBooleanProperty();
            validDate = new SimpleBooleanProperty();
            validEmail = new SimpleBooleanProperty();
            validPassword = new SimpleBooleanProperty();
            equalPasswords = new SimpleBooleanProperty();
            
            validName.setValue(Boolean.FALSE);
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
            
            
            //Check values when user leaves edits
            /*name.focusedProperty().addListener((observable, oldValue, newValue)->
            { if(!newValue){
                    if(nav.exitsNickName(name.textProperty().getValueSafe())){
                        errorname.setText("Nombre de usuario ya existente");
                        manageError(errorname, name, validName );
                    
                }
                    else manageCorrect(errorname, name,validName );
            }
            
            });
            name.focusedProperty().addListener((observable, oldValue, newValue)->
            { if(!newValue){
                    if(!User.checkNickName(name.textProperty().getValueSafe())){
                    //Incorrect email
                    errorname.setText("Debe tener entre 6 y 15 caracteres o dígitos sin espacios, pudiendo usar guiones o sub-guiones");
                    manageError(errorname, name,validName );
                    }
                    else{
                    manageCorrect(errorname, name,validName );
                }
            }
            
            });
            
            date.focusedProperty().addListener((observable, oldValue, newValue)->
            { if(!newValue){ //focuslost.
               checkEditDate();
            }
            });
            
            
            email.focusedProperty().addListener((observable, oldValue, newValue)->
            { if(!newValue){ //focuslost.
                checkEditEmail();
            }
            
            });
            
            password.focusedProperty().addListener((observable, oldValue, newValue)->
            { if(!newValue){ //focuslost.
                checkEditPassword();
            }
            
            });
            
            repeatpassword.focusedProperty().addListener((observable, oldValue, newValue)->
            { if(!newValue){ //focuslost.
                checkEquals();
            }
            
            });*/
            
            date.setDayCellFactory((DatePicker picker) -> {
                return new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    LocalDate today = LocalDate.now();
                    setDisable(empty || date.compareTo(today) > 0 );
                }
                };
            });
           
            BooleanBinding visible1 = Bindings.or(this.name.textProperty().isEmpty(), this.email.textProperty().isEmpty());
            BooleanBinding visible2 = Bindings.or(this.password.textProperty().isEmpty(), this.repeatpassword.textProperty().isEmpty());
            BooleanBinding visible3 = Bindings.or(visible1, visible2);
            BooleanBinding visible = Bindings.or(visible3, this.date.valueProperty().isNull());
            this.bAccept.disableProperty().bind(visible);
           
            //comprueba q todos los campos estan bien y lo guarda en validFields
            //BooleanBinding validFields = Bindings.and(validName, validDate).and(validEmail).and(validPassword).and(equalPasswords);
            //crea enlace de apagar el boton de aceptar si validFields es false osea
            //q si los campos rellenados estan mal
            /*if (validFields.getValue() == Boolean.TRUE){
                bAccept.requestFocus();
            }
            else bAccept.disableProperty().bind(Bindings.not(validFields));
            */
            //si das a cancelar te cierra la app
            bCancel.setOnAction( (event)->{bCancel.getScene().getWindow().hide();});
        } catch (NavegacionDAOException ex) {
            Logger.getLogger(FXMLSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    private void checkEditDate(){
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
    
    private void checkEditEmail(){
        if(!User.checkEmail(email.textProperty().getValueSafe()))
                    //Incorrect email
                    manageError(erroremail, email,validEmail );
                else
                    manageCorrect(erroremail, email,validEmail );
    }
    
    private void checkEditPassword(){
        if(!User.checkPassword(password.textProperty().getValueSafe()))
                    manageError(errorpass,password,validPassword);
                else
                    manageCorrect(errorpass,password,validPassword);
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
    private void registrarse(ActionEvent event) throws NavegacionDAOException, IOException {
        boolean b = name.getText().isEmpty() || date.getValue() == null || email.getText().isEmpty() ||
                    password.getText().isEmpty() || repeatpassword.getText().isEmpty();
        if(b){error.setText("Asegúrese de que todos los campos estén rellenados.");}
        else {error.setText("");
        
        String usuname = this.name.getText();
        String ema = this.email.getText();
        String pass = this.password.getText();
        String passrepeat = this.repeatpassword.getText();
        LocalDate bth = (LocalDate)this.date.getValue();
        LocalDate limite = LocalDate.now().minusYears(16L);
        boolean comprobar = !User.checkNickName(name.getText()) || nav.exitsNickName(usuname) 
                    || !User.checkEmail(ema) || !User.checkPassword(pass) 
                    || !pass.equals(passrepeat) || bth != null && bth.compareTo(limite) > 0;
        
        if(nav.exitsNickName(name.textProperty().getValueSafe())){
                        errorname2.setText("Nombre de usuario ya existente. Debe tener entre 6-15 caracteres, dígitos sin espacios o guiones '–' y '_'.");
                        manageError(errorname2, name, validName );
                    
                }
                    else manageCorrect(errorname2, name,validName );
        
        if(!User.checkNickName(name.textProperty().getValueSafe())){
                    errorname.setText("Debe tener entre 6 y 15 caracteres o dígitos sin espacios, pudiendo usar guiones o sub-guiones.");
                    manageError(errorname, name, validName );
        }
        else manageCorrect(errorname, name,validName );

        checkEditDate();checkEditEmail();checkEditPassword();checkEquals();//comprueba todo lo demás
        
        if(comprobar){error.setText("Asegúrese de que todos los campos sean correctos.");}
        else {
        error.setText("");
        user = nav.registerUser(name.getText(), email.getText(), 
                    password.getText(), avatarElegido, date.getValue());
        
        Alert alert = new Alert(AlertType.INFORMATION, "content text");
        //Cambia el icono por uno propio
            Stage stagee= (Stage) alert.getDialogPane().getScene().getWindow();
            stagee.getIcons().add(new Image(this.getClass().getResourceAsStream("/icons/efw.png")));
            //Añade la hoja de estilo a la alerta
            DialogPane dialogPane= alert.getDialogPane();
            dialogPane.getStylesheets().add(
            getClass().getResource("/icons/alert.css").toExternalForm());
            //Asigna la clase .myAlertal contenedor principal del diálogo
            alert.getDialogPane().getStyleClass().add("myAlert");
        //codigo sacado de
        //https://ajaxhispano.com/ask/alertas-javafx-y-su-tamano-76350/
        alert.getDialogPane().getChildren().stream().filter(node -> node instanceof Label).forEach(node -> ((Label)node).setMinHeight(Region.USE_PREF_SIZE));
        //////////////////////////////////////////////////////////////
        alert.setTitle("Aviso");
        alert.setHeaderText("Usuario " + name.getText() + " registrado con éxito.");
        // ó null si no queremos cabecera
        alert.setContentText("Sus datos han sido guardados en el sistema. ¡Ya puede hacer cuestionarios!");
        alert.showAndWait();
        
        cerrarVentana.getScene().getWindow().hide();
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
        controladorApp.initUser(user,avatarElegido);
        controladorApp.initNombreUser(name.getText());
        }
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
        bAvatar.setCursor(Cursor.HAND);
        
    }

    @FXML
    private void cambiarAvatar(ActionEvent event) throws IOException {
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/Controllers/FXMLAvatar.fxml"));
        Parent root = miCargador.load();
        FXMLAvatarController controladorAvatar = miCargador.getController();
        
        Scene scene = new Scene(root,820,576);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Avatar");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
       
        if (controladorAvatar.getChanged() == true) {
         avatarElegido = controladorAvatar.getImage();
         avatar.setImage(avatarElegido);
        }
    }

    void initButton(Button registrarse) {
        cerrarVentana = registrarse;
    }

    @FXML
    private void enter(KeyEvent event) throws IOException, NavegacionDAOException {
        presionar(event);
    }
    private void presionar(KeyEvent event) throws IOException, NavegacionDAOException {
        if(event.getCode() == KeyCode.ENTER){
         registrarse(new ActionEvent());
        }
    }

    @FXML
    private void entername(KeyEvent event) {
        if(event.getCode()==KeyCode.ENTER)date.requestFocus();
    }

    @FXML
    private void enterdate(KeyEvent event) {
        if(event.getCode()==KeyCode.ENTER)email.requestFocus();
    }

    @FXML
    private void entermail(KeyEvent event) {
        if(event.getCode()==KeyCode.ENTER)password.requestFocus();mostrarTextoContraseña.requestFocus();
    }

    @FXML
    private void enterpass(KeyEvent event) {
        if(event.getCode()==KeyCode.ENTER)repeatpassword.requestFocus();mostrarTextPass2.requestFocus();
    }

    
}
