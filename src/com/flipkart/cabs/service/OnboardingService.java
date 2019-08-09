package com.flipkart.cabs.service;

import com.flipkart.cabs.bean.Cab;
import com.flipkart.cabs.dao.CabDAO;

public class OnboardingService {

	
	
	public boolean onBoardCab(String cityName, Cab cabDetails) {
		return CabDAO.getInstance().addCab(cityName, cabDetails);
	}
	
	public void printAvailableCabs() {
		CabDAO.getInstance().printAvailableCabs();
	}
}
