package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Client;

@Local
public interface IClientService {
	
	public Client saveClient (Client client);
	
	public List<Client> getAllClients ();
	
	public Client getClient (Client client);
}
