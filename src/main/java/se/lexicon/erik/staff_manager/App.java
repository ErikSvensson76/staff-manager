package se.lexicon.erik.staff_manager;

import se.lexicon.erik.staff_manager.controller.StaffManagerConsoleInterface;

public class App 
{
    public static void main( String[] args ){
       
    	StaffManagerConsoleInterface ui = 
    			new StaffManagerConsoleInterface();
    	
    	while(ui.isRunning()) {
    		ui.run();    		
    	}
    	
    }
}
