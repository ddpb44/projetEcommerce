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
@Table(name="commandes")
public class Commande implements Serializable{
	
	// Attributs
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_commande;
	
	private Long date_naissance;
	
	// Association UML --> JAVA
	@OneToMany(mappedBy="attCommande", fetch=FetchType.EAGER)
	private List<LigneCommande> ligneCom;
	
	@ManyToOne
	@JoinColumn(name="client_id", referencedColumnName="id_client")
	private Client attClient;

	// Constructeur vide
	public Commande() {
		super();
	}

	// Getters/Setters

	public Long getId_commande() {
		return id_commande;
	}

	public void setId_commande(Long id_commande) {
		this.id_commande = id_commande;
	}


	public Long getDate_naissance() {
		return date_naissance;
	}

	public void setDate_naissance(Long date_naissance) {
		this.date_naissance = date_naissance;
	}

	public List<LigneCommande> getLigneCom() {
		return ligneCom;
	}

	public void setLigneCom(List<LigneCommande> ligneCom) {
		this.ligneCom = ligneCom;
	}

	public Client getAttClient() {
		return attClient;
	}

	public void setAttClient(Client attClient) {
		this.attClient = attClient;
	}

	// Méthode toString()
	@Override
	public String toString() {
		return "Commande [id_commande=" + id_commande + ", date_naissance=" + date_naissance + ", ligneCom=" + ligneCom
				+ ", attClient=" + attClient + "]";
	}

	
	

	
	
	

}
