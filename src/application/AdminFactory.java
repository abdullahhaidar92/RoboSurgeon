package application;

import controllers.AssisstantDashboard;
import controllers.DoctorsDashboard;
import models.*;
import views.AssisstantDashboardView;
import views.DashboardView;
import views.DoctorsDashboardView;
import components.ImageIcon;
import controllers.Dashboard;
import components.Portal;
import javafx.stage.Stage;

public class AdminFactory implements UserFactory {

    /*
     * creates a portal for the admin from where he may access his dashboards
     * */
    @Override
    public Portal getPortal() {
        Portal portal = new Portal();
        ImageIcon doctorsBtn = new ImageIcon("DOCTORS", "images/doc_logo.png");
        ImageIcon assistantsBtn = new ImageIcon("ASSISTANTS", "images/assis.png");
        portal.addImageIcon(doctorsBtn);
        portal.addImageIcon(assistantsBtn);
        portal.setTitleBox("Admin home");
        doctorsBtn.setOnAction(e -> {
            portal.close();
            Stage s = getDashboard("doctors").getView();
            s.show();

        });
        assistantsBtn.setOnAction(e -> {
            portal.close();
            Stage s = getDashboard("assistants").getView();
            s.show();
        });

        return portal;
    }

    /*
     * creates the two dashboards that the admin will use
     * the doctors dashboard that allows the admin to manage the patients
     * the assistants dashboard that allows the admin to manage the assistants
     * all CRUD operations are enabled for the admin
     * */
    @Override
    public Dashboard getDashboard(String entity) {
        if (entity != null)
            if (entity.equals("doctors")) {
                DashboardView<Doctor> view = new DoctorsDashboardView();
                DashboardModel model = new DoctorsDashboardModel();
                Dashboard controller = new DoctorsDashboard(view, model);
                controller.enableCreate();
                controller.enableDelete();
                controller.enableDetails();
                controller.enableEdit();
                controller.addActionButtons();
                return controller;
            } else if (entity.equals("assistants")) {
                DashboardView<Assistant> view = new AssisstantDashboardView();
                DashboardModel model = new AssisstantDashboardModel();
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
