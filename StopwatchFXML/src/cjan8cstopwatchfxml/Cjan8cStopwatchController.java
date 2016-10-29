/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cjan8cstopwatchfxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Chase
 */
public class Cjan8cStopwatchController implements Initializable {
   
   @FXML
   private AnchorPane root;
   @FXML
   private StackPane clock;
   @FXML
   private ImageView handImageView;
   @FXML
   private ImageView dialImageView;
   @FXML
   private HBox buttonBox;
   @FXML
   public Button startBtn;
   @FXML
   public Button resetBtn;
   @FXML
   public Button stopBtn;
   @FXML
   private HBox digitalTimer;
   @FXML
   private Text digitalTimerMin;
   @FXML
   private Text digitalTimerSec;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupTimer();
    }  
    
    
    //Set up the variables used throughout the program
    private double tickTimeInSeconds = 1.0; //Change this to change resolution
    private double angleDeltaPerSeconds = 6.0;
    private double secondsElapsed = 0;
    private Integer seconds = 0;
    private Integer minutes = 0;
    private String secString = String.format("%02d",seconds);
    private String minString = String.format("%02d",minutes);
    
    private Timeline timeLine;
    private KeyFrame keyFrame;

    //initializing UI
    private StackPane rootContainer;
    private HBox buttonPane;
    
    
    //initialize start, stop, reset button
 
    private void setupTimer() {
        boolean running = false;
        if(timeLine != null){
            if(timeLine.getStatus() == Status.RUNNING){
                running = true;
                timeLine.stop();
            }
        }
        
        keyFrame = new KeyFrame(Duration.millis(tickTimeInSeconds * 1000), (ActionEvent event) -> {
            update();
        });
        
        timeLine = new Timeline(keyFrame);
        timeLine.setCycleCount(Animation.INDEFINITE);
        
        if(running){
            timeLine.play();
        }
    }
    
    private void update() {
        secondsElapsed += tickTimeInSeconds;
        double rotation = secondsElapsed * angleDeltaPerSeconds;
        handImageView.setRotate(rotation); 
        
        /* every second update the seconds integer and convert the new time
           to string. if the second integer gets to 60, reset it to 0
           and update the minute int and string
        */
        seconds += 1;
        secString = String.format("%02d",seconds);
        minString = String.format("%02d", minutes);
        digitalTimerMin.setText(minString);
        digitalTimerSec.setText(secString);
        if(seconds >= 60){
            minutes += 1;
            seconds = 0;
            secString = String.format("%02d",seconds);
            minString = String.format("%02d", minutes);
            digitalTimerMin.setText(minString);
            digitalTimerSec.setText(secString);
        }
    }

    
    public void start(){
        timeLine.play();
    }
    
    public void stop(){
        timeLine.stop();
    }
    
    public void reset(){
        stop();
        secondsElapsed = 0;
        handImageView.setRotate(0.0);
        
        //reset ints and strings back to 0
        seconds = 0;
        minutes = 0;
        secString = String.format("%02d",seconds);
        minString = String.format("%02d", minutes); 
        digitalTimerMin.setText(minString);
        digitalTimerSec.setText(secString);
    }
    
    public void setTickTimeInSeconds(Double tickTimeInSeconds) {
        this.tickTimeInSeconds = tickTimeInSeconds;
        setupTimer();
    }
    
    public boolean isRunning(){
        if(timeLine != null){
            if(timeLine.getStatus() == Status.RUNNING)
                return true;
        }
        
        return false;        
    }    
}
