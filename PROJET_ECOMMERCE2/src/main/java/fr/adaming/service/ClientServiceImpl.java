package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IClientDao;
import fr.adaming.model.Client;

@Service("clService")
@Transactional
public class ClientServiceImpl implements IClientService{
	
	@Autowired
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
