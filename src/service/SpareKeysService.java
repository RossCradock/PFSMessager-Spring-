package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.AccountService;
import dao.SpareKeysDAO;
import model.SpareKeys;

@Service("spareKeysService")
public class SpareKeysService {
	
	private SpareKeysDAO spareKeysDAO;
	private AccountService accountService;
    
    @Autowired
	public void setSpareKeysDAO(SpareKeysDAO spareKeysDAO){
		this.spareKeysDAO = spareKeysDAO;
	}
	
    // not sure if it will work
    @Autowired
    public void setAccountService(AccountService accountService){
        this.accountService = accountService;
    }

	public void setSpareKey(String username, String[] spareKeysArray){
        int userid = getUserIdFromUsername(username);
        SpareKeys[] spareKeys = getArrayAsKeys(userid, spareKeysArray);

        for(SpareKeys spareKey : spareKeys){
            spareKeysDAO.create(spareKey);
        }
	}

	public String getSpareKey(String username){
		int userid = getUserIdFromUsername(username);
        List<SpareKeys> spareKeys = spareKeysDAO.getSpareKeys(userid);
	
        // update that key so that it is removed
		spareKeysDAO.delete(spareKeys.get(0));

        // return spare key in string format
		return spareKeys.get(0).getSpareKey();
	}
	
	
	public int checkSpareKey(String username){
		int userid = getUserIdFromUsername(username);
		List<SpareKeys> spareKeys = spareKeysDAO.getSpareKeys(userid);
		
		return spareKeys.size();
	}
	
    private int getUserIdFromUsername(String username){
        return accountService.getAccountFromUsername(username);
    }

    private SpareKeys[] getArrayAsKeys(int userid, String[] spareKeysArray){
        SpareKeys[] spareKeys = new SpareKeys[spareKeysArray.length];
        for(int i = 0; i < spareKeysArray.length; i++){
           spareKeys[i] = new SpareKeys();
           spareKeys[i].setUserId(userid);
           spareKeys[i].setSpareKey(spareKeysArray[i]);
        }
        return spareKeys;
    }           
}	
