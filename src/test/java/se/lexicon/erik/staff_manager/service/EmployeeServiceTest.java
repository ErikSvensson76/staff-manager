package se.lexicon.erik.staff_manager.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.lexicon.erik.staff_manager.data_access.EmployeeDao;
import se.lexicon.erik.staff_manager.data_access.EmployeeDaoListImpl;
import se.lexicon.erik.staff_manager.exceptions.EmployeeNotFoundException;
import se.lexicon.erik.staff_manager.models.Client;
import se.lexicon.erik.staff_manager.models.employee.Employee;
import se.lexicon.erik.staff_manager.models.employee.SalesPerson;
import se.lexicon.erik.staff_manager.models.employee.SystemDeveloper;

public class EmployeeServiceTest {
	
	private Employee dev;
	private Employee sales;
	
	private int devId;	
	
	private EmployeeService service = EmployeeServiceImpl.get();
	private EmployeeDao daoDependency = EmployeeDaoListImpl.get();
	
	@Before
	public void init() {
		dev = new SystemDeveloper("Test Dev", LocalDate.parse("2018-12-01"));
		sales = new SalesPerson("Test Sales", LocalDate.parse("2018-01-01"));
		devId = dev.getId();
		daoDependency.save(dev);
		daoDependency.save(sales);
	}
	
	@After
	public void tearDown() {
		daoDependency.removeAll();
	}
	
	@Test
	public void test_register_salesPerson_return_salesPerson() {		
		String name = "Erik";
		LocalDate hiringDate = LocalDate.parse("2018-03-02");
		
		SalesPerson result = service.registerNewSalesPerson(name, hiringDate);
		
		assertTrue(result.getDateHired().equals(hiringDate) & result.getName().equals(name));		
	}
	
	@Test(expected = NullPointerException.class)
	public void test_register_salesPerson_null_params() {
		service.registerNewSalesPerson(null, null);
	}
	
	@Test
	public void test_register_systemDev_with_two_params_return_systemDev() {
		String name = "Erik";
		LocalDate hiringDate = LocalDate.parse("2019-01-01");
		
		SystemDeveloper newDev = service.registerNewSystemDeveloper(name, hiringDate);
		
		assertEquals(name, newDev.getName());
		assertEquals(hiringDate, newDev.getDateHired());
	}
	
	@Test(expected = NullPointerException.class)
	public void test_register_systemDev_with_two_null_params() {
		service.registerNewSystemDeveloper(null, null);
	}
	
	@Test
	public void test_register_systemDev_with_four_params_return_systemDev() {
		String name = "Test";
		LocalDate hiringDate = LocalDate.parse("2019-01-01");
		String[] languages = {"Java", "C#", "C++"};
		String[] certificates = {"OCA", "OCP"};
		
		SystemDeveloper newDev = service.registerNewSystemDeveloper(name, hiringDate, certificates, languages);
		
		assertEquals(name, newDev.getName());
		assertEquals(hiringDate, newDev.getDateHired());
		assertArrayEquals(languages, newDev.getLanguages());
		assertArrayEquals(certificates, newDev.getCertificates());
	}
	
	@Test(expected = NullPointerException.class)
	public void test_register_systemDev_with_four_null_params() {
		service.registerNewSystemDeveloper(null, null, null, null);
	}
	
	@Test
	public void test_remove_employee_systemDev_return_true() {
		String name = "Erik";
		LocalDate hiringDate = LocalDate.parse("2019-01-01");
		
		//Creating SysDev to remove
		SystemDeveloper toRemove = service.registerNewSystemDeveloper(name, hiringDate);
		
		assertTrue(service.removeEmployee(toRemove));
	}
	
	@Test
	public void test_remove_employee_salesPerson_return_true_and_client_is_unassigned() {
		String name = "Erik";
		LocalDate hiringDate = LocalDate.parse("2019-01-01");
		Client client = new Client("Test", "Testsson", "Test AB");
		
		//Creating SalesPerson to remove
		SalesPerson toRemove = service.registerNewSalesPerson(name, hiringDate);
		//Adding the Client to our SalesPerson
		toRemove.addClients(false, client);
		
		assertTrue(service.removeEmployee(toRemove));
		assertFalse(client.isAssigned());		
	}
	
	@Test
	public void test_remove_employee_not_in_storage_return_false() {
		Employee param = new SystemDeveloper("Test", LocalDate.now());
		
		assertFalse(service.removeEmployee(param));
	}
	
	@Test
	public void test_find_by_id_return_object_with_id() {
		int param = devId;
		Employee expected = dev;
		
		assertEquals(expected, service.findEmployeeById(param));
	}
	
	@Test(expected = EmployeeNotFoundException.class)
	public void test_find_by_id_throws_EmployeeNotFoundException() {
		int param = 10000;
		service.findEmployeeById(param);		
	}
	
	
	
	


}
