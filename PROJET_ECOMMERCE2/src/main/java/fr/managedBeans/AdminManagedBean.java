package fr.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.model.Admin;
import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Produit;
import fr.adaming.service.IAdminService;
import sun.print.PSStreamPrintService;

@ManagedBean(name = "aMB") 
@RequestScoped
public class AdminManagedBean implements Serializable {

	@ManagedProperty(value = "#{aService}")
	private IAdminService aService;

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

	// Les m�thodes m�tiers
	public String seconnecter() {

		Admin a_out = aService.isexist(this.admin);

		if (a_out != null) {

			// //Recuperation de la liste
			// List<Categorie> listeCat =cService.getAllCategorie();
			// List<String> listNCat = cService.getAllCatNames();
			// List<Produit> listeProd = pService.getAllProduits();
			//
			// //Ajouter la liste des categories et produits dans la session
			// FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("catListe",
			// listeCat);
			// FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nCatListe",
			// listNCat);
			// FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("prodListe",
			// listeProd);

			// Ajouter l'admin dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adminSession", a_out);
			return "admin";

		}else{
			
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("L'identifiant et/ou mot de passe est erron�"));
			return "accueil";
		}

	}

	public String sedeconnecter() {

		// Recuperer la session et la fermer
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "accueil";
	}
}
