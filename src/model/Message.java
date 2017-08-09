package model;

public class Message {

	private int id;
	private int sender;
	private int recipient;
	private String message;
	private long time;
	private String hmac;
	private String senderPublicKey;
	private String recipientPublicKey;
	
	public Message(int sender, int recipient, String message, long time, String hmac, String senderPublicKey, String recipientPublicKey) {
		this.sender = sender;
		this.recipient = recipient;
		this.message = message;
		this.time = time;
		this.hmac = hmac;
		this.senderPublicKey = senderPublicKey;
		this.recipientPublicKey = recipientPublicKey;
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
	
	public String getHmac() {
		return hmac;
	}
	
	public void setHmac(String hmac) {
		this.hmac = hmac;
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
}
