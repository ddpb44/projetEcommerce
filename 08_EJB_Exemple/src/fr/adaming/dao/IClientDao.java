package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Agent;
import fr.adaming.model.Client;

@Local
public interface IClientDao {

	public List<Client> getAllClients(Agent ag);

	public Client addClient(Client cl);
	
	public int updateCLient(Client cl);
	
	public int deleteClient(Client cl);
	
	public Client getClientById(Client cl);
	

}
