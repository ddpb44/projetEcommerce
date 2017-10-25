package fr.adaming.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Agent;

@Stateless
public class AgentDaoImpl implements IAgentDao{
	
	@PersistenceContext(unitName="PU_TP") //Cette annotation sert à injecter une entityManager
	EntityManager em;

	@Override
	public Agent isexist(Agent a) throws Exception {
		//La requete JPQL
		String req="SELECT a FROM Agent a WHERE a.mail=:pmail AND a.mdp=:pmdp";
		
		//Creation de query
		Query query = em.createQuery(req);
	
		//Passage des paramètres
		query.setParameter("pmail", a.getMail());
		query.setParameter("pmdp", a.getMdp());
		
		//Envoyer la requete et recupérer
		Agent a_req = (Agent) query.getSingleResult();
		
		return a_req;
		
	}

}
