/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cjan8cdocuments;

/**
 *
 * @author Chase
 */
public class Document {
    private String title;
    private String author;
    private String body;
    private int version;
    
    public Document(String title, String author){
        this.title = title;
        this.author = author;
        this.version = 0;
    }
    
    public Document(String title, String body, String author){
        this.title = title;
        this.body = body;
        this.author = author;
        this.version = 1;
    }
    
    public void setTitle(String title){
        this.title = title;
        this.version++;
    }
    
    public void setAuthor(String author){
        this.author = author;
    }
    
    public void setBody(String body){
        this.body = body;
        this.version++;
    }
    
    public String getTitle(){
        return title;
    }
    
    public String getAuthor(){
        return author;
    }
    
    public String getBody(){
        return body;
    }
    
    public int getVersion(){
        return version;
    }
}
