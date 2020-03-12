package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import application.Database;
import components.Window;
import javafx.scene.control.ScrollPane;
import models.*;
import views.CreateSurgeryView;
import views.DashboardView;
import views.DeleteOperationView;
import views.OperationDetailsView;

public class OperationDashboard extends Dashboard {
	private ArrayList<Patient> patients;
	private ArrayList<Surgery> surgeries;
	private ArrayList<Machine> machines;
	private CreateSurgeryView createSurgeryView;
	DeleteOperationView deleteOperationView;
	public OperationDashboard(DashboardView view, DashboardModel model) {
		super(view, model);
		getView().getSearchField().setPromptText("Search by id, first name, last name or speciality ");
	}

	@Override
	public void showCreateForm() {
			Window window=new Window("Create",530,455);
			patients=getModel().getPatients();
		    ArrayList<String > patientnames=new ArrayList();
			for(int i=0;i<patients.size();i++)
			patientnames.add(patients.get(i).getFirstName()+" "
					+patients.get(i).getLastName()+" ( "+patients.get(i).getPatientId()+ " ) ");
			 surgeries=getModel().getSurgeries();
			ArrayList<String> surgerynames=new ArrayList<>();
			for(int i=0;i<surgeries.size();i++)
				surgerynames.add(surgeries.get(i).getName());
			machines=getModel().getMachines();
			ArrayList<String> machineNames=new ArrayList<>();
			for(int i=0;i<machines.size();i++)
			machineNames.add(""+machines.get(i).getSerialNumber());
			createSurgeryView=new CreateSurgeryView(patientnames,surgerynames,machineNames);
		createSurgeryView.getSave().setOnAction(e->{
			create();
		});
			window.setContent(createSurgeryView);
		createSurgeryView.setParent(window);
		window.showAndWait();

		
	}

	public void create(){
		createSurgeryView.getErrors().clear();

		Operation o=new Operation();
		o.setSurgeryId(surgeries.get(createSurgeryView.getSurgChoice().getSelectionModel().getSelectedIndex()).getId());
		o.setMachineId(machines.get(createSurgeryView.getMachChoice().getSelectionModel().getSelectedIndex()).getId());
		o.setPatientId(patients.get(createSurgeryView.getPatChoice().getSelectionModel().getSelectedIndex()).getPatientId());
		String str=createSurgeryView.getDatePicker().getValue()+" "+createSurgeryView.getTimeField().getValue();
		try {
			o.setAppointmentDate(Timestamp.valueOf(str));
		}catch (Exception e){
			createSurgeryView.getErrors().add("Invalid Datetime Value");
			createSurgeryView.showAlert(false,"Create");
			return;
		}
		o.setRegistrationDate(Timestamp.from(Instant.now()));

		if(createSurgeryView.getTimerVal().getText().isEmpty()){
			createSurgeryView.getErrors().add("Invalid Emission Duration Value");
			createSurgeryView.showAlert(false,"Create");
			return;
		}


		if(createSurgeryView.getRadiationVal().getText().isEmpty()){
			createSurgeryView.getErrors().add("Invalid Radiation Value");
			createSurgeryView.showAlert(false,"Create");
			return;
		}


        o.setMaxRadiationVal(Integer.parseInt(createSurgeryView.getRadiationVal().getText()));
        o.setMaxTimerVal(Integer.parseInt(createSurgeryView.getTimerVal().getText()));

		createSurgeryView.showAlert(getModel().create(o),"Create");
		reloadProfiles();

	}
	public void update(Operation o){
		createSurgeryView.getErrors().clear();
		o.setSurgeryId(surgeries.get(createSurgeryView.getSurgChoice().getSelectionModel().getSelectedIndex()).getId());
		o.setMachineId(machines.get(createSurgeryView.getMachChoice().getSelectionModel().getSelectedIndex()).getId());
		o.setPatientId(patients.get(createSurgeryView.getPatChoice().getSelectionModel().getSelectedIndex()).getPatientId());
		String str=createSurgeryView.getDatePicker().getValue()+" "+createSurgeryView.getTimeField().getValue();

		try {
			o.setAppointmentDate(Timestamp.valueOf(str));
		}catch (Exception e){
			createSurgeryView.getErrors().add("Invalid Datetime Value");
			createSurgeryView.showAlert(false,"Update");
			return;
		}

		o.setAppointmentDate(Timestamp.valueOf(str));
		o.setRegistrationDate(Timestamp.from(Instant.now()));


		if(createSurgeryView.getTimerVal().getText().isEmpty()){
			createSurgeryView.getErrors().add("Invalid Emission Duration Value");
			createSurgeryView.showAlert(false,"Update");
			return;
		}
		if(createSurgeryView.getRadiationVal().getText().isEmpty()){
			createSurgeryView.getErrors().add("Invalid Radiation Value");
			createSurgeryView.showAlert(false,"Update");
			return;
		}

		o.setMaxRadiationVal(Integer.parseInt(createSurgeryView.getRadiationVal().getText()));
		o.setMaxTimerVal(Integer.parseInt(createSurgeryView.getTimerVal().getText()));
		createSurgeryView.showAlert(getModel().update(o),"Update");
		reloadProfiles();

	}
	public void delete(Operation o){
		deleteOperationView.showAlert(getModel().delete(o),"Delete");
		reloadProfiles();

	}
	@Override
	public void showDetails(int id) {
		Operation selectedOperation =(Operation)getModel().getProfile(id);
        Window window=new Window("Details",600,560);
        OperationDetailsView view=new OperationDetailsView(selectedOperation);
        ScrollPane sp = new ScrollPane();
        sp.setContent(view);
        window.setContent(sp);
        view.setParent(window);
        window.showAndWait();
		
	}

