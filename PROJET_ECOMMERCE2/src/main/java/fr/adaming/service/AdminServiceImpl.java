package fr.adaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IAdminDao;
import fr.adaming.model.Admin;

@Service("aService") // Annotation pour le définir comme un bean
@Transactional //Pour spécifier au conteneur Spring que toutes les méthodes de la classe sont transactionnelles
public class AdminServiceImpl implements IAdminService {

	
	@Autowired
	private IAdminDao adminDao;

	public void setFormateurDao(IAdminDao adminDao) {
		this.adminDao = adminDao;
	}

	
	@Override
	public Admin isexist(Admin a) {
		return adminDao.isexist(a);
	}

	
}
