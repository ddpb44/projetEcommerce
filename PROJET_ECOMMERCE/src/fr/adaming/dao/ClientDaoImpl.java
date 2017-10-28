package fr.adaming.dao;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Produit;

@Stateful
public class ClientDaoImpl implements IClientDao{
	
	@PersistenceContext(unitName="PU_E")
	EntityManager em;

	@Override
	public Produit getProdById(int id) {
		// La requête JPQL
		String req="SELECT prod FROM Produit prod WHERE prod.id_produit=:pProd";
		
		Query query=em.createQuery(req);
		
		// Passage params
		query.setParameter("pProd", id);
		
		// Récupération du produit
		Produit produitOut=(Produit) query.getSingleResult();
		
		return produitOut;
	}

}
