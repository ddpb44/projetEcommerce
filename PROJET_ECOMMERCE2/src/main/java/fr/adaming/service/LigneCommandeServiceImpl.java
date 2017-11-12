package fr.adaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ILigneCommandeDao;
import fr.adaming.model.LigneCommande;

@Service("lComService")
@Transactional
public class LigneCommandeServiceImpl implements ILigneCommandeService {

	@Autowired
	private ILigneCommandeDao lComDao;

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
