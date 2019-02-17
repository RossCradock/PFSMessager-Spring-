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

    @Autowired
    public void setAccountService(AccountService accountService){
        this.accountService = accountService;
    }

	
	public int getKeyHashCount(String username1, String username2){
        int userId1 = getUserIdFromUsername(username1);
        int userId2 = getUserIdFromUsername(username2);
		return keyHashCountDAO.getKeyHashCount(userId1, userId2);
	}
	
	public int setKeyHashCount(String username1, String username2){
        int userId1 = getUserIdFromUsername(username1);
        int userId2 = getUserIdFromUsername(username2);
		return keyHashCountDAO.setKeyHashCount(userId1, userId2);
	}
	
	public void createKeyHashCount(String username1, String username2){
        int userId1 = getUserIdFromUsername(username1);
        int userId2 = getUserIdFromUsername(username2);
		keyHashCountDAO.createKeyHashCount(userId1, userId2);
	}

    private int getUserIdFromUsername(String username){
        return accountService.getAccountFromUsername(username);
    }
}
