package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IClientDao;
import fr.adaming.model.Produit;

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
	public Produit getProdById(int id) {
		
		return clientDao.getProdById(id);
	}
	
	

}
