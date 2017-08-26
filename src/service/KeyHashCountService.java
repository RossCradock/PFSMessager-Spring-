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
	
	public int getKeyHashCount(int user1Id, int user2Id){
		return keyHashCountDAO.getKeyHashCount(user1Id, user2Id);
	}
}
