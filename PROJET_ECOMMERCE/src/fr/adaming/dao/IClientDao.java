package fr.adaming.dao;

import javax.ejb.Local;

import fr.adaming.model.Produit;

@Local
public interface IClientDao {
	
	public Produit getProdById (int id);

}
