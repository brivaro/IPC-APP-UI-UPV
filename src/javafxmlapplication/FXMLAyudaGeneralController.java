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

public class FXMLAyudaGeneralController implements Initializable{


    @FXML
    private Button atras;



    private Text texto_trazado;

    private ImageView imagenTrazados;
  
    private ToggleButton puntoB;
    private ToggleButton arcoB;
    private ToggleButton textoB;
    private ToggleButton angulosB;
    private ToggleButton limpiarB;
    private ToggleButton colorB;
    private ToggleButton grosorB;
    private Text textoColYGros;
    private ImageView imagenColYGros;
    private ToggleButton calculadoraB;
    private ToggleButton zoomB;
    private ToggleButton iconosB;
    private Text textoHerramientas;
    private ImageView imagenHerramientas;
    @FXML
    private ImageView imagenHerramientas2;
    private Text textoTrazados2;
    private ToggleButton problemasB;
    private ToggleButton guardarSesB;
    private Text textoProblemas;
    private ImageView imagenProblemas;
    @FXML
    private ToggleButton data;
    @FXML
    private ToggleGroup TuPerfil;
    @FXML
    private ToggleButton modify;
    @FXML
    private Text texto_perfil;
    @FXML
    private ImageView imagenPerfil;
    @FXML
    private ToggleButton random;
    @FXML
    private ToggleGroup Obra;
    @FXML
    private ToggleButton plist;
    @FXML
    private ToggleButton cartanav;
    @FXML
    private Text textoObra;
    @FXML
    private ImageView imagenObra;
    @FXML
    private ToggleButton ayf;
    @FXML
    private ToggleGroup Acabar;
    @FXML
    private ToggleButton guardar;
    @FXML
    private ToggleButton combicompleta;
    @FXML
    private ToggleButton cerrapp;
    @FXML
    private Text textoAcabar;
    @FXML
    private ImageView imagenAcabar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        imagenPerfil.setFitHeight(254);
        imagenPerfil.setFitWidth(542);
    } 

    @FXML
    private void atras(ActionEvent event) {
        atras.getScene().getWindow().hide();
    }

    private void cerrarApp(ActionEvent event) {
        atras.getScene().getWindow().hide();
    }


    @FXML
    private void mano(MouseEvent event) {

    }
    

    @FXML
    private void tuPerfilClicked(ActionEvent event) {
            if(data.isSelected()) {
            texto_perfil.setText("Para ver los Datos de su Perfil puede acceder de 3 maneras diferentes: pulsando en la opción \"Perfil\" en Menu>Estudiante; usando el atajo de teclado que se indica Alt + P; la última opción es clicar sobre el botón con su Nickname que se encuentra debajo de su foto de perfil.");
            imagenPerfil.setImage(new Image("/icons/ipc4.png"));
            imagenPerfil.setFitWidth(542);
            imagenPerfil.setFitHeight(254);
        }
        if(modify.isSelected()) {
            texto_perfil.setText("Para modificar su Perfil debe ir a sus datos y pulsar el botón con el mismo nombre.");
            imagenPerfil.setImage(new Image("/icons/ipc7.png"));
            imagenPerfil.setFitWidth(542);
            imagenPerfil.setFitHeight(254);
        }
        
    }

    @FXML
    private void ObraClicked(ActionEvent event) {
        if(random.isSelected()) {
            textoObra.setText("Este botón le permite realizar un problema aleatorio de entre todos los que hay la lista de problemas. Al acabar puede elegir si realizar otro o terminar.");
            imagenObra.setImage(new Image("/icons/ipc6.png"));
            imagenObra.setFitWidth(542);
            imagenObra.setFitHeight(254);
        }
        if(plist.isSelected()) {
            textoObra.setText("Este botón le permite acceder a la lista completa de problemas y seleccionar uno para practicar.");
            imagenObra.setImage(new Image("/icons/ipc8,5.png"));
            imagenObra.setFitWidth(542);
            imagenObra.setFitHeight(254);
        }
        if(cartanav.isSelected()) {
            textoObra.setText("Este botón le permite acceder a la carta de navegación para moverse libremente po ella y realizar cualquier trazado.");
            imagenObra.setImage(new Image("/icons/ipc9.png"));
            imagenObra.setFitWidth(542);
            imagenObra.setFitHeight(254);
        }
    }

    @FXML
    private void AcabarClicked(ActionEvent event) {
        if(ayf.isSelected()) {
            textoAcabar.setText("Si desea ver sus aciertos y fallos, así como sus estadísticas, debe pulsar sobre la opción \"Aciertos y fallos\" en Menu>Resultados. También puede acceder más rápido con el atajo de teclado definido: \"Alt + A\".");
            imagenAcabar.setImage(new Image("/icons/ipc5.png"));
            imagenAcabar.setFitWidth(542);
            imagenAcabar.setFitHeight(254);
            
        } 
        
        if(guardar.isSelected()) {
            textoAcabar.setText("Para Guardar Sesión y que queden registrados su puntuación de la sesión debe cerrar la APP o Cerrar Sesión, el proceso de guardado es automático y no se requiere nada más que lo detallado previamente.");
            imagenAcabar.setImage(new Image("/icons/salvar.png"));
            imagenAcabar.setFitWidth(200);
            imagenAcabar.setFitHeight(200);
        }
        if(combicompleta.isSelected()) {
            textoAcabar.setText("Para Cerrar Sesión debe clicar en la opción \"Cerrar Sesión\" dentro de Menu>Estudiante. También puede utilizar el atajo de teclado: \"Alt + E\" ");
            imagenAcabar.setImage(new Image("/icons/ipc11.png"));
            imagenAcabar.setFitWidth(542);
            imagenAcabar.setFitHeight(254);
        }
        if(cerrapp.isSelected()) {
            textoAcabar.setText("Para cerrar la APP debe clicar en la opción \"Cerrar\" dentro de Menu>Aplicación. También puede usar el atajo de teclado: \"Alt + C\" o directamente clicar la \"X\" roja de la esquina superior derecha en la ventana.");
            imagenAcabar.setImage(new Image("/icons/ipc10.png"));
            imagenAcabar.setFitWidth(542);
            imagenAcabar.setFitHeight(254);
        }
        
    }

}
