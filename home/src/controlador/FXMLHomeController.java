package controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.event.ActionListener;
import modelo.producto;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.*;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

/**
 *
 * @author ASUS
 */
public class FXMLHomeController implements Initializable {
    
    
    @FXML
    private Button homelogout;

    
     @FXML
    private Button homecarrito;

    @FXML
    private MenuButton homecategoria;

    @FXML
    private TableView<producto> htabla;

    @FXML
    private TableColumn<producto, String> nombre;

    @FXML
    private TableColumn<producto, String> categoria;

    @FXML
    private TableColumn<producto, String> boton1;

    @FXML
    private TableColumn<producto, String> boton2;
    
    
    //prueba creando productos 
    ArrayList<producto> jordan = new ArrayList<producto>();
    producto hola = new producto("helado","rico");
    producto hola2 = new producto("pan","no rico");
    producto hola3 = new producto("carne","rico");
    producto hola4 = new producto("pollo","no rico");
    producto hola5 = new producto("tacos","muy rico");
    
  
 
       
     
     
    // evento de botones ya creados en la vista 
    @FXML
    private void eventAction(ActionEvent event){
        Object evt = event.getSource();
        
        if (evt.equals(homelogout)){
            loadStage("/vista/FXMLlogout.fxml", event);
       
        
    
        if (evt.equals(homecarrito)){
            loadStage("/vista/FXMLcarrito.fxml", event);
        
        
        for (int i = 0; i < jordan.size(); i++) {
            if(evt.equals(jordan.get(i).boton2)){
                loadStage("/vista/FXMLproducto.fxml", event);
            }
        }
        
    
        }
        }
    }
    
    //funcion para caragar una nueva ventana
    private void loadStage(String url, Event event){
            
        try {
    
           //((Node)(event.getSource())).getScene().getWindow().hide();    
            
            
            Object eventSource = event.getSource(); 
            Node sourceAsNode = (Node) eventSource ;
            Scene oldScene = sourceAsNode.getScene();
            Window window = oldScene.getWindow();
            Stage stage = (Stage) window ;
            stage.hide();
            
           
                        
            Parent root = FXMLLoader.load(getClass().getResource(url));
            Scene scene = new Scene(root);              
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.show();  
                                    
            newStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Platform.exit();
                }
            });  

        } catch (IOException ex) {
            Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }  
    
    //evento de el boton ver prooducto
     class ButtonHandler implements EventHandler<ActionEvent>{

        public void handle(ActionEvent event) {
           
            try {
    
           //((Node)(event.getSource())).getScene().getWindow().hide();    
            
                
            Object eventSource = event.getSource(); 
            Node sourceAsNode = (Node) eventSource ;
            Scene oldScene = sourceAsNode.getScene();
            Window window = oldScene.getWindow();
            Stage stage = (Stage) window ;
            stage.hide();
            
           
                        
            Parent root = FXMLLoader.load(getClass().getResource("/vista/FXMLproducto.fxml"));
            Scene scene = new Scene(root);              
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.show(); 
            
            
                for (int i = 0; i < jordan.size(); i++) {
                    if (jordan.get(i).boton2== event.getSource()){
                        System.out.println(i+1);
                    }
                }
            
                                    
            newStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Platform.exit();
                }
            });  

        } catch (IOException ex) {
            Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //agregando productos
        jordan.add(hola);
        jordan.add(hola2);
        jordan.add(hola3);
        jordan.add(hola4);
        jordan.add(hola5);
        
        
        //adicionandole los eventos a el boton ver producto de cada producto
        for (int i = 0; i < jordan.size(); i++) {
            jordan.get(i).boton2.setOnAction(new ButtonHandler());
            
        }
        
        //creacion de la tabla con los produtos
        ObservableList<producto> datos = FXCollections.observableArrayList(jordan);
        boton1.setCellValueFactory(new PropertyValueFactory<producto, String>("boton1"));
        boton2.setCellValueFactory(new PropertyValueFactory<producto, String>("boton2"));
        categoria.setCellValueFactory(new PropertyValueFactory<producto, String>("categoria"));
        nombre.setCellValueFactory(new PropertyValueFactory<producto, String>("nombre"));
        htabla.setItems(datos);
        
    }  
    
      
    
}