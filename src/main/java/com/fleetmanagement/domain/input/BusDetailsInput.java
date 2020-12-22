package com.fleetmanagement.domain.input;

import java.sql.Blob;
import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class BusDetailsInput {

	private Integer busId;
	private String makeYear;
	private Integer numberOfWheels;
	private Long odometerReading;
	private String airConditioner;
	private String capacity;
	private Date scheduledDate;

	public String getBusName() {
		return busName;
	}
	public Date getScheduledDate() {
		return scheduledDate;
	}
	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}
	public void setBusName(String busName) {
		this.busName = busName;
	}
	private String status;
	private String registrationNumber;
	private String permitStatus;
	private Integer garageId;
	private String busName;
	
	private Date lastServiceDate;
	private Date scheduleServiceDate;
	
	public String getAirConditioner() {
		return airConditioner;
	}
	public void setAirConditioner(String airConditioner) {
		this.airConditioner = airConditioner;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public String getPermitStatus() {
		return permitStatus;
	}
	public void setPermitStatus(String permitStatus) {
		this.permitStatus = permitStatus;
	}
	public Integer getGarageId() {
		return garageId;
	}
	public void setGarageId(Integer garageId) {
		this.garageId = garageId;
	}
	public Integer getBusId() {
		return busId;
	}
	public void setBusId(Integer busId) {
		this.busId = busId;
	}
	public String getMakeYear() {
		return makeYear;
	}
	public void setMakeYear(String makeYear) {
		this.makeYear = makeYear;
	}
	public Integer getNumberOfWheels() {
		return numberOfWheels;
	}
	public void setNumberOfWheels(Integer numberOfWheels) {
		this.numberOfWheels = numberOfWheels;
	}
	public Long getOdometerReading() {
		return odometerReading;
	}
	public void setOdometerReading(Long odometerReading) {
		this.odometerReading = odometerReading;
	}
	
	public Date getLastServiceDate() {
		return lastServiceDate;
	}
	public void setLastServiceDate(Date lastServiceDate) {
		this.lastServiceDate = lastServiceDate;
	}
	public Date getScheduleServiceDate() {
		return scheduleServiceDate;
	}
	public void setScheduleServiceDate(Date scheduleServiceDate) {
		this.scheduleServiceDate = scheduleServiceDate;
	}
	@Override
	public String toString() {
		return "BusDetailsInput [busId=" + busId + ", makeYear=" + makeYear + ", numberOfWheels=" + numberOfWheels
				+ ", odometerReading=" + odometerReading + ", airConditioner=" + airConditioner + ", capacity="
				+ capacity + ", status=" + status + ", registrationNumber=" + registrationNumber + ", permitStatus="
				+ permitStatus + ", garageId=" + garageId + ", lastServiceDate=" + lastServiceDate
				+ ", scheduleServiceDate=" + scheduleServiceDate + "]";
	}
	
}
