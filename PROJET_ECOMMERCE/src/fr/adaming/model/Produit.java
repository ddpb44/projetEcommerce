package fr.adaming.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="produits")
public class Produit implements Serializable {
	
	
	//Attributs
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_produit;
	
	private String designation;
	
	private String description;
	
	private double prix;
	
	private int quantité;
	
	private boolean selectionne;
	
	//Association UML en JAVA
	@ManyToOne
	@JoinColumn(name="cat_id", referencedColumnName="id_cat")
	private Categorie cat;
	
	@OneToMany(mappedBy="attProduit", fetch=FetchType.EAGER)
	private List<LigneCommande> listeLCommandes;

	//Constructeur 
	public Produit() {
		super();
	}

	//Getters et setters
	public Long getId_produit() {
		return id_produit;
	}

	public void setId_produit(Long id_produit) {
		this.id_produit = id_produit;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getQuantité() {
		return quantité;
	}

	public void setQuantité(int quantité) {
		this.quantité = quantité;
	}

	public boolean isSelectionne() {
		return selectionne;
	}

	public void setSelectionne(boolean selectionne) {
		this.selectionne = selectionne;
	}

	public Categorie getCat() {
		return cat;
	}

	public void setCat(Categorie cat) {
		this.cat = cat;
	}

	public List<LigneCommande> getListeLCommandes() {
		return listeLCommandes;
	}

	public void setListeLCommandes(List<LigneCommande> listeLCommandes) {
		this.listeLCommandes = listeLCommandes;
	}

	@Override
	public String toString() {
		return "Produit [id_produit=" + id_produit + ", designation=" + designation + ", description=" + description
				+ ", prix=" + prix + ", quantité=" + quantité + ", selectionne=" + selectionne + ", cat=" + cat
				+ ", listeLCommandes=" + listeLCommandes + "]";
	}
	
	
	

}
