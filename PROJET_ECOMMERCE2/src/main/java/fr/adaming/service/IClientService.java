package fr.adaming.service;

import java.util.List;

import fr.adaming.model.Client;

public interface IClientService {
	
	public Client saveClient (Client client);
	
	public List<Client> getAllClients ();
	
	public Client getClient (Client client);
}
