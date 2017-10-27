package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Produit;

@Local
public interface IProduitDao {
	
	public List<Produit> getAllProduits();

	public Produit addProduit(Produit p);
	
	public int updateProduit(Produit p);
	
	public int deleteProduit(Produit p);
	
	public Produit getProduitById(Produit p);
	

}
