package se.lexicon.erik.staff_manager.data_access;

import java.util.List;
import java.util.Optional;

import se.lexicon.erik.staff_manager.models.Client;

public interface ClientDao {

	boolean save(Client client);

	boolean remove(Client client);

	Optional<Client> findById(int id);

	Optional<Client> findByEmail(String email);

	List<Client> findByName(String name);

	List<Client> findByCompany(String company);
	
	List<Client> findAllClientsByAssign(boolean isAssigned);
	
	List<Client> getAllClients();

}