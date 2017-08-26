package model;

public class KeyHashCount {
	
	private int userId1;
	private int userId2;
	private int keyCount;
	
	public KeyHashCount(){
		
	}
	
	public KeyHashCount(int userId1, int userId2, int keyCount) {
		this.userId1 = userId1;
		this.userId2 = userId2;
		this.keyCount = keyCount;
	}
	
	public int getUserId1() {
		return userId1;
	}
	public void setUserId1(int userId1) {
		this.userId1 = userId1;
	}
	public int getUserId2() {
		return userId2;
	}
	public void setUserId2(int userId2) {
		this.userId2 = userId2;
	}
	public int getKeyCount() {
		return keyCount;
	}
	public void setKeyCount(int keyCount) {
		this.keyCount = keyCount;
	}
}
