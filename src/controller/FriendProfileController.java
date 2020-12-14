/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Friendmodel;

/**
 *
 * @author nicole
 */
public class FriendProfileController {
    
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML // fx:id="profilePic"
    private ImageView profilePic; // Value injected by FXMLLoader

    @FXML // fx:id="name"
    private Label name; // Value injected by FXMLLoader

    Friendmodel selectedModel;
    Scene previousScene;

    public void initData(Friendmodel model) {
        selectedModel = model;
        name.setText(model.getName());

        try {
            // path points to /resource/images/
            String imagename = "/resource/images/" + model.getName() + ".png";
            Image profile = new Image(getClass().getResourceAsStream(imagename));
            profilePic.setImage(profile);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @FXML
    void initialize() {
        assert profilePic != null : "fx:id=\"profilePic\" was not injected: check your FXML file 'FriendsProfilePage.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'FriendsProfilePage.fxml'.";

    }
    
}
