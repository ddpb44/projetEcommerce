package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Admin;
import fr.adaming.model.Categorie;

@Stateful
public class CategorieDaoImpl implements ICategorieDao {
	
	@PersistenceContext(unitName="PU_E")
	EntityManager em;
	

	@Override
	public Categorie addCategorie(Categorie cat) {
		
		em.persist(cat);
		
		return cat;
	}

	@Override
	public int deleteCategorie(Categorie cat) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateCategorie(Categorie cat) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Categorie> getAllCategorie() {
		
		// La requête JPQL
		String req="SELECT cat FROM Categorie cat";
		
		Query query=em.createQuery(req);
		
		// Récupération de la liste
		List<Categorie> catListe=query.getResultList();
		
		return catListe;
	}

}
