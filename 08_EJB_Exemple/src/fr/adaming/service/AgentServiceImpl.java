package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.model.Agent;
import fr.adaming.dao.IAgentDao;

@Stateful
public class AgentServiceImpl implements IAgentService{

	@EJB //Cette annotation sert à injecter un EJB
	private IAgentDao aDao;
	
	
	public IAgentDao getaDao() {
		return aDao;
	}

	public void setaDao(IAgentDao aDao) {
		this.aDao = aDao;
	}

	@Override
	public Agent isexist(Agent a) throws Exception {
		return aDao.isexist(a);
	}

}
