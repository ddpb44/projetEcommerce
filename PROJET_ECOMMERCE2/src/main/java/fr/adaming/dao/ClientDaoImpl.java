package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Client;

@Repository
public class ClientDaoImpl implements IClientDao{
	
	@Autowired
	private SessionFactory sf;
		
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public Client saveClient(Client client) {
		
		// Récupération de la session
		Session s=sf.getCurrentSession();
		
		s.save(client);
		
		return client;
	}

	@Override
	public List<Client> getAllClients() {
		// Récupération de la session
		Session s=sf.getCurrentSession();
				
		// La requête HQL
		String req="FROM Client cl";
		
		// Création du bus
		Query query=s.createQuery(req);
		
		// Récupération de la liste de clients
		@SuppressWarnings("unchecked")
		List<Client> listeClients=query.list();
		
		return listeClients;
	}

	@Override
	public Client getClient(Client client) {
		// Récupération de la session
		Session s=sf.getCurrentSession();
		
		// La requête HQL
		String req="FROM Client cl WHERE cl.nomClient=:pNom AND cl.adresse=:pAdresse AND cl.email=:pEmail AND cl.tel=:pTel";
		
		Query query =s.createQuery(req);
		
		// Passage des params
		query.setParameter("pNom", client.getNomClient());
		query.setParameter("pAdresse", client.getAdresse());
		query.setParameter("pEmail", client.getEmail());
		query.setParameter("pTel", client.getTel());
		
		// Récupération du client
		
		Client clientOut=(Client) query.uniqueResult();
		
		return clientOut;
	}

	

}
