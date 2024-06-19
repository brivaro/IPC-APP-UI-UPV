/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import DBAccess.NavegacionDAOException;
import static java.awt.Color.ORANGE;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Answer;
import model.Navegacion;
import model.Problem;
import model.Session;
import model.User;
import poiupv.Poi;

/**
 * FXML Controller class
 *
 * @author Brian
 */
public class FXMLMapaController implements Initializable {
    // ======================================
    // la variable zoomGroup se utiliza para dar soporte al zoom
    // el escalado se realiza sobre este nodo, al escalar el Group no mueve sus nodos

    @FXML
    private MenuItem cerrar;
    @FXML
    private MenuItem calculadora;
    @FXML
    private Slider zoom_slider;
    @FXML
    private Button atras;
    @FXML
    private ScrollPane map_scrollpane;
    @FXML
    private MenuButton map_pin;
    @FXML
    private MenuItem pin_info;
    // la variable zoomGroup se utiliza para dar soporte al zoom
    // el escalado se realiza sobre este nodo, al escalar el Group no mueve sus nodos
    private Group zoomGroup;
    @FXML
    private AnchorPane pane;
    @FXML
    private ImageView avatar;
    private Button button;
    private User user;
    private String nick;
    @FXML
    private Label enunciado;
    @FXML
    private ToggleGroup trazados;
    @FXML
    private RadioButton an1;
    @FXML
    private RadioButton an2;
    @FXML
    private RadioButton an3;
    @FXML
    private RadioButton an4;
    
    private Problem problemActual;
    private Navegacion nav;
    @FXML
    private ToggleGroup respuestasGrupo;
    @FXML
    private Button corregir;
    private Button b; 
    private Button buton;
    private String namee;
    @FXML
    private Pane mapPane;
    @FXML
    private ToggleButton lineaBoton;
    @FXML
    private ColorPicker selColor;
    @FXML
    private ToggleButton arcoBoton;
    @FXML
    private ToggleButton puntoBoton;
    @FXML
    private ToggleButton textoBoton;
    @FXML
    private Slider grosorSlider;
    @FXML
    private Button limpiarBoton;
    @FXML
    private ToggleButton chincheta;
    @FXML
    private Label error;
    private Line linea;
    
    private Boolean fotobool = false;

    private List<Answer> respuestas;
    @FXML
    private Button siguiente;
    private static int aciertos = 0;
    private static int fallos = 0;
    @FXML
    private MenuItem cerrarsesion;
    private double inicioXArc;
    private double inicioYArc;

    private Circle arcoPainting;
    private ToggleButton anguloBoton;
    @FXML
    private ImageView trans;
    double x_inicio;
    double y_inicio;
    double baseX;
    double baseY;
    @FXML
    private ImageView fotochincheta;
    @FXML
    private ImageView fotoestrella;
    @FXML
    private ImageView fotobandera;
    @FXML
    private ImageView fototimon;
    @FXML
    private ImageView fotoancla;
    @FXML
    private SplitMenuButton cambio;
    @FXML
    private MenuItem fchin;
    @FXML
    private MenuItem fes;
    @FXML
    private MenuItem fban;
    @FXML
    private MenuItem ftin;
    @FXML
    private MenuItem fan;
    @FXML
    private ImageView foto;
    @FXML
    private ToggleButton transBoton;
    
    private String style = this.getClass().getResource("/icons/stylesheet.css") .toExternalForm();
    @FXML
    private MenuItem orientacion;
    @FXML
    private ImageView fondo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            nav = Navegacion.getSingletonNavegacion();
            selColor.setValue(Color.BLUE);
            grosorSlider.setValue(10);
            fondo.setPreserveRatio(true);
            
            //==========================================================
            // inicializamos el slider y enlazamos con el zoom
            zoom_slider.setMin(0.1);
            zoom_slider.setMax(1.9);
            zoom_slider.setValue(1.0);
            zoom_slider.valueProperty().addListener((o, oldVal, newVal) -> zoom((Double) newVal));
            
            //=========================================================================
            //Envuelva el contenido de scrollpane en un grupo para que
            //ScrollPane vuelva a calcular las barras de desplazamiento tras el escalado
            Group contentGroup = new Group();
            zoomGroup = new Group();
            contentGroup.getChildren().add(zoomGroup);
            zoomGroup.getChildren().add(map_scrollpane.getContent());
            map_scrollpane.setContent(contentGroup);
            
            double ini = trans.getFitHeight();
            double fin = trans.getFitWidth();
            
