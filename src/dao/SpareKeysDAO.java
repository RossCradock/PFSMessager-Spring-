package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import model.Account;
import model.SpareKeys;

@Component
public class SpareKeysDAO {
	
	NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc){
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	RowMapper<SpareKeys> rowMapper = new RowMapper<SpareKeys>(){
		public SpareKeys mapRow(ResultSet rs, int arg1) throws SQLException {
			SpareKeys spareKeys = new SpareKeys();
			
			spareKeys.setId(rs.getInt("id"));
			spareKeys.setUserId(rs.getInt("userid"));
			spareKeys.setKey1(rs.getString("key1"));
			spareKeys.setKey2(rs.getString("key2"));
			spareKeys.setKey3(rs.getString("key3"));
			spareKeys.setKey4(rs.getString("key4"));
			spareKeys.setKey5(rs.getString("key5"));
			spareKeys.setKey6(rs.getString("key6"));
			spareKeys.setKey7(rs.getString("key7"));
			spareKeys.setKey8(rs.getString("key8"));
			spareKeys.setKey9(rs.getString("key9"));
			spareKeys.setKey10(rs.getString("key10"));
			
			return spareKeys;
		}
	};
	
	RowMapper<Account> accountRowMapper = new RowMapper<Account>(){
		public Account mapRow(ResultSet rs, int arg1) throws SQLException {
			Account account = new Account();
			
			account.setId(rs.getInt("id"));
			account.setUsername(rs.getString("username"));
			
			return account;
		}
	};
	
	public boolean create(SpareKeys spareKeys){	
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(spareKeys);
		String sql = "insert into spare_keys(userid, key1, key2, key3, key4, key5, key6," +
				"key7, key8, key9, key10) values(:userId, :key1, :key2, :key3, :key4, :key5, :key6," +
				":key7, :key8, :key9, :key10)";
		return jdbc.update(sql, params) == 1;
	}
	
	public SpareKeys getSpareKeys(String username){
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", username);
		String sql = "SELECT * FROM user where username=:username";
		int userId = (jdbc.queryForObject(sql, params, accountRowMapper)).getId();
		params.addValue("userId", userId);
		try{
			return jdbc.queryForObject("SELECT * FROM spare_keys where userid=:userId", params, rowMapper);
		} catch(EmptyResultDataAccessException e){
			SpareKeys newSpareKeys = new SpareKeys();
			newSpareKeys.setUserId(userId);
			newSpareKeys.setKey1("0");
			newSpareKeys.setKey2("0");
			newSpareKeys.setKey3("0");
			newSpareKeys.setKey4("0");
			newSpareKeys.setKey5("0");
			newSpareKeys.setKey6("0");
			newSpareKeys.setKey7("0");
			newSpareKeys.setKey8("0");
			newSpareKeys.setKey9("0");
			newSpareKeys.setKey10("0");
			create(newSpareKeys);
			return newSpareKeys;
		}
	}
	
	public boolean update(SpareKeys spareKeys){
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(spareKeys);
		String sql = "update spare_keys set userid=:userId, key1=:key1, key2=:key2, key3=:key3, key4=:key4, key5=:key5," +
		"key6=:key6, key7=:key7, key8=:key8, key9=:key9, key10=:key10 where userid=:userId";
		return jdbc.update(sql, params) == 1;
	}
}
