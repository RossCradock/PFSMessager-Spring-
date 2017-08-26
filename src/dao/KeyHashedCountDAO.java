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

@Component("keyHashedCountDAO")
public class KeyHashedCountDAO {
	
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
	
	public int getKeyHashCount(int userId){
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", userId);
		return (jdbc.queryForObject("SELECT * FROM message where id=:id", params, rowMapper)).getKeyCount();
	}
}
