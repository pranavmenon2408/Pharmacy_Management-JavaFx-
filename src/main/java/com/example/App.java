package com.example;

import java.io.File;
import java.io.IOException;
import java.util.Random;


import com.example.Employees.EmpInsertDelete;
import com.example.Employees.EmployeeRecord;
//import com.example.Patients.*;
import com.example.Login.*;
import com.example.Patients.Existing;
import com.example.Patients.PatientRecords;
import com.example.Pharmacy.InStock;
import com.example.Pharmacy.PharmacyRecord;
import com.itextpdf.kernel.pdf.PdfDocument;
//import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

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
    static ComboBox<String> med;
    static Label detailLabel, expLabel, prLabel, resultLabel;
    static CheckBox updateStockCheckbox, makeSaleCheckbox;
    static TextField quantityTextField;
    static Button setStockButton, makeSaleButton;
    static int flag=0;
    private static int selectedMedIndex = -1;
    //private static int[] stock = {200, 100, 300};
    static int attempt,check,total;
    static String bill_details;
    static String final_bill;
    Label last;
    TextField textField=new TextField();
    TextField department;
    Button btn3=new Button("");
    Button btn4,btn5;
    private String dept;
    String assign="\nPatients: \n";
    CheckBox c3,c5,c6;
    boolean illness,injury,surgery;
    public static void main(String[] args) {
        
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login Page");
        
        GaussianBlur g=new GaussianBlur();
        g.setRadius(20);
        String relative="src\\main\\java\\com\\example\\img\\digigov-hmis-hospital-management-system-maintain.jpg";
        File file=new File(relative);
        String imageUrl=file.toURI().toString();
        Image bImage=new Image(imageUrl);
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
        Font font=Font.font("Times New Roman",FontWeight.BOLD,54);
        tLabel.setFont(font);
        VBox.setMargin(tLabel, new Insets(5));
        //tLabel.setTranslateY(-10);
        // Create TextFields for username and password
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");
        
        usernameField.setMaxWidth(300);
        usernameField.setMinHeight(30);
        usernameField.setFont(Font.font(16));
        VBox.setMargin(usernameField, new Insets(5));
        passwordField.setMaxWidth(300);
        passwordField.setMinHeight(30);
        passwordField.setFont(Font.font(16));
        
        VBox.setMargin(passwordField, new Insets(5));
        // Create a Login button
        //FlowPane pane=new FlowPane();
        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-radius: 10; -fx-font-size: 20;");
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
                FlowPane vBox2=new FlowPane(Orientation.VERTICAL,10,10);
                vBox2.setPadding(new Insets(20,20,20,20));
                Label tLabel2=new Label("Employee/Patient Details");
                tLabel2.setStyle("-fx-text-fill: darkblue; -fx-background-color: lightgray;");
                tLabel2.setFont(font);
                tLabel2.setTranslateX(350);
                ObservableList<String> options=FXCollections.observableArrayList("Employee","Patient");
                ComboBox<String> comboBox=new ComboBox<>(options);
                comboBox.setPromptText("Select an option");
                comboBox.setStyle("-fx-font-size: 14; -fx-background-radius: 5");
                comboBox.setOnAction(event->{
                        String opt=comboBox.getValue();
                        check=0;
                        if(opt.equals("Employee")){
                            if(last!=null){
                                    vBox2.getChildren().remove(last);
                                    last=null;
                                    assign="\nPatients: \n";
                            }
                            if(c5!=null){
                                vBox2.getChildren().removeAll(c5,btn5);
                                c5=null;
                            }
                            if(btn5!=null){
                                    vBox2.getChildren().removeAll(btn5,btn4);
                                    btn4=null;
                                    btn5=null;
                            }
                            if(vBox2.getChildren().contains(textField))
                                vBox2.getChildren().removeAll(textField,btn3);
                            textField.setPromptText("Enter Employee Name");
                            textField.setMaxWidth(300);
                            textField.setMinHeight(30);
                            textField.setFont(Font.font(16));
                            btn3.setText("Display Employee Details");
                            btn3.setStyle("-fx-background-radius: 10; -fx-font-size: 16;");
                            btn3.setTranslateX(30);
                            vBox2.getChildren().addAll(textField,btn3);
                            
                            
                            btn3.setOnAction(e2->{
                                String name=textField.getText();
                                EmployeeRecord emp=EmpInsertDelete.returnEmp(name);
                                if(last!=null){
                                    vBox2.getChildren().removeAll(last);
                                    last=null;
                                    assign="\nPatients: \n";
                                }
                                if(emp!=null){
                                    
                                    
                                    int i=0;
                                    char c='\t';
                                    for(String a:emp.getAssignments()){
                                        if(i==0){}
                                        else{
                                        assign+=i+". "+a+c;
                                        
                                        if(i%2==0)
                                            c='\n';
                                        else{
                                            c='\t';
                                        }
                                    }i++;
                                    }
                                    Label details=new Label(emp.toString()+assign);
                                    last=details;
                                    details.setStyle("-fx-font-size: 24; -fx-background-color: white; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 4px;");
                                    check=0;
                                    
                                    details.setMaxWidth(800);
                                    details.setMinHeight(300);
                                    vBox2.getChildren().addAll(details);
                                }
                                else{
                                    Alert errorAlert4 = new Alert(Alert.AlertType.ERROR);
                                    errorAlert4.setTitle("Empty data field");
                                    errorAlert4.setHeaderText(null);
                                    errorAlert4.setContentText("One of the fields is empty, all fields are compulsory");
                                    if(name==""){
                                        errorAlert4.showAndWait();
                                    }
                                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                                    errorAlert.setTitle("Employee not in Database");
                                    errorAlert.setHeaderText(null);
                                    errorAlert.setContentText("Employee not present in Database, Add employee or enter correct spelling");
                                    errorAlert.showAndWait();
                                    if(check==2){
                                    TextField age=new TextField();
                                    age.setPromptText("Enter age ");
                                    age.setMaxWidth(300);
                                    age.setMinHeight(30);
                                    age.setFont(Font.font(16));
                                    TextField department=new TextField();
                                    department.setPromptText("Enter department ");
                                    department.setMaxWidth(300);
                                    department.setMinHeight(30);
                                    department.setFont(Font.font(16));
                                    TextField email=new TextField();
                                    email.setPromptText("Enter email ");
                                    email.setMaxWidth(300);
                                    email.setMinHeight(30);
                                    email.setFont(Font.font(16));
                                    TextField phField=new TextField();
                                    phField.setPromptText("Enter Phone number ");
                                    phField.setMaxWidth(300);
                                    phField.setMinHeight(30);
                                    phField.setFont(Font.font(16));
                                    TextField address=new TextField();
                                    address.setPromptText("Enter address ");
                                    address.setMaxWidth(300);
                                    address.setMinHeight(30);
                                    address.setFont(Font.font(16));
                                    Button btn4=new Button("Add Employee Details");
                                    btn4.setStyle("-fx-background-radius: 10; -fx-font-size: 16;");
                                    btn4.setTranslateX(30);
                                    vBox2.getChildren().addAll(age,department,email,phField,address,btn4);
                                    btn4.setOnAction(e3->{
                                        
                                        int a=0;
                                        if(age.getText()==""){
                                            errorAlert4.showAndWait();
                                        }else{
                                        a=Integer.parseInt(age.getText());}
                                        String dept=department.getText();
                                        String em=email.getText();
                                        String pno=phField.getText();
                                        String addr=address.getText();
                                        Random rand=new Random();
                                        int empid=rand.nextInt(999-100+1)+100;
                                        String emptylist[]=new String[1];
                                        if(pno.length()!=10){
                                            Alert errorAlert3 = new Alert(Alert.AlertType.ERROR);
                                            errorAlert3.setTitle("Invalid Phone Number");
                                            errorAlert3.setHeaderText(null);
                                            errorAlert3.setContentText("Invalid Phone Number Enter correct one.");
                                            errorAlert3.showAndWait();
                                        }else if(name=="" || dept==""|| em=="" || addr==""){
                                            errorAlert4.showAndWait();
                                        }
                                        else{
                                        EmpInsertDelete.insertEmp(name, addr, pno, dept, em, a, empid, emptylist);
                                        Alert successAlert2 = new Alert(Alert.AlertType.INFORMATION);
                                        successAlert2.setTitle("Employee Aded");
                                        successAlert2.setHeaderText(null);
                                        successAlert2.setContentText("Employee Added Successfully");
                                        successAlert2.showAndWait();
                                        vBox2.getChildren().removeAll(age,department,email,phField,address,btn4);
                                        
                                        }
                                    });
                                    check=0;
                                }else{
                                    check++;
                                }
                            }
                            });
                        

                        }else if (opt.equals("Patient")) {
                            if(last!=null){
                                    vBox2.getChildren().removeAll(last);
                                    last=null;
                            }

                            if(vBox2.getChildren().contains(textField))
                                vBox2.getChildren().removeAll(textField,btn3);
                            textField.setPromptText("Enter Patient Name");
                            textField.setMaxWidth(300);
                            textField.setMinHeight(30);
                            textField.setFont(Font.font(16));
                            btn3.setText("Display Patient Details");
                           
                            btn3.setStyle("-fx-background-radius: 10; -fx-font-size: 16;");
                            btn3.setTranslateX(30);
                            vBox2.getChildren().addAll(textField,btn3);
                            btn3.setOnAction(e5->{
                                String name=textField.getText();
                                PatientRecords pat=Existing.returnPat(name);
                                
                                if(last!=null){
                                    vBox2.getChildren().remove(last);
                                    last=null;
                                }
                                if(c5!=null){
                                    vBox2.getChildren().removeAll(c5,btn5);
                                    c5=null;
                                }
                                if(btn5!=null){
                                    vBox2.getChildren().removeAll(btn5,btn4);
                                    btn4=null;
                                    btn5=null;
                                }
                                if(pat!=null){
                                    
                                    //String doc=EmpInsertDelete.getAttending(name, pat.getConcernedDepartment());
                                    Label details=new Label(pat.toString());
                                    last=details;
                                    details.setStyle("-fx-font-size: 20; -fx-background-color: white; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 4px;");
                                    details.setMaxWidth(400);
                                    details.setMinHeight(250);
                                    vBox2.getChildren().addAll(details);
                                    if(pat.getAptdone()==false){
                                        c5=new CheckBox("Appointment/Surgery Done?");
                                        
                                        btn5=new Button("Update record");
                                        btn5.setStyle("-fx-background-radius: 10; -fx-font-size: 16;");
                                        btn5.setTranslateX(30);
                                        btn5.setTranslateY(10);
                                        vBox2.getChildren().addAll(c5,btn5);
                                        c5.setStyle("-fx-font-size: 20; -fx-text-fill: yellow;");
                                        btn5.setOnAction(eve2->{
                                            if(c5.isSelected()){
                                                
                                                EmpInsertDelete.removePat(name, EmpInsertDelete.getAttending(name, pat.getConcernedDepartment()));
                                                Existing.updatePat(name);
                                                Alert successAlert2 = new Alert(Alert.AlertType.INFORMATION);
                                                successAlert2.setTitle("Record Updated");
                                                successAlert2.setHeaderText(null);
                                                successAlert2.setContentText("Record Updated Appointment Done");
                                                successAlert2.showAndWait();
                                                vBox2.getChildren().removeAll(c5,btn5);
                                                btn5=new Button("Go To Pharmacy");
                                                btn5.setStyle("-fx-background-radius: 10; -fx-font-size: 16;");
                                                btn4=new Button("Print Bill");
                                                btn4.setStyle("-fx-background-radius: 10; -fx-font-size: 16;");
                                                HBox hBox3=new HBox();
                                                btn4.setTranslateX(15);
                                                hBox3.getChildren().addAll(btn5,btn4);
                                                vBox2.getChildren().add(hBox3);
                                                btn5.setOnAction(eve->{
                                                    pharmScene(primaryStage,pat);
                                                });
                                                btn4.setOnAction(eve3->{
                                                    billScene(primaryStage,pat);
                                                });

                                                
                                            }
                                            
                                        });
                                    }else{
                                        vBox2.getChildren().removeAll(c5,btn5);
                                        btn5=new Button("Go To Pharmacy");
                                        btn5.setStyle("-fx-background-radius: 10; -fx-font-size: 16;");
                                        btn4=new Button("Print Bill");
                                        btn4.setStyle("-fx-background-radius: 10; -fx-font-size: 16;");
                                        HBox hBox3=new HBox();
                                        btn4.setTranslateX(15);
                                        hBox3.getChildren().addAll(btn5,btn4);
                                        vBox2.getChildren().add(hBox3);
                                        btn5.setOnAction(eve->{
                                            pharmScene(primaryStage,pat);
                                        });
                                        btn4.setOnAction(eve3->{
                                            billScene(primaryStage,pat);
                                        });
                                    }
                                
                                    
                                }
                                else{
                                    
                                    Alert errorAlert4 = new Alert(Alert.AlertType.ERROR);
                                    errorAlert4.setTitle("Empty data field");
                                    errorAlert4.setHeaderText(null);
                                    errorAlert4.setContentText("One of the fields is empty, all fields are compulsory");
                                    if(name==""){
                                        errorAlert4.showAndWait();
                                    }
                                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                                    errorAlert.setTitle("Patient not in Database");
                                    errorAlert.setHeaderText(null);
                                    errorAlert.setContentText("Patient not present in Database, Add patient");
                                    errorAlert.showAndWait();
                                    TextField age=new TextField();
                                    age.setPromptText("Enter age ");
                                    age.setMinHeight(30);
                                    age.setMaxWidth(300);
                                    age.setFont(Font.font(16));
                                    TextField email=new TextField();
                                    email.setPromptText("Enter email ");
                                    email.setMaxWidth(300);
                                    email.setMinHeight(30);
                                    email.setFont(Font.font(16));
                                    TextField phField=new TextField();
                                    phField.setPromptText("Enter Phone number ");
                                    phField.setMaxWidth(300);
                                    phField.setMinHeight(30);
                                    phField.setFont(Font.font(16));
                                    TextField address=new TextField();
                                    address.setPromptText("Enter address ");
                                    address.setMaxWidth(300);
                                    address.setMinHeight(30);
                                    address.setFont(Font.font(16));
                                    HBox hBox3=new HBox(10);
                                    CheckBox c1=new CheckBox("Illness");
                                    c1.setStyle("-fx-font-size: 16; -fx-text-fill: white;");
                                    CheckBox c2=new CheckBox("Injury/Diagnosed Patient");
                                    c2.setStyle("-fx-font-size: 16; -fx-text-fill: white;");
                                    hBox3.getChildren().addAll(c1,c2);
                                     Alert errorAlert5=new Alert(Alert.AlertType.ERROR);
                                    c1.setOnAction(e8->{
                                        if(c1.isSelected() && c2.isSelected()){
                                           
                                            errorAlert5.setTitle("Both Illness and Injury boxes clicked");
                                            errorAlert5.setContentText("Both boxes clicked click only one");
                                            errorAlert5.showAndWait();
                                            c1.setSelected(false);
                                            c2.setSelected(false);
                                        }else{
                                            dept="General Physician";
                                            illness=true;
                                            injury=false;
                                            surgery=false;
                                        }
                                    });
                                    c2.setOnAction(e7->{
                                        if(c1.isSelected() && c2.isSelected()){
                                            
                                            errorAlert5.setTitle("Both Illness and Injury boxes clicked");
                                            errorAlert5.setContentText("Both boxes clicked, Click only one");
                                            errorAlert5.showAndWait();
                                            c1.setSelected(false);
                                            c2.setSelected(false);
                                        }else{
                                            department=new TextField();
                                            department.setPromptText("Enter concerned department ");
                                            department.setMaxWidth(300);
                                            department.setMinHeight(30);
                                            department.setFont(Font.font(16));
                                            c3=new CheckBox("Surgery Required?");
                                            c3.setStyle("-fx-font-size: 16; -fx-text-fill: white;");
                                            surgery=false;
                                            illness=false;
                                            injury=true;
                                            c3.setOnAction(ev->{
                                                surgery=c3.isSelected();
                                            });
                                            vBox2.getChildren().removeAll(btn4);
                                            vBox2.getChildren().addAll(department,c3,btn4);


                                        }
                                    });
                                    btn4=new Button("Add Patient Details");
                                    btn4.setStyle("-fx-background-radius: 10; -fx-font-size: 16;");
                                    btn4.setTranslateX(30);
                                    vBox2.getChildren().addAll(age,email,phField,address,hBox3,btn4);
                                    btn4.setOnAction(e6->{
                                        if(c1.isSelected()==false && c2.isSelected()==false){
                                            errorAlert5.setTitle("Neither Illness or Injury boxes clicked");
                                            errorAlert5.setContentText("Neither boxes clicked, Click atleast one");
                                            errorAlert5.showAndWait();
                                        }
                                        int a=0;
                                        if(age.getText()==""){
                                            errorAlert4.showAndWait();
                                        }else{
                                        a=Integer.parseInt(age.getText());}
                                        //dept=department.getText();
                                        String em=email.getText();
                                        String pno=phField.getText();
                                        String addr=address.getText();
                                        if(department!=null){
                                            dept=department.getText();
                                        }
                                        
                                        if(pno.length()!=10){
                                            Alert errorAlert3 = new Alert(Alert.AlertType.ERROR);
                                            errorAlert3.setTitle("Invalid Phone Number");
                                            errorAlert3.setHeaderText(null);
                                            errorAlert3.setContentText("Invalid Phone Number Enter correct one.");
                                            errorAlert3.showAndWait();
                                        }else if(name=="" || dept==""|| em=="" || addr==""){
                                            errorAlert4.showAndWait();
                                        }
                                        else{
                                        
                                        EmpInsertDelete.updateEmp(name, dept);
                                        String doc=EmpInsertDelete.getAttending(name, dept);
                                        Existing.insertPat(name, addr, em, pno, dept, a,illness, injury, surgery,doc);
                                        Alert successAlert2 = new Alert(Alert.AlertType.INFORMATION);
                                        successAlert2.setTitle("Patient Aded");
                                        successAlert2.setHeaderText(null);
                                        successAlert2.setContentText("Patient Added Successfully");
                                        successAlert2.showAndWait();
                                        vBox2.getChildren().removeAll(age,email,phField,address,hBox3,btn4);
                                        if(department!=null){
                                            vBox2.getChildren().removeAll(department,c3);
                                        }
                                       }
                                    

                            });
                                }
                        });
                    }
                });
            
                vBox2.getChildren().addAll(tLabel2,comboBox);
                //pane.getChildren().add(vBox2);
                roPane.getChildren().add(vBox2);
                Scene scene2=new Scene(roPane,1200,634);
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
        HBox.setMargin(loginButton, new Insets(10));
        
        Button signupButton=new Button("Add User");
        signupButton.setStyle("-fx-background-radius: 10; -fx-font-size: 20;");
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
        Scene scene = new Scene(root, 1200, 634);
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
    public static void pharmScene(Stage priStage,PatientRecords patient){
        GaussianBlur g=new GaussianBlur();
        g.setRadius(15);
        String relative="src\\main\\java\\com\\example\\img\\pharmacy.jpg";
        File file=new File(relative);
        String imageUrl=file.toURI().toString();
        Image bImage=new Image(imageUrl);
        ImageView bImageView=new ImageView(bImage);
        bImageView.setEffect(g);
        StackPane root=new StackPane(bImageView);
        //String meds[] = {"A", "B", "C"};
        
        priStage.setTitle("Pharmacy Management");
        Button bill_button=new Button("Generate Bill");
        bill_button.setStyle("-fx-background-radius: 10; -fx-font-size: 20;");
        bill_button.setTranslateX(300);
        bill_button.setTranslateY(5);
        FlowPane pane=new FlowPane(10,10);
        VBox vBox=new VBox(10);
        Label bill=new Label();
        bill.setTranslateX(200);
        
        
        vBox.setPadding(new Insets(15,15,15,15));
        Label label=new Label("Pharmacy");
        label.setFont(Font.font("Times New Roman", FontWeight.BOLD, 42));
        label.setTranslateX(300);
        
        detailLabel = new Label();
        Label pat=new Label("\nPatient Name: "+patient.getName());
        pat.setFont(Font.font("Times New Roman",FontWeight.BOLD,18));
        resultLabel = new Label();
        vBox.getChildren().addAll(label,pat);
        String[] medlist=InStock.getMeds();
        ObservableList<String> medicine = FXCollections.observableArrayList(medlist);
        med = new ComboBox<String>(medicine);
        int amount[]=new int[medlist.length];
        System.out.println(medlist.length);
        for(int i=0;i<medlist.length;i++){
            amount[i]=0;
        }

        updateStockCheckbox = new CheckBox("Update Stock");
        makeSaleCheckbox = new CheckBox("Make Sale");
        updateStockCheckbox.setStyle("-fx-font-size: 16; -fx-text-fill: black;");
        makeSaleCheckbox.setStyle("-fx-font-size: 16; -fx-text-fill: black;");
        quantityTextField = new TextField();
        quantityTextField.setPromptText("Enter Quantity ");
        quantityTextField.setMaxWidth(350);
        quantityTextField.setMinHeight(20);
        quantityTextField.setFont(Font.font(16));
        setStockButton = new Button("Set Stock");
        makeSaleButton = new Button("Make Sale");
        setStockButton.setStyle("-fx-background-radius: 10; -fx-font-size: 16;");
        makeSaleButton.setStyle("-fx-background-radius: 10; -fx-font-size: 16;");
        // Initially, hide the quantity text field and buttons
        quantityTextField.setVisible(false);
        setStockButton.setVisible(false);
        makeSaleButton.setVisible(false);
        resultLabel.setFont(Font.font("Times New Roman",FontWeight.BOLD,28));
        resultLabel.setTranslateY(5);
        resultLabel.setTranslateX(5);
        resultLabel.setStyle("-fx-text-fill: white;");
        med.setOnAction(e-> {
                resultLabel.setText("");
                selectedMedIndex = med.getSelectionModel().getSelectedIndex();
                String medname=med.getValue();
                vBox.getChildren().clear();
                if (selectedMedIndex >= 0) {
                    PharmacyRecord pham=InStock.getDetails(medname);
                    detailLabel.setText(pham.toString());
                    detailLabel.setFont(Font.font("Times New Roman",FontWeight.BOLD,18));
                    
                    System.out.println(pham);

                }
                
                 // Clear previous responses
                vBox.getChildren().addAll(
                    label,
                    pat,
                        med,
                        detailLabel,
                        updateStockCheckbox,
                        makeSaleCheckbox,
                        quantityTextField,
                        setStockButton,
                        makeSaleButton,
                        resultLabel,bill_button
                );
                
                 // Add ComboBox, labels, checkboxes, text field, buttons, and result label to VBox

                
            
        });

        updateStockCheckbox.setOnAction(e2-> {
            
                // Show/hide quantity text field and set stock button based on "Update Stock" checkbox
                quantityTextField.setVisible(updateStockCheckbox.isSelected());
                setStockButton.setVisible(updateStockCheckbox.isSelected());
                quantityTextField.setManaged(updateStockCheckbox.isSelected());
                setStockButton.setManaged(updateStockCheckbox.isSelected());
                makeSaleButton.setVisible(false);
            
        });

        makeSaleCheckbox.setOnAction(e3->{
            
                // Show/hide make sale button based on "Make Sale" checkbox
                makeSaleButton.setVisible(makeSaleCheckbox.isSelected());
                quantityTextField.setVisible(false);
                quantityTextField.setManaged(false);
                setStockButton.setVisible(false);
                setStockButton.setManaged(false);
            
        });

        setStockButton.setOnAction(e4-> {
            
                if (selectedMedIndex >= 0) {
                    try {
                        int quantity = Integer.parseInt(quantityTextField.getText());
                        if (updateStockCheckbox.isSelected()) {
                            InStock.updateMed(med.getValue(), quantity);
                            resultLabel.setText("Updated stock: " + InStock.getDetails(med.getValue()).getStock());
                        }
                    } catch (NumberFormatException e) {
                        resultLabel.setText("Invalid quantity. Please enter a number.");
                    }
                }
            
        });
        
        makeSaleButton.setOnAction(e5-> {
                String display="Medicines Total: \n";
                total=0;
                if (selectedMedIndex >= 0 && makeSaleCheckbox.isSelected()) {
                    InStock.updateMed(med.getValue(), -1);
                    resultLabel.setText("Updated stock: " + InStock.getDetails(med.getValue()).getStock());
                    amount[selectedMedIndex]++;
                }
                for(int i=0;i<medlist.length;i++){
                    int ind=(amount[i]*InStock.getDetails(medlist[i]).getPrice());
                    display+=medlist[i]+"\t "+ind+"\n";
                    total+=ind;
                }
                bill.setText(display+"Total: "+total);
                bill.setStyle("-fx-font-size: 20; -fx-background-color: white; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 4px;");
                final_bill=display;
            
        });
        bill_button.setOnAction(eve->{
            flag=1;
            billScene(priStage, patient);
        });
        vBox.getChildren().addAll(med);
        pane.getChildren().addAll(vBox,bill);
        root.getChildren().add(pane);
        Scene sc = new Scene(root, 800, 534);
        priStage.setScene(sc);

        
    }
    public static void billScene(Stage priStage,PatientRecords pat){
        priStage.setTitle("PDF Bill Generator");
        
        FlowPane pane=new FlowPane(Orientation.VERTICAL,10,10);
        
        bill_details="\nName : "+ pat.getName()+"\n\nAddress : "+pat.getAddress()+"\n\nPhone number : "+pat.getPhoneNo()+"\n\nConcerned Department is "+pat.getConcernedDepartment();
        if(pat.getSurgery()){
            bill_details+="\n\nSurgery Cost: 30000\n";
            if(flag==1){
                bill_details+=final_bill+"\nTotal Cost: "+(30000+total);
                
            }
        }else{
            if(pat.getInjury()){
                bill_details+="\n\nSpecialist Consultation Cost: 1000\n";
                if(flag==1){
                bill_details+=final_bill+"\nTotal Cost: "+(1000+total);
                
            }
            }
            else{
                bill_details+="\n\nGeneral Consultation Cost: 450\n";
                if(flag==1){
                bill_details+=final_bill+"\nTotal Cost: "+(450+total);
                
            }
            }
        }
        Label heading=new Label("*******BILL*******");
        heading.setFont(Font.font("Times New Roman",FontWeight.BOLD,32));
        heading.setTranslateX(40);
        Label label=new Label(bill_details);
        label.setFont(Font.font("Times New Roman",20));
        Button calculateTotalButton = new Button("Generate PDF Bill");
        calculateTotalButton.setStyle("-fx-background-radius: 10; -fx-font-size: 16;");
        calculateTotalButton.setTranslateX(100);
        calculateTotalButton.setTranslateY(10);
        pane.getChildren().addAll(heading,label,calculateTotalButton);
        
        // Event handler for generating the PDF bill
        calculateTotalButton.setOnAction(e -> {
            try {
                    generatePDFBill(heading.getText()+bill_details,pane);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            
        });
        if(flag==1){
            Scene scene = new Scene(pane, 400, 500);
            priStage.setScene(scene);
            priStage.show();
        }else{
            Scene scene = new Scene(pane, 400, 400);
            priStage.setScene(scene);
            priStage.show();
        }
    }

    private static void generatePDFBill(String items,FlowPane pane) throws IOException {
        PdfWriter writer = new PdfWriter("bill.pdf");
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Bill Details:")
                .setTextAlignment(TextAlignment.CENTER));
        document.add(new Paragraph(items));

        document.close();

        Label label=new Label("PDF of Bill generated");
        label.setFont(Font.font(16));
        label.setStyle("-fx-text-fill: green;");
        label.setTranslateX(100);
        pane.getChildren().add(label);
    }
    
}



