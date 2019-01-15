package se.lexicon.erik.staff_manager.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import se.lexicon.erik.staff_manager.data_access.EmployeeDao;
import se.lexicon.erik.staff_manager.data_access.EmployeeDaoListImpl;
import se.lexicon.erik.staff_manager.exceptions.EmployeeNotFoundException;
import se.lexicon.erik.staff_manager.models.Client;
import se.lexicon.erik.staff_manager.models.employee.Employee;
import se.lexicon.erik.staff_manager.models.employee.SalesPerson;
import se.lexicon.erik.staff_manager.models.employee.SystemDeveloper;
import se.lexicon.erik.staff_manager.util.NullChecker;

public class EmployeeServiceImpl implements EmployeeService {
	
	//Static instance
	private static final EmployeeService INSTANCE;
	
	static {
		INSTANCE = new EmployeeServiceImpl();
	}
	
	private EmployeeDao employeeDao;
	
	private EmployeeServiceImpl() {
		employeeDao = EmployeeDaoListImpl.get();
	}
	
	public static EmployeeService get() {
		return INSTANCE;
	}
	
	@Override
	public SalesPerson registerNewSalesPerson(String name, LocalDate hiringDate) {
		if(NullChecker.nullCheck(name, hiringDate))
			throw new NullPointerException("NullPointerException in " + this.getClass() + "\n"
					+ "String name = " + name +", LocalDate hiringDate = " + hiringDate);
		
		SalesPerson newSales = new SalesPerson(name, hiringDate);
		if(employeeDao.save(newSales)) {
			return newSales;
		}else {
			System.out.println("Could not create SalesPerson, duplicate found.");
			return null;
		}
	}
	
	@Override
	public SystemDeveloper registerNewSystemDeveloper(String name, LocalDate hiringDate) {
		if(NullChecker.nullCheck(name, hiringDate)) {
			throw new NullPointerException("NullPointerException in " + this.getClass() + "\n"
					+ "String name = " + name + ", LocalDate hiringDate = " + hiringDate);
		}
		
		SystemDeveloper newDev = new SystemDeveloper(name, hiringDate);
		if(employeeDao.save(newDev)) {
			return newDev;			
		}else {
			System.out.println("Could not create SystemDeveloper, duplicate found.");
			return null;
		}
	}
	
	@Override
	public SystemDeveloper registerNewSystemDeveloper(String name, LocalDate hiringDate, String[] certificates, String[] languages) {
		if(NullChecker.nullCheck(name, hiringDate, certificates, languages)) {
			 throw new NullPointerException("NullPointerException in " + this.getClass() + "\n"
					 + "String name = " + name + ", LocalDate hiringDate = " + hiringDate + "\n"
					 + ", String[] certificates = " + certificates + ", String[] languages");			 
		}
		
		SystemDeveloper newDev = new SystemDeveloper(name, hiringDate, certificates, languages);
		if(employeeDao.save(newDev)) {
			return newDev;
		}else {
			System.out.println("Could not create SystemDeveloper, duplicate found.");
			return null;
		}
	}
	
	@Override
	public boolean removeEmployee(Employee employee) {
		if(NullChecker.nullCheck(employee)) {
			return false;
		}
		
		if(employee instanceof SalesPerson) {
			SalesPerson temp = (SalesPerson) employee;
			for(Client c : temp.getClients()) {
				c.unassign();
			}			
		}
		
		if(employeeDao.remove(employee)) {
			return true;
		}else {
			System.out.println("Could not remove because it doesn't exist in the storage");
			return false;
		}
	}
	
	@Override
	public Employee findEmployeeById(int id) throws EmployeeNotFoundException{
		Optional<Employee> result;
		
		result = employeeDao.findById(id);		
		return result.orElseThrow(() -> new EmployeeNotFoundException("Could not find user with id " + id));						
	}

	@Override
	public List<Employee> findByName(String name) {
		return employeeDao.findByName(name);
	}

	@Override
	public List<Employee> findBySalaryAbove(double salary) {
		return employeeDao.findBySalaryAbove(salary);
	}

	@Override
	public List<Employee> findBySalaryBelow(double salary) {
		return employeeDao.findBySalaryBelow(salary);
	}

	@Override
	public List<SystemDeveloper> findAllSystemDevs() {
		return employeeDao.findAllSystemDevs();
	}

	@Override
	public List<SalesPerson> findAllSalesPersons() {
		return employeeDao.findAllSalesPersons();
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeDao.getAllEmployees();
	}	
}
