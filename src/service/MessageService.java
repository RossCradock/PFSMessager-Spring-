package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MessageDAO;
import model.Message;

@Service("messageService")
public class MessageService {

	private MessageDAO messageDAO;
	
	@Autowired
	public void setMessageDAO(MessageDAO messageDAO) {
		this.messageDAO = messageDAO;
	}

	public List<Message> getAllMessages(){
		return messageDAO.getAllMessages();		
	}
	
	public Message getMessage(int id){
		return messageDAO.getMessage(id);		
	}
}
