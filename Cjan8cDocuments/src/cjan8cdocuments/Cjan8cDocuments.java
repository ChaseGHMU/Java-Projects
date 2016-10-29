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
public class Cjan8cDocuments {

    public static void main(String[] args) {
            
        Document document1 = new Document("Another Life", "Sally Smith");
        document1.setBody("The grass is always greener on the other side.");
        
        Document document2 = new Document("Final Word", "We should plan for the worst and hope for the best.", "Karen Jones");
        document2.setTitle("Final Words");
     
        System.out.println("document1:");
        System.out.printf("title:%s\n", document1.getTitle());
        System.out.printf("author:%s\n", document1.getAuthor());
        System.out.printf("body:%s\n", document1.getBody());
        System.out.printf("version:%d\n\n", document1.getVersion());
        
        System.out.println("document2:"); 
        System.out.printf("title:%s\n", document2.getTitle());
        System.out.printf("author:%s\n", document2.getAuthor());
        System.out.printf("body:%s\n", document2.getBody());
        System.out.printf("version:%d\n", document2.getVersion());
    }
    
}
