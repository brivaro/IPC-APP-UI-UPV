/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author jsoler
 */
public class FXMLDocumentController implements Initializable {
    private Label labelMessage;
    @FXML
    private Button siete;
    @FXML
    private Button ocho;
    @FXML
    private Button nueve;
    @FXML
    private Button cuatro;
    @FXML
    private Button cinco;
    @FXML
    private Button seis;
    @FXML
    private Button uno;
    @FXML
    private Button dos;
    @FXML
    private Button tres;
    @FXML
    private TextField resultado;
    @FXML
    private Button cero;
    
    private int operation;
    private double primerNum;
    private double seconNum;

    private enum operations{SUM, REST, MULT, DIV} //enumeracion privada en Java
    
    //=========================================================
    // you must initialize here all related with the object 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void delete(ActionEvent event) {
        resultado.setText("");
    }

    @FXML
    private void decimales(ActionEvent event) {
        boolean soloUno = true;
        if(soloUno){
            resultado.setText(resultado.getText() + "."); soloUno = false;
        }
    }

    @FXML
    private void getNumber(ActionEvent event) {
        if(resultado.getText().equals("0")){
            resultado.setText(((Button)event.getSource()).getText()); 
//el getSource() es para obtener los id de las cosas y luego un casting de objeto para conseguir el texto
        }
        else{
            resultado.setText(resultado.getText() + ((Button)event.getSource()).getText());
        }
    }

    @FXML
    private void getResult(ActionEvent event) {
        seconNum = new Double(resultado.getText());
        switch(operation){
            case 0:
                double suma = primerNum + seconNum;
                String cadena = String.valueOf(suma);
                resultado.setText(cadena); break;
            case 1:
                double resta = primerNum - seconNum;
                String cadena1 = String.valueOf(resta);
                resultado.setText(cadena1); break;
            case 2:
                double multiplicacion = primerNum * seconNum;
                String cadena2 = String.valueOf(multiplicacion);
                resultado.setText(cadena2); break;
            case 3:
                double division = primerNum / seconNum;
                String cadena3 = String.valueOf(division);
                resultado.setText(cadena3); break;

        }
    }

    @FXML
    private void sum(ActionEvent event) {
        operation = operations.SUM.ordinal();
        primerNum = new Double(resultado.getText());
        resultado.setText("");
        
    }
    
    @FXML
    private void rest(ActionEvent event) {
        operation = operations.REST.ordinal();
        primerNum = new Double(resultado.getText());
        resultado.setText("");
    }

    @FXML
    private void mult(ActionEvent event) {
        operation = operations.MULT.ordinal();
        primerNum = new Double(resultado.getText());
        resultado.setText("");
    }

    @FXML
    private void div(ActionEvent event) {
        operation = operations.DIV.ordinal();
        primerNum = new Double(resultado.getText());
        resultado.setText("");
    }

}
