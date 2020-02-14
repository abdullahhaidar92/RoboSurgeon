package views;

import models.Doctor;

public class DoctorsDashboardView extends DashboardView<Doctor> {

    public DoctorsDashboardView(){
        setHeaderTitle("Doctors List");
        addIntegerColumn("Doctor Id","doctorId");
        addStringColumn("first name","firstName");
        addStringColumn("last name","lastName");
        addStringColumn("Speciality");
    }

}
