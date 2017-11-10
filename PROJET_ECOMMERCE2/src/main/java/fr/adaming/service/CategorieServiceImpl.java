package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IAdminDao;
import fr.adaming.dao.ICategorieDao;
import fr.adaming.model.Admin;
import fr.adaming.model.Categorie;

@Service("catService") // Annotation pour le définir comme un bean
@Transactional //Pour spécifier au conteneur Spring que toutes les méthodes de la classe sont transactionnelles
public class CategorieServiceImpl implements ICategorieService{

	@Autowired
	private ICategorieDao catDao;
	
	public void setCatDao(ICategorieDao catDao) {
		this.catDao = catDao;
	}

	@Override
	public Categorie addCategorie(Categorie cat) {
		return catDao.addCategorie(cat);
	}

	@Override
	public int deleteCategorie(Categorie cat) {
		return catDao.deleteCategorie(cat);
	}

	@Override
	public int updateCategorie(Categorie cat) {
		return catDao.updateCategorie(cat);
	}

	@Override
	public List<Categorie> getAllCategorie() {
		return catDao.getAllCategorie();
	}

	@Override
	public Categorie getCatByName(String name) {
		return catDao.getCatByName(name);
	}

	@Override
	public List<String> getAllCatNames() {
		return catDao.getAllCatNames();
	}
	
	
}
