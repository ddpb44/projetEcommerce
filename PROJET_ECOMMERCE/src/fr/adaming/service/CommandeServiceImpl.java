package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ICommandeDao;
import fr.adaming.model.Commande;

@Stateful
public class CommandeServiceImpl implements ICommandeService{
	
	@EJB
	ICommandeDao comDao;

	// Getters/Setters
	public ICommandeDao getComDao() {
		return comDao;
	}

	public void setComDao(ICommandeDao comDao) {
		this.comDao = comDao;
	}

	// Méthodes
	@Override
	public Commande saveCommande(Commande commande) {
		
		return comDao.saveCommande(commande);
	}
	
	
	

}
