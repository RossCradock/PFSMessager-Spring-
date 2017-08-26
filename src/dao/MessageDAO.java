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

import model.Message;

@Component("messageDAO")
public class MessageDAO {

	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc){
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	RowMapper<Message> rowMapper = new RowMapper<Message>(){
		public Message mapRow(ResultSet rs, int arg1) throws SQLException {
			Message message = new Message();
			
			message.setId(rs.getInt("id"));
			message.setSender(rs.getInt("sender"));
			message.setRecipient(rs.getInt("recipient"));
			message.setMessage(rs.getString("message"));
			message.setTime(rs.getLong("time"));
			message.setSenderPublicKey(rs.getString("sender_public_key"));
			message.setRecipientPublicKey(rs.getString("recipient_public_key"));
			message.setToken(rs.getString("token"));
			
			return message;
		}
	};
	
	public boolean create(Message message){	
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(message);
		return jdbc.update("insert into message(sender, recipient, message, time, hmac, sender_public_key, recipient_public_key)" +
					"values(:sender, :recipient, :message, :time, :hmac, :senderPublicKey, :recipientPublicKey);", params) == 1;
	}
	
	public Message getMessage(int id){
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		return jdbc.queryForObject("SELECT * FROM message where id=:id", params, rowMapper);
	}
	
	public List<Message> getAllMessages(int userId){	
		return jdbc.query("SELECT * FROM message where id=:userId", rowMapper);
	}
	
	/*public boolean update(Message message){
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(message);
		return jdbc.update("update user set name=:username, password=:password, spare_keys=:spareKeys where id=:id", params) == 1;
	}*/
	
	public boolean delete(int id){
		MapSqlParameterSource params = new MapSqlParameterSource("id", id);
		return jdbc.update("delete from message where id=:id", params) == 1;
	}
}

