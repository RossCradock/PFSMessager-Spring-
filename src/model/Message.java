package model;

public class Message {

	private int id;
	private int sender;
	private int recipient;
	private String message;
	private long time;
	private String senderPublicKey;
	private String recipientPublicKey;
	private String token;
	
	public Message(int sender, int recipient, String message, long time, String senderPublicKey, String recipientPublicKey, String token) {
		this.sender = sender;
		this.recipient = recipient;
		this.message = message;
		this.time = time;
		this.senderPublicKey = senderPublicKey;
		this.recipientPublicKey = recipientPublicKey;
		this.token = token;
	}
	
	public Message(){
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getSender() {
		return sender;
	}
	
	public void setSender(int sender) {
		this.sender = sender;
	}
	
	public int getRecipient() {
		return recipient;
	}
	
	public void setRecipient(int recipient) {
		this.recipient = recipient;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public long getTime() {
		return time;
	}
	
	public void setTime(long time) {
		this.time = time;
	}
	
	public String getSenderPublicKey() {
		return senderPublicKey;
	}
	
	public void setSenderPublicKey(String senderPublicKey) {
		this.senderPublicKey = senderPublicKey;
	}
	
	public String getRecipientPublicKey() {
		return recipientPublicKey;
	}
	
	public void setRecipientPublicKey(String recipientPublicKey) {
		this.recipientPublicKey = recipientPublicKey;
	}
	

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
