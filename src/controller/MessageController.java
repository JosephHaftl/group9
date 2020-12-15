/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Allusers;
import model.Createpostmodel;
import model.Friendmodel;
import model.Messagemodel;

/**
 *
 * @author ianchong16
 */
public class MessageController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField textboxPost;

    @FXML
    private TableView<Messagemodel> messageTable;

    @FXML
    private TableColumn<Messagemodel, Integer> ID;

    @FXML
    private TableColumn<Messagemodel, String> Name;

    @FXML
    private TableColumn<Messagemodel, String> PmName;

    @FXML
    private TableColumn<Messagemodel, String> Pm;

    private ObservableList<Messagemodel> messageData;

    @FXML
    private ImageView image;

    public void setTableData(List<Messagemodel> messageList) {
        messageData = FXCollections.observableArrayList();

        messageList.forEach(s -> {
            messageData.add(s);
        });

        messageTable.setItems(messageData);
        messageTable.refresh();
    }

    @FXML
    void createMessage(ActionEvent event) {
        Scanner input = new Scanner(System.in);

        // read input from command line
        System.out.println("Enter ID: ");
        int id = input.nextInt();

        ////https://stackoverflow.com/questions/13102045/scanner-is-skipping-nextline-after-using-next-or-nextfoo
        input.nextLine();

        System.out.println("Enter Name: ");
        String name = input.nextLine();

        System.out.println("Enter the name of your Private Message: ");
        String pmname = input.nextLine();

        String message = textboxPost.getText();

        // create a pm instance
        Messagemodel yourMessage = new Messagemodel();

        // set properties
        yourMessage.setID(id);
        yourMessage.setName(name);
        yourMessage.setPmName(pmname);
        yourMessage.setPm(message);

        //save this student to the database by calling Create operation
        create(yourMessage);
        List<Messagemodel> messages = readAll();
        setTableData(messages);
    }

    @FXML
    void viewMessage(ActionEvent event) {
        List<Messagemodel> messages = readAll();
        setTableData(messages);
    }

    @FXML
    void deleteMessage(ActionEvent event) {
//        int selectedIndex = messageTable.getSelectionModel().getSelectedIndex();
//
//        if (selectedIndex >= 0) {
//            messageTable.getItems().remove(selectedIndex);
//            //delete(selectedIndex);
//        } else {
//            Alert alert = new Alert(AlertType.WARNING);
//            alert.setTitle("No Selection");
//            alert.setHeaderText("No Message Selected");
//            alert.setContentText("Please select a message in the table to delete.");
//
//            alert.showAndWait();
//        }
        Messagemodel model = messageTable.getSelectionModel().getSelectedItem();
        int id = model.getID();

        messageTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ObservableList<Messagemodel> selectedRows = messageTable.getSelectionModel().getSelectedItems();
        ArrayList<Messagemodel> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> messageTable.getItems().remove(row));

        System.out.println(id);

        delete(readByMessageId(id));
        List<Messagemodel> messages = readAll();
        setTableData(messages);

    }

    public void initData(Messagemodel model) {

        try {

            String imagename = "/resource/images/" + model.getName() + ".png";
            Image profile = new Image(getClass().getResourceAsStream(imagename));
            image.setImage(profile);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void actionShowHome(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomePage.fxml"));

        Parent HomePage = loader.load();

        Scene homeViewScene = new Scene(HomePage);

        // MessageController detailedControlled = loader.getController();
        Scene currentScene = ((Node) event.getSource()).getScene();
        //detailedControlled.setPreviousScene(currentScene);

        Stage stage = (Stage) currentScene.getWindow();

        stage.setScene(homeViewScene);
        stage.show();

    }

    @FXML
    public void actionShowFriends(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FriendsPage.fxml"));

        Parent HomePage = loader.load();

        Scene homeViewScene = new Scene(HomePage);

        Scene currentScene = ((Node) event.getSource()).getScene();

        Stage stage = (Stage) currentScene.getWindow();

        stage.setScene(homeViewScene);
        stage.show();

    }

    @FXML
    public void actionShowProfile(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ProfilePage.fxml"));

        Parent HomePage = loader.load();

        Scene homeViewScene = new Scene(HomePage);

        Scene currentScene = ((Node) event.getSource()).getScene();

        Stage stage = (Stage) currentScene.getWindow();

        stage.setScene(homeViewScene);
        stage.show();

    }

    //NOTES
    //Need createPM, deletePM, readPM, updatePM, 
    //update, delete, readbyId, readAll (operations)
    //Database Manager
    EntityManager manager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Code from Demo
        manager = (EntityManager) Persistence.createEntityManagerFactory("group9PU").createEntityManager();

        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        PmName.setCellValueFactory(new PropertyValueFactory<>("PmName"));
        Pm.setCellValueFactory(new PropertyValueFactory<>("Pm"));

        //messageTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    /* 
    Implementing CRUD operations    
     */
    public void create(Messagemodel yourMessage) {
        //inspiration taken from demo code
        try {
            manager.getTransaction().begin();

            if (yourMessage.getID() != null) {
                manager.persist(yourMessage);
                manager.getTransaction().commit();
                System.out.println(yourMessage.toString() + "has been created");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Messagemodel> readAll() {
        //inspiration taken from demo code
        Query query = manager.createNamedQuery("Messagemodel.findAll");
        List<Messagemodel> yourMessage = query.getResultList();

        return yourMessage;
    }
    
    public Messagemodel readByMessageId(int id){
        Query query = manager.createNamedQuery("Messagemodel.findById");

        // setting query parameter
        query.setParameter("id", id);

        // execute query
        Messagemodel msg = (Messagemodel) query.getSingleResult();
        if (msg != null) {

        }

        return msg;
        
    }

    public void delete(Messagemodel post) {
            try {
                Messagemodel existingPost = manager.find(Messagemodel.class, post.getID());

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
}
