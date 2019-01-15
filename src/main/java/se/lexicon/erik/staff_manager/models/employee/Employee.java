package se.lexicon.erik.staff_manager.models.employee;

import java.time.LocalDate;
import java.util.Arrays;

import se.lexicon.erik.staff_manager.models.Client;

public abstract class Employee {
	
	private static int sequencer = 0;
	private int id;
	private String name;
	private double salary;
	private LocalDate dateHired;
	
	public Employee(String name, LocalDate dateHired) {
		this(25000);
		this.name = name;
		this.dateHired = dateHired;
	}
	
	private Employee(double salary) {
		this.id = ++sequencer;
		this.salary = salary;
	}

	protected abstract void calculateSalary();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public double getSalary() {
		return salary;
	}
	
	protected void setSalary(double salary) {
		this.salary = salary;
	}

	public LocalDate getDateHired() {
		return dateHired;
	}

	public void setDateHired(LocalDate dateHired) {
		this.dateHired = dateHired;
	}
	
	static String[] expandArray(String [] array) {
		return Arrays.copyOf(array, array.length + 1);		
	}
	
	static Client[] expandArray(Client[] array) {
		return Arrays.copyOf(array, array.length + 1);
	}
	
	static boolean arrayContains(String[] array, String string) {
		for(String s : array) {
			if(s == null) continue;
			if(s.equals(string)) {
				return true;
			}
		}
		return false;
	}
	
	static boolean arrayContains(Client[] array, Client client) {
		for(Client c : array) {
			if(c == null) continue;
			if(c.equals(client)) {
				return true;
			}
		}
		
		return false;		
	}
	
	 static String[] addStringToArray(String toAdd, String [] array) {
		if(arrayContains(array, toAdd)) {
			return array;
		}
		array = expandArray(array);
		array[array.length-1] = toAdd;
		return array;
	}
	
	 static Client[] addClientToArray(Client toAdd, Client[] array) {
		if(arrayContains(array, toAdd)) {
			return array;
		}
		
		array = expandArray(array);
		array[array.length-1] = toAdd;
		return array;
	}
	 
	 static Client[] removeClientFromArray(Client toRemove, Client[] array) {		 
		 int clientIndex = -1;
		 Client[] newArray = new Client[array.length -1];
		 
		 if(array.length == 1) return newArray;
		 
		 for(int i=0; i<array.length; i++) {
			 if(array[i].equals(toRemove)) {
				 clientIndex = i;
				 break;
			 }
		 }
		 
		 for(int i = 0; i<clientIndex; i++) {
			 newArray[i] = array[i];
		 }
		 
		 for(int i=(clientIndex+1), j = clientIndex; i<array.length; i++) {
			 newArray[j] = array[i];
		 }
		 return newArray;
	 }
}
