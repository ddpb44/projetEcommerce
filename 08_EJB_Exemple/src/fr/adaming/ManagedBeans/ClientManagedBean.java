package fr.adaming.ManagedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Agent;
import fr.adaming.model.Client;

import fr.adaming.service.AgentServiceImpl;

import fr.adaming.service.ClientServiceImpl;
import fr.adaming.service.IAgentService;

import fr.adaming.service.IClientService;

@ManagedBean(name = "cMB")
@RequestScoped
public class ClientManagedBean implements Serializable {

	// Transformation de l'association UML en Java
	@EJB
	private IClientService cService;
	private Agent agent;
	private Client client;
	private boolean indice=false;
	
	HttpSession session;

	public ClientManagedBean() {
		this.client = new Client();
	}

	public IClientService getcService() {
		return cService;
	}

	public void setcService(IClientService cService) {
		this.cService = cService;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	@PostConstruct // Cette annotation sert à éxécuter la methode juste après
					// l'instanciation du managedBean
	public void init() {

		// Recuperation du context
		FacesContext context = FacesContext.getCurrentInstance();
		// Recuperation de la session
		this.session = (HttpSession) context.getExternalContext().getSession(false);
		// Recuperation de l'agent à partir de la session
		this.agent = (Agent) session.getAttribute("agentSession");
	}

	public String ajouterClient() {
		// Appel de la méthode service pour ajouter le client
		Client client_out = cService.addClient(this.client, this.agent);

		if (client_out.getId() != 0) {

			// Recupérer la nouvelle liste à partir de la BD
			List<Client> listeClients = cService.getAllClients(this.agent);
			// Actualiser la liste des clients dans la session
			session.setAttribute("clientsListe", listeClients);

			return "Accueil";

		} else {
			// afficher le message d'erreur sur la page
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout a échoué"));
			return "Ajouter";
		}
	}

	public String modifierClient() {

		int verif = cService.updateCLient(this.client, this.agent);

		if (verif != 0) {

			// Recupérer la nouvelle liste à partir de la BD
			List<Client> listeClients = cService.getAllClients(this.agent);
			// Actualiser la liste des clients dans la session
			session.setAttribute("clientsListe", listeClients);

			return "Accueil";

		} else {
			// afficher le message d'erreur sur la page
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout a échoué"));
			return "Modifier";
		}
	}

	public String supprimerClient() {

		int verif = cService.deleteClient(this.client, this.agent);

		if (verif == 1) {
			// Recuperation de la nouvelle liste à partir de la base de données
			List<Client> listeClients = cService.getAllClients(this.agent);
			// Actualiser la liste
			session.setAttribute("clientsListe", listeClients);
			return "Accueil";

		} else {
			// afficher le message d'erreur sur la page
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout a échoué"));
			return "Supprimer";
		}

	}
	
	public String rechercheClient(){
		
		Client c_out = cService.getClientById(this.client, this.agent);
		
		if(c_out!= null){
			this.client=c_out;
			return "Recherche";
		}else{
			return "Recherche";
		}
	}
}
