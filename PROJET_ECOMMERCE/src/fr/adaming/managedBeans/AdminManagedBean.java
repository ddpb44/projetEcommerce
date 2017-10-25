package fr.adaming.managedBeans;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import fr.adaming.model.Admin;
import fr.adaming.model.Client;
import fr.adaming.service.IAdminService;

@ManagedBean(name = "aMB")
@RequestScoped
public class AdminManagedBean {

	// Transformation de l'association UML en JAVA et l'injection de service
	@EJB
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

	// Les méthodes métiers
	public String seconnecter() {
		try {

			Admin a_out = aService.isexist(this.admin);

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
