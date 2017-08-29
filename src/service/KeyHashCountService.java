package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.KeyHashCountDAO;

@Service("keyHashService")
public class KeyHashCountService {

	private KeyHashCountDAO keyHashCountDAO;
	
	@Autowired
	public void setKeyHashCountDAO(KeyHashCountDAO keyHashCountDAO){
		this.keyHashCountDAO = keyHashCountDAO;
	}
	
	public int getKeyHashCount(String username1, String username2){
		return keyHashCountDAO.getKeyHashCount(username1, username2);
	}
	
	public int setKeyHashCount(String username1, String username2){
		return keyHashCountDAO.setKeyHashCount(username1, username2);
	}
}
