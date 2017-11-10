package fr.adaming.dao;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Admin;

@Repository //Annotation pour la déclaration du bean
public class AdminDaoImpl implements IAdminDao{

	@Autowired // Injection automatique du collaborateur sessionFactoryBean
	private SessionFactory sf;

	// Le setter pour l'injection
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Override
	public Admin isexist(Admin a) {
		// Recuperation de la session
				Session s = sf.getCurrentSession();

				// la requete HQL
				String req = "FROM Admin a WHERE a.mail=:pMail AND a.mdp=:pMdp";

				// Creer un objet query
				Query query = s.createQuery(req);

				// Passage des paramètres
				query.setParameter("pMail", a.getMail());
				query.setParameter("pMdp", a.getMdp());

				// Envoyer de la requete et recupération du resultat
				Admin a_out = (Admin) query.uniqueResult();

				return a_out;
	}
	
	
}
