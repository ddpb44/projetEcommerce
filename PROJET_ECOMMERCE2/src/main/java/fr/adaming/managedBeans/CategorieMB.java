package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Admin;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;
import fr.adaming.service.ICategorieService;

@ManagedBean
@RequestScoped
public class CategorieMB implements Serializable {

	@ManagedProperty(value = "#{catService}")
	private ICategorieService catService;

	// @ManagedProperty(value = "#{prodService}")
	// private IProduitService prodService;

	private Admin admin;
	private Categorie cat;

	// catégorie pour suppression
	private Categorie selectedCat;

	HttpSession session;

	// Constructeur vide
	public CategorieMB() {
		this.cat = new Categorie();
	}

	// Getters/Setters
	public ICategorieService getCatService() {
		return catService;
	}

	public void setCatService(ICategorieService catService) {
		this.catService = catService;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Categorie getCat() {
		return cat;
	}

	public void setCat(Categorie cat) {
		this.cat = cat;
	}

	public Categorie getSelectedCat() {
		return selectedCat;
	}

	public void setSelectedCat(Categorie selectedCat) {
		this.selectedCat = selectedCat;
	}

	@PostConstruct
	public void init() {

		// Récupération du contexte
		FacesContext context = FacesContext.getCurrentInstance();

		// Récupération de la session
		this.session = (HttpSession) context.getExternalContext().getSession(false);

	}

	public String ajouterCategorie() {
		// Appel de la méthode service pour ajouter la catégorie
		Categorie cat_out = catService.addCategorie(cat);

		if (cat_out.getId_cat() != 0) {

			// Récupérer la nouvelle liste à partir de la BD
			List<Categorie> listeCat = catService.getAllCategorie();
			// List<String> listNCat = catService.getAllCatNames();

			// Actualiser la liste des catégories dans la session
			session.setAttribute("catListe", listeCat);
			// session.setAttribute("nCatListe", listNCat);

			return "admin";

		} else {
			// Afficher le message d'erreur sur la page
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout de la catégorie a échoué..."));
			return "ajouterCat";

		}

	}

	// Méthode suppression directe

	public void deleteCategorie() {
		// Appel de la méthode service pour supprimer
		catService.deleteCategorie(selectedCat);

		// Récupérer la nouvelle liste à partir de la BD
		List<Categorie> listeCat = catService.getAllCategorie();
		// List<Produit> listeProd = prodService.getAllProduits();

		// Actualiser la liste des catégories dans la session
		session.setAttribute("catListe", listeCat);
		// session.setAttribute("prodListe", listeProd);

		// Remise à zéro du sélecteur
		selectedCat = null;

	}

	public String modifierCategorie() {
		// Appel de la méthode service pour modifier une catégorie
		int verif = catService.updateCategorie(cat);

		if (verif == 0) {
			// Afficher le message d'erreur sur la page
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("La modification de la catégorie sélectionnée n'a pas pu être complétée"));

			return "modifierCat";
		} else {
			// Récupération de la nouvelle liste à partir de la BD
			List<Categorie> listeCat = catService.getAllCategorie();

			// Actualisation de la liste des catégories dans la session
			session.setAttribute("catListe", listeCat);

			return "admin";
		}

	}

}
