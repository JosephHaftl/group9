/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author ianchong16
 */


import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import model.Createpostmodel;

public class HomePageController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private TableView<Createpostmodel> postTable;

    @FXML
    private TableColumn<Createpostmodel, Integer> ID;
    
    @FXML
    private TableColumn<Createpostmodel, Date> Date;
    
    @FXML
    private TableColumn<Createpostmodel, String> Post;

    @FXML
    void createPost(ActionEvent event) {
        
    }

    @FXML
    void deletePost(ActionEvent event) {

    }

    @FXML
    void updatePost(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }
    
    //Database Manager
    EntityManager manager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Code from Demo
        manager = (EntityManager) 
                
        Persistence.createEntityManagerFactory("group9PU").createEntityManager();
        
        //Inspiration taken from Quiz 4 Demo Code
        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        Post.setCellValueFactory(new PropertyValueFactory<>("Post"));
        
        postTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
}




