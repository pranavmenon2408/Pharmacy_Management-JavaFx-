package com.example;

import java.util.Random;

import com.example.Employees.EmpInsertDelete;
import com.example.Employees.EmployeeRecord;
//import com.example.Patients.*;
import com.example.Login.*;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
//import javafx.scene.layout.Background;
//import javafx.scene.layout.BackgroundFill;
//import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

//import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class App extends Application {
    static int attempt;
    private Label last;
    public static void main(String[] args) {
        
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login Page");
        
        GaussianBlur g=new GaussianBlur();
        g.setRadius(20);
        Image bImage=new Image("file:///C:/Users/Pranav/Downloads/digigov-hmis-hospital-management-system-maintain.jpg");
        ImageView bImageView=new ImageView(bImage);
        bImageView.setEffect(g);
        StackPane root=new StackPane(bImageView);
        // Create a VBox layout to hold the UI components
        VBox vbox = new VBox(10);
        HBox hBox=new HBox();
        hBox.setAlignment(Pos.CENTER);


        //vbox.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,CornerRadii.EMPTY,Insets.EMPTY)));
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20, 20, 20, 20));
        Label tLabel=new Label("Admin Login");
        tLabel.setStyle("-fx-text-fill: yellow;");
        Font font=Font.font("Times New Roman",FontWeight.BOLD,44);
        tLabel.setFont(font);
        VBox.setMargin(tLabel, new Insets(5));
        //tLabel.setTranslateY(-10);
        // Create TextFields for username and password
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");
        usernameField.setMaxWidth(300);
        VBox.setMargin(usernameField, new Insets(5));
        passwordField.setMaxWidth(300);
        VBox.setMargin(passwordField, new Insets(5));
        // Create a Login button
        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-radius: 10; -fx-font-size: 18;");
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            LoginRecord log=Checkpswd.returnLog(username);
            System.out.println(log);
            // Simple validation (you can replace this with actual authentication logic)
            if (log!=null && password.equals(log.getPassword())) {
                // Successful login, show a message or navigate to another screen
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Login Successful");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Welcome, " + username + "!, Page will Load in a minute ....");
                successAlert.showAndWait();
                g.setRadius(5);
                bImageView.setEffect(g);
                StackPane roPane=new StackPane(bImageView);
                VBox vBox2=new VBox(10);
                vBox2.setPadding(new Insets(20,20,20,20));
                Label tLabel2=new Label("Employee/Patient Details");
                tLabel2.setStyle("-fx-text-fill: darkblue; -fx-background-color: lightgray;");
                tLabel2.setFont(font);
                tLabel2.setTranslateX(200);
                ObservableList<String> options=FXCollections.observableArrayList("Employee","Patient");
                ComboBox<String> comboBox=new ComboBox<>(options);
                comboBox.setPromptText("Select an option");
                comboBox.setStyle("-fx-font-size: 14; -fx-background-radius: 5");
                comboBox.setOnAction(event->{
                        String opt=comboBox.getValue();
                        if(opt.equals("Employee")){
                            TextField textField=new TextField();
                            textField.setPromptText("Enter Employee Name");
                            textField.setMaxWidth(250);
                            Button btn3=new Button("Display Employee Details");
                            btn3.setStyle("-fx-background-radius: 10; -fx-font-size: 14;");
                            btn3.setTranslateX(30);
                            vBox2.getChildren().addAll(textField,btn3);
                            
                            btn3.setOnAction(e2->{
                                String name=textField.getText();
                                EmployeeRecord emp=EmpInsertDelete.returnEmp(name);
                                if(last!=null){
                                    last.setVisible(false);
                                    last.setManaged(false);
                                    last=null;
                                }
                                if(emp!=null){
                                    Label details=new Label(emp.toString());
                                    last=details;
                                    details.setStyle("-fx-font-size: 16; -fx-background-color: lightgray; -fx-text-fill: black;");
                                    vBox2.getChildren().addAll(details);
                                }
                                else{
                                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                                    errorAlert.setTitle("Employee not in Database");
                                    errorAlert.setHeaderText(null);
                                    errorAlert.setContentText("Employee not present in Database, Add employee");
                                    errorAlert.showAndWait();
                                    TextField age=new TextField();
                                    age.setPromptText("Enter age ");
                                    age.setMaxWidth(250);
                                    TextField department=new TextField();
                                    department.setPromptText("Enter department ");
                                    department.setMaxWidth(250);
                                    TextField email=new TextField();
                                    email.setPromptText("Enter email ");
                                    email.setMaxWidth(250);
                                    TextField phField=new TextField();
                                    phField.setPromptText("Enter Phone number ");
                                    phField.setMaxWidth(250);
                                    TextField address=new TextField();
                                    address.setPromptText("Enter address ");
                                    address.setMaxWidth(250);
                                    Button btn4=new Button("Add Employee Details");
                                    btn4.setStyle("-fx-background-radius: 10; -fx-font-size: 14;");
                                    btn4.setTranslateX(30);
                                    vBox2.getChildren().addAll(age,department,email,phField,address,btn4);
                                    btn4.setOnAction(e3->{
                                        int a=Integer.parseInt(age.getText());
                                        String dept=department.getText();
                                        String em=email.getText();
                                        String pno=phField.getText();
                                        String addr=address.getText();
                                        Random rand=new Random();
                                        int empid=rand.nextInt(999-100+1)+100;
                                        String emptylist[]=new String[30];
                                        if(pno.length()!=10){
                                            Alert errorAlert3 = new Alert(Alert.AlertType.ERROR);
                                            errorAlert3.setTitle("Invalid Phone Number");
                                            errorAlert3.setHeaderText(null);
                                            errorAlert3.setContentText("Invalid Phone Number Enter correct one.");
                                            errorAlert3.showAndWait();
                                        }else{
                                        EmpInsertDelete.insertEmp(name, addr, pno, dept, em, a, empid, emptylist);
                                        Alert successAlert2 = new Alert(Alert.AlertType.INFORMATION);
                                        successAlert2.setTitle("Employee Aded");
                                        successAlert2.setHeaderText(null);
                                        successAlert2.setContentText("Employee Added Successfully");
                                        successAlert2.showAndWait();
                                        age.setVisible(false);
                                        age.setManaged(false);
                                        department.setVisible(false);
                                        department.setManaged(false);
                                        email.setVisible(false);
                                        email.setManaged(false);
                                        phField.setVisible(false);
                                        phField.setManaged(false);
                                        address.setVisible(false);
                                        address.setManaged(false);
                                        btn4.setVisible(false);
                                        btn4.setManaged(false);
                                        }
                                    });
                                }
                            });
                            

                        }
                });
                vBox2.getChildren().addAll(tLabel2,comboBox);
                roPane.getChildren().add(vBox2);
                Scene scene2=new Scene(roPane,1024,541);
                primaryStage.setTitle("Employee/Patient Page");
                primaryStage.setScene(scene2);
                //primaryStage.show();

            } else {
                // Failed login
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Login Failed");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Invalid username or password. Please try again.");
                errorAlert.showAndWait();
                attempt++;
                if(attempt==3){
                    startDelay();
                    attempt=0;
                }
                }
            
        });
        HBox.setMargin(loginButton, new Insets(5));
        
        Button signupButton=new Button("Add User");
        signupButton.setStyle("-fx-background-radius: 10; -fx-font-size: 18;");
        signupButton.setOnAction(e->{
            String username=usernameField.getText();
            String password=passwordField.getText();
            LoginRecord loginRecord=Checkpswd.returnLog(username);
            if(loginRecord==null){
                Checkpswd.insertLog(username, password);
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("User Added");
                successAlert.setHeaderText(null);
                successAlert.setContentText("New user added successfully");
                successAlert.showAndWait();
            }
            else{
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("User already exists");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Given Username already exists ,give another Username");
                errorAlert.showAndWait();
            }
        });
        HBox.setMargin(signupButton, new Insets(5));
        hBox.getChildren().addAll(loginButton,signupButton);
        // Add components to the VBox
        vbox.getChildren().addAll(tLabel,usernameField, passwordField, hBox);
        root.getChildren().add(vbox);
        // Create a scene and set it on the stage
        Scene scene = new Scene(root, 1024, 541);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
    private void startDelay(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Too Many failed Attempts");
        alert.setContentText("Too many failed attempts, You cant Login for 10 seconds");
        alert.show();

        Task<Void> couTask=new Task<Void>() {
            @Override
            protected Void call(){
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
                return null;
            }
        };
        couTask.setOnSucceeded(event -> {
            alert.close();
            enableOkButton();
        });
        Timeline countdownTimer = new Timeline(new KeyFrame(Duration.seconds(10), ae -> {
            couTask.run();
        }));
        countdownTimer.play();
    }
    private void enableOkButton(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Login Enabled");
        alert.setContentText("You can try Login again");
        alert.showAndWait();
        
    }
}
