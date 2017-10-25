package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Agent;
import fr.adaming.model.Client;

@Local
public interface IClientService {
	
	public List<Client> getAllClients(Agent ag);

	public Client addClient(Client cl, Agent ag);
	
	public int updateCLient(Client cl, Agent ag);
	
	public int deleteClient(Client cl, Agent ag);
	
	public Client getClientById(Client cl, Agent ag);
	
}
