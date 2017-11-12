package fr.adaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ICommandeDao;
import fr.adaming.model.Commande;

@Service("comService")
@Transactional
public class CommandeServiceImpl implements ICommandeService{
	
	@Autowired
	private ICommandeDao comDao;

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
