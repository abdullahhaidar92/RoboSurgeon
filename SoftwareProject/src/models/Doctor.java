package models;
import java.sql.Date;

public class Doctor extends User {

    private int doctorId;
    private String speciality;

    public Doctor(){
    	super();
    }
    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }


    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }


    public Doctor(int profileId, String firstName, String middleName, String lastName) {
        super(profileId, firstName, middleName, lastName);
    }

    public Doctor(int profileId, String firstName, String middleName, String lastName, String email, String phone, Date birthDate, String address, String bloodType) {
        super(profileId, firstName, middleName, lastName, email, phone, birthDate, address, bloodType);
    }
    
    


}
