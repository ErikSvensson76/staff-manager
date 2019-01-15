package se.lexicon.erik.staff_manager.data_access;

import java.util.List;
import java.util.Optional;

import se.lexicon.erik.staff_manager.models.employee.Employee;
import se.lexicon.erik.staff_manager.models.employee.SalesPerson;
import se.lexicon.erik.staff_manager.models.employee.SystemDeveloper;

public interface EmployeeDao {

	boolean save(Employee employee);

	boolean remove(Employee employee);

	Optional<Employee> findById(int id);

	List<Employee> findByName(String name);

	List<Employee> findBySalaryAbove(double salary);

	List<Employee> findBySalaryBelow(double salary);

	List<SystemDeveloper> findAllSystemDevs();

	List<SalesPerson> findAllSalesPersons();
	
	List<Employee> getAllEmployees();
	
	void removeAll();

}