package fr.adaming.managedBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Admin;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "pMB")
@RequestScoped
public class ProduitManagedBean {

	@EJB
	private IProduitService prodService;
	
	@EJB
	private ICategorieService catService;
	
	private Admin admin;
	private Produit prod;
	private List<Categorie> listecat;
	private String nomCat;

	HttpSession session;

	// Constructeur vide
	public ProduitManagedBean() {
		this.prod = new Produit();
	}

	public IProduitService getProdService() {
		return prodService;
	}

	public void setProdService(IProduitService prodService) {
		this.prodService = prodService;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Produit getProd() {
		return prod;
	}

	public void setProd(Produit prod) {
		this.prod = prod;
	}

	public List<Categorie> getListecat() {
		return listecat;
	}

	public void setListecat(List<Categorie> listecat) {
		this.listecat = listecat;
	}

	
	public String getNomCat() {
		return nomCat;
	}

	public void setNomCat(String nomCat) {
		this.nomCat = nomCat;
	}

	// Methodes metiers
	@PostConstruct
	public void init() {

		// Récupération du contexte
		FacesContext context = FacesContext.getCurrentInstance();

		// Récupération de la session
		this.session = (HttpSession) context.getExternalContext().getSession(false);
	}

	public String ajouterProduit() {
		// Appel de la méthode service pour ajouter la catégorie
		Categorie cat_in = catService.getCatByName(this.nomCat);
		this.prod.setCat(cat_in);
		Produit prod_out = prodService.addProduit(this.prod);

		if (prod_out.getId_produit() == 0) {
			// Afficher le message d'erreur sur la page
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout du produit a échoué..."));
			return "ajouterProd";
		} else {
			// Récupérer la nouvelle liste à partir de la BD
			List<Produit> listeProd = prodService.getAllProduits();

			// Actualiser la liste des catégories dans la session
			session.setAttribute("prodListe", listeProd);

			return "admin";

		}

	}

}
