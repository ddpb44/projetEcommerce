package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IClientService;
import fr.adaming.service.ICommandeService;
import fr.adaming.service.ILigneCommandeService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "clMB")
@RequestScoped
public class ClientManagedBean implements Serializable {

	// Transformation de l'association UML en JAVA

	// Injection de service
	@ManagedProperty(value="#{catService}")
	private ICategorieService cService;

	@ManagedProperty(value="#{prodService}")
	private IProduitService pService;

	@ManagedProperty(value="#{clService}")
	private IClientService clService;

	@ManagedProperty(value="#{comService}")
	private ICommandeService comService;

	@ManagedProperty(value="#{lComService}")
	private ILigneCommandeService lComService;

	private Client client;

	private Produit prod;

	private int id_prod;

	private Commande commande;

	private LigneCommande ligneCommande;

	private List<LigneCommande> listeLCom = new ArrayList<LigneCommande>();
	
	private Calendar c=Calendar.getInstance();

	// Constructeur vide
	public ClientManagedBean() {
		this.client = new Client();
		this.ligneCommande = new LigneCommande();
		this.commande = new Commande();
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

	public ICommandeService getComService() {
		return comService;
	}

	public void setComService(ICommandeService comService) {
		this.comService = comService;
	}

	public ILigneCommandeService getlComService() {
		return lComService;
	}

	public void setlComService(ILigneCommandeService lComService) {
		this.lComService = lComService;
	}

	public List<LigneCommande> getListeLCom() {
		return listeLCom;
	}

	public void setListeLCom(List<LigneCommande> listeLCom) {
		this.listeLCom = listeLCom;
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

	public Produit getProd() {
		return prod;
	}

	public void setProd(Produit prod) {
		this.prod = prod;
	}

	// Méthodes ManagedBean
	public String entrerSite() {

		// Récupération des listes
		List<Categorie> listeCat = cService.getAllCategorie();
		List<String> listeNCat = cService.getAllCatNames();
		List<Produit> listeProd = pService.getAllProduits();

		// Ajouter les listes dans la session
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("catListe", listeCat);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nCatListe", listeNCat);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("prodListe", listeProd);

		// Création d'une liste de lignes de commandes dans la session
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("panierActuel", this.listeLCom);

		return "client";

	}

	public String ajouterProduit() {
		// Récupération du produit
		// Produit produitAdd=clService.getProdById(id_prod);

		// Récupérer la liste de lignes de commandes de la session
		List<LigneCommande> listeCourses = (List<LigneCommande>) FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get("panierActuel");

//		System.out.println(
//				"\n--------------------------------------------------------------------------Liste courses avant ajout"
//						+ listeCourses);

		// Vérifier si le produit est dans la liste de commande
		Iterator<LigneCommande> it = listeCourses.iterator();

		LigneCommande ligne = new LigneCommande();

//		System.out.println(
//				"\n--------------------------------------------------------------------------Nouvelle ligne commande pour itérator"
//						+ ligne);

		// Parcours de la liste à la recherche d'une correspondance entre id de
		// produits

		if (it.hasNext()) { // liste avec éléments

			do {
				ligne = it.next();
			} while (it.hasNext() && ligne.getAttProduit().getId_produit() != prod.getId_produit());

		}
		// else {
		//
		// while (it.hasNext() && ligne.getAttProduit().getId_produit() !=
		// prod.getId_produit()) {
		// ligne = it.next();
		// }
		//
		// }

//		System.out
//				.println("\n--------------------------------------------------------------------------Fin de l'itérator"
//						+ ligne);
		if (ligne.getAttProduit() == null || ligne.getAttProduit().getId_produit() != prod.getId_produit()) {

			// Pas de correspondance --> Nouveau produit

			if (prod.getQuantite() == 0) { // Rupture de stock

				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
						"Ce produit est en rupture de stock! Impossible de l'ajouter à votre panier!"));

			} else { // Stock disponible

				// Créer une nouvelle ligne de commande
				ligneCommande.setAttProduit(prod);
				ligneCommande.setPrix(prod.getPrix());
				ligneCommande.setQuantite(ligneCommande.getQuantite() + 1);
				ligneCommande.setPrix2(ligneCommande.getPrix()*ligneCommande.getQuantite());

				// Ajouter la ligne de commande à la liste
				listeCourses.add(this.ligneCommande);

				// Rafraichir la liste dans la session
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("panierActuel",
						listeCourses);
			}

		} else { // Correspondance --> Le produit a déjà été sélectionné

			if (ligne.getQuantite() +1 > prod.getQuantite()) { // Rupture de stock

				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
						"Ce produit est en rupture de stock! Impossible de l'ajouter à votre panier!"));

			} else { // Stock disponible

				// Mettre à jour la ligne de commande
				ligne.setQuantite(ligne.getQuantite() + 1);
				ligne.setPrix2(ligne.getPrix()*ligne.getQuantite());

				// Rafraichir la liste dans la session
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("panierActuel",
						listeCourses);

			}
		}
