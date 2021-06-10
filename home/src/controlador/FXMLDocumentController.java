package controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import home.producto;
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
import javafx.event.EventHandler;
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
public class FXMLDocumentController implements Initializable {
    
    
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
    
    ArrayList<producto> jordan = new ArrayList<producto>();
   
    producto hola = new producto("helado","rico");
    
    

    public FXMLDocumentController() {
    }
    
    
     ObservableList<producto> datos = FXCollections.observableArrayList(
                new producto("pan", "melo"),
                new producto("cepillo", "cabello"),
                hola
             
        );
     
    
    @FXML
    private void eventAction(ActionEvent event){
        Object evt = event.getSource();
        
        if (evt.equals(homelogout)){
            loadStage("/vista/FXMLlogout.fxml", event);
        }else{
            System.out.println("hola"); 
        }
        
        
        if (evt.equals(homecarrito)){
            loadStage("/vista/FXMLcarrito.fxml", event);
        }else{
            System.out.println("hola2");
        }
        
    }
    
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
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        boton1.setCellValueFactory(new PropertyValueFactory<producto, String>("boton1"));
        boton2.setCellValueFactory(new PropertyValueFactory<producto, String>("boton2"));
        categoria.setCellValueFactory(new PropertyValueFactory<producto, String>("categoria"));
        nombre.setCellValueFactory(new PropertyValueFactory<producto, String>("nombre"));
        
        htabla.setItems(datos);
        
        
        
        
        
    }  
    
      
    
}