            grosorSlider.valueProperty().addListener((o, oldVal, newVal) -> trans.setFitHeight(ini + ((Double) newVal)));
            grosorSlider.valueProperty().addListener((o, oldVal, newVal) -> trans.setFitWidth(fin + ((Double) newVal)));

            
          puntoBoton.focusedProperty().addListener((observable, oldValue, newValue)->
            { if(!newValue){
                    if(!grosorSlider.isValueChanging()){
                        error.setText("Selecciona un color y grosor para dibujar a tu gusto, cuidado que mancha!");   
                        if(!selColor.isPressed()) error.setText("Selecciona un color y grosor para dibujar a tu gusto, cuidado que mancha!");
                }
                    else if (!selColor.isPressed()){
                        error.setText("Selecciona un color y grosor para dibujar a tu gusto, cuidado que mancha"); 
                        if(!grosorSlider.isValueChanging()){
                        error.setText("Selecciona un color y grosor para dibujar a tu gusto, cuidado que mancha!");}
                    }
            }
            });
          lineaBoton.focusedProperty().addListener((observable, oldValue, newValue)->
            { if(!newValue){
                    if(!grosorSlider.isValueChanging()){
                        error.setText("Selecciona un color y grosor para dibujar a tu gusto, cuidado que mancha!");   
                        if(!selColor.isPressed()) error.setText("Selecciona un color y grosor para dibujar a tu gusto, cuidado que mancha!");
                }
                    else if (!selColor.isPressed()){
                        error.setText("Selecciona un color y grosor para dibujar a tu gusto, cuidado que mancha!"); 
                        if(!grosorSlider.isValueChanging()){
                        error.setText("Selecciona un color y grosor para dibujar a tu gusto, cuidado que mancha!");}
                    }
            }
            });
          arcoBoton.focusedProperty().addListener((observable, oldValue, newValue)->
            { if(!newValue){
                    if(!grosorSlider.isValueChanging()){
                        error.setText("Selecciona un color y grosor para dibujar a tu gusto, cuidado que mancha!");   
                        if(!selColor.isPressed()) error.setText("Selecciona un color y grosor para dibujar a tu gusto, cuidado que mancha!");
                }
                    else if (!selColor.isPressed()){
                        error.setText("Selecciona un color y grosor para dibujar a tu gusto, cuidado que mancha!"); 
                        if(!grosorSlider.isValueChanging()){
                        error.setText("Selecciona un color y grosor para dibujar a tu gusto, cuidado que mancha!");}
                    }
            }
            });
          textoBoton.focusedProperty().addListener((observable, oldValue, newValue)->
            { if(!newValue){
                    if(!grosorSlider.isValueChanging()){
                        error.setText("Selecciona un color y tamaño de fuente para escribir a tu gusto!");   
                        if(!selColor.isPressed()) error.setText("Selecciona un color y tamaño de fuente para escribir a tu gusto!");
                }
                    else if (!selColor.isPressed()){
                        error.setText("Selecciona un color y tamaño de fuente para escribir a tu gusto!"); 
                        if(!grosorSlider.isValueChanging()){
                        error.setText("Selecciona un color y tamaño de fuente para escribir a tu gusto!");}
                    }
            }
            });

          
          /*BooleanBinding visible = Bindings.and(an1.textProperty().isEmpty(), an2.textProperty().isEmpty());
          BooleanBinding visible2 = Bindings.and(an3.textProperty().isEmpty(),an4.textProperty().isEmpty());
          corregir.disableProperty().bind(visible2);*/
          corregir.setDisable(true);
          siguiente.setVisible(false);
          
        } catch (NavegacionDAOException ex) {
            Logger.getLogger(FXMLMapaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    @FXML
    private void cerrarApp(ActionEvent event) throws NavegacionDAOException {
        if(!cerrar.isDisable()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            //Cambia el icono por uno propio
            Stage stagee= (Stage) alert.getDialogPane().getScene().getWindow();
            stagee.getIcons().add(new Image(this.getClass().getResourceAsStream("/icons/efw.png")));
            alert.setTitle("Aviso");
            alert.setHeaderText("El programa se va a cerrar");
            alert.setContentText("¿Seguro que desea continuar?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK){
            System.out.println("OK");
            LocalDateTime d = LocalDateTime.now();
            Session s = new Session(d,aciertos,fallos);
            user.addSession(s);
            System.exit(0);
            } else {
            System.out.println("CANCEL");
            } 
        }
    }

    @FXML
    private void verCalculadora(ActionEvent event) throws IOException {
        FXMLLoader miCargador1 = new FXMLLoader(getClass().getResource("/javafxmlapplication/FXMLDocument.fxml"));
        Parent root = miCargador1.load();
        FXMLDocumentController controladorIniciar = miCargador1.getController();
        
        Scene scene = new Scene(root,300,400);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Calculadora");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void zoomOut(ActionEvent event) {
        double sliderVal = zoom_slider.getValue();
        zoom_slider.setValue(sliderVal + -0.1);
    }

    @FXML
    private void zoomIn(ActionEvent event) {
        //================================================
        // el incremento del zoom dependerá de los parametros del 
        // slider y del resultado esperado
        double sliderVal = zoom_slider.getValue();
        zoom_slider.setValue(sliderVal += 0.1);
    }

    @FXML
    private void atras(ActionEvent event) throws IOException {
        atras.getScene().getWindow().hide();
    }

    private void zoom(double scaleValue) {
        //===================================================
        //guardamos los valores del scroll antes del escalado
        double scrollH = map_scrollpane.getHvalue();
        double scrollV = map_scrollpane.getVvalue();
        //===================================================
        // escalamos el zoomGroup en X e Y con el valor de entrada
        zoomGroup.setScaleX(scaleValue);
        zoomGroup.setScaleY(scaleValue);
        //===================================================
        // recuperamos el valor del scroll antes del escalado
        map_scrollpane.setHvalue(scrollH);
        map_scrollpane.setVvalue(scrollV);    }

    void initAvatar(Image a, Button b, String name) {
        avatar.setImage(a);
        button = b;
        nick = name;
        user = nav.getUser(name);
    }

    void initProblema(String p, Problem prob, Button bo, Image ima) {
        enunciado.setText(p);
        System.out.println(p);
        problemActual = prob;
        respuestas = new ArrayList(problemActual.getAnswers());
        Collections.shuffle(respuestas);
        an1.setText(respuestas.get(0).getText());
        an2.setText(respuestas.get(1).getText());
        an3.setText(respuestas.get(2).getText());
        an4.setText(respuestas.get(3).getText());
        b = bo;
        avatar.setImage(ima);
    }

    @FXML
    private void circuloClick(MouseEvent event) {
        if(lineaBoton.isSelected()){
            lineaBoton.setStyle("-fx-background-color: MediumSeaGreen");
            arcoBoton.setStyle(style);
            puntoBoton.setStyle(style);
            textoBoton.setStyle(style);
            chincheta.setStyle(style);
            
         linea = new Line(event.getX(),event.getY(),event.getX(),event.getY());
         zoomGroup.getChildren().add(linea);
         error.setText("Trazado de linea seleccionado, a pintar el mapa!");
         linea.setStrokeWidth(grosorSlider.getValue());
         linea.setStroke(selColor.getValue());
         linea.setOnContextMenuRequested(eer-> {
                ContextMenu m = new ContextMenu();
                MenuItem color = new MenuItem("Cambiar color");
                MenuItem borrar = new MenuItem("Eliminar");
                m.getItems().add(color);
                m.getItems().add(borrar);
                
                color.setOnAction(ev-> {
                    error.setText("Cambie primero el color elegido en la paleta.");
                    linea.setStroke(selColor.getValue());
                    ev.consume();
                });
                borrar.setOnAction(ev-> {
                    zoomGroup.getChildren().remove((Node)eer.getSource());
                    ev.consume();
                });
                m.show(linea,eer.getSceneX(),eer.getSceneY());
                eer.consume();
                
            });
        }else lineaBoton.setStyle(style);
        
        if(puntoBoton.isSelected()){
            lineaBoton.setStyle(style);
            arcoBoton.setStyle(style);
            puntoBoton.setStyle("-fx-background-color: MediumSeaGreen");
            textoBoton.setStyle(style);
            chincheta.setStyle(style);
            
          Circle circlePainting = new Circle(grosorSlider.getValue());
          circlePainting.setFill(selColor.getValue());
          zoomGroup.getChildren().add(circlePainting);
          error.setText("Trazado de punto seleccionado, a señalizar el mapa!");
          circlePainting.setCenterX(event.getX());
          circlePainting.setCenterY(event.getY());
          circlePainting.setOnContextMenuRequested(e-> {
                ContextMenu m = new ContextMenu();
                MenuItem extrem = new MenuItem("Marcar Extremos");
                MenuItem color = new MenuItem("Cambiar color");
                MenuItem borrar = new MenuItem("Eliminar");
                m.getItems().add(extrem);
                m.getItems().add(color);
                m.getItems().add(borrar);
                
                extrem.setOnAction(ev-> {
                     error.setText("Elija el grosor de los extremos para el trazado punto. Tenga en cuenta dibujar puntos dentro del mapa, no fuera!");
                     Line hLine = new Line(290,event.getY(),8393,event.getY());            
                     Line vLine = new Line(event.getX(),370,event.getX(),5405);
            
                     vLine.setStroke(selColor.getValue());
                     vLine.setStrokeWidth(grosorSlider.getValue() );
                     hLine.setStroke(selColor.getValue());
                     hLine.setStrokeWidth(grosorSlider.getValue() );
                     
                     vLine.setOnContextMenuRequested(ee-> {
                     ContextMenu mm = new ContextMenu();
                     MenuItem colorr = new MenuItem("Cambiar color");
                     MenuItem borrarr = new MenuItem("Eliminar");
                     mm.getItems().add(colorr);
                     mm.getItems().add(borrarr);
                     
                     colorr.setOnAction(eev-> {
                     error.setText("Cambie primero el color elegido en la paleta.");
                     vLine.setStroke(selColor.getValue());
                     eev.consume();
                        });
                     
                     borrarr.setOnAction(eev-> {
                     zoomGroup.getChildren().remove((Node)ee.getSource());
                     eev.consume();
                        });
                     
                        mm.show(vLine,ee.getSceneX(),ee.getSceneY());
                        ee.consume();
                    });
                     
                     hLine.setOnContextMenuRequested(eee-> {
                     ContextMenu mmm = new ContextMenu();
                     MenuItem colorrr = new MenuItem("Cambiar color");
                     MenuItem borrarrr = new MenuItem("Eliminar");
                     mmm.getItems().add(colorrr);
                     mmm.getItems().add(borrarrr);
                     
                     colorrr.setOnAction(eev-> {
                     error.setText("Cambie primero el color elegido en la paleta.");
                     hLine.setStroke(selColor.getValue());
                     eev.consume();
                        });
                     
                     borrarrr.setOnAction(eeev-> {
                     zoomGroup.getChildren().remove((Node)eee.getSource());
                     eeev.consume();
                        });
                     
                        mmm.show(hLine,eee.getSceneX(),eee.getSceneY());
                        eee.consume();
                    });
                     
                     zoomGroup.getChildren().add(hLine); 
                     zoomGroup.getChildren().add(vLine);
                    ev.consume();
                });
                
                color.setOnAction(ev-> {
                    error.setText("Cambie primero el color elegido en la paleta.");
                    circlePainting.setFill(selColor.getValue());
                    ev.consume();
                });
                borrar.setOnAction(ev-> {
                    zoomGroup.getChildren().remove((Node)e.getSource());
                    ev.consume();
                });
                m.show(circlePainting,e.getSceneX(),e.getSceneY());
                e.consume();
            });
        }else puntoBoton.setStyle(style);     
        
        if(arcoBoton.isSelected()) {
            lineaBoton.setStyle(style);
            arcoBoton.setStyle("-fx-background-color: MediumSeaGreen");
            puntoBoton.setStyle(style);
            textoBoton.setStyle(style);
            chincheta.setStyle(style);
            
            arcoPainting = new Circle(grosorSlider.getValue());
            arcoPainting.setStrokeWidth(grosorSlider.getValue());
            arcoPainting.setStroke(selColor.getValue());
            //arcoPainting.setFill(Color.TRANSPARENT);
            arcoPainting.setFill(null);
            zoomGroup.getChildren().add(arcoPainting);
            error.setText("Trazado de arco seleccionado, a marcar territorios por el mapa!");
            arcoPainting.setCenterX(event.getX());
            arcoPainting.setCenterY(event.getY());
            inicioXArc = event.getX();
            inicioYArc = event.getY();
            
            arcoPainting.setOnContextMenuRequested(e-> {
                ContextMenu m = new ContextMenu();
                MenuItem color = new MenuItem("Cambiar color");
                MenuItem borrar = new MenuItem("Eliminar");
                m.getItems().add(color);
                m.getItems().add(borrar);
                color.setOnAction(ev-> {
                    error.setText("Cambie primero el color elegido en la paleta.");
                    arcoPainting.setStroke(selColor.getValue());
                    ev.consume();
                });
                borrar.setOnAction(ev-> {
                    zoomGroup.getChildren().remove((Node)e.getSource());
                    ev.consume();
                });
                m.show(arcoPainting,e.getSceneX(),e.getSceneY());
                e.consume();
            });
        }else arcoBoton.setStyle(style);
        
        if(chincheta.isSelected()){
            lineaBoton.setStyle(style);
            arcoBoton.setStyle(style);
            puntoBoton.setStyle(style);
            textoBoton.setStyle(style);
            chincheta.setStyle("-fx-background-color: MediumSeaGreen");
            
        error.setText("Punto de interés con chincheta seleccionado, cuidado, no te cargues el mapa!");
        
        Image image1;
        if(fotobool == false){
        image1 = new Image(getClass().getResource("/icons/1f4cc.png").toExternalForm());
        error.setText("Haga click primero en las flechas negras para cambiar de chincheta si desea de seleccionar una nueva.");
        }
        
        else {image1 = foto.getImage();}
        
        ImageView imageView1 = new ImageView(image1);
        imageView1.setFitHeight(100);
        imageView1.setFitWidth(100);
        zoomGroup.getChildren().add(imageView1);   
        imageView1.setX(event.getX() );
        imageView1.setY(event.getY()-95); 
        
        imageView1.setOnContextMenuRequested(e-> {
                ContextMenu m = new ContextMenu();
                MenuItem borrar = new MenuItem("Eliminar");
                m.getItems().add(borrar);
                borrar.setOnAction(ev-> {
                    zoomGroup.getChildren().remove((Node)e.getSource());
                    ev.consume();
                });
                m.show(imageView1,e.getSceneX(),e.getSceneY());
                e.consume();
            });
        }
        if (textoBoton.isSelected()) {
            lineaBoton.setStyle(style);
            arcoBoton.setStyle(style);
            puntoBoton.setStyle(style);
            textoBoton.setStyle("-fx-background-color: MediumSeaGreen");
            chincheta.setStyle(style);
            
            error.setText("Anotaciones seleccionadas, cuidado, queda poco tipex!");
            Font font = new Font(grosorSlider.getValue());
            
            TextField texto = new TextField();
            //texto.setBackground(null); Hace que el TextField se ponga sin backGround, i.e. "transoarente";
            texto.setOpacity(0.8);
            texto.setFont(font);
            zoomGroup.getChildren().add(texto);
            texto.setLayoutX(event.getX());
            texto.setLayoutY(event.getY());
            texto.requestFocus();
            
            texto.setOnAction(e -> {
                Text textoT = new Text(texto.getText());
                textoT.setX(texto.getLayoutX());
                textoT.setY(texto.getLayoutY());
                
                textoT.setFont(font);
                
                textoT.setFill(selColor.getValue());
                zoomGroup.getChildren().add(textoT);
                zoomGroup.getChildren().remove(texto);
                
                textoT.setOnContextMenuRequested(eve -> {
                ContextMenu menuContext = new ContextMenu();
                MenuItem color = new MenuItem("Cambiar color");
                MenuItem borrarItem = new MenuItem("Eliminar");
                menuContext.getItems().add(color);
                menuContext.getItems().add(borrarItem);
                color.setOnAction(even -> {
                    error.setText("Cambie primero el color elegido en la paleta.");
                    textoT.setFill(selColor.getValue());
                    even.consume();
                });
                borrarItem.setOnAction(even -> {
                    zoomGroup.getChildren().remove((Node)eve.getSource());
                    even.consume();
                });
                menuContext.show(textoT, eve.getSceneX(), eve.getSceneY());
                eve.consume();
            });
            }); 
            
        }else textoBoton.setStyle(style);

    }
    
    @FXML
    private void circuloDrag(MouseEvent event) {
        if(lineaBoton.isSelected()){
        linea.setEndX(event.getX());
        linea.setEndY(event.getY());
        event.consume();
        }
        if(arcoBoton.isSelected()){
        double radio = Math.sqrt(Math.pow(event.getX() - inicioXArc, 2) + Math.pow(event.getY() - inicioYArc,2));
        arcoPainting.setRadius(radio);
        event.consume();
        }
        else{
        //guardamos los valores del scroll antes del escalado
        double scrollH = map_scrollpane.getHvalue();
        double scrollV = map_scrollpane.getVvalue();
        //===================================================
        // recuperamos el valor del scroll antes del escalado
        map_scrollpane.setHvalue(scrollH);
        map_scrollpane.setVvalue(scrollV);
        }
    }

    @FXML
    private void limpiar(ActionEvent event) {
        while(zoomGroup.getChildren().size()>1){
        int k = zoomGroup.getChildren().size();
        zoomGroup.getChildren().remove(k-1);
        }
    }

    @FXML
    private void verCorreccion(ActionEvent event) { //FALTA GUARDAR LOS RESULTADOS EN EL USER CON ADD SESION O LO Q SEA
        if(!an1.isSelected() && !an2.isSelected() && !an3.isSelected() && !an4.isSelected()){error.setText("Error, seleccione una respuesta.");}
        List<Answer> respuestass = problemActual.getAnswers();
        if(an1.isSelected()){error.setText("");
         Boolean an11 = respuestass.get(0).getValidity();
         if(an11 == false){an1.styleProperty().setValue("-fx-background-color: #F37878");
         respuestacorrecta();fallo();corregir.setDisable(true);siguiente.setVisible(true);
         fallos++;
         }
         else {an1.styleProperty().setValue("-fx-background-color: #A1F378");
         acierto();corregir.setDisable(true);siguiente.setVisible(true);
         aciertos++;
         }
         an1.setDisable(true);
         an2.setDisable(true);
         an3.setDisable(true);
         an4.setDisable(true);
        }
        if(an2.isSelected()){error.setText("");
         Boolean an11 = respuestass.get(1).getValidity();
         if(an11 == false){an2.styleProperty().setValue("-fx-background-color: #F37878");
         respuestacorrecta();fallo();corregir.setDisable(true);siguiente.setVisible(true);
         fallos++;
         }
         else {an2.styleProperty().setValue("-fx-background-color: #A1F378");
         acierto();corregir.setDisable(true);siguiente.setVisible(true);
         aciertos++;
         }
         an1.setDisable(true);
         an2.setDisable(true);
         an3.setDisable(true);
         an4.setDisable(true);
        }
        if(an3.isSelected()){error.setText("");
         Boolean an11 = respuestass.get(2).getValidity();
         if(an11 == false){an3.styleProperty().setValue("-fx-background-color: #F37878");
         respuestacorrecta();fallo();corregir.setDisable(true);siguiente.setVisible(true);
         fallos++;
        }
         else {an3.styleProperty().setValue("-fx-background-color: #A1F378");
         acierto();corregir.setDisable(true);siguiente.setVisible(true);
         aciertos++;
         }
         an1.setDisable(true);
         an2.setDisable(true);
         an3.setDisable(true);
         an4.setDisable(true);
        }
        if(an4.isSelected()){error.setText("");
         Boolean an11 = respuestass.get(3).getValidity();
         if(an11 == false){an4.styleProperty().setValue("-fx-background-color: #F37878");
         respuestacorrecta();fallo();corregir.setDisable(true);siguiente.setVisible(true);
         fallos++;
         }
         else {an4.styleProperty().setValue("-fx-background-color: #A1F378");
         acierto();corregir.setDisable(true);siguiente.setVisible(true);
         aciertos++;
         }
         an1.setDisable(true);
         an2.setDisable(true);
         an3.setDisable(true);
         an4.setDisable(true);
        }
        System.out.println(aciertos +","+ fallos);
    }

    private void fallo(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "content text");
        //Cambia el icono por uno propio
            Stage stagee= (Stage) alert.getDialogPane().getScene().getWindow();
            stagee.getIcons().add(new Image(this.getClass().getResourceAsStream("/icons/efw.png")));
        //codigo sacado de
        //https://ajaxhispano.com/ask/alertas-javafx-y-su-tamano-76350/
        alert.getDialogPane().getChildren().stream().filter(node -> node instanceof Label).forEach(node -> ((Label)node).setMinHeight(Region.USE_PREF_SIZE));
        //////////////////////////////////////////////////////////////
        alert.setTitle("Fallo");
        alert.setHeaderText("Más suerte la próxima vez!");
        // ó null si no queremos cabecera
        alert.setContentText("Se ha esforzado mucho, tómese un descanso, se lo ha merecido. "
                + "Vuelva por estos mares cuando quiera resolver más problemas.");
        alert.showAndWait();
         
    }
    private void acierto(){
    Alert alert = new Alert(Alert.AlertType.INFORMATION, "content text");
    //Cambia el icono por uno propio
            Stage stagee= (Stage) alert.getDialogPane().getScene().getWindow();
            stagee.getIcons().add(new Image(this.getClass().getResourceAsStream("/icons/efw.png")));
        //codigo sacado de
        //https://ajaxhispano.com/ask/alertas-javafx-y-su-tamano-76350/
        alert.getDialogPane().getChildren().stream().filter(node -> node instanceof Label).forEach(node -> ((Label)node).setMinHeight(Region.USE_PREF_SIZE));
        //////////////////////////////////////////////////////////////
        alert.setTitle("Acierto");
        alert.setHeaderText("Enhorabuena!");
        // ó null si no queremos cabecera
        alert.setContentText("Ha dado en el blanco! Tómese un descanso, se lo ha merecido. "
                + "Vuelva por estos mares cuando quiera resolver más problemas.");
        alert.showAndWait();
    }

    @FXML
    private void verSiguiente(ActionEvent event) throws IOException {
        List<Problem> problemas = nav.getProblems();
        Random n = new Random();
        int valor = n.nextInt(18);
        String enunciado = problemas.get(valor).getText();
        Problem p = problemas.get(valor);
        FXMLLoader miCargador1 = new FXMLLoader(getClass().getResource("/Controllers/FXMLMapa.fxml"));
        Parent root = miCargador1.load();
        FXMLMapaController controladorIniciar = miCargador1.getController();
        controladorIniciar.initStats3(aciertos,fallos);
        controladorIniciar.initUser(nick,avatar.getImage());
        controladorIniciar.initProblema(enunciado,p,b,avatar.getImage());
        Scene scene = new Scene(root,1181,731);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Carta Náutica");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);//tiene q redimensionarse
        stage.show();
        corregir.getScene().getWindow().hide();
    }

    @FXML
    private void opcion(ActionEvent event) {
        corregir.setDisable(false);
    }
    
    private void respuestacorrecta(){
        List<Answer> respuestass = problemActual.getAnswers();
        if(an1.isSelected()){
         Boolean an11 = respuestass.get(0).getValidity();
         Boolean an12 = respuestass.get(1).getValidity();
         Boolean an13 = respuestass.get(2).getValidity();
         Boolean an14 = respuestass.get(3).getValidity();
         if(an11 == true){}
         else{
         if(an12 == true){an2.styleProperty().setValue("-fx-background-color: #A1F378");}
         if(an13 == true){an3.styleProperty().setValue("-fx-background-color: #A1F378");}
         if(an14 == true){an4.styleProperty().setValue("-fx-background-color: #A1F378");}}
        }
        if(an2.isSelected()){
         Boolean an11 = respuestass.get(0).getValidity();
         Boolean an12 = respuestass.get(1).getValidity();
         Boolean an13 = respuestass.get(2).getValidity();
         Boolean an14 = respuestass.get(3).getValidity();
        if(an12 == true){}
         else{
         if(an11 == true){an1.styleProperty().setValue("-fx-background-color: #A1F378");}
         if(an13 == true){an3.styleProperty().setValue("-fx-background-color: #A1F378");}
         if(an14 == true){an4.styleProperty().setValue("-fx-background-color: #A1F378");}}
        }
        if(an3.isSelected()){
         Boolean an11 = respuestass.get(0).getValidity();
         Boolean an12 = respuestass.get(1).getValidity();
         Boolean an13 = respuestass.get(2).getValidity();
         Boolean an14 = respuestass.get(3).getValidity();
         if(an13 == true){}
         else{
         if(an12 == true){an2.styleProperty().setValue("-fx-background-color: #A1F378");}
         if(an11 == true){an1.styleProperty().setValue("-fx-background-color: #A1F378");}
         if(an14 == true){an4.styleProperty().setValue("-fx-background-color: #A1F378");}}
        }
        if(an4.isSelected()){
         Boolean an11 = respuestass.get(0).getValidity();
         Boolean an12 = respuestass.get(1).getValidity();
         Boolean an13 = respuestass.get(2).getValidity();
         Boolean an14 = respuestass.get(3).getValidity();
         if(an14 == true){}
         else{
         if(an12 == true){an2.styleProperty().setValue("-fx-background-color: #A1F378");}
         if(an13 == true){an3.styleProperty().setValue("-fx-background-color: #A1F378");}
         if(an11 == true){an1.styleProperty().setValue("-fx-background-color: #A1F378");}}
        }
    }

    @FXML
    private void cerrarSesion(ActionEvent event) throws IOException, NavegacionDAOException {
        if(!cerrarsesion.isDisable()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            //Cambia el icono por uno propio
            Stage stagee= (Stage) alert.getDialogPane().getScene().getWindow();
            stagee.getIcons().add(new Image(this.getClass().getResourceAsStream("/icons/efw.png")));
            alert.setTitle("Aviso");
            alert.setHeaderText("La sesión de " + nick + " va a cerrarse");
            alert.setContentText("¿Seguro que desea continuar?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK){
                System.out.println("OK"); 
                b.getScene().getWindow().hide();
                corregir.getScene().getWindow().hide();
                //AÑADIR SESION AL USUARIO
                LocalDateTime d = LocalDateTime.now();
                Session s = new Session(d,aciertos,fallos);
                user.addSession(s);
                FXMLLoader miCargador2 = new FXMLLoader(getClass().getResource("/Controllers/MainContainer.fxml"));
                Parent root2 = miCargador2.load();
                MainContainerController controladorIniciar = miCargador2.getController();
                Scene scene = new Scene(root2,790,528);
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

    void initButton(Button ayuda) {
        b = ayuda;
    }

    void initUser(String name, Image ima) {
        nick = name;
        user = nav.getUser(name);
        avatar.setImage(ima);
    }

    private void initStats3(int a, int f) {
        aciertos = a;
        fallos = f;
    }

    @FXML
    private void mano(MouseEvent event) {
        corregir.setCursor(Cursor.HAND);
        siguiente.setCursor(Cursor.HAND); 
        limpiarBoton.setCursor(Cursor.HAND);
        chincheta.setCursor(Cursor.HAND); 
        lineaBoton.setCursor(Cursor.HAND); 
        puntoBoton.setCursor(Cursor.HAND); 
        textoBoton.setCursor(Cursor.HAND); 
        atras.setCursor(Cursor.HAND);
    }
    
    public int getHit(){return aciertos;}
    public int getFault(){return fallos;}


    @FXML
    private void verTrans(ActionEvent event) {
        error.setText("Transportador de ángulos seleccionado, puede encontrarlo en la esquina superior izquierda.");
        trans.setVisible(transBoton.isSelected());
        transBoton.setStyle("-fx-background-color: MediumSeaGreen");
        if(transBoton.isSelected()==false) error.setText(""); transBoton.setStyle(style);
    }

    @FXML
    private void arrastrarTrans(MouseEvent event) {
        double despX = event.getSceneX() - x_inicio;
        double despY = event.getSceneY() - y_inicio;
        trans.setTranslateX(baseX + despX/zoom_slider.getValue());
        trans.setTranslateY(baseY + despY/zoom_slider.getValue());
        event.consume();
    }

    @FXML
    private void marcarInicio(MouseEvent event) {
        x_inicio = event.getSceneX();
        y_inicio = event.getSceneY();
        baseX = trans.getTranslateX();
        baseY = trans.getTranslateY();
        event.consume();
    }

    @FXML
    private void verCambioFoto(ActionEvent event) {
        fchin.setOnAction((e)-> {
            Image i = fotochincheta.getImage();
            foto.setImage(i);
        System.out.println("Choice 1 selected");
        });
        fes.setOnAction((e)-> {
            Image i = fotoestrella.getImage();
            foto.setImage(i);
        System.out.println("Choice 2 selected");
        });
        fban.setOnAction((e)-> {
            Image i = fotobandera.getImage();
            foto.setImage(i);
        System.out.println("Choice 3 selected");
        });
        ftin.setOnAction((e)-> {
            Image i = fototimon.getImage();
            foto.setImage(i);
        System.out.println("Choice 4 selected");
        });
        fan.setOnAction((e)-> {
            Image i = fotoancla.getImage();
            foto.setImage(i);
        System.out.println("Choice 5 selected");
        });
        fotobool = true;
    }

    @FXML
    private void verOrien(ActionEvent event) throws IOException {
        System.out.println("debug");
        FXMLLoader miCargador2 = new FXMLLoader(getClass().getResource("/Controllers/FXMLAyudaMap.fxml"));
        Parent root = miCargador2.load();
        FXMLAyudaMapController controladorIniciar = miCargador2.getController();
        
        Scene scene = new Scene(root,866, 536);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Ayuda");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void verMapa(ActionEvent event) throws IOException {
        FXMLLoader miCargador1 = new FXMLLoader(getClass().getResource("/Controllers/FXMLVista.fxml"));
        Parent root = miCargador1.load();
        FXMLVistaController controladorIniciar = miCargador1.getController();
        controladorIniciar.initEnunciado(enunciado.getText());
        Scene scene = new Scene(root,600,650);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Carta Náutica");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }


}

