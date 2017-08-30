package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import model.Account;
import service.AccountService;
import service.KeyHashCountService;
import service.SpareKeysService;

@Controller
public class AccountController {

	private AccountService accountService;
	private SpareKeysService spareKeysService;
	private KeyHashCountService KeyHashCountService;
	
	@Autowired
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@Autowired
	public void setSpareKeysService(SpareKeysService spareKeysService){
		this.spareKeysService = spareKeysService;
	}
	
	@Autowired
	public void setKeyHashCountService(KeyHashCountService keyHashCountService){
		this.KeyHashCountService = keyHashCountService;
	}
	
	@RequestMapping(value="/account/checkUsername", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Map<String, Object> checkUsername(HttpServletRequest request){
		return generateJSON(request.getParameter("username1"), request.getParameter("username2"), false);
	}
	
	@RequestMapping(value="/account/newUser", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Map<String, Object> newUser(HttpServletRequest request){
		return generateJSON(request.getParameter("username1"), request.getParameter("username2"), true);
	}
	
	private Map<String, Object> generateJSON(String username1, String username2, boolean newUser){
		Map<String, Object> data = new HashMap<String, Object>();
		List<Account> accounts = accountService.getAllAccounts();
		
		// iterate through all database accounts and see if there is anyone with the same username
		for(Account account : accounts){
			
			//for checkUsername requests
			if(account.getUsername().equals(username1) && !newUser){
				
				data.put("response", "username exists");
				data.put("username", account.getUsername());
				data.put("publicKey", spareKeysService.getSpareKey(username1));
				data.put("token", account.getToken());
				KeyHashCountService.createKeyHashCount(username1, username2);
				return data;
			}
			
			// for newUser
			if(account.getUsername().equals(username1) && newUser){
				data.put("response", "username exists");
				return data;
			}
		}
		
		// if no user name was found create one for newUser or return "failed" for checkUsername
		if(newUser){
			// be aware username2 in this argument means token
			accountService.createAccount(username1, username2);
			data.put("response", "success");
		}else{
			data.put("response", "failed");
		}
		return data;
	}
}
