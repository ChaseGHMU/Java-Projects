/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cjan8cstopwatch;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Chase
 */
public class Cjan8cStopwatch extends Application {
    
    private String appName = "Stopwatch";
    
    @Override
    public void start(Stage primaryStage) {
        
        AnalogStopwatch analogStopWatch = new AnalogStopwatch();
        
        analogStopWatch.resetBtn.setOnAction((ActionEvent event) -> {
            analogStopWatch.reset();
        });
        
        analogStopWatch.startBtn.setOnAction((ActionEvent event) -> {
            analogStopWatch.start();
        });
        
        analogStopWatch.stopBtn.setOnAction((ActionEvent event) -> {
            analogStopWatch.stop();
        });
        
        Scene scene = new Scene(analogStopWatch.getRootContainer(),500, 400);
        
        
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
