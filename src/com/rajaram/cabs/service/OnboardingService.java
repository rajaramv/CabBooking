package com.rajaram.cabs.service;

import com.rajaram.cabs.bean.Cab;
import com.rajaram.cabs.dao.CabDAO;

public class OnboardingService {

	
	
	public boolean onBoardCab(String cityName, Cab cabDetails) {
		return CabDAO.getInstance().addCab(cityName, cabDetails);
	}
	
	public void printAvailableCabs() {
		CabDAO.getInstance().printAvailableCabs();
	}
}
