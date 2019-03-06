package controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import service.SpareKeysService;

@Controller
public class SpareKeyController {
	
	private SpareKeysService spareKeysService;
	
	@Autowired
	public void setSpareKeyService(SpareKeysService spareKeysService){
		this.spareKeysService = spareKeysService;
	}
	
	@RequestMapping(value="/keys/checkSpareKeys", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Map<String, Object> checkSpareKeys(HttpServletRequest request){
		Map<String, Object> data = new HashMap<String, Object>();
		int numberOfEmptyKeys = spareKeysService.checkSpareKey(request.getParameter("username"));
		data.put("spareKeys", numberOfEmptyKeys);
		return data;
	}
	
	@RequestMapping(value="/keys/storeKeys", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Map<String, Object> storeSpareKeys(HttpServletRequest request){
		String username = request.getParameter("username");
		String keysInString = request.getParameter("keys");
		keysInString.trim().replaceAll("\n ", "");
		String[] keys = keysInString.split("==");
		Map<String, Object> data = new HashMap<String, Object>();
		spareKeysService.setSpareKey(username, keys);
		data.put("spareKeys", "success");
		return data;
	}
}
