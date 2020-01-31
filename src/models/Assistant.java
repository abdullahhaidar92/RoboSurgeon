package models;

public class Assistant extends User {

    private String contractType;


    private String assistantId;

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    private Doctor doctor;

    public Assistant(int id, String firstName, String middleName, String lastName) {
        super(id, firstName, middleName, lastName);
    }

    public Assistant(int id, String firstName, String middleName, String lastName, String email, String phone, String birthDate, String address, String bloodType) {
        super(id, firstName, middleName, lastName, email, phone, birthDate, address, bloodType);
    }

    public String getAssistantId() {
        return assistantId;
    }

    public void setAssistantId(String assistantId) {
        this.assistantId = assistantId;
    }
}
