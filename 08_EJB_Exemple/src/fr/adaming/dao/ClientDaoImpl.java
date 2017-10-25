package fr.adaming.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Agent;
import fr.adaming.model.Client;

@Stateless
public class ClientDaoImpl implements IClientDao {

	@PersistenceContext(unitName = "PU_TP") // Cette annotation sert à injecter
											// une entityManager
	EntityManager em;

	@Override
	public List<Client> getAllClients(Agent ag) {
		// La requete JPQL
		String req = "SELECT cl from Client cl WHERE cl.agent.id=:pId";

		Query query = em.createQuery(req);

		// Passage des paramètres
		query.setParameter("pId", ag.getId());

		List<Client> liste = query.getResultList();

		return liste;

	}

	@Override
	public Client addClient(Client cl) {
		em.persist(cl);
		return cl;

	}

	@Override
	public int updateCLient(Client cl) {
		// La requete JPQL
		String req = "UPDATE Client cl SET cl.nom=:pnom, cl.prenom=:pprenom, cl.dateNaissance=:pdateNaissance WHERE cl.agent.id=:pId_a AND cl.id=:pId";
		
		Query query = em.createQuery(req);
		
		//Passage des paramètres
		
		query.setParameter("pnom", cl.getNom());
		query.setParameter("pprenom", cl.getPrenom());
		query.setParameter("pdateNaissance", cl.getDateNaissance());
		query.setParameter("pId_a", cl.getAgent().getId());
		query.setParameter("pId", cl.getId());
		
		int verif = query.executeUpdate();
		
		return verif;
		
	}

	@Override
	public int deleteClient(Client cl) {
		//Redaction de la requete
		String req = "DELETE FROM Client cl WHERE cl.id=:pId AND cl.agent.id=:pAgent";
		
		Query query = em.createQuery(req);
		
		//Passage des paramètres
		query.setParameter("pId", cl.getId());
		query.setParameter("pAgent", cl.getAgent().getId());
		
		return query.executeUpdate();
				
		
	}

	@Override
	public Client getClientById(Client cl) {
		
		Client rechClient = em.find(Client.class, cl.getId());
		
		if (rechClient!=null){
		if(cl.getAgent().getId() == rechClient.getAgent().getId()){
			return rechClient;
		}else{
			return rechClient;
		}
		}else{
			return null;
		}
		
	}

}