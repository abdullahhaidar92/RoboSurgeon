package components;

import models.DashboardModel;
import models.Profile;
import views.DashboardView;

import java.util.ArrayList;

public abstract class Dashboard {


    private DashboardView view;
    private DashboardModel model;

    public Dashboard(DashboardView view, DashboardModel model) {
        this.view = view;
        this.model = model;
        this.view.getTableData().addAll(model.getProfiles());
        this.view.setController(this);
        this.view.getCreateButton().setOnAction(e -> {
            showCreateForm();
        });

        this.view.getSearchField().setOnAction(e->{

                getModel().setProfiles(filter(getView().getSearchField().getText()));
                getView().getTableData().addAll(getModel().getProfiles());
        });
    }

    public DashboardView getView() {
        return view;
    }

    public DashboardModel getModel() {
        return model;
    }

    public void enableCreate() {
        view.enableCreate();
    }

    public void enableDetails() {
        view.enableDetails();
    }

    public void enableEdit() {
        view.enableEdit();
    }

    public void enableDelete() {
        view.enableDelete();
    }

    public void addActionButtons() {
        view.addActionButtons();
    }

    public void reloadProfiles(){
        getModel().setProfiles(model.loadProfiles());
        view.getTableData().setAll(getModel().getProfiles());
    }

    public abstract void showCreateForm();

    public abstract void showDetails(int id);

    public abstract void showEditForm(int id);

    public abstract void showDeleteForm(int id);

    public abstract ArrayList<?> filter(String query);

}
