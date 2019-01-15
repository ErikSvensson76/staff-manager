package se.lexicon.erik.staff_manager.models.employee;

import java.time.LocalDate;

import se.lexicon.erik.staff_manager.models.Client;

public class SalesPerson extends Employee{
	
	private Client[] clients;
	private int aquiredClients;

	public SalesPerson(String name, LocalDate dateHired) {
		super(name, dateHired);
		clients = new Client[0];		
	}	

	public Client[] getClients() {
		return clients;
	}

	public int getAquiredClients() {
		return aquiredClients;
	}
	
	public boolean addClients(boolean aquired, Client client) {
		if(client == null) return false;
		if(client.isAssigned()) return false;
		
		Client[]array = addClientToArray(client, clients);		
		if(array.length != clients.length) {
			clients = array;
			if(aquired)
				aquiredClients++;
			
			client.assign();
			calculateSalary();
			return true;
		}else{
			return false;
		}
	}
	
	public boolean unassignClient(Client client) {
		if(client == null) return false;		
		if(getClient(client) == null) return false;
		
		this.clients = removeClientFromArray(client, this.clients);
		client.unassign();
		calculateSalary();
		return true;		
	}

	@Override
	public void calculateSalary() {
		double oldSalary = getSalary();
		double newSalary = 25000;
		newSalary += (500 * clients.length) + (1500 * getAquiredClients());		
		setSalary(newSalary == oldSalary ? oldSalary : newSalary);			
	}
	
	public Client getClient(Client client) {
		for(Client c : clients) {
			if(c.equals(client)) {
				return c;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "SalesPerson [getName()=" + getName() + ", getId()=" + getId() + ", getSalary()=" + getSalary()
				+ ", getDateHired()=" + getDateHired() + "]";
	}
}
