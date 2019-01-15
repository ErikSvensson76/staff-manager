package se.lexicon.erik.staff_manager.models.employee;

import java.time.LocalDate;

public class SystemDeveloper extends Employee{
	
	private String [] certificates = new String[0];
	private String [] languages = new String[0];	
	
	public SystemDeveloper(String name, LocalDate dateHired, String[] certificates, String[] languages) {
		this(name,dateHired);
		if(certificates != null) this.certificates = certificates;
		if(languages != null) this.languages = languages;
		calculateSalary();
	}

	public SystemDeveloper(String name, LocalDate dateHired) {
		super(name, dateHired);
	}

	@Override
	public void calculateSalary() {
		double oldSalary = getSalary();
		double newSalary = 25000;
		newSalary += (1000 * certificates.length);
		newSalary += (1500 * languages.length);
		setSalary(oldSalary == newSalary ? oldSalary : newSalary);
	}
	
	public String[] getCertificates() {
		return certificates;
	}

	public String[] getLanguages() {
		return languages;
	}
	
	public boolean addLanguage(String language) {
		if(language == null) return false;
		
		String[]array = addStringToArray(language, languages);		
		if(array.length != languages.length) {
			languages = array;
			calculateSalary();
			return true;
		}else{
			return false;
		}
	}
	
	public boolean addCertificate(String certificate) {
		if(certificate == null) return false;
		
		String[]array = addStringToArray(certificate, certificates);
		if(array.length != certificates.length) {
			this.certificates = array;
			calculateSalary();
			return true;
		}else{
			return false;
		}
	}

	@Override
	public String toString() {
		return "SystemDeveloper [getName()=" + getName() + ", getId()=" + getId() + ", getSalary()=" + getSalary()
				+ ", getDateHired()=" + getDateHired() + "]";
	}
	
	
}
