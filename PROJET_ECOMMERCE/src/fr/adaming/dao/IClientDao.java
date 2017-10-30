package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Client;

@Local
public interface IClientDao {
	
	public Client saveClient (Client client);
	
	public List<Client> getAllClients ();
	
	public Client getClient (Client client);

}
