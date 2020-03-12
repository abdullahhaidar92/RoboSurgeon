package views;

import models.Patient;

public class PatientDashboardView extends DashboardView<Patient> {

    public PatientDashboardView(){
        setHeaderTitle("Patients List");
        addIntegerColumn("Patient Id","patientId");
        addStringColumn("first name","firstName");
        addStringColumn("last name","lastName");
    }

}