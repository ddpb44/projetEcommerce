package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IAdminDao;
import fr.adaming.model.Admin;

@Stateful
public class AdminServiceImpl implements IAdminService {

	@EJB //Cette annotation sert à injecter un EJB
	private IAdminDao aDao;
	
		
	public IAdminDao getaDao() {
		return aDao;
	}

	public void setaDao(IAdminDao aDao) {
		this.aDao = aDao;
	}

	@Override
	public Admin isexist(Admin a) throws Exception {
		// TODO Auto-generated method stub
		return aDao.isexist(a);
	}

}
