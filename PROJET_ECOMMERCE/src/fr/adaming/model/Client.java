package fr.adaming.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="clients")
public class Client implements Serializable {
	
	// Attributs
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_client;
	
	private String nomClient;
	
	private String adresse;
	
	private String email;
	
	private String tel;
	
	
	// Association UML --> JAVA
	@OneToMany(mappedBy="attClient", fetch=FetchType.EAGER)
	private List<Commande> listeCommande;


	// Constructeur vide
	public Client() {
		super();
	}


	// Getters/Setters
	public Long getId_client() {
		return id_client;
	}


	public void setId_client(Long id_client) {
		this.id_client = id_client;
	}


	public String getNomClient() {
		return nomClient;
	}


	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public List<Commande> getListeCommande() {
		return listeCommande;
	}


	public void setListeCommande(List<Commande> listeCommande) {
		this.listeCommande = listeCommande;
	}


	// Méthode toString()
	@Override
	public String toString() {
		return "Client [id_client=" + id_client + ", nomClient=" + nomClient + ", adresse=" + adresse + ", email="
				+ email + ", tel=" + tel + ", listeCommande=" + listeCommande + "]";
	}
	
	
	
	
	
	
	

}
