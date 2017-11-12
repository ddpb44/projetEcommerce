package fr.adaming.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.LigneCommande;

@Repository
public class LigneCommandeDaoImpl implements ILigneCommandeDao {
	
	@Autowired
	private SessionFactory sf;

	@Override
	public void saveLCommande(LigneCommande l) {
		
		// Récupération de la session
		Session s=sf.getCurrentSession();

		s.save(l);
		
	}

}
