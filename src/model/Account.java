package model;

public class Account {
	
	private int id;
	private String username;
	
	public Account(String username) {
		this.username = username;
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
}
