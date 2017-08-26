package controllers;

/*import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;*/
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Message;

@Controller
public class MessageController {
	
	final private static String SERVER_KEY = "AAAArRI8oD4:APA91bHyFdh9MqYPCo-i-yghvdbxAVr6op657tZ9NmoMoDtWs3kg15DVqTMx8sogvUyDWU7bi4Z5cxPEcZ3K5_EC807V2C15kNb-imRgVb_lSuqQWUUPHrxL6Ht7lQSzYyE-AmI5mbh-";
	/*private MessageService messageService;
	
	@Autowired
	public void setMessageService(MessageService messageService){
		this.messageService = messageService;
	}
	
	/*@RequestMapping(value="/messages", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public Map<String, Object> getMessages(@RequestParam(value="userId") int userId){
		
		List<Message> messages = null;
		messages = messageService.getAllMessages(userId);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("messages", messages);
		data.put("number", messages.size());
		return data;
	}*/
	
	@RequestMapping(value="/messages/sendMessage", method=RequestMethod.POST)
	@ResponseBody
	public void sendMessage(@RequestBody Message message){
		
		String uri = "https://fcm.googleapis.com/fcm/send";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	    
		// create request body
		ObjectMapper mapper = new ObjectMapper();
		String messageJson = "";
		try {
			messageJson = mapper.writeValueAsString(message);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String input = "{ \"data\": " + messageJson + ",\"to\" : \"" + message.getToken() + "\" }"; 

	    // set headers
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.set("Authorization", "key= " + SERVER_KEY);
	    HttpEntity<String> entity = new HttpEntity<String>(input, headers);
	    
	    /*ResponseEntity<String> response =*/ restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
	    System.out.println("messageJson: " + messageJson);
	    System.out.println("input: " + input);
	}
}
