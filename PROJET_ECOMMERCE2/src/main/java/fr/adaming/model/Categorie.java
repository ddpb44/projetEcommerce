package fr.adaming.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="categories")
public class Categorie implements Serializable{
	
	
	//Attributs
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_cat;
	
	private String nomCategorie;
	
	private String description;
	
	//Association UML en JAVA
	@OneToMany(mappedBy="cat", fetch=FetchType.LAZY, cascade=CascadeType.REMOVE)
	private List<Produit> listeProduits;

	//Constructeur
	public Categorie() {
		super();
	}

	//Getters et Setters
	public Long getId_cat() {
		return id_cat;
	}

	public void setId_cat(Long id_cat) {
		this.id_cat = id_cat;
	}

	public String getNomCategorie() {
		return nomCategorie;
	}

	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Categorie [id_cat=" + id_cat + ", nomCategorie=" + nomCategorie + ", description=" + description + "]";
	}
	
}
