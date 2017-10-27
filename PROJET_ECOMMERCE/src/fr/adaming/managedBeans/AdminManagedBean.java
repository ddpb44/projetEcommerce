package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import fr.adaming.model.Admin;
import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Produit;
import fr.adaming.service.IAdminService;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;
import sun.print.PSStreamPrintService;

@ManagedBean(name = "aMB")
@RequestScoped
public class AdminManagedBean implements Serializable{

	// Transformation de l'association UML en JAVA et l'injection de service
	@EJB
	private IAdminService aService;
	
	@EJB
	private ICategorieService cService;
	
	@EJB
	private IProduitService pService;

	private Admin admin;

		
	// Constructeurs
	public AdminManagedBean() {
		this.admin = new Admin();
	}

	// Getters et setters
	public IAdminService getaService() {
		return aService;
	}

	public void setaService(IAdminService aService) {
		this.aService = aService;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	
	public ICategorieService getcService() {
		return cService;
	}

	public void setcService(ICategorieService cService) {
		this.cService = cService;
	}

	// Les méthodes métiers
	public String seconnecter() {
		try {
			
			Admin a_out = aService.isexist(this.admin);
		
			//Recuperation de la liste
			List<Categorie> listeCat =cService.getAllCategorie();
			List<String> listNCat = cService.getAllCatNames();
			List<Produit> listeProd = pService.getAllProduits();
					
			//Ajouter la liste des categories et produits dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("catListe", listeCat);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nCatListe", listNCat);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("prodListe", listeProd);

			// Ajouter l'admin dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adminSession", a_out);
			return "admin";

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("L'identifiant et/ou le mdp est erroné"));
			return "accueil";
		}

	}
}
