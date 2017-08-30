package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import model.Account;
import model.KeyHashCount;

@Component("keyHashCountDAO")
public class KeyHashCountDAO {
	
private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc){
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	private static final String SQL_WHERE_CLAUSE = " where (user1id=:user1Id && user2id=:user2Id) or (user1id=:user2Id && user2id=:user1Id)";
	
	RowMapper<KeyHashCount> rowMapper = new RowMapper<KeyHashCount>(){
		public KeyHashCount mapRow(ResultSet rs, int arg1) throws SQLException {
			KeyHashCount keyHashCount = new KeyHashCount();
			
			keyHashCount.setUserId1(rs.getInt("user1id"));
			keyHashCount.setUserId2(rs.getInt("user2id"));
			keyHashCount.setKeyCount(rs.getInt("hash_number"));
			
			return keyHashCount;
		}
	};
	
	RowMapper<Account> rowMapperAccount = new RowMapper<Account>(){
		public Account mapRow(ResultSet rs, int arg1) throws SQLException {
			Account account = new Account();
			
			account.setId(rs.getInt("id"));
			account.setUsername(rs.getString("username"));
			
			return account;
		}
	};
	
	public int getKeyHashCount(String username1, String username2){
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		// get user IDs
		params.addValue("username", username1);
		String sql = "SELECT * FROM user where username=:username";
		int user1Id = (jdbc.queryForObject(sql, params, rowMapperAccount)).getId();
		params.addValue("username", username2);
		int user2Id = (jdbc.queryForObject(sql, params, rowMapperAccount)).getId();
		
		// get hash count for users
		params.addValue("user1Id", user1Id);
		params.addValue("user2Id", user2Id);
		sql = "SELECT * FROM key_hash_count" + SQL_WHERE_CLAUSE;
		return (jdbc.queryForObject(sql, params, rowMapper)).getKeyCount();
	}
	
	public int setKeyHashCount(String username1, String username2){
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		// get user IDs
		params.addValue("username", username1);
		String sql = "SELECT * FROM user where username=:username";
		int user1Id = (jdbc.queryForObject(sql, params, rowMapperAccount)).getId();
		params.addValue("username", username2);
		int user2Id = (jdbc.queryForObject(sql, params, rowMapperAccount)).getId();
		
		
		// get hash count for users
		params.addValue("user1Id", user1Id);
		params.addValue("user2Id", user2Id);
		sql = "SELECT * FROM key_hash_count where (user1id=:user1Id && user2id=:user2Id) or (user1id=:user2Id && user2id=:user1Id)";
		int hashCount = (jdbc.queryForObject(sql, params, rowMapper)).getKeyCount();
		sql = "update key_hash_count set hash_number=" + (hashCount + 1) + SQL_WHERE_CLAUSE;
		jdbc.update(sql, params);
		sql = "SELECT * FROM key_hash_count " + SQL_WHERE_CLAUSE;
		return jdbc.queryForObject(sql, params, rowMapper).getKeyCount();
	}
	
	public void createKeyHashCount(String username1, String username2){
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		// get user IDs
		params.addValue("username", username1);
		String sql = "SELECT * FROM user where username=:username";
		int user1Id = (jdbc.queryForObject(sql, params, rowMapperAccount)).getId();
		params.addValue("username", username2);
		int user2Id = (jdbc.queryForObject(sql, params, rowMapperAccount)).getId();
		
		
		// get hash count for users
		Map<String, String> insertParams = new HashMap<>();
		insertParams.put("user1Id", String.valueOf(user1Id));
		insertParams.put("user2Id", String.valueOf(user2Id));
		sql = "insert into key_hash_count(user1id, user2id, hash_number) values(:user1Id, :user2Id, 0)";
		jdbc.update(sql, insertParams);
	}
}
