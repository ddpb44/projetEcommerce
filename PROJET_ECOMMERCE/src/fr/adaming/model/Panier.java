package fr.adaming.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;

public class Panier implements Serializable{
	
	//Attributs
	private List<LigneCommande> listeLCommandes=new ArrayList<LigneCommande>();

	//Constructeur
	public Panier() {
		super();
	}

	//Getters et setters
	public List<LigneCommande> getListeLCommandes() {
		return listeLCommandes;
	}

	public void setListeLCommandes(List<LigneCommande> listeLCommandes) {
		this.listeLCommandes = listeLCommandes;
	}

	@Override
	public String toString() {
		return "Panier [listeLCommandes=" + listeLCommandes + "]";
	}
	
	

}
