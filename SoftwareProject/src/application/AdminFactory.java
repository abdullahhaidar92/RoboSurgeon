package application;

import controllers.AssisstantDashboard;
import controllers.DoctorsDashboard;
import models.*;
import views.AssisstantDashboardView;
import views.DashboardView;
import views.DoctorsDashboardView;
import components.ImageIcon;
import components.Dashboard;
import components.Portal;
import javafx.stage.Stage;

public class AdminFactory implements UserFactory {
    @Override
    public Portal getPortal() {
        Portal portal=new Portal();
        ImageIcon doctorsBtn=new ImageIcon("DOCTORS","images/doc_logo.png");
        ImageIcon assistantsBtn=new ImageIcon("ASSISTANTS","images/assis.png");
        portal.addImageIcon(doctorsBtn);
        portal.addImageIcon(assistantsBtn);
        portal.setTitleBox("Admin home");
        doctorsBtn.setOnAction(e->{
            portal.close();
            Stage s= getDashboard("doctors").getView();
            s.show();

        });
        assistantsBtn.setOnAction(e->{
            portal.close();
            Stage s= getDashboard("assistants").getView();
            s.show();
        });

        return portal;
    }

    @Override
    public Dashboard getDashboard(String entity) {
        if(entity!=null)
            if (entity.equals("doctors")) {
                DashboardView<Doctor> view = new DoctorsDashboardView();
                DashboardModel model=new DoctorsDashboardModel();
                Dashboard controller = new DoctorsDashboard(view, model);
                controller.enableCreate();
                controller.enableDelete();
                controller.enableDetails();
                controller.enableEdit();
                controller.addActionButtons();
                return controller;
            }
            else if (entity.equals("assistants")) {
                DashboardView<Assistant> view = new AssisstantDashboardView();
                DashboardModel model=new AssisstantDashboardModel();
                Dashboard controller = new AssisstantDashboard(view, model);
                controller.enableCreate();
                controller.enableDelete();
                controller.enableDetails();
                controller.enableEdit();
                controller.addActionButtons();
                return controller;
            }

        throw new IllegalArgumentException();
    }
}
