package controllers;

import java.util.ArrayList;

import components.Dashboard;
import components.Window;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.Assistant;
import models.DashboardModel;
import models.Doctor;
import models.Patient;
import views.AssistantCreateView;
import views.AssistantDeleteView;
import views.AssistantEditView;
import views.CreatePatientView;
import views.DashboardView;
import views.EditPatientView;
import views.PatientDeleteView;
import views.PatientDetailsView;

public class PatientDashboard extends Dashboard {

	Button saveButton;
	private static boolean flag;
	public PatientDashboard(DashboardView view, DashboardModel model) {
		super(view, model);
		getView().getSearchField().setPromptText("Search by id, first name, last name");
	}

	@Override
	public void showCreateForm() {
	     
	     Window window=new Window("Create Patient",550,700);
	     CreatePatientView view = new CreatePatientView();
	     saveButton = view.getSaveButton();
	     saveButton.setOnAction(new EventHandler<ActionEvent>() {
	         @Override
	         public void handle(ActionEvent event) {
	             Patient p = view.getPatientInfo();
	 	    	 if(p != null) {
	 	    		 getModel().create(p);
	 	    	     Alert alert = new Alert(AlertType.INFORMATION);
	     			 alert.setTitle("Create Patient " );
	     			 alert.setHeaderText(null);
	     		
	     			 alert.setContentText("Patient has been added successfully");
	     			 alert.showAndWait();
	 		    	 reloadProfiles();
	 	    	 }
	 	    	 
	 	    	 else {
	 	    		 //System.out.println("Patient null");
	 	    		 Alert alert = new Alert(AlertType.INFORMATION);
	     			 alert.setTitle("Create Patient " );
	     			 alert.setHeaderText(null);
	     		
	     			 alert.setContentText("Patient couldn't be added");
	     			 alert.showAndWait();
	 	    	 }
	         }
	     });
	     ScrollPane sp = new ScrollPane();
		 sp.setContent(view);
		 window.setContent(sp);
	     window.showAndWait();
	}

	@Override
	public void showDetails(int id) {
		Patient selectedPatient =(Patient)getModel().getProfile(id);
        Window window=new Window("Details",500,700);
        PatientDetailsView view=new PatientDetailsView(selectedPatient);
        ScrollPane sp = new ScrollPane();
        sp.setContent(view);
        window.setContent(sp);
        view.setParent(window);
        window.showAndWait();
		
	}

	@Override
    public void showEditForm(int id) {
        Patient selectedPatient =(Patient)getModel().getProfile(id);
        Window window=new Window("Edit Patient",550,700);
        EditPatientView view=new EditPatientView(selectedPatient);
        saveButton = view.getSaveButton();
	     saveButton.setOnAction(new EventHandler<ActionEvent>() {
	         @Override
	         public void handle(ActionEvent event) {
	             Patient p = view.getPatientInfo();
	 	    	 if(p != null) {
	 	    		 p.setPatientId(selectedPatient.getPatientId());
	 	    		 p.setProfileId(selectedPatient.getProfileId());
	 	    		 getModel().edit(p);
	 	    	     Alert alert = new Alert(AlertType.INFORMATION);
	     			 alert.setTitle("Edit Patient " );
	     			 alert.setHeaderText(null);
	     		
	     			 alert.setContentText("Patient has been edited successfully");
	     			 alert.showAndWait();
	 		    	 reloadProfiles();
	 	    	 }
	 	    	 
	 	    	 else {
	 	    		 //System.out.println("Patient null");
	 	    		 Alert alert = new Alert(AlertType.INFORMATION);
	     			 alert.setTitle("Edit Patient " );
	     			 alert.setHeaderText(null);
	     		
	     			 alert.setContentText("Patient couldn't be e");
	     			 alert.showAndWait();
	 	    	 }
	         }
	     });
        
        
        
        ScrollPane sp = new ScrollPane();
        sp.setContent(view);
        window.setContent(sp);
        //view.setParent(window);
        window.showAndWait();
    }

	@Override
	public void showDeleteForm(int id) {
		 flag = false;
    	 Patient selectedAssistant =(Patient)getModel().getProfile(id);
     	 Window window=new Window("Delete Patient",500,350);
    	 PatientDeleteView view= new PatientDeleteView(selectedAssistant);
         window.setContent(view);
         view.setParent(window);
         window.showAndWait();
         if(flag) {
        	 getModel().remove(selectedAssistant);
             reloadProfiles();
         }
		
	}

	@Override
	public  ArrayList<Patient> filter(String query) {
        getView().getTableData().clear();
        if(query.isEmpty())
            return getModel().loadProfiles();
        return getModel().filter(query);

    }
	
	 public static void setFlag(boolean val) {
	    	flag = val;
			
	}
	
}
