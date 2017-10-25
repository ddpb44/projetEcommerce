package fr.adaming.ManagedBeans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.service.*;
import fr.adaming.model.*;

@ManagedBean(name="aMB")
@RequestScoped
public class AgentManagedBean implements Serializable {
	
	//Transformation de l'association UML en JAVA et l'injection de service
	@EJB
	private IAgentService aService;
	@EJB
	private IClientService cService;
	private Agent agent;
	
	private List<Client> listeClients;

	public AgentManagedBean() {
		this.agent=new Agent();
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public IAgentService getaService() {
		return aService;
	}

	public void setaService(IAgentService aService) {
		this.aService = aService;
	}
	
	
	public IClientService getcService() {
		return cService;
	}

	public void setcService(IClientService cService) {
		this.cService = cService;
	}

	//Les methodes metier du ManagedBean
	public String seconnecter(){
		
		try {
			
			Agent a_out = aService.isexist(this.agent);
			//Recuperation de la liste
			List<Client> listeClients = a_out.getListeClients(); //cService.getAllClients(a_out);
			
			//Ajouter la liste des clients dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("clientsListe", listeClients);
						
			//Ajouter l'agent dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("agentSession", a_out);
			return "succes";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'identifiant et/ou le mdp est erroné"));
			return "echec";
		}
		
	}
	
	public String sedeconnecter(){
		
		//Recuperer la session et la fermer
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "Login";
	}

	// Un commentaire pour tester
}
