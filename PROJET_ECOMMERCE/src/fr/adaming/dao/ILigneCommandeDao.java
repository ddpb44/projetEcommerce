package fr.adaming.dao;

import javax.ejb.Local;

import fr.adaming.model.LigneCommande;

@Local
public interface ILigneCommandeDao {
	
	public void saveLCommande (LigneCommande l);

}
