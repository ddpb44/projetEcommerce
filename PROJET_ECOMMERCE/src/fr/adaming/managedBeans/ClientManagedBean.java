package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;

@ManagedBean(name="clMB")
@RequestScoped
public class ClientManagedBean implements Serializable{
	
	// Transformation de l'association UML en JAVA
	
	// Injection de service
	@EJB
	private ICategorieService cService;
	
	@EJB
	private IProduitService pService;
	
	private Client client;

	// Constructeur vide
	public ClientManagedBean() {
		this.client=new Client();
	}

	// Getters/Setters
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
		
	public ICategorieService getcService() {
		return cService;
	}

	public void setcService(ICategorieService cService) {
		this.cService = cService;
	}

	public IProduitService getpService() {
		return pService;
	}

	public void setpService(IProduitService pService) {
		this.pService = pService;
	}

	// Méthodes ManagedBean
	public String entrerSite () {
		
		// Récupération des listes
		List<Categorie> listeCat=cService.getAllCategorie();
		List<String> listeNCat=cService.getAllCatNames();
		List<Produit> listeProd=pService.getAllProduits();
		
		// Ajouter les listes dans la session
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("catListe", listeCat);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nCatListe", listeNCat);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("prodListe", listeProd);
		
		return "client";
		
	}

}
