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

	public List<Account> getAllAccounts(){
		return accountDAO.getAllAccounts();		
	}
	
	public Account getAccount(int id){
		return accountDAO.getAccount(id);		
	}
	
	public void createAccount(String username){
		accountDAO.create(username);
	}
}
