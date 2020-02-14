package application;



import components.Dashboard;
import components.ImageIcon;
import components.Portal;
import controllers.AssisstantDashboard;
import controllers.DoctorsDashboard;
import controllers.OperationDashboard;
import controllers.PatientDashboard;
import javafx.stage.Stage;
import models.AssisstantDashboardModel;
import models.Assistant;
import models.DashboardModel;
import models.Doctor;
import models.DoctorsDashboardModel;
import models.Operation;
import models.OperationDashboardModel;
import models.Patient;
import models.PatientDashboardModel;
import views.AssisstantDashboardView;
import views.DashboardView;
import views.DoctorsDashboardView;
import views.OperationDashboardView;
import views.PatientDashboardView;

public class DoctorFactory implements UserFactory {
    @Override
    public Portal getPortal() {
        Portal portal=new Portal();
        ImageIcon patientsBtn=new ImageIcon("PATIENTS","images/pat_logo.png");
        ImageIcon surgeriesBtn=new ImageIcon("SURGERIES","images/surgery.png");
        portal.addImageIcon(patientsBtn);
        portal.addImageIcon(surgeriesBtn);
        portal.setTitleBox("Doctor home");
        patientsBtn.setOnAction(e->{
            portal.close();
            Stage s= getDashboard("patients").getView();
            s.show();

        });
        surgeriesBtn.setOnAction(e->{
            portal.close();
            Stage s= getDashboard("surgeries").getView();
            s.show();
        });

        return portal;
    }

    @Override
    public Dashboard getDashboard(String entity) {
    	if(entity!=null)
            if (entity.equals("patients")) {
                DashboardView<Patient> view = new PatientDashboardView();
                DashboardModel model=new PatientDashboardModel();
                Dashboard controller = new PatientDashboard(view, model);
               // controller.enableCreate();
               // controller.enableDelete();
                controller.enableDetails();
                controller.enableEdit();
                controller.addActionButtons();
                return controller;
            }
            else if (entity.equals("surgeries")) {
                DashboardView<Operation> view = new OperationDashboardView();
                DashboardModel model = new OperationDashboardModel();
                Dashboard controller = new OperationDashboard(view, model);
             // controller.enableCreate();
                // controller.enableDelete();
                 controller.enableDetails();
                 controller.enableEdit();
                 controller.addActionButtons();
                 return controller;
                
            }

        throw new IllegalArgumentException();
    }
}