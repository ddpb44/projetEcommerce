package fr.adaming.service;

import java.util.List;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

public interface IProduitService {
	
	public List<Produit> getAllProduits();

	public Produit addProduit(Produit p);
	
	public int updateProduit(Produit p);
	
	public int deleteProduit(Produit p);
	
	
}
