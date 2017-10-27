package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.model.Admin;
import fr.adaming.model.Categorie;

@Stateful
public class CategorieServiceImpl implements ICategorieService{
	
	@EJB
	ICategorieDao categorieDao;
	
	// Getters/Setters
	public ICategorieDao getCategorieDao() {
		return categorieDao;
	}

		public void setCategorieDao(ICategorieDao categorieDao) {
		this.categorieDao = categorieDao;
	}

	// Méthodes
	@Override
	public Categorie addCategorie(Categorie cat) {
		
		return categorieDao.addCategorie(cat);
	}

	@Override
	public int deleteCategorie(Categorie cat) {
		
		return categorieDao.deleteCategorie(cat);
	}

	@Override
	public int updateCategorie(Categorie cat) {
		
		return categorieDao.updateCategorie(cat);
	}

	@Override
	public List<Categorie> getAllCategorie() {
		
		return categorieDao.getAllCategorie();
	}

	@Override
	public Categorie getCatByName(String name) {
		return categorieDao.getCatByName(name);
	}

	@Override
	public List<String> getAllCatNames() {
		return categorieDao.getAllCatNames();
	}

}
