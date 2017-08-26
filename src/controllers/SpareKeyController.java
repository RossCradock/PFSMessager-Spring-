package controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import service.SpareKeysService;

@Controller
public class SpareKeyController {
	
	private SpareKeysService spareKeysService;
	
	@Autowired
	public void setSpareKeyService(SpareKeysService spareKeysService){
		this.spareKeysService = spareKeysService;
	}
	
	@RequestMapping(value="/keys/checkSpareKeys", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public Map<String, Object> checkSpareKeys(@RequestParam(value="userId") int userId){
		Map<String, Object> data = new HashMap<String, Object>();
		int numberOfEmptyKeys = spareKeysService.checkSpareKey(userId);
		data.put("spareKeys", numberOfEmptyKeys);
		return data;
	}
	
	@RequestMapping(value="/keys/storeKeys", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public Map<String, Object> storeSpareKeys(@RequestParam(value="userId") int userId, 
			@RequestParam(value="keys") String[] keys){
		Map<String, Object> data = new HashMap<String, Object>();
		spareKeysService.setSpareKey(userId, keys);
		data.put("spareKeys", "success");
		return data;
	}
}
