package views;

import components.Validation;
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


public class DoctorEditView extends GridPane {

    private Stage parentStage;
    
    public  DoctorEditView(Doctor d){
 
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
        firstNameField.setText(d.getFirstName().trim());
        firstNameField.setTextFormatter(new TextFormatter<>(change -> (change.getControlNewText().matches("([A-Za-z]*)?")) ? change : null));
        
        
        
  	  // Add Middle Name Label
        Label middleNameLabel = new Label("Middle Name : ");
        grid.add(middleNameLabel, 0,2);

        // Add Middle Name Text Field
        TextField middleNameField = new TextField();
        middleNameField.setPrefHeight(30);
        grid.add(middleNameField, 1,2);
        middleNameField.setText(d.getMiddleName().trim());
        //middleNameField.setTextFormatter(new TextFormatter<>(change -> (change.getControlNewText().matches("([A-Z]*|[a-z]*)?")) ? change : null));
        
    	  // Add Last Name Label
        Label lastNameLabel = new Label("Last Name : ");
        grid.add(lastNameLabel, 0,3);

        // Add Last Name Text Field
        TextField lastNameField = new TextField();
        lastNameField.setPrefHeight(30);
        grid.add(lastNameField, 1,3);
        lastNameField.setText(d.getLastName().trim());
        
  	    // Add User Name Label

        Label userNameLabel = new Label("User Name : ");
        grid.add(userNameLabel, 0,4);
        
        Label userTextLabel = new Label("");
        grid.add(userTextLabel, 1,4);
        userTextLabel.setText(d.getUserName());
    
        
    	// Add BirthDate Label
        Label birthDateLabel = new Label("BirthDate : ");
        grid.add(birthDateLabel, 0,7);
        
        // Add BirthDate Text Field
        DatePicker birthDateField = new DatePicker();
        birthDateField.setPrefHeight(30);
        grid.add(birthDateField, 1,7);
        birthDateField.setValue(d.getBirthDate().toLocalDate());
        
  	  // Add Email Label
        Label emailLabel = new Label("Email : ");
        grid.add(emailLabel, 0,8);
        
        // Add email Text Field
        TextField emailField = new TextField();
        emailField.setPrefHeight(30);
        grid.add(emailField, 1,8);
        emailField.setText(d.getEmail().trim());
       
        // Add Phone Label
        Label phoneLabel = new Label("Phone : ");
        grid.add(phoneLabel, 0,9);
        
        // Add Phone Field
        TextField phoneField = new TextField();
        grid.add(phoneField, 1,9);
        phoneField.setText(d.getPhone().trim());
       // phoneField.setTextFormatter(new TextFormatter<>(change -> (change.getControlNewText().matches("([1-9][0-9]*)?")) ? change : null));
        
        
        // Add Address Label
        Label addressLabel = new Label("Address : ");
        grid.add(addressLabel, 0,10);
        
        // Add Address Field
        TextField address = new TextField();
        grid.add(address, 1,10);
        address.setText(d.getAddress());
        
        // Add Blood Type Label
        Label bloodTypeLabel = new Label("Blood Type : ");
        grid.add(bloodTypeLabel, 0,11);
        
        // Add Blood Type Field
        ComboBox bloodType = new ComboBox();
        bloodType.getItems().addAll("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");

        grid.add(bloodType, 1,11);
        bloodType.setValue(d.getBloodType().trim());
      
        // Add specialty Label
        Label specialtyLabel = new Label("Specialty : ");
        grid.add(specialtyLabel, 0,12);
        
        // Add Specialty Field
        TextField specialty = new TextField();
        grid.add(specialty, 1,12);
        specialty.setText(d.getSpeciality());
        
        firstNameField.textProperty().addListener((Observer, oldValue, newValue) ->
       {
    	   
    	 userTextLabel.setText(   firstNameField.getText()+"." + lastNameField.getText());
    	   
       });
        
        lastNameField.textProperty().addListener((Observer, oldValue, newValue) ->
        {
     	   String s = userTextLabel.getText().substring(0, userTextLabel.getText().indexOf('.') +1);

     	 userTextLabel.setText(  s +  lastNameField.getText());
     	   
        });
         
       
        // Add create button
        Button editBtn = new Button("Edit Doctor");
        GridPane.setHalignment(editBtn, HPos.CENTER);
        HBox box=new HBox();
        grid.add(box, 1,14);
        box.setSpacing(20);
        box.setPadding(new Insets(10,0,30,0));
        editBtn.getStyleClass().add("edit");

        editBtn.setOnAction(e->{
        	if(
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
        		// d.setBirthDate(Date.from(birthDateField.getValue().atStartOfDay(defaultZoneId).toInstant()));
        		 d.setBirthDate(Date.valueOf(birthDateField.getValue()));         		
        		 d.setEmail(emailField.getText());
        		 d.setPhone(phoneField.getText());
        		 d.setAddress(address.getText());
        		 d.setBloodType(bloodType.getValue().toString());
        		 d.setSpeciality(specialty.getText());
        	     d.setRole("Doctor"); 
        	     
        	     Alert alert = new Alert(AlertType.INFORMATION);
     			 alert.setTitle("Edit Doctor " );
     			 alert.setHeaderText(null);
     		
     			 alert.setContentText("Doctor " + firstNameField.getText() + " " + lastNameField.getText() +" has been edited successfully");
     			 alert.showAndWait();
        	     
        	     Stage stage = (Stage) this.getScene().getWindow();
                 stage.close();
        		
        		}
        	else System.out.print("No ");
        	
        });
        
        Button close =new Button("Cancel");
        GridPane.setHalignment(close, HPos.RIGHT);

        close.getStyleClass().add("cancel");
        close.setOnAction(e->{ 
           Stage stage = (Stage) this.getScene().getWindow();
           stage.close();
          
        });
        box.getChildren().addAll(close,editBtn);

        getStylesheets().add(getClass().getResource("/css/edit.css").toExternalForm());

    }
    public void setParent(Stage parentStage) {
        this.parentStage = parentStage;
    }

}
