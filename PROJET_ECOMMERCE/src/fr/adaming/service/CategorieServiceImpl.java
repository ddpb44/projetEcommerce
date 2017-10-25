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

	@Override
	public Categorie addCategorie(Categorie cat) {
		
		return categorieDao.addCategorie(cat);
	}

	@Override
	public int deleteCategorie(Categorie cat) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateCategorie(Categorie cat) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Categorie> getAllCategorie() {
		
		return categorieDao.getAllCategorie();
	}

}
