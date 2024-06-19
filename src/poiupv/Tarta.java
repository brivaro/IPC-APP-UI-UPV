/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv;

import DBAccess.NavegacionDAOException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafxmlapplication.FXMLMapaController;
import model.Navegacion;
import model.User;

/**
 *
 * @author Brian
 */
public class Tarta implements Initializable{

    @FXML
    private PieChart tarta;
    private User user;
    private Navegacion nav;
    private static int aciertos;
    private static int fallos;
    ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
    
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
            
            //tarta.getData().stream().forEach(this::info);
            
        } catch (IOException ex) {
            Logger.getLogger(Tarta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NavegacionDAOException ex) {
            Logger.getLogger(Tarta.class.getName()).log(Level.SEVERE, null, ex);
        }
}

    public void initUser(String u) {
        user = nav.getUser(u);
        aciertos = 0;
        fallos = 0;
            for(int i = 0; i < user.getSessions().size(); i++){
                aciertos+= user.getSessions().get(i).getHits();
            }
            for(int i = 0; i < user.getSessions().size(); i++){
                fallos+= user.getSessions().get(i).getFaults();
            }
            
            data.add(new PieChart.Data("Aciertos: " + aciertos, aciertos));
            data.add(new PieChart.Data("Fallos: " + fallos, fallos));
            tarta.setTitle(tarta.getTitle()+ " " + user.getNickName());
            tarta.setData(data);
    }
    
    
}
