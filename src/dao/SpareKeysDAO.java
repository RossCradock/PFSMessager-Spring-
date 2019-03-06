package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

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
			spareKeys.setSpareKey(rs.getString("spareKey"));
			
			return spareKeys;
		}
	};
	
	public boolean create(SpareKeys spareKeys){	
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(spareKeys);
		String sql = "insert into spare_key(userid, spare_key) values(:userId, :spareKey)";
		return jdbc.update(sql, params) == 1;
	}
	
	public List<SpareKeys> getSpareKeys(int userId){
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("userId", userId);
		return jdbc.query("SELECT * FROM spare_key where userid=:userId", params, rowMapper);
	}

    public boolean delete(SpareKeys spareKeys){
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(spareKeys);
		String sql = "delete from spare_key where (userid = :userid && spare_key = :spareKey)";
		return jdbc.update(sql, params) == 1;
    }
}
