/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mynewstopwatch;

import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author Chase
 */
public class AnalogStopWatch {
    
    private double tickTimeInSeconds = 0.1; //Change this to change resolution
    private double angleDeltaPerSeconds = 6.0;
    private double secondsElapsed = 0; 
    
    private Timeline timeLine;
    private KeyFrame keyFrame;
    
    
    private StackPane rootContainer;
    private ImageView dialImageView;
    private ImageView handImageView;
    private Image dialImage;
    private Image handImage;
    private String dialImageName = "clockface.png";
    private String handImageName = "hand.png";
   
    public AnalogStopWatch(){
        setupUI();
        setupTimer();
    }
        
    public void setupTimer() {
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
    }
    
    private void setupUI() {
        rootContainer = new StackPane();
        dialImageView = new ImageView();
        handImageView = new ImageView();
        dialImage = new Image(getClass().getResourceAsStream(dialImageName));
        handImage = new Image(getClass().getResourceAsStream(handImageName));
        dialImageView.setImage(dialImage);
        handImageView.setImage(handImage);
        rootContainer.getChildren().addAll(dialImageView,handImageView);
    }
    
    public Parent getRootContainer(){
        return rootContainer;
    }
    
    public double getWidth() {
        
        if(dialImage != null)
            return dialImage.getWidth();
        else
            return 0.0;
    }
    
    public double getHeight() {
        
        if(dialImage != null)
            return dialImage.getHeight();
        else
            return 0.0;
        
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
        handImageView.setRotate(0);
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
