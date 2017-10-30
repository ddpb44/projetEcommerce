package fr.adaming.dao;

import javax.ejb.Local;

import fr.adaming.model.Commande;

@Local
public interface ICommandeDao {
	
	public Commande saveCommande (Commande commande);

}
