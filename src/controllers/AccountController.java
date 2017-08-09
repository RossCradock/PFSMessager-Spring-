package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import model.Account;
import service.AccountService;

@Controller
public class AccountController {

	private AccountService accountService;
	
	@Autowired
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@RequestMapping("/")
	public String showHome(Model model){
		
		List<Account> accounts = accountService.getAllAccounts();
		model.addAttribute("accounts", accounts);
		return "home";
	}
	
	@RequestMapping("/account")
	public String showIndex(Model model){
		
		Account account = accountService.getAccount(29);
		model.addAttribute("account", account);
		return "home";
	}
	
}
