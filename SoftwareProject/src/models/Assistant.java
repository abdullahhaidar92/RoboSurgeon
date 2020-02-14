package models;

import java.sql.Date;

public class Assistant extends User {
	private int assistantId;
	private int doctorId;
    private String contractType;

    
    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
    
        this.doctorId = doctorId;
    }
    public int getAssistantId() {
        return assistantId;
    }

    public void setAssistantId(int Id) {
        this.assistantId = Id;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }
    
    public Assistant() {
    	
    }

    public Assistant(int id, String firstName, String middleName, String lastName) {
        super(id, firstName, middleName, lastName);
    }

    public Assistant(int id,String firstName, String middleName, String lastName, String email, String phone, Date birthDate, String address, String bloodType) {
    	super(id, firstName, middleName, lastName, email, phone, birthDate, address, bloodType);
    }
}
