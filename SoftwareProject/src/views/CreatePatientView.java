package views;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.Database;
import components.Validation;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import models.Patient;

public class CreatePatientView  extends GridPane{
	int insertedProfileId = 0;
	Button save;
	TextField firstName, middleName, lastName, email, phone, address, pressure, temperature,
	pulse, oxygen, normalPressure, normalTemperature, normalPulse, normalOxygen, weight, height;
	DatePicker dob;
	ComboBox<String> bloodType;

	public CreatePatientView() {//(Doctor doctor, ArrayList<String> patients,ArrayList<String> surgeries){
	    int x=0,y=0,width=220;
	    setHgap(20);
	    setVgap(10);
	    setPadding(new Insets(30,30,30,30));
	    Label[] names=new Label[20],values=new Label[20];

	   // names[y]=new Label("Patient ");
	   // names[y].getStyleClass().add("name");
	  //  ChoiceBox patChoice=new ChoiceBox();
	 //   patChoice.getItems().setAll(patients);
	 //   patChoice.getSelectionModel().selectFirst();
	 //   patChoice.setPrefWidth(width);
	 //   addRow(y,names[y],patChoice);

	   // names[++y]=new Label("Surgery Type ");
	   // names[y].getStyleClass().add("name");
	   // ChoiceBox surgChoice=new ChoiceBox();
	   // surgChoice.getItems().setAll(surgeries);
	   // surgChoice.getSelectionModel().selectFirst();
	   // surgChoice.setPrefWidth(width);
	  //  addRow(y,names[y],surgChoice);
	    
	    
	    names[++y]=new Label("First Name");   //wrap all in create profile(common for all)
	    names[y].getStyleClass().add("name");
	    firstName = new TextField();
	    firstName.setPrefWidth(width);
	    addRow(y,names[y], firstName);

	    
	    names[++y]=new Label("Middle Name");
	    names[y].getStyleClass().add("name");
	    middleName = new TextField();
	    middleName.setPrefWidth(width);
	    addRow(y,names[y], middleName);
	    
	    
	    
	    names[++y]=new Label("Last Name");
	    names[y].getStyleClass().add("name");
	    lastName = new TextField();
	    firstName.setPrefWidth(width);
	    addRow(y,names[y], lastName);
	    
	    
	    
	    names[++y]=new Label("Email");
	    names[y].getStyleClass().add("name");
	    email = new TextField();
	    firstName.setPrefWidth(width);
	    addRow(y,names[y], email);
	    
	    
	    names[++y]=new Label("Phone");
	    names[y].getStyleClass().add("name");
	    phone = new TextField();
	    firstName.setPrefWidth(width);
	    addRow(y,names[y], phone);
	    	    
	    
	    names[++y]=new Label("Date of Birth");
	    names[y].getStyleClass().add("name");
	    dob = new DatePicker();
	    dob.setPrefWidth(width);
	    addRow(y,names[y], dob);
	    
	    names[++y]=new Label("Address");
	    names[y].getStyleClass().add("name");
	    address = new TextField();
	    address.setPrefWidth(width);
	    addRow(y,names[y], address);
	    
	    
	    names[++y]=new Label("Blood Type");
	    names[y].getStyleClass().add("name");
	   // TextField bloodType = new TextField();
	   // bloodType.setPrefWidth(width);
	    
	    bloodType = new ComboBox<String>();
        bloodType.getItems().addAll("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");
	    bloodType.setPrefWidth(220);
	    addRow(y,names[y], bloodType);
	    
	    
	    
	    names[++y]=new Label("Pressure");
	    names[y].getStyleClass().add("name");
	    pressure = new TextField();
	    pressure.setPrefWidth(width);
	    addRow(y,names[y], pressure);
	    
	    
	    
	    names[++y]=new Label("Temperature");
	    names[y].getStyleClass().add("name");
	    temperature = new TextField();
	    temperature.setPrefWidth(width);
	    addRow(y,names[y], temperature);
	    
	    
	    
	    names[++y]=new Label("Pulse");
	    names[y].getStyleClass().add("name");
	    pulse = new TextField();
	    pulse.setPrefWidth(width);
	    addRow(y,names[y], pulse);
	    
	    
	    
	    names[++y]=new Label("Oxygen Level");
	    names[y].getStyleClass().add("name");
	    oxygen = new TextField();
	    oxygen.setPrefWidth(width);
	    addRow(y,names[y], oxygen);
	    
	    
	    
	    names[++y]=new Label("Normal Pressure");
	    names[y].getStyleClass().add("name");
	    normalPressure = new TextField();
	    normalPressure.setPrefWidth(width);
	    addRow(y,names[y], normalPressure);
	    
	    
	    
	    names[++y]=new Label("Normal Temperature");
	    names[y].getStyleClass().add("name");
	    normalTemperature = new TextField();
	    normalTemperature.setPrefWidth(width);
	    addRow(y,names[y], normalTemperature);
	    
	    
	    
	    names[++y]=new Label("Normal Pulse");
	    names[y].getStyleClass().add("name");
	    normalPulse = new TextField();
	    normalPulse.setPrefWidth(width);
	    addRow(y,names[y], normalPulse);
	    
	    
	    
	    names[++y]=new Label("Normal Oxygen Level");
	    names[y].getStyleClass().add("name");
	    normalOxygen = new TextField();
	    normalOxygen.setPrefWidth(width);
	    addRow(y,names[y], normalOxygen);
	    
	    
	    
	    names[++y]=new Label("Weight");
	    names[y].getStyleClass().add("name");
	    weight = new TextField();
	    weight.setPrefWidth(width);
	    addRow(y,names[y], weight);
	    
	    
	    
	    names[++y]=new Label("Height");
	    names[y].getStyleClass().add("name");
	    height = new TextField();
	    height.setPrefWidth(width);
	    addRow(y,names[y], height);
	    
	    

	    save =new Button("save");
	    save.setPrefSize(100,30);
	    addRow(++y,new Label(" "),save);
	    //save.setOnAction(e->{
	    //	System.out.println("ddddd");
	    /*	String insertProfileSql = "insert into Profile (FIRSTNAME, MIDDLENAME, LASTNAME, EMAIL,"
	    			+ " PHONE, DATEOFBIRTH, ADDRESS, BLOODTYPE) values (";
	    	insertProfileSql += "'" + firstName.getText() + "',";
	    	insertProfileSql += "'" + middleName.getText() + "',";
	    	insertProfileSql += "'" + lastName.getText() + "',";
	    	insertProfileSql += "'" + email.getText() + "',";
	    	insertProfileSql += "'" + phone.getText() + "',";
	    	insertProfileSql += "'" + dob.getValue() + "',";
	    	insertProfileSql += "'" + address.getText() + "',";
	    	insertProfileSql += "'" + bloodType.valueProperty().getValue() + "')";
	    	try {
	    		Database.execute(insertProfileSql);
	    		ResultSet rs = Database.getResults("Select MAX(PROFILEID) from Profile");
	    		rs.next();
	    		insertedProfileId = rs.getInt(1);
	    	}
	    	catch(Exception e1) {
	    		e1.printStackTrace();
	    	}
	    	
	    	
	    	String insertPatientSql =  "insert into Patient (PROFILEID, PRESSURE, TEMPERATURE, PULSE, OXYGEN,"
	    			+ " NORMALPRESSURE, NORMALTEMPERATURE, NORMALPULSE, NORMALOXYGEN, "
	    			+ "WEIGHT, HEIGHT) values (";
	    	insertPatientSql += insertedProfileId + ","; 
	    	insertPatientSql += "'" + pressure.getText() + "',";
	    	insertPatientSql += Float.parseFloat(temperature.getText()) + ",";
	    	insertPatientSql += Integer.parseInt(pulse.getText()) + ",";
	    	insertPatientSql += Integer.parseInt(oxygen.getText()) + ",";
	    	insertPatientSql += "'" + normalPressure.getText() + "',";
	    	insertPatientSql += Float.parseFloat(normalTemperature.getText()) + ",";
	    	insertPatientSql += Integer.parseInt(normalPulse.getText()) + ",";
	    	insertPatientSql += Integer.parseInt(normalOxygen.getText()) + ",";
	    	insertPatientSql += Integer.parseInt(weight.getText()) + ",";
	    	insertPatientSql += Integer.parseInt(height.getText()) + ")";
	    	
	    	System.out.println(insertPatientSql);
	    	try {
	    		Database.execute(insertPatientSql);
	    	}
	    	catch(Exception e2) {
	    		e2.printStackTrace();
	    	} */
	    	
	    	
	    	
	    	
	   // });
	    getStylesheets().add(getClass().getResource("/css/createSurgery.css").toExternalForm());
	    
	}
	
