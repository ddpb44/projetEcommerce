package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ILigneCommandeDao;
import fr.adaming.model.LigneCommande;

@Stateful
public class LigneCommandeServiceImpl implements ILigneCommandeService {

	@EJB
	ILigneCommandeDao lComDao;

	// Getters/Setters

	public ILigneCommandeDao getlComDao() {
		return lComDao;
	}

	public void setlComDao(ILigneCommandeDao lComDao) {
		this.lComDao = lComDao;
	}

	// Méthode
	@Override
	public void saveLCommande(LigneCommande l) {

		lComDao.saveLCommande(l);

	}

}
