package controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
	
	@RequestMapping(value="/keyHashCount", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Map<String, Object> getKeyHashCount(@RequestParam(value="user1Id") int user1Id,
			@RequestParam(value="user2Id") int user2Id){
		
		int hashCount = keyHashCountService.getKeyHashCount(user1Id, user2Id);
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("hashCount", hashCount);
		return data;
	}
}
