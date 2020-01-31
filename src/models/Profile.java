package models;

public abstract class Profile {
    private int profileId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phone;
    private String birthDate;
    private String address;
    private String bloodType;

    public Profile(int profileId, String firstName, String middleName, String lastName) {
        this.profileId = profileId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public Profile(int profileId, String firstName, String middleName, String lastName, String email, String phone, String birthDate, String address, String bloodType) {
        this(profileId,firstName,middleName,lastName);
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.address = address;
        this.bloodType = bloodType;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int id) {
        this.profileId = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }
}
