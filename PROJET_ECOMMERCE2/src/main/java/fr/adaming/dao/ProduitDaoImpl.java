package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Produit;

@Repository
public class ProduitDaoImpl implements IProduitDao {

	@Autowired // Injection automatique du collaborateur sessionFactoryBean
	private SessionFactory sf;

	// Le setter pour l'injection
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public List<Produit> getAllProduits() {
		// Recuperation de la session
		Session s = sf.getCurrentSession();

		// Le requete HQL
		String req = "FROM Produit p";

		// Creer un objet query
		Query query = s.createQuery(req);

		@SuppressWarnings("unchecked")
		List<Produit> listeProduits = query.list();

		return listeProduits;
	}

	@Override
	public Produit addProduit(Produit p) {
		// Recuperation de la session
		Session s = sf.getCurrentSession();

		s.save(p);
		return p;
	}

	@Override
	public int updateProduit(Produit p) {
		// Recuperation de la session
		Session s = sf.getCurrentSession();

		// Le requete HQL
		String req = "UPDATE Produit p SET p.designation=:pDesignation, p.description=:pDescription, p.prix=:pPrix, p.quantite=:pQuantite WHERE p.id_produit=:pId";

		// Creer un objet query
		Query query = s.createQuery(req);

		// Passage des paramètres
		query.setParameter("pId", p.getId_produit());
		query.setParameter("pDesignation", p.getDesignation());
		query.setParameter("pDescription", p.getDescription());
		query.setParameter("pPrix", p.getPrix());
		query.setParameter("pQuantite", p.getQuantite());

		// Recuperatino du resultat
		int verif = query.executeUpdate();

		return verif;
	}

	@Override
	public int deleteProduit(Produit p) {
		// Recuperation de la session
		Session s = sf.getCurrentSession();

		// Le requete HQL
		String req = "DELETE FROM Produit p WHERE p.id_produit=:pId";

		// Creer un objet query
		Query query = s.createQuery(req);

		// Passage des paramètres
		query.setParameter("pId", p.getId_produit());

		// Recuperatino du resultat
		int verif = query.executeUpdate();

		return verif;
	}

}
