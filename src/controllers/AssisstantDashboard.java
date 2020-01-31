package controllers;

import components.Dashboard;
import models.Assistant;
import models.DashboardModel;
import models.Profile;
import views.DashboardView;

import java.util.ArrayList;

public class AssisstantDashboard extends Dashboard {

    public AssisstantDashboard(DashboardView view, DashboardModel model) {
        super(view, model);
    }

    @Override
    public void showCreateForm() {

    }

    @Override
    public void showDetails(int id) {

    }

    @Override
    public void showEditForm(int id) {

    }

    @Override
    public void showDeleteForm(int id) {

    }

    @Override
    public ArrayList<Assistant> filter(String query) {
        return null;
    }
}
