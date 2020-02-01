package controllers;

import models.DashboardModel;
import models.Doctor;
import views.CreateSurgeryView;
import views.DashboardView;
import views.DoctorDetailsView;
import components.Window;
import components.Dashboard;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class DoctorsDashboard extends Dashboard {



    public DoctorsDashboard(DashboardView view, DashboardModel model) {
        super(view, model);
        getView().getSearchField().setPromptText("Search by id, first name, last name or speciality ");

    }

    @Override
    public void showCreateForm() {
        Window window=new Window("Create",500,300);
        Doctor doc=(Doctor)getModel().getProfile(0);
        ArrayList<String > patients=new ArrayList();
        patients.add("Abdullah Haidar ( 1 )");
        patients.add("Hadi Jaidar ( 2 )");
        patients.add("Noura Joudeih ( 3 )");
        patients.add("Abdullah Haidar ( 4 )");
        patients.add("Hadi Jaidar ( 5 )");
        patients.add("Noura Joudeih ( 6 )");
        ArrayList<String > surgeries=new ArrayList();
        surgeries.add("Left Brain Surgery 1");
        surgeries.add("Right Brain Surgery 2");
        surgeries.add("Left Brain Surgery 3");
        surgeries.add("Right Brain Surgery 4");
        window.setContent(new CreateSurgeryView(doc,patients,surgeries));
        window.showAndWait();

    }

    @Override
    public void showDetails(int id) {
        Doctor selectedDoctor =(Doctor)getModel().getProfile(id);
        Window window=new Window("Details",400,500);
        DoctorDetailsView view=new DoctorDetailsView(selectedDoctor);
        window.setContent(view);
        view.setParent(window);
        window.showAndWait();
    }

    @Override
    public void showEditForm(int id) {
        Window window=new Window("Edit",500,400);
        window.setContent(new VBox(new Text("Doctor : " +id)));
        window.showAndWait();
    }

    @Override
    public void showDeleteForm(int id) {
        Window window=new Window("Delete",500,400);
        window.setContent(new VBox(new Text("Doctor : " +id)));
        window.showAndWait();

    }

    @Override
    public  ArrayList<Doctor> filter(String query) {
        getView().getTableData().clear();
        if(query.isEmpty())
            return getModel().loadProfiles();
        return getModel().filter(query);

    }


}
