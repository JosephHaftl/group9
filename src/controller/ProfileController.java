package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Createpostmodel;
import model.Profilemodel;

public class ProfileController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField gradyrField;

    @FXML
    private TextArea bioField;

    @FXML
    private ImageView profilepic;
    
    @FXML
    private Button backButton;
    
    @FXML
    private TableView<Profilemodel> profileTable;
    
    @FXML
    private TableColumn<Profilemodel, Integer> ID;

    @FXML
    private TableColumn<Profilemodel, String> Name;

    @FXML
    private TableColumn<Profilemodel, Integer> Age;

    @FXML
    private TableColumn<Profilemodel, Integer> Gradyr;
    
    @FXML
    private TableColumn<Profilemodel, String> Bio;

    private ObservableList<Profilemodel> profileData;
    
    @FXML
    void actionshowFriends(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FriendsPage.fxml"));    

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
    void actionshowHome(ActionEvent event)throws IOException  {
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
    void actionshowMessages(ActionEvent event) throws IOException{
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
//    @FXML
//    void goBack(ActionEvent event) {
//     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        
//        if (previousScene != null) {
//            stage.setScene(previousScene);
//        }
//    }
//     
//    
//    Profilemodel selectedModel;
//    Scene previousScene;
//    
//        public void setPreviousScene(Scene scene) {
//        previousScene = scene;
//        backButton.setDisable(false);
//    }
       
       
    @FXML
    void createProfile(ActionEvent event) {
        Scanner scn = new Scanner(System.in);
        
        System.out.println("Enter ID:");
        int id = scn.nextInt();
        
        System.out.println("Enter Name:");
        String name = scn.nextLine();
        
        System.out.println("Enter Age");
        int age = scn.nextInt();
        
        System.out.println("Enter Graduation Year");
        int gradyr = scn.nextInt();
        
        System.out.println("Enter Bio: ");
        String bio = scn.nextLine();
        
        // create a like instance
        Profilemodel profile = new Profilemodel();
        
        // set properties
        profile.setId(id);
        profile.setName(name);
        profile.setAge(age);
        profile.setGradyear(gradyr);
        profile.setBio(bio);
        
        
        // save this student to databse by calling Create operation        
        create(profile);

    }

    @FXML
    void deleteProfile(ActionEvent event) {
        Scanner input = new Scanner(System.in);
        
         // read input from command line
        System.out.println("Enter ID of the profile you'd like to delete:");
        int id = input.nextInt();
        
        Profilemodel profile = readById(id);
        System.out.println("we are deleting this profile: "+ profile.toString());
        delete(profile);
    }

    @FXML
    void updateProfile(ActionEvent event) {
        Scanner scn = new Scanner(System.in);
        
        System.out.println("Enter ID:");
        int id = scn.nextInt();
        
        System.out.println("Enter Name:");
        String name = scn.nextLine();
        
        System.out.println("Enter Age");
        int age = scn.nextInt();
        
        System.out.println("Enter Graduation Year");
        int gradyr = scn.nextInt();
        
        System.out.println("Enter Bio");
        String bio = scn.nextLine();
        
        // create a like instance
        Profilemodel profile = new Profilemodel();
        
        // set properties
        profile.setId(id);
        profile.setName(name);
        profile.setAge(age);
        profile.setGradyear(gradyr);
        
        // save this student to databse by calling Create operation        
        update(profile);
        
    }
        
    
        public void setTableData(List<Profilemodel> profiles){
        
        profileData = FXCollections.observableArrayList();
        
        profiles.forEach(s -> {
            profileData.add(s);
        });
        
        profileTable.setItems(profileData);
        profileTable.refresh();
    } 
    
    @FXML
    void viewProfiles(ActionEvent event) {
        List<Profilemodel> profiles = readAll();
        setTableData(profiles);
    }
    
    
    
    EntityManager manager;
    
     //@Override
     public void initialize(URL url, ResourceBundle rb) {

        manager = (EntityManager) 
        Persistence.createEntityManagerFactory("group9PU").createEntityManager();
        
        ID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        Age.setCellValueFactory(new PropertyValueFactory<>("Age"));
        Gradyr.setCellValueFactory(new PropertyValueFactory<>("Gradyear"));
        Bio.setCellValueFactory(new PropertyValueFactory<>("Bio"));
        

        //eanble row selection
        // (SelectionMode.MULTIPLE);
        profileTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
     }
    
    public void create(Profilemodel like) {
        try {
            // begin transaction
            manager.getTransaction().begin();
            
            // sanity check
            if (like.getId() != null) {
                
                // create student
                manager.persist(like);
                
                // end transaction
                manager.getTransaction().commit();
                
                System.out.println(like.toString() + " is created");
                
            }
           
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void update(Profilemodel model) {
        try {

            Profilemodel existingProfile = manager.find(Profilemodel.class, model.getId());

            if (existingProfile != null) {
                // begin transaction
                manager.getTransaction().begin();
                
                existingProfile.setName(model.getName());
                existingProfile.setAge(model.getAge());
                existingProfile.setGradyear(model.getGradyear());
                existingProfile.setBio(model.getBio());
                
                
                // update all atttributes
//                existingLike.setName(model.getName());
//                existingLike.setLikepost(model.getLikepost());
//                existingLike.setLikeid(model.getLikeid());
                
                // end transaction
                manager.getTransaction().commit();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
       public void delete(Profilemodel like) {
        try {
            Profilemodel existingLike = manager.find(Profilemodel.class, like.getId());

            // sanity check
            if (existingLike != null) {
                
                
                // begin transaction
                manager.getTransaction().begin();
                
                //remove student
                manager.remove(existingLike);
                
                // end transaction
                manager.getTransaction().commit();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            
        }
    }
       
    public List<Profilemodel> readAll(){
        Query query = manager.createNamedQuery("Profilemodel.findAll");
        List<Profilemodel> profile = query.getResultList();
        
       return profile;
        
       } 
    
    public Profilemodel readById(int id){
        Query query = manager.createNamedQuery("Profilemodel.findById");
        
        // setting query parameter
        query.setParameter("id", id);
        
        // execute query
        Profilemodel profile = (Profilemodel) query.getSingleResult();
        if (profile != null) {
            System.out.println(profile.getId() + " " + profile.getName() + " " + profile.getAge() + " " 
                    + profile.getGradyear() + " " + profile.getBio());
        }
        
        return profile;
    }  
    

    @FXML
    void initialize() {
        assert idField != null : "fx:id=\"idField\" was not injected: check your FXML file 'ProfilePage.fxml'.";
        assert nameField != null : "fx:id=\"nameField\" was not injected: check your FXML file 'ProfilePage.fxml'.";
        assert ageField != null : "fx:id=\"ageField\" was not injected: check your FXML file 'ProfilePage.fxml'.";
        assert gradyrField != null : "fx:id=\"gradyrField\" was not injected: check your FXML file 'ProfilePage.fxml'.";
        assert bioField != null : "fx:id=\"bioField\" was not injected: check your FXML file 'ProfilePage.fxml'.";
        assert profilepic != null : "fx:id=\"profilepic\" was not injected: check your FXML file 'ProfilePage.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'ProfilePage.fxml'.";
        assert profileTable != null : "fx:id=\"profileTable\" was not injected: check your FXML file 'ProfilePage.fxml'.";
        
    }
}

