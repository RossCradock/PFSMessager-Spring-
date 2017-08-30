package controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import service.KeyHashCountService;

@Controller
public class KeyHashCountController {
	
	private KeyHashCountService keyHashCountService;
	
	@Autowired
	public void setKeyHashService(KeyHashCountService keyHashCountService){
		this.keyHashCountService = keyHashCountService;
	}
	
	@RequestMapping(value="/keyHashCount/getHashCount", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Map<String, Object> getKeyHashCount(HttpServletRequest request){
		
		int hashCount = keyHashCountService.getKeyHashCount(request.getParameter("username1"), request.getParameter("username2"));
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("hashCount", hashCount);
		return data;
	}
	
	@RequestMapping(value="/keyHashCount/setHashCount", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Map<String, Object> setKeyHashCount(HttpServletRequest request){
		
		int hashCount = keyHashCountService.setKeyHashCount(request.getParameter("username1"), request.getParameter("username2"));
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("hashCount", hashCount);
		return data;
	}
}
