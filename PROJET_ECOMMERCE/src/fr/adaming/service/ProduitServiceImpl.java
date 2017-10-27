package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.dao.IProduitDao;
import fr.adaming.model.Produit;

@Stateful
public class ProduitServiceImpl implements IProduitService{

	@EJB
	IProduitDao prodDao;
	
	public IProduitDao getProdDao() {
		return prodDao;
	}

	public void setProdDao(IProduitDao prodDao) {
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteProduit(Produit p) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Produit getProduitById(Produit p) {
		// TODO Auto-generated method stub
		return null;
	}

}
