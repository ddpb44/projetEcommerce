package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
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

@ManagedBean(name = "clMB")
@RequestScoped
public class ClientManagedBean implements Serializable {

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

	private List<LigneCommande> listeLCom = new ArrayList<LigneCommande>();

	// Constructeur vide
	public ClientManagedBean() {
		this.client = new Client();
		this.ligneCommande = new LigneCommande();
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

		System.out.println(
				"\n--------------------------------------------------------------------------Liste courses avant ajout"
						+ listeCourses);

		// Vérifier si le produit est dans la liste de commande
		Iterator<LigneCommande> it = listeCourses.iterator();

		LigneCommande ligne = new LigneCommande();

		System.out.println(
				"\n--------------------------------------------------------------------------Nouvelle ligne commande pour itérator"
						+ ligne);

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

		System.out
				.println("\n--------------------------------------------------------------------------Fin de l'itérator"
						+ ligne);
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

				// Ajouter la ligne de commande à la liste
				listeCourses.add(ligneCommande);

				// Rafraichir la liste dans la session
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("panierActuel",
						listeCourses);
			}

		} else { // Correspondance --> Le produit a déjà été sélectionné

			if (ligne.getQuantite() >= prod.getQuantite()) { // Rupture de stock

				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
						"Ce produit est en rupture de stock! Impossible de l'ajouter à votre panier!"));

			} else { // Stock disponible

				// Mettre à jour la ligne de commande
				ligne.setQuantite(ligne.getQuantite() + 1);

				// Rafraichir la liste dans la session
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("panierActuel",
						listeCourses);

			}
		}
		System.out.println(
				"\n--------------------------------------------------------------------------Liste de courses après l'ajout"
						+ listeCourses);

		return "client";
	}

	public String enleverProduit() {

		List<LigneCommande> listeCourses = (List<LigneCommande>) FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get("panierActuel");

		System.out.println(
				"\n--------------------------------------------------------------------------Liste courses avant retrait"
						+ listeCourses);

		// Vérifier si le produit est dans la liste de commande
		Iterator<LigneCommande> it = listeCourses.iterator();

		LigneCommande ligne = new LigneCommande();

		System.out.println(
				"\n--------------------------------------------------------------------------Nouvelle ligne commande pour itérator"
						+ ligne);

		// Parcours de la liste à la recherche d'une correspondance entre id de
		// produits

		if (it.hasNext()) { // liste avec éléments

			do {
				ligne = it.next();
			} while (it.hasNext() && ligne.getAttProduit().getId_produit() != prod.getId_produit());

		}

		System.out
				.println("\n--------------------------------------------------------------------------Fin de l'itérator"
						+ ligne);

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
		System.out.println(
				"\n--------------------------------------------------------------------------Liste de courses après le retrait"
						+ listeCourses);

		return "client";

	}

}
