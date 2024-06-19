package javafxmlapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class FXMLAyudaMapController implements Initializable{


    @FXML
    private Button atras;



    @FXML
    private Text texto_trazado;

    @FXML
    private ImageView imagenTrazados;
  
    @FXML
    private ToggleButton comoDibujarB;
    @FXML
    private ToggleButton lineaB;
    @FXML
    private ToggleButton puntoB;
    @FXML
    private ToggleButton arcoB;
    @FXML
    private ToggleButton textoB;
    @FXML
    private ToggleButton angulosB;
    @FXML
    private ToggleButton limpiarB;
    @FXML
    private ToggleGroup trazados;
    @FXML
    private ToggleButton colorB;
    @FXML
    private ToggleGroup colorYGrosor;
    @FXML
    private ToggleButton grosorB;
    @FXML
    private Text textoColYGros;
    @FXML
    private ImageView imagenColYGros;
    @FXML
    private ToggleButton calculadoraB;
    @FXML
    private ToggleGroup herramientas;
    @FXML
    private ToggleButton zoomB;
    @FXML
    private ToggleButton iconosB;
    @FXML
    private Text textoHerramientas;
    @FXML
    private ImageView imagenHerramientas;
    @FXML
    private ImageView imagenHerramientas2;
    @FXML
    private Text textoTrazados2;
    @FXML
    private ToggleButton problemasB;
    @FXML
    private ToggleGroup problemas;
    @FXML
    private ToggleButton guardarSesB;
    @FXML
    private Text textoProblemas;
    @FXML
    private ImageView imagenProblemas;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        imagenTrazados.setFitHeight(200);
        imagenTrazados.setFitWidth(100);
    } 

    @FXML
    private void atras(ActionEvent event) {
        atras.getScene().getWindow().hide();
    }



    @FXML
    private void mano(MouseEvent event) {

    }
    

    @FXML
    private void trazadosClicked(ActionEvent event) {
            if(comoDibujarB.isSelected()) {
            texto_trazado.setText("Para poder Dibujar, primero, debes escoger mediante el apartado \"Color y grosor\""
                    + " el grosor y el color del elemento a dibujar. A continuación seleccionaras cualquiera de las opciones posibles.");
            imagenTrazados.setImage(new Image("/icons/Trazados.png"));
            imagenTrazados.setFitHeight(200);
            imagenTrazados.setFitWidth(100);
            textoTrazados2.setText(null);
        }
        if(lineaB.isSelected()) {
            texto_trazado.setText("Para dibujar una Línea en el mapa, habiendo escogido el color y grosor,"
                    + " seleccionaras el botón \"Línea\". A continuación debes mantener el click presionado en el mapa y "
                    + "arrastrar para definir la dirección y tamaño de la línea");
            imagenTrazados.setImage(new Image("/icons/Linea.png"));
            imagenTrazados.setFitHeight(150);
            imagenTrazados.setFitWidth(200);
            textoTrazados2.setText("Avanzado: Con un \"click derecho\" encima de una línea dibujada puedes "
                    + "eliminar esa línea mediante la opción  \"eliminar\" o cambiar el color de esta con la opción"
                    + " \"Cambia color\". Para cambiar el color hay que definir primero el color y luego usar la opción.");
        }
        if(puntoB.isSelected()) {
            texto_trazado.setText("Para dibujar un punto o un círculo, luego de haber definido su color y grosor, debes "
                    + "escoger la opción \"Punto\". Habiendo escogido esta opción, debes clicar encima del mapa para "
                    + "dibujar puntos tu");
            imagenTrazados.setImage(new Image("/icons/Punto.png"));
            imagenTrazados.setFitHeight(150);
            imagenTrazados.setFitWidth(200);
            textoTrazados2.setText("Avanzado: Con un \"click derecho\" encima de un punto dibujado puedes "
                    + "eliminar ese punto mediante la opción  \"eliminar\" o cambiar el color de este con la opción"
                    + " \"Cambia color\" o hacer aparecer unas líneas verticales y horizontales a partir"
                    + " de ese punto con la opción \"Marcar Extremos\". Para cambiar el color hay que definir "
                    + "primero el color y luego usar la opción.");
        }
        if(arcoB.isSelected()) {
            texto_trazado.setText("Para dibujar un arco, habiendo definido el color y el grosor, debes "
                    + "escoger la opción \"Arco\". Escogida la opción, debes clicar y mantener encima del mapa para "
                    + "luego arrastrar para definir el radio del arco.");
            imagenTrazados.setImage(new Image("/icons/Arco.png"));
            imagenTrazados.setFitHeight(150);
            imagenTrazados.setFitWidth(200);
            textoTrazados2.setText("Avanzado: Con un \"click derecho\" encima de un arco dibujado puedes "
                    + "eliminar ese arco mediante la opción  \"eliminar\" o cambiar el color de este con la opción"
                    + " \"Cambia color\". Para cambiar el color hay que definir primero el color y luego usar la opción.");
        }
        if(textoB.isSelected()) {
            texto_trazado.setText("Para escribir texto en el mapa, luego de escoger el color y tamaño de la letra"
                    + " deberás escoger la opción de \"Texto\". A continuación, si clicas encima del mapa, aparecerá una zona"
                    + " para escribir. Luego de haber escrito puedes apretar \"ENTER\" para poner el texto en el mapa.");
            imagenTrazados.setImage(new Image("/icons/Texto.png"));
            imagenTrazados.setFitHeight(150);
            imagenTrazados.setFitWidth(200);
            textoTrazados2.setText("Avanzado: Con un \"click derecho\" encima de una línea de texto ya escrita puedes "
                    + "eliminar ese texto mediante la opción  \"eliminar\" o cambiar el color de este con la opción"
                    + " \"Cambia color\". Para cambiar el color hay que definir primero el color y luego usar la opción.");
        }
        if(angulosB.isSelected()) {
            texto_trazado.setText("Esta opción sirve para sacar un \"Transportador\" en el mapa para tomar todo tipo de ángulos."
                    + " Para utilizar el transportador, tan solo seleccionamos la opción \"Ángulos\" y aparecerá un transportador"
                    + " en la esquina SUPERIOR IZQUIERDA del mapa. El transportador se puede mover desplazandolo"
                    + " con el ratón mientras mantienes el click encima. También se puede ajustar el tamaño del elemento.");
            imagenTrazados.setImage(new Image("/icons/Angulos.png"));
            imagenTrazados.setFitHeight(150);
            imagenTrazados.setFitWidth(200);
            textoTrazados2.setText(null);
        }
        if(limpiarB.isSelected()) {
            texto_trazado.setText("Este botón sirve para eliminar TODOS los elementos que haya en el mapa.");
            imagenTrazados.setImage(new Image("/icons/Limpiar.png"));
            imagenTrazados.setFitHeight(150);
            imagenTrazados.setFitWidth(200);
            textoTrazados2.setText(null);
        }
    
    }

    @FXML
    private void colYGrosClicked(ActionEvent event) {
        if(colorB.isSelected()) {
            textoColYGros.setText("Esta primera opción permite definir el color de los elementos a dibujar en el mapa."
                    + " Si haces click en esta opción, se abre un desplegable con muchos colores a elegir. Luego de "
                    + "elegir el color, si dibujas cualquier elemento del apartado trazados, este será del color escogido. "
                    + "También puedes escoger un color \"PERSONALIZADO\".");
            imagenColYGros.setImage(new Image("/icons/Color.png"));
            imagenColYGros.setFitHeight(182);
            imagenColYGros.setFitWidth(178);
        }
        if(grosorB.isSelected()) {
            textoColYGros.setText("Este elemento deslizante permite guardar el grosor o tamaño del siguiente elemento "
                    + "que pongas en el mapa. Simplemente debes clicar y desplazar la barra para escoger el tamaño deseado");
            imagenColYGros.setImage(new Image("/icons/Grosor.png"));
            imagenColYGros.setFitHeight(182);
            imagenColYGros.setFitWidth(178);
        }
    }

    @FXML
    private void herramientasClicked(ActionEvent event) {
        if(iconosB.isSelected()) {
            textoHerramientas.setText("Esta opción permite dibujar iconos dentro de una lista variada. Podemos "
                    + "escoger la imagen a dibujar clicando primero el botón con \"dos flechas negras\" y luego abriendo"
                    + " el desplegable para escoger el icono deseado. Después de haber escogido el icono, pulsando"
                    + " encima del mapa se dibujarán las imagenes.");
            imagenHerramientas.setImage(new Image("/icons/iconos1.png"));
            imagenHerramientas.setFitHeight(182);
            imagenHerramientas.setFitWidth(178);
            imagenHerramientas2.setImage(new Image("/icons/iconos2.png"));
            
        } 
        
        if(calculadoraB.isSelected()) {
            textoHerramientas.setText("KartoMapp tiene su propia CALCULADORA para realizar todo tipo de operaciones básicas. "
                    + "Para acceder a la calculadora, tan solo debes dirigirte a la barra de herramientas de arriba de la "
                    + "aplicación y en el apartado \"Herramientas\" encontrarás la opción \"Calculadora\".");
            imagenHerramientas.setImage(new Image("/icons/Calculadora.png"));
            imagenHerramientas.setFitHeight(182);
            imagenHerramientas.setFitWidth(178);
            imagenHerramientas2.setImage(null);
        }
        if(zoomB.isSelected()) {
            textoHerramientas.setText("Para acercar o alejar el mapa usaremos la función \"Zoom\" de la aplicación. Esta función se "
                    + "encuentra en la zona superior derecha de la ventana. Podemos regular el zoom con los botones \"+\" y \"-\""
                    + " o utilizando la barra desplazante.");
            imagenHerramientas.setImage(new Image("/icons/Zoom.png"));
            imagenHerramientas.setFitHeight(200);
            imagenHerramientas.setFitWidth(200);
            imagenHerramientas2.setImage(null);
        }
        
    }

    @FXML
    private void problemasClicked(ActionEvent event) {
        if(problemasB.isSelected()) {
            textoProblemas.setText("En esta pantalla podrás realizar los distintos problemas que hayas escogido "
                    + "mediante la \"Lista de Problemas\" o mediante la opción de \"Problema Aleatorio\". Puedes"
                    + " escoger la opción que veas correcta y al apretar el botón \"Corregir\", se mostrará el resultado correcto.");
            imagenProblemas.setImage(new Image("/icons/Problema.png"));
            imagenProblemas.setFitHeight(335);
            imagenProblemas.setFitWidth(246);            
        }
        if(guardarSesB.isSelected()) {
            textoProblemas.setText("Para guardar correctamente los resultados en la sesión, recuerda que se puede hacer"
                    + " de dos maneras: \n\n"
                    + "Puedes utilizar la opción de \"Cerrar Sesión\" del apartado \"Estudiante\" o mediante la opción \"Cerrar\" "
                    + "del apartado \"Aplicación\" de la barra de herramientas.");
            imagenProblemas.setImage(null);
            }

    }
}
