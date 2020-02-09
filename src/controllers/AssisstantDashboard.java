package controllers;

import components.Dashboard;
import components.Window;
import models.Assistant;
import models.DashboardModel;
import models.Doctor;
import models.Profile;
import views.DashboardView;
import views.AssistantEditView;
import views.AssistantCreateView;

import java.util.ArrayList;

public class AssisstantDashboard extends Dashboard {

    public AssisstantDashboard(DashboardView view, DashboardModel model) {
        super(view, model);
    }


    @Override
    public void showCreateForm() {
        Window window=new Window("Add new asisstant",500,500);
        // content Vbox
        Assistant a = new Assistant();
        AssistantCreateView view=new AssistantCreateView(a);
        window.setContent(view);
        window.showAndWait();
        getModel().create(a);
        reloadProfiles();

    }

    @Override
    public void showDetails(int id) {

    }

    @Override
    public void showEditForm(int id) {
        Assistant selectedAssistant =(Assistant)getModel().getProfile(id);
        Window window=new Window("Edit Assistant",500,500);
        AssistantEditView view=new AssistantEditView(selectedAssistant);
        window.setContent(view);
        view.setParent(window);
        window.showAndWait();
        getModel().edit(selectedAssistant);
        reloadProfiles();
    }


    @Override
    public void showDeleteForm(int id) {

    }

    @Override
    public  ArrayList<Assistant> filter(String query) {
        getView().getTableData().clear();
        if(query.isEmpty())
            return getModel().loadProfiles();
        return getModel().filter(query);

    }
}
