package fr.adaming.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Commande;

@Repository
public class CommandeDaoImpl implements ICommandeDao {
	
	@Autowired
	private SessionFactory sf;

	@Override
	public Commande saveCommande(Commande commande) {
		
		// Récupération de la session
		Session s=sf.getCurrentSession();

		s.save(commande);
		
		return commande;
	}
	
	

}
