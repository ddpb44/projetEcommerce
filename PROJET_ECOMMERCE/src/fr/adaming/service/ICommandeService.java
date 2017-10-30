package fr.adaming.service;

import javax.ejb.Local;

import fr.adaming.model.Commande;

@Local
public interface ICommandeService {
	
	public Commande saveCommande (Commande commande);

}
