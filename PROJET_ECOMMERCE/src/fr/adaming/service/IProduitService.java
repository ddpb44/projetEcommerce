package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Local
public interface IProduitService {
	
	public List<Produit> getAllProduits();

	public Produit addProduit(Produit p);
	
	public int updateProduit(Produit p);
	
	public int deleteProduit(Produit p);
	
	
}
