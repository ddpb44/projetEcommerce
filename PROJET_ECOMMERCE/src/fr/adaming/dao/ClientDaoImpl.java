package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Client;

@Stateful
public class ClientDaoImpl implements IClientDao{
	
	@PersistenceContext(unitName="PU_E")
	EntityManager em;

	@Override
	public Client saveClient(Client client) {
		
		em.persist(client);
		
		return client;
	}

	@Override
	public List<Client> getAllClients() {
		// La requête JPQL
		String req="SELECT cl FROM Client cl";
		
		Query query=em.createQuery(req);
		
		// Récupération de la liste de clients
		List<Client> listeClients=query.getResultList();
		
		return listeClients;
	}

	@Override
	public Client getClient(Client client) {
		// La requête JPQL
		String req="SELECT cl FROM Client cl WHERE cl.nomClient=:pNom AND cl.adresse=:pAdresse AND cl.email=:pEmail AND cl.tel=:pTel";
		
		Query query =em.createQuery(req);
		
		// Passage des params
		query.setParameter("pNom", client.getNomClient());
		query.setParameter("pAdresse", client.getAdresse());
		query.setParameter("pEmail", client.getEmail());
		query.setParameter("pTel", client.getTel());
		
		// Récupération du client
		
		Client clientOut=(Client) query.getSingleResult();
		
		return clientOut;
	}

	

}
