/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizhelper;

import javafx.scene.control.Alert;

/**
 *
 * @author Chase
 */
public class Question implements QuestionInterface {
    public String question;
    public String answer;
    
    public Question(){
        question = getQuestion();
        answer = getAnswer();
    }
    
    public Question(String question, String answer){
        this.question = question;
        this.answer = answer;
    }
    
    @Override
    public String getQuestion() {
        return this.question;
    }
    
    @Override
    public String getAnswer() {
        return this.answer;
    }

    public static void about() {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("About Quiz Helper");
        info.setHeaderText("About this app");
        info.setContentText("Quiz helper was made by Chase Allen to help get through finals week."
                + " The app consists of a page to write questions and a page to quiz yourself over the "
                + "questions you made. The way it works is whenever you submit a question, it adds the "
                + "question and answer you wrote to an ArrayList. Whenever you save your file, it adds"
                + " the file as a JSON object so that it can be parsed easier for the quiz section. The "
                + "quiz section has a load button for you to take a saved JSON and answer questions. The "
                + "save and load functions are convenient in the fact that you can load an old file that you"
                + " have studied in the past and refresh on those questions instead of having to find old notes"
                + " and look over them all again.");
        
        info.showAndWait();}
        
}
