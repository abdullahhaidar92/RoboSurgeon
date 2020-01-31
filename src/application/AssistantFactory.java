package application;

import components.Dashboard;
import components.ImageIcon;
import components.Portal;
import javafx.stage.Stage;

public class AssistantFactory implements UserFactory {
    @Override
    public Portal getPortal() {
        Portal portal=new Portal();
        ImageIcon patientsBtn=new ImageIcon("PATIENTS","images/pat_logo.png");
        ImageIcon surgeriesBtn=new ImageIcon("SURGERIES","images/surgery.png");
        portal.addImageIcon(patientsBtn);
        portal.addImageIcon(surgeriesBtn);
        portal.setTitleBox("Assistant home");
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
        return null;
    }
}
