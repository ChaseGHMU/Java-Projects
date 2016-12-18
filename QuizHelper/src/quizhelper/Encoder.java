/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizhelper;

import java.util.ArrayList;

/**
 *
 * @author Chase
 */
public class Encoder extends Question {
    
    public ArrayList<Question> list = new ArrayList();
    public int i = 0;
    
    public Encoder(String question, String answer){
        this.answer = answer;
        this.question = question;
    }
    
    public ArrayList<Question> genericReturn(ArrayList<Question> list){

     list.add(new Question(question,answer));

     return list;
    }
        
}
