package models;

import java.sql.Timestamp;

public class Operation {
	private int doctorId, patientId, machineId, surgeryId, machineRoomNb,maxTimerVal, maxRadiationVal;
	private Timestamp appointmentDate, registrationDate, startTime, endTime;
	private String name, description, duration;
	private String doctorName, patientName, machineState;

	public Operation(){
	}

	public Operation(int doctorId, String doctorName, int patientId, String patientName,
			int machineId, int machineRoomNb, String machineState, int surgeryId,
			Timestamp appointmentDate, Timestamp registrationDate, Timestamp startTime,
			Timestamp endTime, String name, String description, String duration) {
		
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.patientId = patientId;
		this.patientName = patientName;
		this.machineId = machineId;
		this.machineRoomNb = machineRoomNb;
		this.machineState = machineState;
		this.surgeryId = surgeryId;
		this.appointmentDate = appointmentDate;
		this.registrationDate = registrationDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.name = name;
		this.description = description;
		this.duration = duration;
		
	}

	public int getSurgeryId() {
		// TODO Auto-generated method stub
		return surgeryId;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	
	public String getDoctorName() {
		return doctorName;
	}
	
	public String getPatientName() {
		return patientName;
	}
	
	public String getAppointmentDate() {
		return appointmentDate.toString();
	}

	public int getMachineId() {
		return machineId;
	}

	public void setMachineId(int machineId) {
		this.machineId = machineId;
	}

	public int getMachineRoomNb() {
		return machineRoomNb;
	}

	public void setMachineRoomNb(int machineRoomNb) {
		this.machineRoomNb = machineRoomNb;
	}

	public Timestamp getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Timestamp registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getMachineState() {
		return machineState;
	}

	public void setMachineState(String machineState) {
		this.machineState = machineState;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public void setSurgeryId(int surgeryId) {
		this.surgeryId = surgeryId;
	}

	public void setAppointmentDate(Timestamp appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public int getMaxTimerVal() {
		return maxTimerVal;
	}

	public void setMaxTimerVal(int maxTimerVal) {
		this.maxTimerVal = maxTimerVal;
	}

	public int getMaxRadiationVal() {
		return maxRadiationVal;
	}

	public void setMaxRadiationVal(int maxRadiationVal) {
		this.maxRadiationVal = maxRadiationVal;
	}


}
