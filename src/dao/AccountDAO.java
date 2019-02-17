package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import model.Account;


@Component("accountDAO")
public class AccountDAO {

	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc){
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	RowMapper<Account> rowMapper = new RowMapper<Account>(){
		public Account mapRow(ResultSet rs, int arg1) throws SQLException {
			Account account = new Account();
			
			account.setId(rs.getInt("id"));
			account.setUsername(rs.getString("username"));
			account.setUsername(rs.getString("token"));
			
			return account;
		}
	};
	
	public boolean create(String username, String token){	
		Map<String, String> params = new HashMap<>();
		params.put("username", username);
		params.put("token", token);
		return jdbc.update("insert into user(username, token) values(:username, :token)", params) == 1;
	}
	
	public Account getAccount(int id){
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		return jdbc.queryForObject("SELECT * FROM user where id=:id", params, rowMapper);
	}

    public Account getAccountFromUsername(String username){
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", username);
		return jdbc.queryForObject("SELECT * FROM user where username=:username", params, rowMapper);
    }
	
	public List<Account> getAllAccounts(){	
		return jdbc.query("SELECT * FROM user", rowMapper);
	}
	
	public boolean update(Account account){
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(account);
		return jdbc.update("update user set name=:username, password=:password, spare_keys=:spareKeys where id=:id", params) == 1;
	}
	
	public boolean delete(int id){
		MapSqlParameterSource params = new MapSqlParameterSource("id", id);
		return jdbc.update("delete from user where id=:id", params) == 1;
	}
}
