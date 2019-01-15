package se.lexicon.erik.staff_manager.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.lexicon.erik.staff_manager.data_access.ClientDao;
import se.lexicon.erik.staff_manager.data_access.ClientDaoListImpl;
import se.lexicon.erik.staff_manager.models.Client;

import static org.junit.Assert.*;

public class ClientDaoTest {
	
	private static final List<Client> allClients;
	private static final Client testClient;
	
	private ClientDao underTest;
	private int clientId;
	
	static {
		allClients = new ArrayList<>();
		testClient = new Client("Test Testsson", "test@test.com", "Test company");
		allClients.add(testClient);
		allClients.add(new Client("Test1","test1@test.com", "Test company"));
		allClients.add(new Client("Test2","test2@test.com", "Test company"));
	}
	
	@Before
	public void init() {
		underTest = ClientDaoListImpl.get();
		clientId = testClient.getId();
		allClients.forEach(underTest::save);
	}
	
	@After
	public void tearDown() {
		allClients.forEach(underTest::remove);
	}
	
	@Test
	public void test_save_with_duplicate_email_return_false() {
		Client client = new Client("Test", "test@test.com", "Test company");
		
		assertFalse(underTest.save(client));
	}
	
	@Test
	public void test_findById_return_optional_of_testClient() {
		Optional<Client> expected = Optional.of(testClient);
		
		assertEquals(expected, underTest.findById(clientId));		
	}
	
	@Test
	public void test_findByName_return_list_of_objects_containing_name() {
		String searchName = "Test Testsson";
		
		List<Client> result = underTest.findByName(searchName);
		
		assertTrue(result.stream().allMatch(c->c.getName().contains(searchName)));		
	}
	
	@Test
	public void test_findByCompany_return_list_of_objects_containing_searchName() {
		String searchName = "Test company";
		
		List<Client> result = underTest.findByCompany(searchName);
		
		assertTrue(result.stream().allMatch(c->c.getCompany().contains(searchName)));
	}
	
	@Test
	public void test_findAllClientsByAssigned() {
		testClient.assign(); //Making assign true
		
		assertTrue(underTest.findAllClientsByAssign(true).stream()
				.allMatch(Client::isAssigned));
		
		assertFalse(underTest.findAllClientsByAssign(false).stream()
				.allMatch(Client::isAssigned));		
	}
	
	

}
