package com.rajaram.cab;

import java.util.concurrent.Callable;

import com.rajaram.cabs.bean.Cab;
import com.rajaram.cabs.service.OnboardingService;

public class OnboardThread implements Callable<Boolean>{

	
	String cityName;
	
	Cab cabDetails;
	
	OnboardingService service;
	
	
	
	public OnboardThread(String cityName, Cab cabDetails, OnboardingService service) {
		super();
		this.cityName = cityName;
		this.cabDetails = cabDetails;
		this.service = service;
	}



	



	@Override
	public Boolean call() throws Exception {
		return service.onBoardCab(cityName, cabDetails);
	}

}
