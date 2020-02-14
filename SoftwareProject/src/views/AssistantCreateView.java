package views;

import application.Database;
import components.Validation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Assistant;
import views.AssistantCreateView.DoctorCombo;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;


public class AssistantCreateView extends GridPane {

    private Stage parentStage;
    class DoctorCombo{
        String firstname;
        String lastname;
        int doctorId;
        
        DoctorCombo(String n, String a, int id){
            firstname = n;
            lastname = a;
            doctorId = id;
        }

        @Override
        public String toString() {
            return firstname.concat(" ").concat(lastname);
        }
        
        public int getDoctorId() { return doctorId; }

    }
    public  AssistantCreateView(Assistant a){
 
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
        ComboBox<String> bloodType = new ComboBox<String>();
        bloodType.getItems().addAll("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");
        
        grid.add(bloodType, 1,11);
        
        // Add doctor Label
        Label doctorLabel = new Label("Doctor : ");
        grid.add(doctorLabel, 0,12);
        
        // Add Doctor Field
        ComboBox <DoctorCombo> doctor = new ComboBox<DoctorCombo>();
        ObservableList<DoctorCombo> listacombo= FXCollections.observableArrayList();
        ResultSet rs = Database.getResults("Select * from " +
                                            "DOCTOR join [User] on DOCTOR.USERID= [USER].USERID " +
                                            "join Profile   on Profile.PROFILEID = [USER].PROFILEID ");
        try {
          if (rs != null)
        	  
        	  
           while ( rs.next() ) 
           { 
        	  DoctorCombo dc = new DoctorCombo(rs.getString("FIRSTNAME"),rs.getString("LASTNAME"),rs.getInt("DoctorId"));
        	
        	  listacombo.add(dc);
            } 
         }catch (SQLException e) 
            {
               e.printStackTrace();
            }
                                  
        doctor.setItems(listacombo); 
        grid.add(doctor, 1,12);
        
        // Add CONTRACTTYPE Label
        Label contracttypeLabel = new Label("Contract type : ");
        grid.add(contracttypeLabel, 0,13);
                
        // Add CONTRACTTYPE Field 
        ComboBox<String> contracttype = new ComboBox<String>();
        contracttype.getItems().addAll("FULL TIME", "PART TIME");             
        grid.add(contracttype, 1,13);
        
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
        Button createBtn = new Button("Add Assistant");
        GridPane.setHalignment(createBtn, HPos.CENTER);
        grid.add(createBtn, 0,14);
        createBtn.setOnAction(e->{ 
        	if( Validation.validateConfirmPassword(passwordField, confirmPasswordField)  &&
        		Validation.validateString(firstNameField,"First Name") &&
        		Validation.validateString(middleNameField,"Middle Name") &&
        		Validation.validateString(lastNameField,"Last Name") &&
        	    Validation.validateNumber(phoneField,"Phone") &&
        	    Validation.validateEmail(emailField))
        		{
        		 a.setFirstName(firstNameField.getText());
        		 a.setMiddleName(middleNameField.getText());
        		 a.setLastName(lastNameField.getText());
        		 a.setUserName(userTextLabel.getText());
        		 a.setPassword(passwordField.getText());
        		// d.setBirthDate(Date.from(birthDateField.getValue().atStartOfDay(defaultZoneId).toInstant()));
        		 a.setBirthDate(Date.valueOf(birthDateField.getValue()));         		
        		 a.setEmail(emailField.getText());
        		 a.setPhone(phoneField.getText());
        		 a.setAddress(address.getText());
        		 a.setBloodType(bloodType.getValue().toString());
           		 a.setDoctorId(doctor.getValue().getDoctorId());
        		 a.setContractType(contracttype.getValue().toString());
        	     a.setRole("Assistant"); 
        	     
        	     Alert alert = new Alert(AlertType.INFORMATION);
     			 alert.setTitle("Create Assistant " );
     			 alert.setHeaderText(null);
     		
     			 alert.setContentText("Assistant has been added successfully");
     			 alert.showAndWait();
        	     
        	     Stage stage = (Stage) this.getScene().getWindow();
                 stage.close();
        		
        		}
        	else System.out.print("No ");
        	
        });
        
        Button close =new Button("Close");
        GridPane.setHalignment(close, HPos.RIGHT);
        grid.add(close, 2,14);
        
        close.setOnAction(e->{ 
           Stage stage = (Stage) this.getScene().getWindow();
           stage.close();
          
        });      
      
     }
    
    public void setParent(Stage parentStage) {
        this.parentStage = parentStage;
    }

}
