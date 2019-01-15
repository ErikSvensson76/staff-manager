package se.lexicon.erik.staff_manager.data_access;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import se.lexicon.erik.staff_manager.models.employee.Employee;
import se.lexicon.erik.staff_manager.models.employee.SalesPerson;
import se.lexicon.erik.staff_manager.models.employee.SystemDeveloper;

public class EmployeeDaoListImpl implements EmployeeDao {
	
	private static final EmployeeDao instance;
	
	static {
		instance = new EmployeeDaoListImpl();
	}
	
	private List<Employee> storage;
	
	private EmployeeDaoListImpl () {
		storage = new ArrayList<>();
	}
	
	public static EmployeeDao get() {
		return instance;
	}
	
	@Override
	public boolean save(Employee employee) {
		if(employee == null) {
			return false;
		}		
		
		if(!storage.contains(employee)){
			return storage.add(employee);
		}
		return false;
	}
	
	@Override
	public boolean remove(Employee employee) {
		if(employee == null) {
			return false;
		}
		
		if(storage.contains(employee)) {
			return storage.remove(employee);
		}
		return false;
	}
	
	@Override
	public Optional<Employee> findById(int id) {
		Optional<Employee> result = Optional.empty();
		
		for(Employee e : storage) {
			if(e.getId() == id) {
				result = Optional.of(e);
				break;
			}
		}
		return result;
	}
	
	@Override
	public List<Employee> findByName(String name) {
		List<Employee> result = new ArrayList<>();
		
		for(Employee e : storage) {
			if(e.getName().toLowerCase().contains(name.toLowerCase())) {
				result.add(e);
			}
		}
		return result;		
	}
	
	@Override
	public List<Employee> findBySalaryAbove(double salary){
		List<Employee> result = new ArrayList<>();
		
		for(Employee e: storage) {
			if(e.getSalary() > salary) {
				result.add(e);
			}
		}
		
		return result;
	}
	
	@Override
	public List<Employee> findBySalaryBelow(double salary){
		List<Employee> result = new ArrayList<>();
		
		for(Employee e: storage) {
			if(e.getSalary() < salary) {
				result.add(e);
			}
		}
		
		return result;
	}
	
	@Override
	public List<SystemDeveloper> findAllSystemDevs(){
		List<SystemDeveloper> result = new ArrayList<>();
		
		for(Employee e: storage) {
			if(e instanceof SystemDeveloper) {
				result.add((SystemDeveloper) e);
			}
		}
		return result;
	}
	
	@Override
	public List<SalesPerson> findAllSalesPersons(){
		List<SalesPerson> result = new ArrayList<>();
		
		for(Employee e : storage) {
			if(e instanceof SalesPerson) {
				result.add((SalesPerson) e);
			}
		}
		return result;
	}

	@Override
	public List<Employee> getAllEmployees() {
		return this.storage;
	}

	@Override
	public void removeAll() {
		storage.clear();		
	}

}
