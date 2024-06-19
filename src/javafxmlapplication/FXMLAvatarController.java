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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Navegacion;

/**
 *
 * @author Brian
 */
public class FXMLAvatarController implements Initializable {
    @FXML
    private Button bAccept;
    @FXML
    private Button bCancel;
    @FXML
    private ToggleGroup select;
    @FXML
    private ImageView image1;
    @FXML
    private ImageView image2;
    @FXML
    private ImageView image3;
    @FXML
    private ImageView image5;
    @FXML
    private ImageView image4;
    @FXML
    private RadioButton option1;
    @FXML
    private RadioButton option2;
    @FXML
    private RadioButton option3;
    @FXML
    private RadioButton option5;
    @FXML
    private RadioButton option4;
       
    private boolean confirmar;
    private BooleanProperty validConfirm;
    private Image avatar;
    private ImageView salvarImageView;
    private Image avatarPorDefecto;
    private Navegacion nav;
    @FXML
    private ImageView image6;
    @FXML
    private Button botonFotoPc;
    @FXML
    private Label defecto;

   /**
     * Initializes the controller class.
     */
   @Override
   public void initialize(URL url, ResourceBundle rb) {
        try {
            nav = Navegacion.getSingletonNavegacion();
            
            
            this.confirmar = false;
            this.avatar = null;
            validConfirm = new SimpleBooleanProperty();
            validConfirm.setValue(Boolean.FALSE);
            if (validConfirm.getValue() == Boolean.TRUE){
                bAccept.requestFocus();
            }
            else bAccept.disableProperty().bind(Bindings.not(validConfirm));
        } catch (NavegacionDAOException ex) {
            Logger.getLogger(FXMLAvatarController.class.getName()).log(Level.SEVERE, null, ex);
        }

   }

   @FXML
   private void confirmar(ActionEvent event) throws IOException {
      if (this.option1.isSelected()) {
         this.avatar = this.image1.getImage();
         salvarImageView = this.image1;
      }

      if (this.option2.isSelected()) {
         this.avatar = this.image2.getImage();
         salvarImageView = this.image2;
      }

      if (this.option3.isSelected()) {
         this.avatar = this.image3.getImage();
         salvarImageView = this.image3;
      }

      if (this.option4.isSelected()) {
         this.avatar = this.image4.getImage();
         salvarImageView = this.image4;
      }

      if (this.option5.isSelected()) {
         this.avatar = this.image5.getImage();
         salvarImageView = this.image5;
      }

      if (this.avatar != null) {
         this.confirmar = true;
         validConfirm.setValue(Boolean.TRUE);
      }
      
   }
   @FXML
    private void confirmarAvatar(ActionEvent event) throws IOException {      
      bAccept.getScene().getWindow().hide();
    }

   @FXML
    private void mano(MouseEvent event) {
        bAccept.setCursor(Cursor.HAND);
        bCancel.setCursor(Cursor.HAND);   
    }

    @FXML
    private void atras(ActionEvent event) throws IOException {
        confirmar = false;
        bCancel.getScene().getWindow().hide();
    }
    
    public boolean getChanged() {
      return this.confirmar;
   }

   public Image getImage() {
      return this.avatar;
   }

    @FXML
    private void elegirFotoPc(ActionEvent event) {
      FileChooser buscar = new FileChooser();
      buscar.setTitle("Seleccionar avatar");
      buscar.getExtensionFilters().addAll(new ExtensionFilter[]{new ExtensionFilter("Imágenes", new String[]{"*.png", "*.jpg", "*.gif"})});
      File imageSeleccionada = buscar.showOpenDialog(((Node)event.getSource()).getScene().getWindow());
      if (imageSeleccionada != null) {
         String imageLocation = imageSeleccionada.toURI().toString();
         Image image = new Image(imageLocation);
         this.image6.setImage(image);
         this.botonFotoPc.setVisible(true);
         this.avatar = this.image6.getImage();
         salvarImageView = this.image6;
         this.confirmar = true;
         validConfirm.setValue(Boolean.TRUE);
    }
    }

    void resetDefaultLabel(String string) {
        defecto.setText("");
    }

}