//		System.out.println(
//				"\n--------------------------------------------------------------------------Liste de courses après l'ajout"
//						+ listeCourses);

		return "client";
	}

	public String enleverProduit() {

		List<LigneCommande> listeCourses = (List<LigneCommande>) FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get("panierActuel");

//		System.out.println(
//				"\n--------------------------------------------------------------------------Liste courses avant retrait"
//						+ listeCourses);

		// Vérifier si le produit est dans la liste de commande
		Iterator<LigneCommande> it = listeCourses.iterator();

		LigneCommande ligne = new LigneCommande();

//		System.out.println(
//				"\n--------------------------------------------------------------------------Nouvelle ligne commande pour itérator"
//						+ ligne);

		// Parcours de la liste à la recherche d'une correspondance entre id de
		// produits

		if (it.hasNext()) { // liste avec éléments

			do {
				ligne = it.next();
			} while (it.hasNext() && ligne.getAttProduit().getId_produit() != prod.getId_produit());

		}

//		System.out
//				.println("\n--------------------------------------------------------------------------Fin de l'itérator"
//						+ ligne);

		if (ligne.getAttProduit() == null || ligne.getAttProduit().getId_produit() != prod.getId_produit()) {

			// Pas de correspondance --> Produit non selectionné

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Un produit doit être selectionné avant de pouvoir le retirer du panier"));

		} else { // Correspondance --> Retrait possible

			if (ligne.getQuantite() == 1) { // 1 unité selectionnée --> Effacer
											// la ligne du panier
				listeCourses.remove(ligne);

			} else { // Plusieurs unités selectionnées --> retirer une unité

				ligne.setQuantite(ligne.getQuantite() - 1);

			}

			// Rafraichir la liste dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("panierActuel", listeCourses);

		}
//		System.out.println(
//				"\n--------------------------------------------------------------------------Liste de courses après le retrait"
//						+ listeCourses);

		return "client";

	}

	public String enregistrerCommande() {

		// Récupération de la liste de clients inscrits dans la BD
		List<Client> listeClients = clService.getAllClients();

		if (listeClients.isEmpty()) { // La liste de clients est vide

			// Ajout du client à la BD
			clService.saveClient(client);

		} else { // Des clients sont déjà enregistrés

			// Parcours de la liste à la recherche d'une correspondance
			Iterator<Client> it = listeClients.iterator();

			Client cl = new Client();

			do {
				it.next();
			} while (it.hasNext()
					&& (cl.getNomClient() != client.getNomClient() || cl.getAdresse() != client.getAdresse()
							|| cl.getEmail() != client.getEmail() || cl.getTel() != client.getTel()));

			if (cl.getNomClient() != client.getNomClient() || cl.getAdresse() != client.getAdresse()
					|| cl.getEmail() != client.getEmail() || cl.getTel() != client.getTel()) {

				// Pas de correspondance complète --> Ajout du client à la BD
				clService.saveClient(client);

			}

		}

		// Récupération du client avec ID (après passage dans la BD)
		Client clientEnregistre = clService.getClient(client);

		// Passage du client et de la date à la commande
		commande.setAttClient(clientEnregistre);
		 commande.setDate_commande(c.getTime());

		// Enregistrement de la commande dans la base de données
		Commande comEnregistree = comService.saveCommande(commande);

		// Récupération de la liste de lignes de commandes (panier) à partir de
		// la session
		List<LigneCommande> listeCourses = (List<LigneCommande>) FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get("panierActuel");

		// Transmission de la commande à toutes les lignes de commandes et
		// enregistrement de ces lignes dans la BD
		for (LigneCommande ligne : listeCourses) {
			ligne.setAttCommande(comEnregistree);
			lComService.saveLCommande(ligne);
		}
		return "client";

	}

}
