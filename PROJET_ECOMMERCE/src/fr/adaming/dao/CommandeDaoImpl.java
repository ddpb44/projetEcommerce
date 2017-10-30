package fr.adaming.dao;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.adaming.model.Commande;

@Stateful
public class CommandeDaoImpl implements ICommandeDao {
	
	@PersistenceContext(unitName="PU_E")
	EntityManager em;

	@Override
	public Commande saveCommande(Commande commande) {

		em.persist(commande);
		
		return commande;
	}
	
	

}
