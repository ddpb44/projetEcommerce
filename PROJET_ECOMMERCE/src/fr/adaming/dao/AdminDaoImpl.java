package fr.adaming.dao;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sun.org.apache.xml.internal.security.encryption.AgreementMethod;

import fr.adaming.model.Admin;

@Stateful
public class AdminDaoImpl implements IAdminDao{
	
	@PersistenceContext(unitName="PU_E") //Cette annotation sert � injecter une entityManager
	EntityManager em;

	@Override
	public Admin isexist(Admin a) throws Exception {
		//La requete JPQL
				String req="SELECT a FROM Admin a WHERE a.mail=:pmail AND a.mdp=:pmdp";
				
				//Creation de query
				Query query = em.createQuery(req);
			
				//Passage des param�tres
				query.setParameter("pmail", a.getMail());
				query.setParameter("pmdp", a.getMdp());
				
				//Envoyer la requete et recup�rer
				Admin a_req = (Admin) query.getSingleResult();
				
				return a_req;
				
			}
	
	
}
