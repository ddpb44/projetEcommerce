package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IClientDao;
import fr.adaming.model.Client;

@Stateful
public class ClientServiceImpl implements IClientService{
	
	@EJB
	IClientDao clientDao;

	// Getters/Setters
	public IClientDao getClientDao() {
		return clientDao;
	}

	public void setClientDao(IClientDao clientDao) {
		this.clientDao = clientDao;
	}

	// Méthodes
	@Override
	public Client saveClient(Client client) {
		
		return clientDao.saveClient(client);
	}

	@Override
	public List<Client> getAllClients() {
		
		return clientDao.getAllClients();
	}

	@Override
	public Client getClient(Client client) {
		
		return clientDao.getClient(client);
	}

	
	
	
	

}
