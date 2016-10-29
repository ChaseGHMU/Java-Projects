/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysecondfxapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Chase
 */
public class MySecondFXApp extends Application {
    public String appName = "My Second App";
    public int width = 600;
    public int height = 400;
    
    
    @Override
    public void start(Stage primaryStage) {
        
        GridPane root = new GridPane();
        
        Button button = new Button("Hello World!");
        
        Label infoLabel = new Label();
        
        button.setOnAction((ActionEvent event) -> {
            infoLabel.setText("Hello World!");
        });;
        
        root.add(infoLabel, 0, 0);
        root.add(button, 0, 1);
        
        Scene scene = new Scene(root, width, height);
        
        primaryStage.setTitle(appName);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
