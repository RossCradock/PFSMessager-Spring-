package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import model.KeyHashCount;

@Component("keyHashCountDAO")
public class KeyHashCountDAO {
	
private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc){
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	RowMapper<KeyHashCount> rowMapper = new RowMapper<KeyHashCount>(){
		public KeyHashCount mapRow(ResultSet rs, int arg1) throws SQLException {
			KeyHashCount keyHashCount = new KeyHashCount();
			
			keyHashCount.setUserId1(rs.getInt("userId1"));
			keyHashCount.setUserId2(rs.getInt("userId2"));
			keyHashCount.setKeyCount(rs.getInt("keyCount"));
			
			return keyHashCount;
		}
	};
	
	public int getKeyHashCount(int user1Id, int user2Id){
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("user1Id", user1Id);
		params.addValue("user2Id", user2Id);
		String sql = "SELECT * FROM key_hash_count where (user1id=:user1Id && user2id=:user2Id) or (user1id=:user2Id && user2id=:user1Id)";
		return (jdbc.queryForObject(sql, params, rowMapper)).getKeyCount();
	}
}
