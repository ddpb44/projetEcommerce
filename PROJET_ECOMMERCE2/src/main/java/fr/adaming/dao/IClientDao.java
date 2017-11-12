package fr.adaming.dao;

import java.util.List;

import fr.adaming.model.Client;

public interface IClientDao {
	
	public Client saveClient (Client client);
	
	public List<Client> getAllClients ();
	
	public Client getClient (Client client);

}
