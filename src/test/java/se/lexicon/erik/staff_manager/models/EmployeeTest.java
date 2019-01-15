package se.lexicon.erik.staff_manager.models;

import java.time.LocalDate;

import org.junit.*;
import static org.junit.Assert.*;

import se.lexicon.erik.staff_manager.models.Client;
import se.lexicon.erik.staff_manager.models.employee.SalesPerson;
import se.lexicon.erik.staff_manager.models.employee.SystemDeveloper;

public class EmployeeTest {
	
	private SystemDeveloper testDev;
	private SalesPerson testSales;
	private Client testClient;
	
	@Before
	public void init() {
		testDev = new SystemDeveloper("Test TestDev", LocalDate.parse("2019-01-01"));
		testSales = new SalesPerson("Test TestSales", LocalDate.parse("2019-01-01"));
		testClient = new Client("TestClient", "test@test.com", "Test AB");
		
	}
	
	//**SYSTEMDEVELOPER TESTS START***
	
	@Test
	public void test_addCertificate() {
		String certificate = "TestCertificate";
		double expectedSalary = 26000;
		assertTrue(testDev.addCertificate(certificate));
		assertEquals(expectedSalary, testDev.getSalary(), 0);
		
	}
	
	@Test
	public void test_addCertificate_duplicate_return_false() {
		//Given
		testDev.addCertificate("OCA");		
		String certificate = "OCA";
		double expectedSalary = 26000;
		assertFalse(testDev.addCertificate(certificate));
		assertEquals(expectedSalary, testDev.getSalary(),0);
	}
	
	@Test
	public void test_addCertificate_null_return_false() {
		assertFalse(testDev.addCertificate(null));
	}
	
	@Test
	public void test_addLanguage() {
		String language = "Java";
		double expectedSalary = 26500;
		assertTrue(testDev.addLanguage(language));
		assertEquals(expectedSalary, testDev.getSalary(), 0);		
	}
	
	@Test
	public void test_addLanguage_duplicate_return_false() {
		String language = "Java";
		double expectedSalary = 25000 + 1500;
		testDev.addLanguage(language);
		
		assertFalse(testDev.addLanguage(language));
		assertEquals(expectedSalary, testDev.getSalary(),0);		
	}
	
	@Test
	public void test_addLanguage_null_return_false() {
		assertFalse(testDev.addLanguage(null));
	}
	
	@Test
	public void test_SystemDev_invoke_calculateSalary_withNoChanges_salary_unchanged() {
		testDev.addCertificate("OCA");
		testDev.addLanguage("Java");
		double expectedSalary = 25000 + 1000 + 1500;
		
		testDev.calculateSalary();
		
		assertEquals(expectedSalary, testDev.getSalary(),0);		
	}
	
	//**SYSTEMDEVELOPER TESTS END***
	
	//**SALESPERSON TESTS START***
	
	@Test
	public void test_addClient_notAquired_return_true() {
		double expectedSalary = 25000 + 500;
		
		assertTrue(testSales.addClients(false, testClient));
		assertEquals(expectedSalary, testSales.getSalary(),0);
	}
	
	@Test
	public void test_addClient_aquired_return_true() {
		double expectedSalary = 25000 + 500 + 1500;
		
		assertTrue(testSales.addClients(true, testClient));
		assertEquals(expectedSalary, testSales.getSalary(),0);
	}
	
	@Test
	public void test_addClient_duplicate_aquired_return_false() {
		double expectedSalary = 25000 + 1500 + 500;
		testSales.addClients(true, testClient);
		
		assertFalse(testSales.addClients(true, testClient));
		assertEquals(expectedSalary, testSales.getSalary(),0);		
	}
	
	@Test
	public void test_addClient_null_return_false() {
		assertFalse(testSales.addClients(false, null));
	}
	
	@Test
	public void test_SalesPerson_invoke_calculateSalary_withNoChanges_salary_unchanged() {
		testSales.addClients(true, testClient); //Invokes calculate salary 
		double expectedSalary = 25000 + 1500 + 500;
		
		testSales.calculateSalary(); //Invoke calculate salary again
		
		assertEquals(expectedSalary, testSales.getSalary(),0);
	}
	
	@Test
	public void test_remove_testClient_from_SalesPerson_with_more_than_one_client_return_true() {
		testSales.addClients(true, testClient);
		testSales.addClients(false, new Client("Nisse", "nisse@nisse.com", "acme"));
		double oldSalary = 25000 + 1500 + 500 + 500;
		double newExpectedSalary = oldSalary - 500;
		
		assertTrue(testSales.unassignClient(testClient));
		assertEquals(newExpectedSalary, testSales.getSalary(),0);
	}
	
	@Test
	public void test_remove_testClient_from_SalesPerson_with_only_one_client_return_true() {
		testSales.addClients(true, testClient);
		double oldSalary = 25000 + 1500 + 500;
		double newExpectedSalary = oldSalary - 500;
		
		assertTrue(testSales.unassignClient(testClient));
		assertEquals(newExpectedSalary, testSales.getSalary(),0);
	}
	
	@Test
	public void test_remove_testClient_from_SalesPerson_with_more_than_two_clients_return_true() {
		testSales.addClients(true, new Client("test1", "test1", "test1"));
		testSales.addClients(false, testClient);
		testSales.addClients(false, new Client("test2", "test2", "test2"));
		double oldSalary = 25000 + 1500 + 500 + 500 + 500;
		double newExpectedSalary = oldSalary - 500;
		
		assertTrue(testSales.unassignClient(testClient));
		assertEquals(newExpectedSalary, testSales.getSalary(),0);
		
	}
	
	//**SALESPERSON TESTS END**
	
	
	
	
	
	

}
