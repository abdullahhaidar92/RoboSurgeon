package controllers;

import models.Assistant;
import models.DashboardModel;
import models.Doctor;
import views.*;
import components.Window;
import components.Dashboard;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class DoctorsDashboard extends Dashboard {


	private static boolean flag;
    public DoctorsDashboard(DashboardView view, DashboardModel model) {
        super(view, model);
        getView().getSearchField().setPromptText("Search by id, first name, last name or speciality ");

    }

    @Override
    public void showCreateForm() {
        Window window=new Window("Add new doctor",500,650);
        // content Vbox
        Doctor d = new Doctor();
        DoctorCreateView view=new DoctorCreateView(d);
        view.setParent(window);
        window.setParentStage(this);
        window.setContent(view);
        window.showAndWait();
        getModel().create(d);
        reloadProfiles();

    }


    @Override
    public void showDetails(int id) {
        Doctor selectedDoctor =(Doctor)getModel().getProfile(id);
        Window window=new Window("Details",430,480);
        DoctorDetailsView view=new DoctorDetailsView(selectedDoctor);
        window.setContent(view);
        view.setParent(window);
        window.showAndWait();
    }

    @Override
    public void showEditForm(int id) {
        Doctor selectedDoctor =(Doctor)getModel().getProfile(id);
        Window window=new Window("Edit Doctor",500,500);
        DoctorEditView view=new DoctorEditView(selectedDoctor);
        window.setContent(view);
        view.setParent(window);
        window.showAndWait();
        getModel().edit(selectedDoctor);
        reloadProfiles();
    }


    @Override
    public void showDeleteForm(int id) {
    	flag = false;
    	Doctor selectedDr =(Doctor)getModel().getProfile(id);
    	 Window window=new Window("Delete Doctor",500,350);
    	 DoctorDeleteView view= new DoctorDeleteView(selectedDr);
         window.setContent(view);
         view.setParent(window);
         window.showAndWait();
         if(flag) {
        	 getModel().remove(selectedDr);
             reloadProfiles();
         }

    }

    @Override
    public  ArrayList<Doctor> filter(String query) {
        getView().getTableData().clear();
        if(query.isEmpty())
            return getModel().loadProfiles();
        return getModel().filter(query);

    }

    public static void setFlag(boolean val) {
    	flag = val;
    }
}
