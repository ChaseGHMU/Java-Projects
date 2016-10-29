/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cjan8clanguagebasics;

import java.util.ArrayList;

/**
 *
 * @author Chase
 */
public class Cjan8cLanguageBasics {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        byte sample1 = 0x3A;
        byte sample2 = 58;
        short heartRate = 85;
        long deposits = 135002796;
        float acceleration = 9.584f;
        float mass = 14.6f;
        double distance = 129.763001;
        boolean lost = true;
        boolean expensive = true;
        int choice = 1;
        char integral = '\u222B';
        char letter1 = 'a';
        char letter2 = 97;
        String greeting = "Hello";
        String name = "Karen";
        
        if(sample1 == sample2){
           System.out.println("The samples are equal.");
        }
        else{
            System.out.println("The sample are not equal.");           
        }
        
        if(heartRate > 40 && heartRate <= 80){
            System.out.println("Heart rate is normal.");
        }
        else{
            System.out.println("Heart rate is not normal.");
        }
        
        if(deposits >= 100000000){
            System.out.println("You are exceedingly wealthy.");
        }
        else{
            System.out.println("Sorry you are so poor.");
        }
        
        float force = mass * acceleration;
        
        System.out.printf("force = %.3f\n",force);
        
        System.out.printf("%f is the distance.\n",distance);
        
        if(lost == true && expensive == true)
        {
            System.out.println("I am really sorry! I will get the manager.");
        }else if(lost == true && expensive == false)
        {
            System.out.println("Here is a coupon for 10%% off.");
        }
        
        switch(choice){
            case 1: System.out.println("You chose 1.");
                    break;
            
            case 2: System.out.println("You chose 2.");
                    break;
                    
            case 3: System.out.println("You chose 3.");
                    break;
                    
            default: System.out.println("You made an unknown choice.");
                     break;
        }
        
        System.out.printf("%c is an integral.\n", integral);
        
        if(letter1 == letter2){
            System.out.printf("%c and %c are the same.\n", letter1, letter2);
        }else{
            System.out.printf("%c and %c are not the same.\n", letter1, letter2);
        }
        
        int i;
        
        for(i = 5; i <=10; i++){
            System.out.printf("i = %d\n",i);
        }
        
        int age = 0;
        
        while(age < 6){
            System.out.printf("age = %d\n", age);
            age++;
        }
        
        Integer[] test = {0,4,6};
        
        System.out.println(test.length);

    Integer a = 360;
    int b = 360;
    Integer c = 360;
    
    if(a == c){
    System.out.println("A == C");
    }
    
    if(a == b){
        System.out.println("A == B");
    }
    
    if(b == c){
        System.out.println("B == C");
    }
    
    if(a.equals(c)){
        System.out.println("a.equals(c)");
    }
     
    if(a.equals(b)){
        System.out.println("a.equals(b)");
    }
    
    }
    
}
