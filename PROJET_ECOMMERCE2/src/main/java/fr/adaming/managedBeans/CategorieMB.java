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

	// cat�gorie pour suppression
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

		// R�cup�ration du contexte
		FacesContext context = FacesContext.getCurrentInstance();

		// R�cup�ration de la session
		this.session = (HttpSession) context.getExternalContext().getSession(false);

	}

	public String ajouterCategorie() {
		// Appel de la m�thode service pour ajouter la cat�gorie
		Categorie cat_out = catService.addCategorie(cat);

		if (cat_out.getId_cat() != 0) {

			// R�cup�rer la nouvelle liste � partir de la BD
			List<Categorie> listeCat = catService.getAllCategorie();
			// List<String> listNCat = catService.getAllCatNames();

			// Actualiser la liste des cat�gories dans la session
			session.setAttribute("catListe", listeCat);
			// session.setAttribute("nCatListe", listNCat);

			return "admin";

		} else {
			// Afficher le message d'erreur sur la page
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout de la cat�gorie a �chou�..."));
			return "ajouterCat";

		}

	}

	// M�thode suppression directe

	public void deleteCategorie() {
		// Appel de la m�thode service pour supprimer
		catService.deleteCategorie(selectedCat);

		// R�cup�rer la nouvelle liste � partir de la BD
		List<Categorie> listeCat = catService.getAllCategorie();
		// List<Produit> listeProd = prodService.getAllProduits();

		// Actualiser la liste des cat�gories dans la session
		session.setAttribute("catListe", listeCat);
		// session.setAttribute("prodListe", listeProd);

		// Remise � z�ro du s�lecteur
		selectedCat = null;

	}

	public String modifierCategorie() {
		// Appel de la m�thode service pour modifier une cat�gorie
		int verif = catService.updateCategorie(cat);

		if (verif == 0) {
			// Afficher le message d'erreur sur la page
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("La modification de la cat�gorie s�lectionn�e n'a pas pu �tre compl�t�e"));

			return "modifierCat";
		} else {
			// R�cup�ration de la nouvelle liste � partir de la BD
			List<Categorie> listeCat = catService.getAllCategorie();

			// Actualisation de la liste des cat�gories dans la session
			session.setAttribute("catListe", listeCat);

			return "admin";
		}

	}

}
