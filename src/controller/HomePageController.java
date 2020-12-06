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

public class HomePageController implements Initializable {
    @FXML
    private TextField textboxPost;
   
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private TableView<Messagemodel> messageTable;
    
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

        System.out.println("Enter Todays Date (mm/dd/yyyy): ");
        String str = input.nextLine();
        
        
//        try{
//            Date date = sdf.parse(str);
//            
//            sdf = new SimpleDateFormat("MM dd yy");
//            System.out.println(sdf.format(date)); 
//        } catch (ParseException e) {
//            System.out.println("Parse Exception");
//        }
        
        String post = textboxPost.getText();
        
        // create a post instance
        Createpostmodel yourPost = new Createpostmodel();
        
        // set properties
        yourPost.setId(id);
        yourPost.setDate(str);
        yourPost.setPost(post);
        
        //save this student to the database by calling Create operation
        create(yourPost);
        List<Createpostmodel> Posts = readAll();
        setTableData(Posts);
    }
    
    
    @FXML
    void viewPost (ActionEvent event) {
        List<Createpostmodel> Posts = readAll();
        setTableData(Posts);
    }
    
    @FXML
    void deletePostSimple(ActionEvent event){
    Scanner input = new Scanner(System.in);
    System.out.println("Enter the id of the post you wish to delete");
    int id = input.nextInt();
    delete(readById(id));
    List<Createpostmodel> Posts = readAll();
    setTableData(Posts);
    }
    
    @FXML
    void deletePost(ActionEvent event) {
        TablePosition pos = postTable.getSelectionModel().getSelectedCells().get(0);

        int id = pos.getRow();
        id = id +1 ;   
        
        postTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ObservableList<Createpostmodel> selectedRows = postTable.getSelectionModel().getSelectedItems();
        ArrayList<Createpostmodel> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> postTable.getItems().remove(row));
    
        
        System.out.println(id);
    
        delete(readById(id));
    
   
    }

    @FXML
    void updatePost(ActionEvent event) {
        //inspired from demo code
        Scanner sc = new Scanner(System.in);
      
        System.out.println("Enter ID: ");
        int id = sc.nextInt();
        
        System.out.println("Enter New date: (mm/dd/yyyy) ");
        String date = sc.next();
        
        //System.out.println("Update post: ");
        String post = textboxPost.getText();
        
        
        
        Createpostmodel yourPost = new Createpostmodel();
        
        yourPost.setId(id);
        yourPost.setDate(date);
        yourPost.setPost(post);
        
        update(yourPost);
        
        List<Createpostmodel> Posts = readAll();
        setTableData(Posts);
    }
    
    
    @FXML
    public void actionShowMessages(ActionEvent event) throws IOException {
        
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
    public List<Createpostmodel> readAll(){
        Query query = manager.createNamedQuery("Createpostmodel.findAll");
        List<Createpostmodel> posts = query.getResultList();

        
        return posts;
    }
         
    public void update (Createpostmodel yourPost){
        try {

            Createpostmodel existingPost = manager.find(Createpostmodel.class, yourPost.getId());
            
            manager.getTransaction().begin();

            if (existingPost != null) {       

                existingPost.setId(yourPost.getId());
                existingPost.setDate(yourPost.getDate());
                existingPost.setPost(yourPost.getPost());
               
                manager.getTransaction().commit();
                System.out.println(yourPost.toString() + "has been updated"); 

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    public void delete(Createpostmodel post) {
        try {
            Createpostmodel existingPost = manager.find(Createpostmodel.class, post.getId());

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
      
      
    public Createpostmodel readById(int id){
        Query query = manager.createNamedQuery("Createpostmodel.findById");
        
        // setting query parameter
        query.setParameter("id", id);
        
        // execute query
        Createpostmodel post = (Createpostmodel) query.getSingleResult();
        if (post != null) {
        
        }
        
        return post;
    } 
     
     
     
}
             

    
    





