package views;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import application.Database;
import components.NumberField;
import components.Validation;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Patient;

public class EditPatientView  extends GridPane{
	int insertedProfileId = 0;
	Button save;
	TextField firstName, middleName, lastName, email, phone, address, pressure, temperature,
			pulse, oxygen, normalPressure, normalTemperature, normalPulse, normalOxygen, weight, height;
	DatePicker dob;
	ComboBox<String> bloodType;
	ArrayList<String> errors=new ArrayList();
	private Stage parentStage;

	public EditPatientView(Patient p) {
		int x=0,y=0,width=220;
		setHgap(20);
		setVgap(10);
		setPadding(new Insets(30,30,30,30));
		Label[] names=new Label[20],values=new Label[20];


		names[++y]=new Label("First Name");
		names[y].getStyleClass().add("name");
		firstName = new TextField();
		firstName.setPrefWidth(width);
		firstName.setText(p.getFirstName());
		addRow(y,names[y], firstName);


		names[++y]=new Label("Middle Name");
		names[y].getStyleClass().add("name");
		middleName = new TextField();
		middleName.setPrefWidth(width);
		middleName.setText(p.getMiddleName());
		addRow(y,names[y], middleName);



		names[++y]=new Label("Last Name");
		names[y].getStyleClass().add("name");
		lastName = new TextField();
		lastName.setPrefWidth(width);
		lastName.setText(p.getLastName());
		addRow(y,names[y], lastName);



		names[++y]=new Label("Email");
		names[y].getStyleClass().add("name");
		email = new TextField();
		email.setPrefWidth(width);
		email.setText(p.getEmail());
		addRow(y,names[y], email);


		names[++y]=new Label("Phone");
		names[y].getStyleClass().add("name");
		phone = new TextField();
		phone.setPrefWidth(width);
		phone.setText(p.getPhone());
		addRow(y,names[y], phone);



		names[++y]=new Label("Date of Birth");
		names[y].getStyleClass().add("name");
		dob = new DatePicker();
		dob.setPrefWidth(width);
		dob.setValue(p.getBirthDate().toLocalDate());
		addRow(y,names[y], dob);

		names[++y]=new Label("Address");
		names[y].getStyleClass().add("name");
		address = new TextField();
		address.setPrefWidth(width);
		address.setText(p.getAddress());
		addRow(y,names[y], address);


		names[++y]=new Label("Blood Type");
		names[y].getStyleClass().add("name");
		bloodType = new ComboBox<String>();
		bloodType.getItems().addAll("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");
		bloodType.setValue(p.getBloodType());
		bloodType.setPrefWidth(220);
		addRow(y,names[y], bloodType);


		names[++y]=new Label("Pressure");
		names[y].getStyleClass().add("name");
		pressure = new NumberField();
		pressure.setPrefWidth(width);
		pressure.setText(p.getPressure());
		addRow(y,names[y], pressure);



		names[++y]=new Label("Temperature");
		names[y].getStyleClass().add("name");
		temperature = new NumberField();
		temperature.setPrefWidth(width);
		temperature.setText(Float.toString(p.getTemperature()));
		addRow(y,names[y], temperature);



		names[++y]=new Label("Pulse");
		names[y].getStyleClass().add("name");
		pulse = new NumberField();
		pulse.setPrefWidth(width);
		pulse.setText(Integer.toString(p.getPulse()));
		addRow(y,names[y], pulse);



		names[++y]=new Label("Oxygen");
		names[y].getStyleClass().add("name");
		oxygen = new NumberField();
		oxygen.setPrefWidth(width);
		oxygen.setText(Integer.toString(p.getOxygen()));
		addRow(y,names[y], oxygen);



		names[++y]=new Label("Normal Pressure");
		names[y].getStyleClass().add("name");
		normalPressure = new NumberField();
		normalPressure.setPrefWidth(width);
		normalPressure.setText(p.getNormalPressure());
		addRow(y,names[y], normalPressure);



		names[++y]=new Label("Normal Temperature");
		names[y].getStyleClass().add("name");
		normalTemperature = new NumberField();
		normalTemperature.setPrefWidth(width);
		normalTemperature.setText(Float.toString(p.getNormalTemperature()));
		addRow(y,names[y], normalTemperature);



		names[++y]=new Label("Normal Pulse");
		names[y].getStyleClass().add("name");
		normalPulse = new NumberField();
		normalPulse.setPrefWidth(width);
		normalPulse.setText(Integer.toString(p.getNormalPulse()));
		addRow(y,names[y], normalPulse);



		names[++y]=new Label("Normal Oxygen");
		names[y].getStyleClass().add("name");
		normalOxygen = new NumberField();
		normalOxygen.setPrefWidth(width);
		normalOxygen.setText(Integer.toString(p.getNormalOxygen()));
		addRow(y,names[y], normalOxygen);



		names[++y]=new Label("Weight");
		names[y].getStyleClass().add("name");
		weight = new NumberField();
		weight.setPrefWidth(width);
		weight.setText(Integer.toString(p.getWeight()));
		addRow(y,names[y], weight);



		names[++y]=new Label("Height");
		names[y].getStyleClass().add("name");
		height = new NumberField();
		height.setPrefWidth(width);
		height.setText(Integer.toString(p.getHeight()));
		addRow(y,names[y], height);



		save =new Button("save");
		save.setPrefSize(100,30);
		addRow(++y,new Label(" "),save);
		getStylesheets().add(getClass().getResource("/css/createSurgery.css").toExternalForm());

	}

	public Button getSaveButton() {
		return save;
	}

	public Patient getPatientInfo() {
		Patient p = null;
		errors.clear();
		boolean flag=true;

		if(bloodType.valueProperty().getValue()==null) {
			errors.add("Blood Type Field Can't Be Empty");
			flag=false;
		}

		if(normalOxygen.getText().isEmpty()) {
			errors.add("Oxygen Level Field Can't Be Empty");
			flag=false;
		}
		if(normalPressure.getText().isEmpty()) {
			errors.add("Pressure Field Can't Be Empty");
			flag=false;
		}
		if(normalPulse.getText().isEmpty()) {
			errors.add("Pulse Field Can't Be Empty");
			flag=false;
		}
		if(normalTemperature.getText().isEmpty()) {
			errors.add("Temperature Level Field Can't Be Empty");
			flag=false;
		}
		if(weight.getText().isEmpty()) {
			errors.add("Weight Level Field Can't Be Empty");
			flag=false;
		}

		if(height.getText().isEmpty()) {
			errors.add("height Level Field Can't Be Empty");
			flag=false;
		}

		if(Validation.validateString(firstName, "First Name")
				&& Validation.validateString(lastName, "Last Name")
				&& Validation.validateString(middleName, "Middle Name")
				&& Validation.validateString(address, "Address")
				&& Validation.validateEmail(email)
				&& Validation.validateNumber(phone, "Phone Number")
				&& flag) {

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

		if(!flag){
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Validate Value" );
			alert.setHeaderText(null);

			String err="";
			for(String e:errors)
				err+=e+"\n";

			alert.setContentText(err);
			alert.showAndWait();
		}

		return p;
	}

	public void setParent(Stage parentStage) {
		this.parentStage = parentStage;
	}

	public Stage getParentStage() {
		return parentStage;
	}

}