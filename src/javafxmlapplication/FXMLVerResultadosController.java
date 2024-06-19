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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Navegacion;
import model.Session;
import model.User;

/**
 * FXML Controller class
 *
 * @author Brian
 */
public class FXMLVerResultadosController implements Initializable {

    @FXML
    private ImageView avatar;
    @FXML
    private Button atras;
    private User user;
    private Navegacion nav;
    @FXML
    private DatePicker desde;
    @FXML
    private DatePicker hasta;
    private Button aux;

    @FXML
    private Button buscar;
    @FXML 
    private TableView<Session> vistaTabla;
    private List<Session> sesioness;
    private ObservableList<Session> rangoSesiones = null;
    
    @FXML
    private TableColumn<Session, String> tablaDia;
    @FXML
    private TableColumn<Session, LocalDateTime> tablaHora;
    @FXML
    private TableColumn<Session, Integer> tablaAciertos;
    @FXML
    private TableColumn<Session, Integer> tablaFallos;
    
    private LocalDate inicio;
    private LocalDate fin;
    @FXML
    private Button limpiar;
    @FXML
    private Label error;
    @FXML
    private Label labelaciertos;
    @FXML
    private Label labelfallos;
    @FXML
    private Button tarta;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            nav = Navegacion.getSingletonNavegacion();
            
            FXMLLoader miCargadorX = new FXMLLoader(getClass().getResource("/Controllers/FXMLMapa.fxml"));
            Parent rootX = miCargadorX.load();
            FXMLMapaController controladorIniciarX = miCargadorX.getController();
            labelaciertos.setText(labelaciertos.getText() + controladorIniciarX.getHit());
            labelfallos.setText(labelfallos.getText() + controladorIniciarX.getFault());

            desde.setDayCellFactory((DatePicker picker) -> {
                return new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    LocalDate today = LocalDate.now();
                    setDisable(empty || date.compareTo(today) > 0 );
                }
                };
            });
            hasta.setDayCellFactory((DatePicker picker) -> {
                return new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    LocalDate today = LocalDate.now();
                    setDisable(empty || date.compareTo(today) > 0 );
                }
                };
            });
            
            
        } catch (NavegacionDAOException ex) {
            Logger.getLogger(FXMLVerResultadosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLVerResultadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      BooleanBinding visible = Bindings.or(desde.valueProperty().isNull(), hasta.valueProperty().isNull());
      buscar.disableProperty().bind(visible);
      limpiar.setDisable(false);
      ///////////////////////
      ArrayList<Session> sesi = new ArrayList();  //arrayList para meter aqui os datos
      rangoSesiones = FXCollections.observableArrayList(sesi); //observableList
      vistaTabla.setItems(rangoSesiones); // vinculacion entre la vista y el modelo
      //////////////////////
      
      tablaDia.setCellValueFactory((cellData) -> {
         return new SimpleObjectProperty(((Session)cellData.getValue()).getLocalDate());
      });
      tablaHora.setCellValueFactory((cellData) -> {
         return new SimpleObjectProperty(((Session)cellData.getValue()).getTimeStamp().toLocalTime().withNano(0));
      });
      tablaAciertos.setCellValueFactory((cellData) -> {
         return new SimpleObjectProperty(((Session)cellData.getValue()).getHits());
      });
      tablaFallos.setCellValueFactory((cellData) -> {
         return new SimpleObjectProperty(((Session)cellData.getValue()).getFaults());
      });
      
      desde.focusedProperty().addListener((observable, oldValue, newValue)->
            { if(!newValue){
                    if(desde.getValue() != null){
                        error.setText("Introduzca las fechas entre las que quiere buscar los resultados.");
                    }
              }
            });
      
      hasta.focusedProperty().addListener((observable, oldValue, newValue)->
            { if(!newValue){
                    if(hasta.getValue() != null){
                        error.setText("Introduzca las fechas entre las que quiere buscar los resultados.");
                    }
              }
            });
      
    }    

    void initUser(User us, String n, Button b) {
        user = nav.getUser(n);
        aux = b;
        avatar.setImage(user.getAvatar());
        if(user.getSessions() != null) {sesioness = user.getSessions();
        for(int i = 0; i<sesioness.size();i++){rangoSesiones.add(sesioness.get(i));}
      }
    }

    @FXML
    private void atras(ActionEvent event) {
        atras.getScene().getWindow().hide();
    }


    @FXML
    private void buscarResultados(ActionEvent event) {
        inicio = desde.getValue();
        fin = hasta.getValue();
        if (inicio != null && fin != null) {
         if (fin.isBefore(inicio)) {
            Alert alert = new Alert(AlertType.ERROR);
            //Cambia el icono por uno propio
            Stage stagee= (Stage) alert.getDialogPane().getScene().getWindow();
            stagee.getIcons().add(new Image(this.getClass().getResourceAsStream("/icons/efw.png")));
            alert.getDialogPane().getChildren().stream().filter(node -> node instanceof Label).forEach(node -> ((Label)node).setMinHeight(Region.USE_PREF_SIZE));
            alert.setTitle("Error");
            alert.setHeaderText("Error en las fechas introducidas.");
            alert.setContentText("Por favor, revise las fechas introducidas, "
                    + "recuerde que la fecha de inicio no puede ser posterior a la de fin.");
            alert.showAndWait();
         }
         else {
             error.setText("");
             rangoSesiones.clear();
             sesioness.forEach((session) -> {
               LocalDateTime dateTime = session.getTimeStamp();
               LocalDate date = dateTime.toLocalDate();
               if (date.compareTo(inicio) >= 0 && date.compareTo(fin) <= 0) {
                  rangoSesiones.add(session);
               }
               if(rangoSesiones.size() == 0){ error.setText("No hay sesiones guardadas.");
                 limpiar.setDisable(false);
               }
               else{error.setText("");}

              });

          }
        }
    }

    @FXML
    private void limpiarTabla(ActionEvent event) {
        rangoSesiones.clear();
        error.setText("");
        limpiar.setDisable(true);
    }
    
    @FXML
    private void mano(MouseEvent event) {
        limpiar.setCursor(Cursor.HAND);
        buscar.setCursor(Cursor.HAND); 
        atras.setCursor(Cursor.HAND); 
        tarta.setCursor(Cursor.HAND); 
    }

    @FXML
    private void verTarta(ActionEvent event) throws IOException {
        FXMLLoader miCargador1 = new FXMLLoader(getClass().getResource("/poiupv/TartaVista.fxml"));
        Parent root1 = miCargador1.load();
        poiupv.Tarta controladorIniciar1 = miCargador1.getController();
        controladorIniciar1.initUser(user.getNickName());
        Scene scene = new Scene(root1,507,405);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Stats");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
     
    }
    
}
