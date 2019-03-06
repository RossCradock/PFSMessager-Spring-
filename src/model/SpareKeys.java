package model;

public class SpareKeys {
	
	private int id;
	private int userid;
	private String spareKey;
	
	public SpareKeys(){
		
	}
	
	public SpareKeys(int id, int userid, String spareKey) {
		this.id = id;
		this.userid = userid;
		this.spareKey = spareKey;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userid;
	}

	public void setUserId(int userId) {
		this.userid = userId;
	}

	public String getSpareKey() {
		return spareKey;
	}

	public void setSpareKey(String spareKey) {
		this.spareKey = spareKey;
	}
}
