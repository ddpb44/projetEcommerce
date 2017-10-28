package fr.adaming.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lignes_de_commande")
public class LigneCommande implements Serializable {

	// Attributs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_lc;

	private int quantite;

	private double prix;

	// Associations UML --> JAVA
	@ManyToOne
	@JoinColumn(name = "produit_id", referencedColumnName = "id_produit")
	private Produit attProduit;

	@ManyToOne
	@JoinColumn(name = "commande_id", referencedColumnName = "id_commande")
	private Commande attCommande;

	// Constructeur vide
	public LigneCommande() {
		super();
	}

	// Getters/Setters
	public int getId_lc() {
		return id_lc;
	}

	public void setId_lc(int id_lc) {
		this.id_lc = id_lc;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Produit getAttProduit() {
		return attProduit;
	}

	public void setAttProduit(Produit attProduit) {
		this.attProduit = attProduit;
	}

	public Commande getAttCommande() {
		return attCommande;
	}

	public void setAttCommande(Commande attCommande) {
		this.attCommande = attCommande;
	}

	// Méthode toString()
	@Override
	public String toString() {
		return "LigneCommande [id_lc=" + id_lc + ", quantite=" + quantite + ", prix=" + prix + ", attProduit="
				+ attProduit + ", attCommande=" + attCommande + "]";
	}

}
