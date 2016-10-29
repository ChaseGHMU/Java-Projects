/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cjan8cnotifier;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Chase
 */
public class Cjan8cNotifier extends Application {
    
    @Override
    public void start(Stage primaryStage) {

        GridPane root = new GridPane();
        Button notify = new Button();
        Button clear = new Button();
        TextField text = new TextField();
        int gap = 10;
        int appWidth = 400;
        int appHeight = 250;
        
        notify.setText("Notify");
        clear.setText("Clear");
        
        root.setVgap(gap);
        root.setHgap(gap);
        root.setAlignment(Pos.CENTER);
        
        notify.setOnAction((ActionEvent event) -> {
            text.setText("You have been notified!");
        });
        
        clear.setOnAction((ActionEvent event) -> {
            text.setText("");
        });
        
        root.add(text, 0, 0);
        root.add(notify, 1, 0);
        root.add(clear, 1, 1);
        
        
        Scene scene = new Scene(root, appWidth, appHeight);
        primaryStage.setTitle("Notifier");
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
