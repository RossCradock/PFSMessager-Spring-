package model;

public class Account {
	
	private int id;
	private String username;
	private String token;
	
	public Account(String username, String token) {
		this.username = username;
		this.token = token;
	}
	
	public Account(){
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
