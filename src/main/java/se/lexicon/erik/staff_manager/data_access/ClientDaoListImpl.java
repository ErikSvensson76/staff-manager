package se.lexicon.erik.staff_manager.data_access;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import se.lexicon.erik.staff_manager.models.Client;

public class ClientDaoListImpl implements ClientDao {
	
	private static final ClientDao instance;
	
	static {
		instance = new ClientDaoListImpl();
	}
	
	public static ClientDao get() {
		return instance;
	}
	
	private List<Client> storage;
	
	private ClientDaoListImpl(){
		storage = new ArrayList<>();
	}
	
	@Override
	public boolean save(Client client) {
		if(client == null) {
			return false;
		}
		
		if(findByEmail(client.getEmail()).isPresent()) {
			return false;
		}
		
		if(storage.contains(client)) {
			return false;
		}
		return storage.add(client);
	}
	
	@Override
	public boolean remove(Client client) {
		if(client == null) {
			return false;
		}
		if(!storage.contains(client)) {
			return false;
		}
		return storage.remove(client);
	}
	
	@Override
	public Optional<Client> findById(int id){
		Optional<Client> result = Optional.empty();
		
		for(Client c : storage) {
			if(c.getId() == id) {
				result = Optional.of(c);
			}
		}
		return result;
	}
	
	@Override
	public Optional<Client> findByEmail(String email){		
		Optional<Client> result = Optional.empty();
		
		for(Client c: storage) {
			if(c.getEmail().toLowerCase().equals(email.toLowerCase())) {
				result = Optional.of(c);
			}				
		}
		return result;
	}
	
	@Override
	public List<Client> findByName(String name){
		List<Client> result = new ArrayList<>();
		
		for(Client c: storage) {
			if(c.getName().toLowerCase().contains(name.toLowerCase())) {
				result.add(c);
			}
		}
		return result;
	}
	
	@Override
	public List<Client> findByCompany(String company){
		List<Client> result = new ArrayList<>();
		
		for(Client c: storage) {
			if(c.getCompany().toLowerCase().contains(company.toLowerCase())) {
				result.add(c);
			}
		}
		return result;
	}

	@Override
	public List<Client> findAllClientsByAssign(boolean isAssigned) {
		return storage.stream()
				.filter(c -> c.isAssigned() == isAssigned)
				.collect(Collectors.toList());
	}

	@Override
	public List<Client> getAllClients() {
		return this.storage;
	}

}
