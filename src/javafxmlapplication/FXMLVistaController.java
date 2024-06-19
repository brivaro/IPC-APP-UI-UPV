/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Answer;

/**
 * FXML Controller class
 *
 * @author Brian
 */
public class FXMLVistaController implements Initializable {

    @FXML
    private ScrollPane map_scrollpane;
    @FXML
    private Pane mapPane;
    @FXML
    private MenuButton map_pin;
    @FXML
    private MenuItem pin_info;
    @FXML
    private Slider zoom_slider;
    private Group zoomGroup;
    @FXML
    private ToggleButton lineaBoton;
    @FXML
    private ToggleButton arcoBoton;
    @FXML
    private ToggleButton puntoBoton;
    @FXML
    private ToggleButton textoBoton;
    @FXML
    private ColorPicker selColor;
    @FXML
    private Slider grosorSlider;
    @FXML
    private ToggleButton chincheta;
    @FXML
    private Button limpiarBoton;

    private Line linea;
    private double inicioXArc;
    private double inicioYArc;

    private Circle arcoPainting;
    double x_inicio;
    double y_inicio;
    double baseX;
    double baseY;
    @FXML
    private Label error;
    @FXML
    private ImageView trans;
    @FXML
    private ImageView foto;
    @FXML
    private SplitMenuButton cambio;
    @FXML
    private MenuItem fchin;
    @FXML
    private ImageView fotochincheta;
    @FXML
    private MenuItem fes;
    @FXML
    private ImageView fotoestrella;
    @FXML
    private MenuItem fban;
    @FXML
    private ImageView fotobandera;
    @FXML
    private MenuItem ftin;
    @FXML
    private ImageView fototimon;
    @FXML
    private MenuItem fan;
    @FXML
    private ImageView fotoancla;
    private Boolean fotobool = false;
    @FXML
    private ToggleGroup trazados1;
    @FXML
    private ToggleButton transBoton;
    private String style = this.getClass().getResource("/icons/stylesheet.css") .toExternalForm();;
    @FXML
    private Label enunciado;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //==========================================================
            // inicializamos el slider y enlazamos con el zoom
            zoom_slider.setMin(0.1);
            zoom_slider.setMax(1.9);
            zoom_slider.setValue(1.0);
            zoom_slider.valueProperty().addListener((o, oldVal, newVal) -> zoom((Double) newVal));
            selColor.setValue(Color.BLUE);
            grosorSlider.setValue(10);
            //=========================================================================
            //Envuelva el contenido de scrollpane en un grupo para que
            //ScrollPane vuelva a calcular las barras de desplazamiento tras el escalado
            Group contentGroup = new Group();
            zoomGroup = new Group();
            contentGroup.getChildren().add(zoomGroup);
            zoomGroup.getChildren().add(map_scrollpane.getContent());
            map_scrollpane.setContent(contentGroup);
            grosorSlider.valueProperty().addListener((o, oldVal, newVal) -> trans.setFitHeight((Double) newVal + 200));
            grosorSlider.valueProperty().addListener((o, oldVal, newVal) -> trans.setFitWidth((Double) newVal + 200));

            
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

    @FXML
    private void mano(MouseEvent event) {
        limpiarBoton.setCursor(Cursor.HAND);
        chincheta.setCursor(Cursor.HAND); 
        lineaBoton.setCursor(Cursor.HAND); 
        puntoBoton.setCursor(Cursor.HAND); 
        textoBoton.setCursor(Cursor.HAND); 
    }

    @FXML
    private void verTrans(ActionEvent event) {
         error.setText("Transportador de ángulos seleccionado, puede encontrarlo en la esquina superior izquierda.");
        trans.setVisible(transBoton.isSelected());
        transBoton.setStyle("-fx-background-color: MediumSeaGreen");
        if(transBoton.isSelected()==false) error.setText(""); transBoton.setStyle(style);
    }

    @FXML
    private void limpiar(ActionEvent event) {
        while(zoomGroup.getChildren().size()>1){
        int k = zoomGroup.getChildren().size();
        zoomGroup.getChildren().remove(k-1);
        }
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

    void initEnunciado(String text) {
        enunciado.setText(text);
    }
    
}
