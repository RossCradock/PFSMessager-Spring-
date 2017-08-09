package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import model.Message;
import service.MessageService;

@Controller
public class MessageController {
	
	private MessageService messageService;
	
	@Autowired
	public void setMessageService(MessageService messageService){
		this.messageService = messageService;
	}
	
	@RequestMapping(value="/messages", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public Map<String, Object> getMessages(){
		
		List<Message> messages = null;
		messages = messageService.getAllMessages();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("messages", messages);
		data.put("number", messages.size());
		return data;
	}
}
