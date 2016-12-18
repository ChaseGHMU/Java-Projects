/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizhelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 * FXML Controller class
 * 
 * @author Chase
 */
public class AnswerScreenController extends SceneSwitcher implements Initializable {
    @FXML
    private AnchorPane root;
    
    @FXML
    private TextArea question;
    
    @FXML
    private TextArea answer;
    
    @FXML
    private Button hideButton;
    
    @FXML
    private Button answerButton;
    
    @FXML
    private Button nextQuestion;
    
    @FXML
    private Text numQuestions;
    
    @FXML
    private Text questionNumber;
    
    public int x = 0;
    private String questionError = "Error Loading this question. Check your file.";
    private String answerError = "Error Loading this answer. Check your file.";
    public ArrayList<Question> list = new ArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void switchScene(){
        useFXML ("QuestionScreen");
    }
    
    @FXML 
    private void handleOpen(ActionEvent event) throws FileNotFoundException, IOException, Exception {
        
        list.removeAll(list);
        x = 0;
        answer.setText("");
        question.setText("");
        
        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage)root.getScene().getWindow();
        File directory = new File(System.getProperty("user.home"),"/Documents");
        
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files","*.json"));
        fileChooser.setInitialDirectory(directory);
        
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            
            BufferedReader bufferedReader = null;
            String document = "";
                    
            try {
                bufferedReader= new BufferedReader(new FileReader(file));
                
                String line = "";
                
                while ( (line = bufferedReader.readLine()) != null) {
                    document += line;
                }
                
            } catch (FileNotFoundException fnfex) {
                throw fnfex;
            } catch (IOException ioex) {
                throw ioex;
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Exception ex) {
                        throw ex;
                    }
                }
            }
            try{
                parseFile(document);
            }catch(Exception ex){
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText("Empty File");
                alert.setContentText("The JSON you are trying to load is either empty or not suitable for this program."
                                    + "Please try a different file.");
                alert.showAndWait();
                throw ex;
            }
        }
        
        if(list.isEmpty()){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("Empty File");
            alert.setContentText("The JSON you are trying to load is either empty or not suitable for this program."
                                + "Please try a different file.");
            numQuestions.setText("Number of Questions: ");
            alert.showAndWait();            
        }
    }
    
    private void parseFile(String fileString) throws Exception {
        if(fileString == null || fileString.equals("")) 
            return;
        
        JSONObject obj;
        
        try{
            obj = (JSONObject)JSONValue.parse(fileString);
        } catch(Exception ex) {
            throw ex;
        }
        
        if(obj == null)
            return;
        
        JSONArray array;
        
        try{
            array = (JSONArray)obj.get("questions");
        } catch(Exception ex){          
            throw ex;
        }
        
        for(Object doc: array){
            try{
                JSONObject questionAndAnswers = (JSONObject)doc;
                String question = (String)questionAndAnswers.getOrDefault("Question:", questionError);
                String answer = (String)questionAndAnswers.getOrDefault("Answer:",answerError);

                System.out.println("Question: " + question);
                System.out.println("Answer: " + answer);
                System.out.println("--------------------------------");

                Encoder e= new Encoder(question,answer);

                list = e.genericReturn(list);
            } catch(Exception ex){             
                throw ex;
            }
        }
       for(int x = 0; x < list.size();x++){
           System.out.println("Question: " + list.get(x).question);
           System.out.println("Answer: " + list.get(x).answer);
           System.out.println("-----------------");
       }
       
       Alert alert = new Alert(AlertType.INFORMATION);
       alert.setHeaderText("Your file has been loaded");
       alert.setContentText("Your file has succesfully been loaded. Click Start quiz to begin studying");
       alert.showAndWait();
       numQuestions.setText("Number of Questions: " + list.size());
    }
    
    @FXML
    public void startQuiz(){
        if(list.isEmpty()){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setHeaderText("ERROR");
            alert.setContentText("You must first load a .json file to start quiz");
            alert.showAndWait();            
        }else{
            answer.setEditable(false);
            question.setEditable(false);
            
            question.setText(list.get(x).question);
            questionNumber.setText("Question Number: " + (x+1));
            
            if(list.get(x).answer.equals(answerError)){
                answer.setText(list.get(x).answer);
            }

            answerButton.setOnAction((ActionEvent event) -> {
                if(!(list.isEmpty()))
                    answer.setText(list.get(x).answer);
            });
            
            hideButton.setOnAction((ActionEvent event) -> {
                if(!list.isEmpty())
                    answer.setText("");
            });

            nextQuestion.setOnAction((ActionEvent event) -> {
                if(!(list.isEmpty())){
                    if(x < list.size()-1){
                        x++;
                        answer.setText("");
                        startQuiz();
                    }else{
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setHeaderText("You've ended file");
                        alert.setContentText("Quiz is restarting. Load a new file if you want to study a different topic");
                        alert.showAndWait();
                        x = 0;
                        answer.setText("");
                        startQuiz();
                    }
                }
            });

            System.out.println("Question Number " + x);
        }
    }
    
    public void about(){
        Question.about();
    }

}