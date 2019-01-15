package se.lexicon.erik.staff_manager.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.lexicon.erik.staff_manager.data_access.EmployeeDao;
import se.lexicon.erik.staff_manager.data_access.EmployeeDaoListImpl;
import se.lexicon.erik.staff_manager.models.employee.Employee;
import se.lexicon.erik.staff_manager.models.employee.SalesPerson;
import se.lexicon.erik.staff_manager.models.employee.SystemDeveloper;

public class EmployeeDaoTest {
	
	private EmployeeDao underTest;
	
	private static final List<Employee> allEmployees;	
	private static final SalesPerson testSales;
	private static final SystemDeveloper testDev;
	
	private int testDevId;
	
	
	static {
		allEmployees = new ArrayList<>();
		SalesPerson sales = new SalesPerson("SaleTest Testsson", LocalDate.parse("2019-01-01"));
		SystemDeveloper dev = new SystemDeveloper("DevTest Testsson", LocalDate.parse("2018-12-01"));
		allEmployees.add(sales);
		allEmployees.add(dev);
		allEmployees.add(new SalesPerson("Test1", LocalDate.now()));
		allEmployees.add(new SystemDeveloper("Test2", LocalDate.now()));
		testSales = sales;
		testDev = dev;		
	}
	
		
	@Before
	public void init() {
		testDevId = testDev.getId();
		underTest = EmployeeDaoListImpl.get();
		allEmployees.forEach(underTest::save);
	}
	
	@After
	public void tearDown() {
		allEmployees.forEach(underTest::remove);
	}
	
	@Test
	public void testFindByIdReturnExpected() {
		Optional<Employee> expected = Optional.of(testDev);
		
		assertEquals(expected, underTest.findById(testDevId));
	}
	
	@Test
	public void testFindByNameReturnObjectsContainingName() {
		String searchName = "testsson";
		assertTrue(underTest.findByName(searchName).stream()
				.allMatch(e->e.getName().toLowerCase().contains(searchName.toLowerCase())));		
	}
	
	@Test
	public void testFindBySalaryAbove_24000() {
		double salaryParam = 24000;
		
		List<Employee> result = underTest.findBySalaryAbove(salaryParam);
		
		assertTrue(result.stream().allMatch(e->e.getSalary() > salaryParam));
	}
	
	@Test
	public void testFindBySalaryBelow_30000() {
		double salaryParam = 30000;
		
		List<Employee> result = underTest.findBySalaryBelow(salaryParam);
		
		assertTrue(result.stream().allMatch(e->e.getSalary() < salaryParam));
	}
	
	@Test
	public void testFindAllSystemDevs_all_instanceof_SystemDeveloper() {		
		List<SystemDeveloper> result = underTest.findAllSystemDevs();
		assertTrue(result.stream().allMatch(e -> e instanceof SystemDeveloper));		
	}
	
	@Test
	public void testFindAllSalesPersons_all_instanceof_SalesPerson() {
		List<SalesPerson> result = underTest.findAllSalesPersons();
		assertTrue(result.stream().allMatch(e -> e instanceof SalesPerson));
	}

}
