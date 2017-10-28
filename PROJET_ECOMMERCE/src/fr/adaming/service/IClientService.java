package fr.adaming.service;

import javax.ejb.Local;

import fr.adaming.model.Produit;

@Local
public interface IClientService {
	
	public Produit getProdById (int id);
	
}
