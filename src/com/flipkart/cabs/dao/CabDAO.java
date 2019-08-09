package com.flipkart.cabs.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.flipkart.cabs.bean.Cab;
import com.flipkart.cabs.service.CabTrackingService;

public class CabDAO {
	
	Map<String,List<Cab>> citiesCabMap;
	
	private static CabDAO cabDao = new CabDAO();
	
	private  CabDAO() {
		
		citiesCabMap = new HashMap<>();
	}
	
	public static CabDAO getInstance() {
		return cabDao;
	}
	
	public boolean addCab(String cityName, Cab cabDetails) {
		List<Cab> cabList = citiesCabMap.get(cityName);
		if(cabList == null) {
			cabList = new ArrayList<>();
			cabList.add(cabDetails);
			citiesCabMap.put(cityName, cabList);
		} else{
			cabList.add(cabDetails);
		}
		CabTrackingService.getInstance().addCab(cityName, cabDetails);
		return true;
	}
	
	public void printAvailableCabs() {
		citiesCabMap.forEach((key,value) -> value.stream().forEach(val -> System.out.println(key +"," + val)));
	}

}
