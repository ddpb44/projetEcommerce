package fr.adaming.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="commandes")
public class Commande implements Serializable{
	
	// Attributs
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_commande;
	
	@Temporal(TemporalType.DATE)
	private Date date_commande;
	
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

	public Date getDate_commande() {
		return date_commande;
	}

	public void setDate_commande(Date date_commande) {
		this.date_commande = date_commande;
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
		return "Commande [id_commande=" + id_commande + ", date_commande=" + date_commande + ", ligneCom=" + ligneCom
				+ ", attClient=" + attClient + "]";
	}

	
	

	
	

	
	
	

}
