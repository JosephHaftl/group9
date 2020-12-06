/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Createpostmodel;
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
    
    
    public void setTableData(List<Messagemodel> messageList){
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
    void viewMessage (ActionEvent event) {
        List<Messagemodel> messages = readAll();
        setTableData(messages);
    }

    
    @FXML
    void deleteMessage(ActionEvent event) {

    }


    //NOTES
    //Need createPM, deletePM, readPM, updatePM, 
    
    //update, delete, readbyId, readAll (operations)
    

    
    //Database Manager
    EntityManager manager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Code from Demo
        manager = (EntityManager)          
        Persistence.createEntityManagerFactory("group9PU").createEntityManager();
        
        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        PmName.setCellValueFactory(new PropertyValueFactory<>("PmName"));
        Pm.setCellValueFactory(new PropertyValueFactory<>("Pm"));
        
        //messageTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
    
    
    /* 
    Implementing CRUD operations    
    */   
    
    public void create (Messagemodel yourMessage){
        //inspiration taken from demo code
        try{
            manager.getTransaction().begin();
        
            if (yourMessage.getID() != null){
                manager.persist(yourMessage);
                manager.getTransaction().commit();
                System.out.println(yourMessage.toString() + "has been created");            
            }       
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }
    
    public List<Messagemodel> readAll(){
        //inspiration taken from demo code
        Query query = manager.createNamedQuery("Messagemodel.findAll");
        List<Messagemodel> yourMessage = query.getResultList();
 
        return yourMessage;
    }  
    
    
    
}
