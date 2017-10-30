package fr.adaming.dao;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.adaming.model.LigneCommande;

@Stateful
public class LigneCommandeDaoImpl implements ILigneCommandeDao {
	
	@PersistenceContext(unitName="PU_E")
	EntityManager em;

	@Override
	public void saveLCommande(LigneCommande l) {

		em.persist(l);
		
	}

}
