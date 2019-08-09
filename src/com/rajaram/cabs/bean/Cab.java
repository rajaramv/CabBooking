package com.rajaram.cabs.bean;

public class Cab {

	public Cab(String cabId) {
		super();
		this.cabId = cabId;
		
		// TODO Auto-generated constructor stub
	}

	private String cabId;
	
	private String driverName;
	
	private CabStatusEnum status = CabStatusEnum.IDLE;
	
	private long idleTime = System.currentTimeMillis();

	public String getCabId() {
		return cabId;
	}

	public void setCabId(String cabId) {
		this.cabId = cabId;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public CabStatusEnum getStatus() {
		return status;
	}

	public void setStatus(CabStatusEnum status) {
		this.status = status;
	}

	public long getIdleTime() {
		return idleTime;
	}

	public void setIdleTime(long idleTime) {
		this.idleTime = idleTime;
	}
	
	@Override
	public String toString() {
		return cabId;
	}
	
	
}
