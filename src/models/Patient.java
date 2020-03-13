package models;
import java.sql.Date;
import java.time.LocalDate;

public class Patient extends Profile{
	
	public Patient() {
		
	}
	
	public Patient(int profileId, String firstName, String middleName, String lastName) {
		super(profileId, firstName, middleName, lastName);
		// TODO Auto-generated constructor stub
	}
	
	public Patient(int profileId, String firstName, String middleName, String lastName, String email, String phone, Date birthDate, String address, String bloodType) {
		super(profileId, firstName, middleName, lastName, email, phone, birthDate, address, bloodType);
	}
	
	
	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}



	public int getPulse() {
		return pulse;
	}

	public void setPulse(int pulse) {
		this.pulse = pulse;
	}



	public int getNormalPulse() {
		return normalPulse;
	}

	public void setNormalPulse(int normalPulse) {
		this.normalPulse = normalPulse;
	}



	public int getOxygen() {
		return oxygen;
	}

	public void setOxygen(int oxygen) {
		this.oxygen = oxygen;
	}



	public int getNormalOxygen() {
		return normalOxygen;
	}

	public void setNormalOxygen(int normalOxygen) {
		this.normalOxygen = normalOxygen;
	}



	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}



	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	

	public String getPressure() {
		return pressure;
	}

	public void setPressure(String pressure) {
		this.pressure = pressure;
	}



	public String getNormalPressure() {
		return normalPressure;
	}

	public void setNormalPressure(String normalPressure) {
		this.normalPressure = normalPressure;
	}



	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}



	public float getNormalTemperature() {
		return normalTemperature;
	}

	public void setNormalTemperature(float normalTemperature) {
		this.normalTemperature = normalTemperature;
	}

	



	private int patientId, pulse, normalPulse, oxygen, normalOxygen, weight, height;
	private String pressure, normalPressure;
	private float temperature, normalTemperature;
}
