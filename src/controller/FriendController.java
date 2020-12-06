
package controller;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import model.Createpostmodel;
import model.Friendmodel;
import model.Messagemodel;
import model.Allusers;
//testing pushing to branch



/**
 *
 * @author Joseph Haftl
 */


public class FriendController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private TableView<Allusers> userTable;

    @FXML
    private TableColumn<Allusers, Integer> uID;

    @FXML
    private TableColumn<Allusers, String> uName;

    @FXML
    private TableColumn<Allusers, Boolean> uStatus;

    @FXML
    private TableColumn<Allusers, String> uNotes;

    @FXML
    private TableView<Friendmodel> friendTable;

    @FXML
    private TableColumn<Friendmodel, Integer> fID;

    @FXML
    private TableColumn<Friendmodel, String> fName;

    @FXML
    private TableColumn<Friendmodel, String> fNotes;

    @FXML
    private TextField uSearchText;

    @FXML
    private TextField fSearchText;

    private ObservableList<Allusers> userData;
    private ObservableList<Friendmodel> friendData;
    
    @FXML
    void addFriend(ActionEvent event) {

    }

    @FXML
    void back(ActionEvent event) {

    }

    @FXML
    void deleteFriend(ActionEvent event) {

    }

    @FXML
    void searchFriends(ActionEvent event) {

    }

    @FXML
    void searchUsers(ActionEvent event) {

    }

    @FXML
    void viewFriends(ActionEvent event) {
       List<Friendmodel> Friends = readAllFriends();
        setFriendTableData(Friends);
    }

    @FXML
    void viewUsers(ActionEvent event) {
       List<Allusers> Users = readAllUsers();
        setUserTableData(Users);
        
    }

    
    
    public void setUserTableData(List<Allusers> userList){
        //Inspiration taken from Quiz 4 Demo Code
        userData = FXCollections.observableArrayList();
        
        userList.forEach(a -> {
            userData.add(a);
        });
        
        userTable.setItems(userData);
        userTable.refresh();
       
    }  
        public void setFriendTableData(List<Friendmodel> friendList){
        //Inspiration taken from Quiz 4 Demo Code
        friendData = FXCollections.observableArrayList();
        
        friendList.forEach(f -> {
            friendData.add(f);
        });
        
        friendTable.setItems(friendData);
        friendTable.refresh();
       
    }
    
    public List<Allusers> readAllUsers(){
        Query query = manager.createNamedQuery("Allusers.findAll");
        List<Allusers> users = query.getResultList();

       
        return users;
    }
        public List<Friendmodel> readAllFriends(){
        Query query = manager.createNamedQuery("Friendmodel.findAll");
        List<Friendmodel> friends = query.getResultList();

       
        return friends;
    }
    EntityManager manager;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        manager = (EntityManager) 
                
        Persistence.createEntityManagerFactory("group9PU").createEntityManager();
        
       //Inspiration taken from Quiz 4 Demo Code
        uID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        uName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        uStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        uNotes.setCellValueFactory(new PropertyValueFactory<>("Notes"));
           
        
        fID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        fName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        fNotes.setCellValueFactory(new PropertyValueFactory<>("Notes"));
        
        userTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);


    }
}
