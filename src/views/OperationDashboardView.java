package views;

import models.Operation;

public class OperationDashboardView extends DashboardView<Operation>{
	
	public OperationDashboardView(){
        setHeaderTitle("Operations List");
        addIntegerColumn("Doctor Name","doctorName");
        addIntegerColumn("Patient Name","patientName");
        addStringColumn("Date", "appointmentDate");
    }
	
}
