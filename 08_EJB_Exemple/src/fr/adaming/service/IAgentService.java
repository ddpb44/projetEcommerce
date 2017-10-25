package fr.adaming.service;

import javax.ejb.Local;

import fr.adaming.model.Agent;

@Local
public interface IAgentService {
	
	public Agent isexist(Agent a) throws Exception;

}
