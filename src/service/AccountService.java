package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AccountDAO;
import model.Account;

@Service("accountService")
public class AccountService {
	
	private AccountDAO accountDAO;
	
	@Autowired
	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}
	
    public void createAccount(String username, String token){
		accountDAO.create(username, token);
	}
	
	public Account getAccount(int id){
		return accountDAO.getAccount(id);		
	}

    public int getAccountFromUsername(String username){
        return accountDAO.getAccountFromUsername(username).getId();
    }

	public List<Account> getAllAccounts(){
		return accountDAO.getAllAccounts();		
	}
	
}
