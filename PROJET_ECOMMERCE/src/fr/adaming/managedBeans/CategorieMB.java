package fr.adaming.managedBeans;

import java.io.Serializable;
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
import fr.adaming.service.ICategorieService;

@ManagedBean
@RequestScoped
public class CategorieMB implements Serializable{
	
	// Association UML --> JAVA
	
	@EJB
	private ICategorieService catService;
	private Admin admin;
	private Categorie cat;
	
	
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
	
	
	@PostConstruct
	public void init(){
		
		// R�cup�ration du contexte
		FacesContext context= FacesContext.getCurrentInstance();
		
		// R�cup�ration de la session
		this.session=(HttpSession) context.getExternalContext().getSession(false);
	}
	
	public String ajouterCategorie() {
		// Appel de la m�thode service pour ajouter la cat�gorie
		Categorie cat_out=catService.addCategorie(cat);
		
		if (cat_out.getId_cat()==0) {
			// Afficher le message d'erreur sur la page
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout de la cat�gorie a �chou�..."));
			return "ajouterCat";
		} else {
			// R�cup�rer la nouvelle liste � partir de la BD
			List<Categorie> listeCat=catService.getAllCategorie();
			
			// Actualiser la liste des cat�gories dans la session
			session.setAttribute("categorieListe", listeCat);
			
			return "accueil";
			
		}
		
	}
	
	public String supprimerCategorie(){
		// Appel de la m�thode service pour supprimer une cat�gorie
		int verif=catService.deleteCategorie(cat);
		
		if (verif==0){
			// Afficher le message d'erreur sur la page
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cette cat�gorie n'a pas pu �tre supprim�e"));
			
			return "supprimerCat";
		} else {
			// R�cup�rer la nouvelle liste � partir de la BD
			List<Categorie> listeCat=catService.getAllCategorie();
			
			// Actualiser la liste des cat�gories dans la session
			session.setAttribute("categorieListe", listeCat);
			
			return "accueil";
		}
		
	}
	
	
	public String modifierCategorie() {
		// Appel de la m�thode service pour modifier une cat�gorie
		int verif=catService.updateCategorie(cat);
		
		if(verif==0){
			// Afficher le message d'erreur sur la page
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La modification de la cat�gorie s�lectionn�e n'a pas pu �tre compl�t�e"));
			
			return "modifierCat";
		} else {
			// R�cup�ration de la nouvelle liste � partir de la BD
			List<Categorie> listeCat=catService.getAllCategorie();
			
			// Actualisation de la liste des cat�gories dans la session
			session.setAttribute("categorieListe", listeCat);
			
			return "accueil";
		}
		
		
	}

}
