package models;
import java.sql.Date;

public class Assistant extends User {

    private String contractType;
    private int doctorId;


    private int assistantId;

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
    
        this.doctorId = doctorId;
    }


    
    public Assistant(){}
    public Assistant(int profileId, String firstName, String middleName, String lastName) {
        super(profileId, firstName, middleName, lastName);
    }

    public Assistant(int profileId, String firstName, String middleName, String lastName, String email, String phone, Date birthDate, String address, String bloodType) {
        super(profileId, firstName, middleName, lastName, email, phone, birthDate, address, bloodType);
    }

    public int getAssistantId() {
        return assistantId;
    }

    public void setAssistantId(int assistantId) {
        this.assistantId = assistantId;
    }
    
    
}
