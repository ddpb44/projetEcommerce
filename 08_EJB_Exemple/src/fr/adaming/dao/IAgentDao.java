package fr.adaming.dao;

import javax.ejb.Local;
import fr.adaming.model.Agent;

@Local
public interface IAgentDao {
	
	public Agent isexist(Agent a) throws Exception;

}
