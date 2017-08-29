package service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.SpareKeysDAO;
import model.SpareKeys;

@Service("spareKeysService")
public class SpareKeysService {
	
	public SpareKeysDAO spareKeysDAO;
	
	@Autowired
	public void setSpareKeysDAO(SpareKeysDAO spareKeysDAO){
		this.spareKeysDAO = spareKeysDAO;
	}
	
	public String getSpareKey(String username){
		SpareKeys spareKeys = spareKeysDAO.getSpareKeys(username);
		String[] keys = getKeysAsArray(spareKeys);
		
		// select a random key
		Random random = new Random();
		int index = random.nextInt(9);
		
		// update that key so that it is removed
		setKeyNumberToKey(spareKeys, index, "0");
		spareKeysDAO.update(spareKeys);
		
		return keys[index];
	}
	
	public void setSpareKey(String username, String[] spareKeysArray){
		SpareKeys spareKeys = spareKeysDAO.getSpareKeys(username);
		String[] keys = getKeysAsArray(spareKeys);
		
		// iterate through the sparekeys from the user
		for(int i = 0; i < spareKeysArray.length; i++){
			
			// iterate through array until empty key is found then update the DAO with spareKey from method arg
			for(int j = 0; j < keys.length; j++){
				if(keys[j].equals("0")){
					SpareKeys updatedSpareKeys = setKeyNumberToKey(spareKeys, i, spareKeysArray[j]);
					spareKeysDAO.update(updatedSpareKeys);
				}
			}
		}
	}
	
	public int checkSpareKey(String username){
		SpareKeys spareKeys = spareKeysDAO.getSpareKeys(username);
		String[] keys = getKeysAsArray(spareKeys);
		int numberOfEmptyKeys = 0;
		
		for(int i =0; i < keys.length; i++){
			if(keys[i].equals("0")){
				numberOfEmptyKeys++;
			}
		}
		return numberOfEmptyKeys;
	}
	
	private String[] getKeysAsArray(SpareKeys spareKeys){
		String[] array = new String[10];
		array[0] = spareKeys.getKey1();
		array[1] = spareKeys.getKey2();
		array[2] = spareKeys.getKey3();
		array[3] = spareKeys.getKey4();
		array[4] = spareKeys.getKey5();
		array[5] = spareKeys.getKey6();
		array[6] = spareKeys.getKey7();
		array[7] = spareKeys.getKey8();
		array[8] = spareKeys.getKey9();
		array[9] = spareKeys.getKey10();
		return array;
	}
	
	private SpareKeys setKeyNumberToKey(SpareKeys spareKeys, int index, String key){
		switch(index){
			case 0:
				spareKeys.setKey1(key);
				break;
			case 1:
				spareKeys.setKey2(key);
				break;
			case 2:
				spareKeys.setKey3(key);
				break;
			case 3:
				spareKeys.setKey4(key);
				break;
			case 4:
				spareKeys.setKey5(key);
				break;
			case 5:
				spareKeys.setKey6(key);
				break;
			case 6:
				spareKeys.setKey7(key);
				break;
			case 7:
				spareKeys.setKey8(key);
				break;
			case 8:
				spareKeys.setKey9(key);
				break;
			case 9:
				spareKeys.setKey10(key);
				break;
		}		
		return spareKeys;
	}
}
