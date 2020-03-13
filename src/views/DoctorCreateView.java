package views;

import components.Validation;
import components.Window;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.Doctor;

import java.sql.Date;
import java.time.ZoneId;


public class DoctorCreateView extends GridPane {

    private Window parentStage;

    public  DoctorCreateView(Doctor d){
 
    	ZoneId defaultZoneId = ZoneId.systemDefault();
    	//Creating a GridPane container
    	GridPane grid = this;
    	 
    	grid.setPadding(new Insets(10, 10, 10, 10));
    	grid.setVgap(5);
    	grid.setHgap(5);
    	//Defining the Name text field
     
    	  // Add First Name Label
        Label firstNameLabel = new Label("First Name : ");
        grid.add(firstNameLabel, 0,1);
        
         // Add First Name Text Field
        TextField firstNameField = new TextField();
        firstNameField.setPrefHeight(30);
        grid.add(firstNameField, 1,1);
        
        firstNameField.setTextFormatter(new TextFormatter<>(change -> (change.getControlNewText().matches("([A-Za-z]*)?")) ? change : null));
        
        
  	  // Add Middle Name Label
        Label middleNameLabel = new Label("Middle Name : ");
        grid.add(middleNameLabel, 0,2);

        // Add Middle Name Text Field
        TextField middleNameField = new TextField();
        middleNameField.setPrefHeight(30);
        grid.add(middleNameField, 1,2);
        
        //middleNameField.setTextFormatter(new TextFormatter<>(change -> (change.getControlNewText().matches("([A-Z]*|[a-z]*)?")) ? change : null));
        
    	  // Add Last Name Label
        Label lastNameLabel = new Label("Last Name : ");
        grid.add(lastNameLabel, 0,3);

        // Add Last Name Text Field
        TextField lastNameField = new TextField();
        lastNameField.setPrefHeight(30);
        grid.add(lastNameField, 1,3);
        
        
  	    // Add User Name Label

        Label userNameLabel = new Label("User Name : ");
        grid.add(userNameLabel, 0,4);
        
        Label userTextLabel = new Label("");
        grid.add(userTextLabel, 1,4);
         
  	   // Add Password Label
        Label passwordLabel = new Label("Password : ");
        grid.add(passwordLabel, 0,5);

        // Add Last Name Text Field
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(30);
        grid.add(passwordField, 1,5);
        
   	   // Add ConfirmPassword Label
        Label confirmPasswordLabel = new Label("Confirm Password : ");
        grid.add(confirmPasswordLabel, 0,6);

        // Add Last Name Text Field
        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPrefHeight(30);
        grid.add(confirmPasswordField, 1,6);
        
        
    	// Add BirthDate Label
        Label birthDateLabel = new Label("BirthDate : ");
        grid.add(birthDateLabel, 0,7);
        
        // Add BirthDate Text Field
        DatePicker birthDateField = new DatePicker();
        birthDateField.setPrefHeight(30);
        grid.add(birthDateField, 1,7);
        
  	  // Add Email Label
        Label emailLabel = new Label("Email : ");
        grid.add(emailLabel, 0,8);
        
        // Add email Text Field
        TextField emailField = new TextField();
        emailField.setPrefHeight(30);
        grid.add(emailField, 1,8);
        
       
        // Add Phone Label
        Label phoneLabel = new Label("Phone : ");
        grid.add(phoneLabel, 0,9);
        
        // Add Phone Field
        TextField phoneField = new TextField();
        grid.add(phoneField, 1,9);
        
       // phoneField.setTextFormatter(new TextFormatter<>(change -> (change.getControlNewText().matches("([1-9][0-9]*)?")) ? change : null));
        
        
        // Add Address Label
        Label addressLabel = new Label("Address : ");
        grid.add(addressLabel, 0,10);
        
        // Add Address Field
        TextField address = new TextField();

        grid.add(address, 1,10);
        
        // Add Blood Type Label
        Label bloodTypeLabel = new Label("Blood Type : ");
        grid.add(bloodTypeLabel, 0,11);
        
        // Add Blood Type Field
        ComboBox bloodType = new ComboBox();
        bloodType.getItems().addAll("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");
        
        grid.add(bloodType, 1,11);
        
        // Add specialty Label
        Label specialtyLabel = new Label("Specialty : ");
        grid.add(specialtyLabel, 0,12);
        
        // Add Specialty Field
        TextField specialty = new TextField();
        grid.add(specialty, 1,12);
        
        firstNameField.textProperty().addListener((Observer, oldValue, newValue) ->
       {
    	   
    	 userTextLabel.setText(   firstNameField.getText()+"." + lastNameField.getText());
    	   
       });
        
        lastNameField.textProperty().addListener((Observer, oldValue, newValue) ->
        {
     	   String s = userTextLabel.getText().substring(0, userTextLabel.getText().indexOf('.') +1);

     	 userTextLabel.setText(  s +  lastNameField.getText());
     	   
        });
         
        birthDateField.setOnAction(b ->
        {
     	     Validation.validateConfirmPassword(passwordField, confirmPasswordField);
        });
        // Add create button
        Button createBtn = new Button("Add Doctor");
        GridPane.setHalignment(createBtn, HPos.CENTER);
        createBtn.setOnAction(e->{ 
        	if( Validation.validateConfirmPassword(passwordField, confirmPasswordField)  &&
        		Validation.validateString(firstNameField,"First Name") &&
        		Validation.validateString(middleNameField,"Middle Name") &&
        		Validation.validateString(lastNameField,"Last Name") &&
        	    Validation.validateNumber(phoneField,"Phone") &&
        	    Validation.validateEmail(emailField))
        		{
        		 d.setFirstName(firstNameField.getText());
        		 d.setMiddleName(middleNameField.getText());
        		 d.setLastName(lastNameField.getText());
        		 d.setUserName(userTextLabel.getText());
        		 d.setPassword(passwordField.getText());
        		// d.setBirthDate(Date.from(birthDateField.getValue().atStartOfDay(defaultZoneId).toInstant()));
        		 d.setBirthDate(Date.valueOf(birthDateField.getValue()));         		
        		 d.setEmail(emailField.getText());
        		 d.setPhone(phoneField.getText());
        		 d.setAddress(address.getText());
        		 d.setBloodType(bloodType.getValue().toString());
        		 d.setSpeciality(specialty.getText());
        	     d.setRole("Doctor"); 
        	     
        	     Alert alert = new Alert(AlertType.INFORMATION);
     			 alert.setTitle("Create Doctor " );
     			 alert.setHeaderText(null);
     		
     			 alert.setContentText("Doctor has been added successfully");
     			alert.showAndWait();
                    parentStage.getDashboard().reloadProfiles();
                    parentStage.close();

        		}
        	else System.out.print("No ");
        	
        });
        bloodType.setPrefWidth(210);
        birthDateField.setPrefWidth(210);
        Button close =new Button("Close");
        close.getStyleClass().add("cancel");
        createBtn.getStyleClass().add("edit");
        GridPane.setHalignment(close, HPos.RIGHT);
        HBox bx=new HBox(close,createBtn);
        bx.setSpacing(20);
        bx.setPadding(new Insets(10,0,30,0));
        grid.addRow(14,new Label(" "),bx);
        
        close.setOnAction(e->{
            parentStage.getDashboard().reloadProfiles();
           parentStage.close();
          
        });
        getStylesheets().add(getClass().getResource("/css/edit.css").toExternalForm());
     }
    
    public void setParent(Window parentStage) {
        this.parentStage = parentStage;
    }

}
