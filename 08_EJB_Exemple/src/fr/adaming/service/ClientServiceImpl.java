package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.model.Agent;
import fr.adaming.model.Client;
import fr.adaming.dao.ClientDaoImpl;
import fr.adaming.dao.IClientDao;

@Stateful
public class ClientServiceImpl implements IClientService {

	@EJB
	IClientDao clientDao;
	
	@Override
	public List<Client> getAllClients(Agent ag) {
		return clientDao.getAllClients(ag);
	}

	@Override
	public Client addClient(Client cl, Agent ag) {
		//Mettre le client et l'agent en relation
		cl.setAgent(ag);
		return clientDao.addClient(cl);
	}

	@Override
	public int updateCLient(Client cl, Agent ag) {
		cl.setAgent(ag);
		return clientDao.updateCLient(cl);
	}

	@Override
	public int deleteClient(Client cl, Agent ag) {
		cl.setAgent(ag);
		return clientDao.deleteClient(cl);
	}

	@Override
	public Client getClientById(Client cl, Agent ag) {
		cl.setAgent(ag);
		return clientDao.getClientById(cl);
	}
	
}