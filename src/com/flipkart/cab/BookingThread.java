package com.flipkart.cab;

import java.util.concurrent.Callable;

import com.flipkart.cab.exception.CabServiceExecption;
import com.flipkart.cabs.bean.Cab;
import com.flipkart.cabs.service.CabTrackingService;

public class BookingThread implements Callable<Cab>{
	
	String name;
	
	String srcCity;
	
	String destCity;
	
	CabTrackingService service = null;
	
	public BookingThread(String name,String srcCity, String destCity, CabTrackingService service) {
		super();
		this.name = name;
		this.srcCity = srcCity;
		this.destCity = destCity;
		this.service = service;
	}

	@Override
	public Cab call()  {
		Cab booked = null;
		try {
			 booked  = service.bookCab(srcCity, destCity);
			 if(booked != null) {
				 System.out.println("Booked Cab for " +name + " from SrcCity:" + srcCity + " Destination : " + booked);
			 } else {
				 System.out.println("Rejected for " +name + " from SrcCity:" + srcCity + " Destination : " );
			 }
			 
		} catch (CabServiceExecption e) {
			System.out.println(e.getMessage());
		}
		return booked;
		
	}

}
