package views;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import models.Operation;

public class OperationDashboardView extends DashboardView<Operation> implements EventHandler<KeyEvent>{
	
	public OperationDashboardView(){
        setHeaderTitle("Operations List");
        addIntegerColumn("Doctor Name","doctorName");
        addIntegerColumn("Patient Name","patientName");
        addStringColumn("Date", "appointmentDate");
    }

	@Override
	public void handle(KeyEvent event) {
		// TODO Auto-generated method stub
		System.out.println("ldskfj");
	}
	
}
