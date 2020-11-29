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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private TableColumn<Createpostmodel, String> Date;
    
    @FXML
    private TableColumn<Createpostmodel, String> Post;
    
    private ObservableList<Createpostmodel> postData;
    
    
    public void setTableData(List<Createpostmodel> postList){
        //Inspiration taken from Quiz 4 Demo Code
        postData = FXCollections.observableArrayList();
        
        postList.forEach(s -> {
            postData.add(s);
        });
        
        postTable.setItems(postData);
        postTable.refresh();
    }       
    
    

    @FXML
    void createPost(ActionEvent event) {
         //inspiration taken from demo code
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        
        Scanner input = new Scanner(System.in);
        
        // read input from command line
        System.out.println("Enter ID: ");
        int id = input.nextInt();
        
        ////https://stackoverflow.com/questions/13102045/scanner-is-skipping-nextline-after-using-next-or-nextfoo
        input.nextLine();

        System.out.println("Enter Todays Date (MM/dd/yy): ");
        String str = input.nextLine();
        
        try{
            Date date = sdf.parse(str);
            
            sdf = new SimpleDateFormat("MM dd yy");
            System.out.println(sdf.format(date)); 
        } catch (ParseException e) {
            System.out.println("Parse Exception");
        }
        
        System.out.println("Enter Your Post: ");
        String post = input.nextLine();
        
        // create a post instance
        Createpostmodel yourPost = new Createpostmodel();
        
        // set properties
        yourPost.setId(id);
        yourPost.setDate(str);
        yourPost.setPost(post);
        
        //save this student to the database by calling Create operation
        create(yourPost);
        
         
    }

    @FXML
    void deletePost(ActionEvent event) {

    }

    @FXML
    void updatePost(ActionEvent event) {

    }

    
    //Database Manager
    EntityManager manager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Code from Demo
        manager = (EntityManager) 
                
        Persistence.createEntityManagerFactory("group9PU").createEntityManager();
        
        //Inspiration taken from Quiz 4 Demo Code
        ID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        Post.setCellValueFactory(new PropertyValueFactory<>("Post"));
        
        postTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
    
    
    /* 
    Implementing CRUD operations    
    */        
    
    //Create operation
    public void create (Createpostmodel yourPost){
        //inspiration taken from demo code
        try{
            manager.getTransaction().begin();
        
            if (yourPost.getId() != null){
                manager.persist(yourPost);
                manager.getTransaction().commit();
                System.out.println(yourPost.toString() + "has been created");            
            }       
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }
    
    
    
}




