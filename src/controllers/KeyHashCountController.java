package controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import service.KeyHashCountService;

@Component
public class KeyHashCountController {
	
	private KeyHashCountService keyHashCountService;
	
	@Autowired
	public void setKeyHashService(KeyHashCountService keyHashCountService){
		this.keyHashCountService = keyHashCountService;
	}
	
	@RequestMapping(value="/keyHashCount/getHashCount", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Map<String, Object> getKeyHashCount(@ModelAttribute("username1") String username1,
			@RequestParam(value="username2") String username2){
		
		int hashCount = keyHashCountService.getKeyHashCount(username1, username2);
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("hashCount", hashCount);
		return data;
	}
	
	@RequestMapping(value="/keyHashCount/setHashCount", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Map<String, Object> setKeyHashCount(@ModelAttribute("username1") String username1,
			@RequestParam(value="username2") String username2){
		
		int hashCount = keyHashCountService.setKeyHashCount(username1, username2);
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("hashCount", hashCount);
		return data;
	}
}
