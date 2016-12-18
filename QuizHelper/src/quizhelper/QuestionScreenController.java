/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizhelper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.simple.JSONObject;

/**
 * FXML Controller class
 *
 * @author Chase
 */
public class QuestionScreenController extends SceneSwitcher implements Initializable {
    
    @FXML
    public TextArea answer;
    
    @FXML
    public TextArea question;
    
    @FXML
    private AnchorPane root;
    
    private Encoder add;
    
    private ArrayList<Question> list = new ArrayList();
    
    private boolean save = false;
    
    @FXML
    private void addtoList(){
        String answerText = answer.getText();
        String questionText = question.getText();
        
        if(!(answerText.isEmpty()) && !(questionText.isEmpty())){
            add = new Encoder(questionText,answerText);

            list = add.genericReturn(list);
            question.setText("");
            answer.setText("");
        }else{
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Error");
           alert.setHeaderText("You can't do that.");
           alert.setContentText("Must have text in both question and answer to submit.");
           
           alert.showAndWait();
        }
        
        for(int x = 0; x<list.size();x++){
            System.out.printf("%d: Question: %s Answer: %s\n",x+1,list.get(x).question,list.get(x).answer);                
        }
        System.out.println("----------------------------");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void handleSave(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) root.getScene().getWindow();
        File directory = new File(System.getProperty("user.home"),"/Documents");
        
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".json","*.json"));
        fileChooser.setInitialDirectory(directory);
        
        File file = fileChooser.showSaveDialog(stage);
        
        FileWriter writer = null;
        
        if (file != null) {
            try {
                writer = new FileWriter(file);
                writer.write("{\"questions\":[");
                
                for(int x = 0; x < list.size(); x++){
                    JSONObject obj = new JSONObject();
                    obj.put("Question:", list.get(x).question);
                    obj.put("Answer:",list.get(x).answer); 
                    writer.write(obj.toString());
                    if(x != list.size()-1){
                        writer.write(",");
                    }
                }
                
                writer.write("]}");
                
                
            } catch (IOException ioex) {
                throw ioex;
            } catch (Exception ex) {
                throw ex;
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (Exception ex) {
                        throw ex;
                    }
                }
            }
            save = true;
        }

    }
    //Used for testing
    public void convertToJSON(){
        int x;
        for(x = 0; x < list.size(); x++){
            JSONObject obj = new JSONObject();
            obj.put("Question:", list.get(x).question);
            obj.put("Answer:",list.get(x).answer); 
            System.out.println(obj);
        }
    }
    
    public void switchScene(){
        if(save == false){
            Alert info = new Alert(Alert.AlertType.CONFIRMATION);
            info.setTitle("Haven't saved");
            info.setHeaderText("Save your File?");
            info.setContentText("File hasn't been saved. If you have questions submitted, please save"
                    + " before switching to quiz me. Click OK to go to Quiz Me or cancel if you want to save.");
            Optional<ButtonType> result=info.showAndWait();
        
            if(result.get() == ButtonType.OK){
                save = false;
                SceneSwitcher.useFXML("AnswerScreen");
            }
        }else{
            SceneSwitcher.useFXML("AnswerScreen");
        }
    }
   
    @FXML
    public void about() {
        Question.about();
    }
}