	public Button getSaveButton() {
		return save;
	}
	
	public Patient getPatientInfo() {
		Patient p = null;
		
		
		
		if(Validation.validateString(firstName, "First Name")
			&& Validation.validateString(lastName, "Last Name")
			&& Validation.validateString(middleName, "Middle Name")
			&& Validation.validateString(address, "Address")
			&& Validation.validateEmail(email)
			&& Validation.validateNumber(phone, "Phone Number")) {
			
			p = new Patient();
			
			p.setFirstName(firstName.getText());
			p.setMiddleName(middleName.getText());
			p.setLastName(lastName.getText());
			p.setAddress(address.getText());
			p.setEmail(email.getText());
			p.setPhone(phone.getText());
			p.setBirthDate(Date.valueOf(dob.getValue()));
			p.setAddress(address.getText());  //?
			p.setBloodType(bloodType.valueProperty().getValue());
			p.setPressure(pressure.getText());  //?
			p.setTemperature(Float.parseFloat(temperature.getText()));  //?
			p.setPulse(Integer.parseInt(pulse.getText()));   //?
			p.setOxygen(Integer.parseInt(oxygen.getText()));  //?
			p.setNormalPressure(normalPressure.getText()); //?
			p.setTemperature(Float.parseFloat(normalTemperature.getText()));  //?
			p.setNormalPulse(Integer.parseInt(normalPulse.getText())); //?
			p.setNormalOxygen(Integer.parseInt(normalOxygen.getText())); //?
			p.setWeight(Integer.parseInt(weight.getText())); //?
			p.setHeight(Integer.parseInt(height.getText())); ///?
			
		}
		
		
	
				
		return p;
	}
	
}
