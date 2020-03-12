package controllers;

import components.Window;
import models.Assistant;
import models.DashboardModel;
import views.DashboardView;
import views.AssistantEditView;
import views.AssitantDetailsView;
import views.AssistantCreateView;
import views.AssistantDeleteView;

import java.util.ArrayList;

public class AssisstantDashboard extends Dashboard {

	private static boolean flag;
    public AssisstantDashboard(DashboardView view, DashboardModel model) {
        super(view, model);
        getView().getSearchField().setPromptText("Search by id, first name, last name or contract type");
    }


    @Override
    public void showCreateForm() {
        Window window=new Window("Add new asisstant",500,570);
        Assistant a = new Assistant();
        AssistantCreateView view=new AssistantCreateView(a);
        window.setContent(view);
        window.showAndWait();
        getModel().create(a);
        reloadProfiles();

    }

    @Override
    public void showDetails(int id) {
    	 Assistant selectedAssistant =(Assistant)getModel().getProfile(id);
         Window window=new Window("Details",450,500);
         AssitantDetailsView view=new AssitantDetailsView(selectedAssistant);
         window.setContent(view);
         view.setParent(window);
         window.showAndWait();

    }

    @Override
    public void showEditForm(int id) {
        Assistant selectedAssistant =(Assistant)getModel().getProfile(id);
        Window window=new Window("Edit Assistant",500,550);
        AssistantEditView view=new AssistantEditView(selectedAssistant);
        window.setContent(view);
        view.setParent(window);
        window.showAndWait();
        getModel().edit(selectedAssistant);
        reloadProfiles();
    }


    @Override
    public void showDeleteForm(int id) {
    	flag = false;
    	Assistant selectedAssistant =(Assistant)getModel().getProfile(id);
    	 Window window=new Window("Delete Assistant",500,350);
    	 AssistantDeleteView view= new AssistantDeleteView(selectedAssistant);
         window.setContent(view);
         view.setParent(window);
         window.showAndWait();
         if(flag) {
        	 getModel().remove(selectedAssistant);
             reloadProfiles();
         }
    }

    @Override
    public  ArrayList<Assistant> filter(String query) {
        getView().getTableData().clear();
        if(query.isEmpty())
            return getModel().loadProfiles();
        return getModel().filter(query);

    }
    
    public static void setFlag(boolean val) {
    	flag = val;
		
	}
}
