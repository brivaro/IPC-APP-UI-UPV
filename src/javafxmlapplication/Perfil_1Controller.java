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
public class Perfil_1Controller implements Initializable {

    @FXML
    private Button bCancel;
    @FXML
    private Text perfilde;
    
    private User user;
    private Navegacion nav;
    private Button bb;
    @FXML
    private Label name;
    @FXML
    private Label date;
    @FXML
    private Label email;
    @FXML
    private Label pass;
    @FXML
    private ImageView avatar;
    @FXML
    private Button modif;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            nav = Navegacion.getSingletonNavegacion();
            
            //si das a cancelar te cierra la app
            bCancel.setOnAction( (event)->{bCancel.getScene().getWindow().hide();});
        } catch (NavegacionDAOException ex) {
            Logger.getLogger(Perfil_1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    

    @FXML
    private void atras(ActionEvent event) {
        bCancel.getScene().getWindow().hide();
    }

    @FXML
    private void mano(MouseEvent event) {
        bCancel.setCursor(Cursor.HAND);
        modif.setCursor(Cursor.HAND);
        
    }

    void initPerfil(User user,Button b) {
        this.user = user;
        bb = b;
    }

    @FXML
    private void modificarPerfil(ActionEvent event) throws IOException {
        FXMLLoader miCargador1 = new FXMLLoader(getClass().getResource("/Controllers/FXMLModificarPerfil.fxml"));
        Parent root = miCargador1.load();
        FXMLModificarPerfilController controladorIniciar = miCargador1.getController();
        
        Scene scene = new Scene(root,830,533);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Modificar perfil");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
        controladorIniciar.initPerfil(user, bCancel, bb);
        controladorIniciar.initPerfil(name.getText(),user.getEmail(),user.getPassword(),user.getBirthdate().toString(),user.getAvatar());
    }

    void initPerfil(String nickName, String emai, String password, String fecha, Image avat) {
        name.setText(nickName);
        email.setText(emai);
        pass.setText(password);
        date.setText(fecha);
        avatar.setImage(avat);
    }
    
}
