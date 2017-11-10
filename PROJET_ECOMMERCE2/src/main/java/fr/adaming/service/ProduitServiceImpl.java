package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.dao.IProduitDao;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Service("prodService") // Annotation pour le définir comme un bean
@Transactional //Pour spécifier au conteneur Spring que toutes les méthodes de la classe sont transactionnelles
public class ProduitServiceImpl implements IProduitService {
	
	@Autowired
	private IProduitDao prodDao;
	
	public void setCatDao(IProduitDao prodDao) {
		this.prodDao = prodDao;
	}

	@Override
	public List<Produit> getAllProduits() {
		return prodDao.getAllProduits();
	}

	@Override
	public Produit addProduit(Produit p) {
		return prodDao.addProduit(p);
	}

	@Override
	public int updateProduit(Produit p) {
		return prodDao.updateProduit(p);
	}

	@Override
	public int deleteProduit(Produit p) {
		return prodDao.deleteProduit(p);
	}

}
