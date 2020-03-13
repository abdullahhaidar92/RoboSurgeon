package views;

import models.Operation;

public class OperationDashboardView extends DashboardView<Operation>{
	
	public OperationDashboardView(){
        setHeaderTitle("Operations List");
        addIntegerColumn("Patient Name","patientName");
        addIntegerColumn("Surgery Type","name");
        addStringColumn("Date", "appointmentDate");
    }
	
}
