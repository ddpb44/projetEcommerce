package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Produit;

@Stateless
public class ProduitDaoImpl implements IProduitDao {

	@PersistenceContext(unitName = "PU_E") // Cette annotation sert à injecter
											// une entityManager
	EntityManager em;

	@Override
	public List<Produit> getAllProduits() {
		// La requete JPQL
		String req = "SELECT p from Produit p ";

		Query query = em.createQuery(req);

		List<Produit> liste = query.getResultList();

		return liste;
	}

	@Override
	public Produit addProduit(Produit p) {
		em.persist(p);

		return p;
	}

	@Override
	public int updateProduit(Produit p) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteProduit(Produit p) {
		// La requête JPQL
		String req = "DELETE FROM Produit prod WHERE prod.id_produit=:pId";

		Query query = em.createQuery(req);

		// Passage des params
		query.setParameter("pId", p.getId_produit());

		int verif = query.executeUpdate();

		return verif;
	}

	
	}
