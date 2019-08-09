package com.rajaram.cabs.service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

import com.rajaram.cab.exception.CabNotAvailableException;
import com.rajaram.cab.exception.CabServiceExecption;
import com.rajaram.cabs.bean.Cab;
import com.rajaram.cabs.bean.CabStatusEnum;
import com.rajaram.cabs.dao.CabDAO;

public class CabTrackingService {
	
	Map<String,Queue<Cab>> availableCabs;
	
	private static CabTrackingService cabTrackingService = new CabTrackingService();
	
	boolean rejected = false;
	
	private  CabTrackingService() {
		
		availableCabs = new HashMap<>();
	}
	
	public static CabTrackingService getInstance() {
		return cabTrackingService;
	}
	
	public boolean addCab(String cityName, Cab cabDetails) {
		Queue<Cab> cabList = availableCabs.get(cityName);
		if(cabList == null) {
			cabList = new PriorityBlockingQueue<Cab>(
			          10, 
			          Comparator.comparing(Cab::getIdleTime)); 
			cabList.add(cabDetails);
			availableCabs.put(cityName, cabList);
		} else{
			cabList.add(cabDetails);
		}
		return true;
	}
	
	public Cab bookCab(String srcCity , String destCity) throws CabServiceExecption{
		if(availableCabs.get(srcCity) == null || availableCabs.get(srcCity).isEmpty()) {
			throw new CabNotAvailableException("Cabs are not available for : " + srcCity);
		}
		boolean accepted = false;
		Cab available = null;
		while(!accepted) {
			 available = availableCabs.get(srcCity).poll();
			
			if(!rejected && available.getCabId() == "Cab 2") {
				reject(available, srcCity);
				System.out.println("Request to cab is rejected for :" + srcCity + " to :" + destCity);
				rejected= true;
			} else {
				accept(available);
				accepted = true;
			}
		}
		
		
		return available;
	}
	
	public boolean endTrip(Cab cab,String destCity) throws CabServiceExecption {
		if(cab.getStatus() == CabStatusEnum.IDLE) {
			throw new CabServiceExecption(" Cab is not engaged in a trip");
		}
		cab.setStatus(CabStatusEnum.IDLE);
		cab.setIdleTime(System.currentTimeMillis());
		addCab(destCity, cab);
		return true;
	}
	
	public boolean accept(Cab cabDetails) {
		cabDetails.setStatus(CabStatusEnum.ON_TRIP);
		return true;
	}
	
	public boolean reject(Cab details,String cityName) {
		//details.setIdleTime(System.currentTimeMillis() - details.getIdleTime());
		details.setStatus(CabStatusEnum.IDLE);
		addCab(cityName, details);
		return true;
	}
	
	public void printAvailableCabs() {
		availableCabs.forEach((key,value) -> value.stream().forEach(val -> System.out.println(key +"," + val)));
	}

}
