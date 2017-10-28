package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IClientService;
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
	
	@EJB
	private IClientService clService;
	
	private Client client;
	
	private Produit prod;
	
	private int id_prod;
	
	private Commande commande;
	
	private LigneCommande ligneCommande;
	
	private Panier panier;
	
	
	// Constructeur vide
	public ClientManagedBean() {
		this.client=new Client();
		this.ligneCommande=new LigneCommande();
		this.panier=new Panier();
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

	public IClientService getClService() {
		return clService;
	}

	public void setClService(IClientService clService) {
		this.clService = clService;
	}

	public int getId_prod() {
		return id_prod;
	}

	public void setId_prod(int id_prod) {
		this.id_prod = id_prod;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public LigneCommande getLigneCommande() {
		return ligneCommande;
	}

	public void setLigneCommande(LigneCommande ligneCommande) {
		this.ligneCommande = ligneCommande;
	}

	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	public Produit getProd() {
		return prod;
	}

	public void setProd(Produit prod) {
		this.prod = prod;
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
		
		// Création d'un panier dans la session
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("panierActuel", this.panier);
		
		
		
		return "client";
		
	}
	
	public String ajouterProduit (){
		
		// Transmettre le produit à la ligne de commande
		ligneCommande.setAttProduit(prod);
		
		// Récupérer le panier de la session
		Panier panierCourses= (Panier) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("panierActuel");
		
		// Ajouter la ligne de commande au panier
		List<LigneCommande> liste=panierCourses.getListeLCommandes();
		liste.add(ligneCommande); 
		panierCourses.setListeLCommandes(liste);
		
		// Ajouter la liste du panier dans la session
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("panierActuel", panierCourses);
		
		return "client";
	}

}
