    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cjan8cstopwatch;

import java.util.Timer;
import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author Chase
 */
public class AnalogStopwatch {
    
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
    private GridPane root;
    private HBox buttonPane;
    private HBox digitalTimer;
    private Text digitalTimerMin;
    private Text digitalTimerSec;
    
    //initialize Images
    private ImageView dialImageView;
    private ImageView handImageView;
    private Image dialImage;
    private Image handImage;
    private String dialImageName = "clockface.png";
    private String handImageName = "hand.png";
    
    //initialize start, stop, reset button
    public Button startBtn;
    public Button stopBtn;
    public Button resetBtn;
   
    public AnalogStopwatch(){
        setupUI();
        setupTimer();
    }
        
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
    
    private void setupUI() {
        
        //create the UI used in the project
        root = new GridPane();
        rootContainer = new StackPane();
        buttonPane = new HBox();
        digitalTimer = new HBox();
        digitalTimerMin = new Text();
        digitalTimerSec = new Text();
        
        //create buttons used for project
        startBtn = new Button("Start");
        resetBtn = new Button("Reset");
        stopBtn = new Button("Stop");
        
        //create imageview and images. add image to imageview
        dialImageView = new ImageView();
        handImageView = new ImageView(); 
        dialImage = new Image(getClass().getResourceAsStream(dialImageName));
        handImage = new Image(getClass().getResourceAsStream(handImageName));
        dialImageView.setImage(dialImage);
        handImageView.setImage(handImage);
        
        //convert ints into strings to be used in text box  
        digitalTimerSec.setText(secString.toString());
        digitalTimerMin.setText(minString.toString());
        
        //buttons set to be even in size
        startBtn.setMaxWidth(Double.MAX_VALUE);
        stopBtn.setMaxWidth(Double.MAX_VALUE);
        resetBtn.setMaxWidth(Double.MAX_VALUE);

        //more button customization
        buttonPane.setSpacing(10.0);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.getChildren().addAll(startBtn, resetBtn, stopBtn);
        
        //add the strings to the digitalTimer Text box
        digitalTimer.getChildren().addAll(digitalTimerMin,digitalTimerSec);
        digitalTimer.setAlignment(Pos.CENTER);
        digitalTimer.setSpacing(3.0);
        
        //add everything to UI, put images in stackpane and everything in gridpane
        rootContainer.getChildren().addAll(dialImageView,handImageView);
        root.add(digitalTimer,0,1);
        root.add(rootContainer, 0, 2);
        root.add(buttonPane, 0, 3);
        
        root.setVgap(4.0);
        root.setAlignment(Pos.CENTER);

    }
    
    public Parent getRootContainer(){
        return root;
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
    
