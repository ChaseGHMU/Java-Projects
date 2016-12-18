/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizhelper;

import java.util.HashMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author Chase
 */
public class SceneSwitcher {
    public static Scene scene;
    private Parent root;
    public static final HashMap<String, SceneSwitcher> controllers = new HashMap<>();
    
    public void root(Parent root) {
        this.root = root;
    }
    
    public Parent getParentRoot(){
        return root;
    }
    
    public static SceneSwitcher addController(String name) {
        SceneSwitcher controller;
        
        controller = controllers.get(name);
        
        if (controller == null) {
            try {
                FXMLLoader loader = new FXMLLoader(SceneSwitcher.class.getResource(name + ".fxml"));
                Parent root = (Parent)loader.load();
                controller = (SceneSwitcher)loader.getController();
                controller.root(root);
                controllers.put(name, controller);
            } catch (Exception ex) {
                System.out.println("Error loading " + name + ".fxml\n" + ex);
                controller = null;
            }
        }
        
        return controller;
    }
    
    public static void useFXML(String name) {
        SceneSwitcher controller = controllers.get(name);
        
        if (controller == null) {
            controller = addController(name);
        }
        
        if (controller != null) {
            if (scene != null) {
                scene.setRoot(controller.getParentRoot());
            }
        }
    }
    
    public static SceneSwitcher controllerName(String name) {
        return controllers.get(name);
    }   
}
