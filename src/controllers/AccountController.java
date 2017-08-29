package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import model.Account;
import service.AccountService;
import service.SpareKeysService;

@Controller
public class AccountController {

	private AccountService accountService;
	private SpareKeysService spareKeysService;
	
	@Autowired
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@Autowired
	public void setSpareKeysService(SpareKeysService spareKeysService){
		this.spareKeysService = spareKeysService;
	}
	
	@RequestMapping(value="/account/checkUsername", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Map<String, Object> checkUsername(@ModelAttribute("username") String username){
		return generateJSON(username, false);
	}
	
	@RequestMapping(value="/account/newUser", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Map<String, Object> newUser(HttpServletRequest request){
		return generateJSON(request.getParameter("username"), true);
	}
	
	private Map<String, Object> generateJSON(String username, boolean newUser){
		Map<String, Object> data = new HashMap<String, Object>();
		List<Account> accounts = accountService.getAllAccounts();
		
		// iterate through all database accounts and see if there is anyone with the same username
		for(Account account : accounts){
			
			//for checkUsername requests
			if(account.getUsername().equals(username) && !newUser){
				
				data.put("response", "username exists");
				data.put("username", account.getUsername());
				data.put("publicKey", spareKeysService.getSpareKey(username));
				return data;
			}
			
			// for newUser
			if(account.getUsername().equals(username) && newUser){
				data.put("response", "username exists");
				return data;
			}
		}
		
		// if no user name was found create one for newUser or return "failed" for checkUsername
		if(newUser){
			accountService.createAccount(username);
			data.put("response", "success");
		}else{
			data.put("response", "failed");
		}
		return data;
	}
}