	@Override
	public void showEditForm(int id) {
		int l=0,j=0,k=0;
		Operation o =(Operation)getModel().getProfile(id);
		Window window=new Window("Edit Operation",530,455);
		patients=getModel().getPatients();
		ArrayList<String > patientnames=new ArrayList();
		for(int i=0;i<patients.size();i++)
			if(patients.get(i).getPatientId()==o.getPatientId()) {
				patientnames.add(patients.get(i).getFirstName() + " "
						+ patients.get(i).getLastName() + " ( " + patients.get(i).getPatientId() + " ) ");
				break;
			}

		surgeries=getModel().getSurgeries();
		ArrayList<String> surgerynames=new ArrayList<>();
		for(int i=0;i<surgeries.size();i++) {
			surgerynames.add(surgeries.get(i).getName());
			if(surgeries.get(i).getId()==o.getSurgeryId())
				l=i;
		}
		machines=getModel().getMachines();
		ArrayList<String> machineNames=new ArrayList<>();
		for(int i=0;i<machines.size();i++) {
			machineNames.add("" + machines.get(i).getSerialNumber());
			if(machines.get(i).getId()==o.getMachineId())
				k=i;
		}
		createSurgeryView=new CreateSurgeryView(patientnames,surgerynames,machineNames);
		createSurgeryView.getPatChoice().getSelectionModel().select(j);
		createSurgeryView.getSurgChoice().getSelectionModel().select(l);
		createSurgeryView.getMachChoice().getSelectionModel().select(k);
		LocalDate date=LocalDate.parse(o.getAppointmentDate().substring(0,10));
		createSurgeryView.getDatePicker().setValue(date);
		createSurgeryView.getDatePicker().setDisable(true);
		createSurgeryView.getTimerVal().setText(o.getMaxTimerVal()+"");
		createSurgeryView.getRadiationVal().setText(o.getMaxRadiationVal()+"");
		Date date1=null;
		try {
			date1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(o.getAppointmentDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		GregorianCalendar calendar=new GregorianCalendar();
		if(date1!=null) {
			calendar.setTime(date1);
			createSurgeryView.getTimeField().setHours(calendar.get(Calendar.HOUR));
			createSurgeryView.getTimeField().setMinutes(calendar.get(Calendar.MINUTE));
		}
		createSurgeryView.getSave().setOnAction(e->{
			update(o);
		});
		createSurgeryView.getTimeField().setDisable(true);
		window.setContent(createSurgeryView);
		createSurgeryView.setParent(window);
		window.showAndWait();
		
	}

	@Override
	public void showDeleteForm(int id) {
		Operation selectedOperation =(Operation)getModel().getProfile(id);
		Window window=new Window("Delete",570,320);
		deleteOperationView=new DeleteOperationView(selectedOperation);
		deleteOperationView.getConfirmDelete().setOnAction(e->{
				delete(selectedOperation);
		});
		ScrollPane sp = new ScrollPane();
		sp.setContent(deleteOperationView);
		window.setContent(sp);
		deleteOperationView.setParent(window);
		window.showAndWait();
		
	}

	@Override
	public ArrayList<Operation> filter(String query) {
		getView().getTableData().clear();
        if(query.isEmpty())
            return getModel().loadProfiles();
        return getModel().filter(query);
	}

	@Override
	public OperationDashboardModel getModel() {
		return (OperationDashboardModel)super.getModel();
	}

	@Override
	public  void launch(int id){

	}

}
