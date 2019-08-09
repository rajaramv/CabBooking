package com.rajaram.cab;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.rajaram.cabs.bean.Cab;
import com.rajaram.cabs.service.CabTrackingService;
import com.rajaram.cabs.service.OnboardingService;

public class CabManagerDriver {
	
	public static void main(String[] args) throws Exception {
		System.out.println("Welcome to FK Inter city Services");
		
		OnboardingService obService = new OnboardingService();
		OnboardThread bangalore1 = new OnboardThread("Bangalore", new Cab("Cab 1"), obService);
		OnboardThread bangalore2 = new OnboardThread("Bangalore", new Cab("Cab 2"), obService);
		//OnboardThread bangalore3 = new OnboardThread("Bangalore", new Cab("Cab 3"), obService);
		//OnboardThread bangalore4 = new OnboardThread("Bangalore", new Cab("Cab 4"), obService);
		OnboardThread chennai1 = new OnboardThread("Chennai", new Cab("Cab 5"), obService);
		OnboardThread chennai2 = new OnboardThread("Chennai", new Cab("Cab 6"), obService);
		OnboardThread chennai3 = new OnboardThread("Chennai", new Cab("Cab 7"), obService);
		OnboardThread chennai4 = new OnboardThread("Chennai", new Cab("Cab 8"), obService);
		List<OnboardThread> list = new ArrayList<>();
		list.add(bangalore1);
		list.add(bangalore2);
		list.add(chennai1);
		list.add(chennai2);
		CabTrackingService service = CabTrackingService.getInstance();
		service.addCab("Bangalore", new Cab("Cab 1"));
		Thread.sleep(10);
		//service.addCab("Bangalore", new Cab("Cab 2"));
		Thread.sleep(10);
		//obService.onBoardCab("Bangalore", new Cab("Cab 3"));
		//Thread.sleep(1000);
		service.addCab("Chennai", new Cab("Cab 5"));
		Thread.sleep(10);
		service.addCab("Chennai", new Cab("Cab 6"));
		
		/*ExecutorService executor = Executors.newFixedThreadPool(2);
		List<Future<Boolean>> futures =executor.invokeAll(list);
		try {
			executor.awaitTermination(10000, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
			 
			}
		executor.shutdown();*/
		
		
		obService.printAvailableCabs();
		
		
		BookingThread thread1 = new BookingThread("User1" ,"Bangalore", "Chennai", service);
		BookingThread thread2 = new BookingThread("User2" ,"Bangalore", "Chennai", service);
		BookingThread thread3 = new BookingThread("User4" ,"Bangalore", "Chennai", service);
		//BookingThread thread2 = new BookingThread("User2" ,"Bangalore", "Chennai", service);
		BookingThread thread4 = new BookingThread("User3" ,"Chennai", "Bangalore", service);
		
		List<BookingThread> bookingList = new ArrayList<>();
		bookingList.add(thread1);
		//bookingList.add(thread2);
		bookingList.add(thread3);
		//bookingList.add(thread4);
		ExecutorService bookingExecutor = Executors.newFixedThreadPool(2);
		bookingExecutor.submit(thread1);
		bookingExecutor.submit(thread2);
		Thread.sleep(1000);
		service.addCab("Bangalore", new Cab("Cab 3"));
		bookingExecutor.shutdown();
		service.printAvailableCabs();
		//Thread.sleep(1000000);
		/*executor.submit(chennai1);
		executor.submit(chennai2);
		executor.submit(chennai3);
		executor.submit(chennai4);*/
		
		
	}

}
