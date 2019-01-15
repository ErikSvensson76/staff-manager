package se.lexicon.erik.staff_manager.models;

public class Client {
	
	private static int sequencer = 0;
	
	private final int id;
	private String name;
	private String email;
	private String company;
	private boolean assigned = false;
	
	public Client(String name, String email, String company) {
		id = ++sequencer;
		this.name = name;
		this.email = email;
		this.company = company;
	}	

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}	

	public boolean isAssigned() {
		return assigned;
	}
	
	public void assign() {
		this.assigned = true;
	}
	
	public void unassign() {
		this.assigned = false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", email=" + email + ", company=" + company + "]";
	}

		
}
