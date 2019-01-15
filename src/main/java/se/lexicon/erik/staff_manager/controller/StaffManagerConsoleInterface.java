package se.lexicon.erik.staff_manager.controller;

import se.lexicon.erik.staff_manager.service.EmployeeService;
import se.lexicon.erik.staff_manager.service.EmployeeServiceImpl;

public class StaffManagerConsoleInterface {
	
	private EmployeeService employeeService;
	private boolean running;
		
	public StaffManagerConsoleInterface() {
		employeeService = EmployeeServiceImpl.get();
		running = true;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public void run() {
		//Write code here		
	}
	
	

}
