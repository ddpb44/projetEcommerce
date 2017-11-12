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

	// M�thodes ManagedBean
	public String entrerSite() {

		// R�cup�ration des listes
		List<Categorie> listeCat = cService.getAllCategorie();
		List<String> listeNCat = cService.getAllCatNames();
		List<Produit> listeProd = pService.getAllProduits();

		// Ajouter les listes dans la session
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("catListe", listeCat);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nCatListe", listeNCat);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("prodListe", listeProd);

		// Cr�ation d'une liste de lignes de commandes dans la session
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("panierActuel", this.listeLCom);

		return "client";

	}

	public String ajouterProduit() {
		// R�cup�ration du produit
		// Produit produitAdd=clService.getProdById(id_prod);

		// R�cup�rer la liste de lignes de commandes de la session
		List<LigneCommande> listeCourses = (List<LigneCommande>) FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get("panierActuel");

//		System.out.println(
//				"\n--------------------------------------------------------------------------Liste courses avant ajout"
//						+ listeCourses);

		// V�rifier si le produit est dans la liste de commande
		Iterator<LigneCommande> it = listeCourses.iterator();

		LigneCommande ligne = new LigneCommande();

//		System.out.println(
//				"\n--------------------------------------------------------------------------Nouvelle ligne commande pour it�rator"
//						+ ligne);

		// Parcours de la liste � la recherche d'une correspondance entre id de
		// produits

		if (it.hasNext()) { // liste avec �l�ments

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
//				.println("\n--------------------------------------------------------------------------Fin de l'it�rator"
//						+ ligne);
		if (ligne.getAttProduit() == null || ligne.getAttProduit().getId_produit() != prod.getId_produit()) {

			// Pas de correspondance --> Nouveau produit

			if (prod.getQuantite() == 0) { // Rupture de stock

				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
						"Ce produit est en rupture de stock! Impossible de l'ajouter � votre panier!"));

			} else { // Stock disponible

				// Cr�er une nouvelle ligne de commande
				ligneCommande.setAttProduit(prod);
				ligneCommande.setPrix(prod.getPrix());
				ligneCommande.setQuantite(ligneCommande.getQuantite() + 1);
				ligneCommande.setPrix2(ligneCommande.getPrix()*ligneCommande.getQuantite());

				// Ajouter la ligne de commande � la liste
				listeCourses.add(this.ligneCommande);

				// Rafraichir la liste dans la session
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("panierActuel",
						listeCourses);
			}

		} else { // Correspondance --> Le produit a d�j� �t� s�lectionn�

			if (ligne.getQuantite() +1 > prod.getQuantite()) { // Rupture de stock

				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
						"Ce produit est en rupture de stock! Impossible de l'ajouter � votre panier!"));

			} else { // Stock disponible

				// Mettre � jour la ligne de commande
				ligne.setQuantite(ligne.getQuantite() + 1);
				ligne.setPrix2(ligne.getPrix()*ligne.getQuantite());

				// Rafraichir la liste dans la session
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("panierActuel",
						listeCourses);

			}
		}
//		System.out.println(
//				"\n--------------------------------------------------------------------------Liste de courses apr�s l'ajout"
//						+ listeCourses);

		return "client";
	}

	public String enleverProduit() {

		List<LigneCommande> listeCourses = (List<LigneCommande>) FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get("panierActuel");

//		System.out.println(
//				"\n--------------------------------------------------------------------------Liste courses avant retrait"
//						+ listeCourses);

		// V�rifier si le produit est dans la liste de commande
		Iterator<LigneCommande> it = listeCourses.iterator();

		LigneCommande ligne = new LigneCommande();

//		System.out.println(
//				"\n--------------------------------------------------------------------------Nouvelle ligne commande pour it�rator"
//						+ ligne);

		// Parcours de la liste � la recherche d'une correspondance entre id de
		// produits

		if (it.hasNext()) { // liste avec �l�ments

			do {
				ligne = it.next();
			} while (it.hasNext() && ligne.getAttProduit().getId_produit() != prod.getId_produit());

		}

//		System.out
//				.println("\n--------------------------------------------------------------------------Fin de l'it�rator"
//						+ ligne);

		if (ligne.getAttProduit() == null || ligne.getAttProduit().getId_produit() != prod.getId_produit()) {

			// Pas de correspondance --> Produit non selectionn�

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Un produit doit �tre selectionn� avant de pouvoir le retirer du panier"));

		} else { // Correspondance --> Retrait possible

			if (ligne.getQuantite() == 1) { // 1 unit� selectionn�e --> Effacer
											// la ligne du panier
				listeCourses.remove(ligne);

			} else { // Plusieurs unit�s selectionn�es --> retirer une unit�

				ligne.setQuantite(ligne.getQuantite() - 1);

			}

			// Rafraichir la liste dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("panierActuel", listeCourses);

		}
//		System.out.println(
//				"\n--------------------------------------------------------------------------Liste de courses apr�s le retrait"
//						+ listeCourses);

		return "client";

	}

	public String enregistrerCommande() {

		// R�cup�ration de la liste de clients inscrits dans la BD
		List<Client> listeClients = clService.getAllClients();

		if (listeClients.isEmpty()) { // La liste de clients est vide

			// Ajout du client � la BD
			clService.saveClient(client);

		} else { // Des clients sont d�j� enregistr�s

			// Parcours de la liste � la recherche d'une correspondance
			Iterator<Client> it = listeClients.iterator();

			Client cl = new Client();

			do {
				it.next();
			} while (it.hasNext()
					&& (cl.getNomClient() != client.getNomClient() || cl.getAdresse() != client.getAdresse()
							|| cl.getEmail() != client.getEmail() || cl.getTel() != client.getTel()));

			if (cl.getNomClient() != client.getNomClient() || cl.getAdresse() != client.getAdresse()
					|| cl.getEmail() != client.getEmail() || cl.getTel() != client.getTel()) {

				// Pas de correspondance compl�te --> Ajout du client � la BD
				clService.saveClient(client);

			}

		}

		// R�cup�ration du client avec ID (apr�s passage dans la BD)
		Client clientEnregistre = clService.getClient(client);

		// Passage du client et de la date � la commande
		commande.setAttClient(clientEnregistre);
		 commande.setDate_commande(c.getTime());

		// Enregistrement de la commande dans la base de donn�es
		Commande comEnregistree = comService.saveCommande(commande);

		// R�cup�ration de la liste de lignes de commandes (panier) � partir de
		// la session
		List<LigneCommande> listeCourses = (List<LigneCommande>) FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get("panierActuel");

		// Transmission de la commande � toutes les lignes de commandes et
		// enregistrement de ces lignes dans la BD
		for (LigneCommande ligne : listeCourses) {
			ligne.setAttCommande(comEnregistree);
			lComService.saveLCommande(ligne);
		}
		return "client";

	}

}
