package fr.adaming.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Admin;
import fr.adaming.model.Categorie;

@Repository
public class CategorieDaoImpl implements ICategorieDao {

	@Autowired // Injection automatique du collaborateur sessionFactoryBean
	private SessionFactory sf;

	// Le setter pour l'injection
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public Categorie addCategorie(Categorie cat) {
		// Recuperation de la session
		Session s = sf.getCurrentSession();

		s.save(cat);
		return cat;
	}

	@Override
	public int deleteCategorie(Categorie cat) {
		// Recuperation de la session
		Session s = sf.getCurrentSession();

		// Le requete HQL
		String req = "DELETE FROM Categorie cat WHERE cat.id_cat=:pId";

		// Creer un objet query
		Query query = s.createQuery(req);

		// Passage des paramètres
		query.setParameter("pId", cat.getId_cat());

		// Recuperatino du resultat
		int verif = query.executeUpdate();

		return verif;
	}

	@Override
	public int updateCategorie(Categorie cat) {
		// Recuperation de la session
		Session s = sf.getCurrentSession();

		// Le requete HQL
		String req = "UPDATE Categorie cat SET cat.nomCategorie=:pNom, cat.description=:pDescription WHERE cat.id_cat=:pId";

		// Creer un objet query
		Query query = s.createQuery(req);

		// Passage des paramètres
		query.setParameter("pId", cat.getId_cat());
		query.setParameter("pNom", cat.getNomCategorie());
		query.setParameter("pDescription", cat.getDescription());

		// Recuperatino du resultat
		int verif = query.executeUpdate();

		return verif;
	}

	@Override
	public List<Categorie> getAllCategorie() {
		// Recuperation de la session
		Session s = sf.getCurrentSession();

		// Le requete HQL
		String req = "FROM Categorie c";

		// Creer un objet query
		Query query = s.createQuery(req);

		@SuppressWarnings("unchecked")
		List<Categorie> listeCategories = query.list();

		return listeCategories;
	}

	@Override
	public Categorie getCatByName(String name) {
		// Recuperation de la session
		Session s = sf.getCurrentSession();

		// Le requete HQL
		String req = "FROM Categorie cat WHERE cat.nomCategorie=:pNom";

		// Creer un objet query
		Query query = s.createQuery(req);

		// Passage du param
		query.setParameter("pNom", name);

		Categorie cat_out = (Categorie) query.uniqueResult();

		return cat_out;

	}

	@Override
	public List<String> getAllCatNames() {
		// Recuperation de la session
		Session s = sf.getCurrentSession();

		// Le requete HQL
		String req = "SELECT cat.nomCategorie FROM Categorie cat";

		// Creer un objet query
		Query query = s.createQuery(req);
		
		@SuppressWarnings("unchecked")
		List<String> listeNoms = query.list();
		
		return listeNoms;

	}

}
