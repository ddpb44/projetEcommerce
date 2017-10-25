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
		// La requête JPQL
		String req="DELETE FROM Categorie cat WHERE cat.id_cat=:pId";
		
		Query query=em.createQuery(req);
		
		// Passage des params
		query.setParameter("pId", cat.getId_cat());
		
		int verif=query.executeUpdate();
		
		return verif;
	}

	@Override
	public int updateCategorie(Categorie cat) {
		// La requête JPQL
		String req="UPDATE Categorie cat SET cat.nomCategorie=:pNom, cat.description=:pDescription WHERE cat.id_cat=:pId";
		
		Query query=em.createQuery(req);
		
		// Passage des params
		query.setParameter("pNom", cat.getNomCategorie());
		query.setParameter("pDescription", cat.getDescription());
		query.setParameter("pId", cat.getId_cat());
		
		int verif=query.executeUpdate();
		
		return verif;
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
