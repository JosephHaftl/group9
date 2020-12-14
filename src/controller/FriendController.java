
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
import javafx.scene.control.Alert;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private ImageView image;
    
    
    @FXML
    void addFriend(ActionEvent event) {
        Allusers model = userTable.getSelectionModel().getSelectedItem();
        int id = model.getId();
        String name = model.getName();
        String notes = model.getNotes();
        model.setStatus(true);

        
        // create a student instance
       Friendmodel friend = new Friendmodel();
        
        // set properties
       friend.setId(id);
       friend.setName(name);
       friend.setNotes(notes);
        
        // save this student to databse by calling Create operation        
        create(friend); //need to make the create method before this can work
        List<Friendmodel> friends = readAllFriends();
        setFriendTableData(friends);
        List<Allusers> users = readAllUsers();
        setUserTableData(users);
        
    }



    @FXML
    void deleteFriend(ActionEvent event) {
        
        Friendmodel model = friendTable.getSelectionModel().getSelectedItem();
        int id = model.getId();
        
        friendTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ObservableList<Friendmodel> selectedRows = friendTable.getSelectionModel().getSelectedItems();
        ArrayList<Friendmodel> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> friendTable.getItems().remove(row));
       
        
        System.out.println(id);
    
        delete(readByFriendId(id));
        List<Friendmodel> friends = readAllFriends();
        setFriendTableData(friends);
        List<Allusers> users = readAllUsers();
        setUserTableData(users);
        Allusers user = readByUserId(id);
        user.setStatus(false);
    }

    @FXML
    void searchFriends(ActionEvent event) {
        // getting the name from input box        
        String name = fSearchText.getText();

        // calling a db read operaiton, readByName
        List<Friendmodel> friends = readByNameAdvancedF(name);

        // setting table data
        //setTableData(students);
        // add an alert
        if (friends == null || friends.isEmpty()) {

            // show an alert to inform user 
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog Box");// line 2
            alert.setHeaderText("This is header section to write heading");// line 3
            alert.setContentText("No user");// line 4
            alert.showAndWait(); // line 5
        } else {
            // setting table data
            setFriendTableData(friends);
        }
    }

    @FXML
    void searchUsers(ActionEvent event) {
        

        // getting the name from input box        
        String name = uSearchText.getText();

        // calling a db read operaiton, readByName
        List<Allusers> users = readByNameAdvancedU(name);

        // setting table data
        //setTableData(students);
        // add an alert
        if (users == null || users.isEmpty()) {

            // show an alert to inform user 
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog Box");// line 2
            alert.setHeaderText("This is header section to write heading");// line 3
            alert.setContentText("No user");// line 4
            alert.showAndWait(); // line 5
        } else {
            // setting table data
            setUserTableData(users);
        }
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
    
//    public void initData(Messagemodel model) {
//
//        try {
//
//            String imagename = "/resource/images/" + model.getName() + ".png";
//            Image profile = new Image(getClass().getResourceAsStream(imagename));
//            image.setImage(profile);
//
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
    
    @FXML
    void actionShowHome(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomePage.fxml"));    

        Parent MessagePage = loader.load();

        Scene messageViewScene = new Scene(MessagePage);

       // MessageController detailedControlled = loader.getController();
        
        Scene currentScene = ((Node) event.getSource()).getScene();
        //detailedControlled.setPreviousScene(currentScene);
        
        Stage stage = (Stage) currentScene.getWindow();

        stage.setScene(messageViewScene);
        stage.show();
    }

    @FXML
    void actionShowMessages(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MessagePage.fxml"));

        Parent MessagePage = loader.load();

        Scene messageViewScene = new Scene(MessagePage);

       // MessageController detailedControlled = loader.getController();
        
        Scene currentScene = ((Node) event.getSource()).getScene();
        //detailedControlled.setPreviousScene(currentScene);
        
        Stage stage = (Stage) currentScene.getWindow();

        stage.setScene(messageViewScene);
        stage.show(); 
    }

    @FXML
    void actionShowProfile(ActionEvent event) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ProfilePage.fxml"));

        Parent MessagePage = loader.load();

        Scene messageViewScene = new Scene(MessagePage);

       // MessageController detailedControlled = loader.getController();
        
        Scene currentScene = ((Node) event.getSource()).getScene();
        //detailedControlled.setPreviousScene(currentScene);
        
        Stage stage = (Stage) currentScene.getWindow();

        stage.setScene(messageViewScene);
        stage.show(); 
    }
    
        @FXML
    void showFriendProfile(ActionEvent event) throws IOException{
        Friendmodel selectedFriend = friendTable.getSelectionModel().getSelectedItem();
        
        // fxml loader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FriendsProfilePage.fxml"));

        // load the ui elements
        Parent DetailedModelView = loader.load();

        // load the scene
        Scene tableViewScene = new Scene(DetailedModelView);

        //access the detailedControlled and call a method
        FriendProfileController detailedControlled = loader.getController();


        detailedControlled.initData(selectedFriend);

        // create a new state
        Stage stage = new Stage();
        stage.setScene(tableViewScene);
        stage.show();
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
        
    public List<Allusers> readByNameAdvancedU(String name) {
        Query query = manager.createNamedQuery("Allusers.findByNameAdvanced");

        // setting query parameter
        query.setParameter("name", name);

        // execute query
        List<Allusers> users = query.getResultList();
        for (Allusers user : users) {
          
        }

        return users;
    }    
    public List<Friendmodel> readByNameAdvancedF(String name) {
        Query query = manager.createNamedQuery("Friendmodel.findByNameAdvanced");

        // setting query parameter
        query.setParameter("name", name);

        // execute query
        List<Friendmodel> friends = query.getResultList();
        for (Friendmodel friend : friends) {
          
        }

        return friends;
    } 
    
    public Friendmodel readByFriendId(int id){
        Query query = manager.createNamedQuery("Friendmodel.findById");
        
        // setting query parameter
        query.setParameter("id", id);
        
        // execute query
        Friendmodel friend = (Friendmodel) query.getSingleResult();
        if (friend != null) {
        
        }
        
        return friend;
    } 
    public Allusers readByUserId(int id){
        Query query = manager.createNamedQuery("Allusers.findById");
        
        // setting query parameter
        query.setParameter("id", id);
        
        // execute query
        Allusers user = (Allusers) query.getSingleResult();
        if (user != null) {
        
        }
        
        return user;
    }
    
    public void delete(Friendmodel post) {
        try {
            Friendmodel existingPost = manager.find(Friendmodel.class, post.getId());

            // sanity check
            if (existingPost != null) {
                
                // begin transaction
                manager.getTransaction().begin();
                
                //remove student
                manager.remove(existingPost);
                
                // end transaction
                manager.getTransaction().commit();
            }
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
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
    public void create(Friendmodel friend) {
        try {
            // begin transaction
            manager.getTransaction().begin();
            
            // sanity check
            if (friend.getId() != null) {
                
                // create student
                manager.persist(friend);
                
                // end transaction
                manager.getTransaction().commit();
                
                
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}

