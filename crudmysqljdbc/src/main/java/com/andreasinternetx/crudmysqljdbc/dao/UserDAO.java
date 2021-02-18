package com.andreasinternetx.crudmysqljdbc.dao;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.andreasinternetx.crudmysqljdbc.models.UserDB;


@Component
public class UserDAO implements DAO<UserDB> {
	
	private static final Logger log = LoggerFactory.getLogger(UserDAO.class);
	private JdbcTemplate template;
	
	RowMapper<UserDB> rowMapper = (rs, rowNum) -> {
		UserDB user = new UserDB();
		user.setId(rs.getInt("id"));
		user.setLogin(rs.getString("login"));
		user.setPassword(rs.getString("password"));
		user.setFname(rs.getString("fname"));
		user.setLname(rs.getString("lname"));
		user.setEmail(rs.getString("email"));
		return user;
	};
	
	public UserDAO(JdbcTemplate template) {
		this.template = template;
	}

	// Get all users as list
	@Override
	public List<UserDB> list() {
		String sql = "SELECT id, login, password, fname, lname, email from user";
		return template.query(sql,rowMapper);
	}

	// POST
	@Override
	public void create(UserDB user) {
		String sql = "insert into user(login,password,fname,lname,email) values (?,?,?,?,?)";
		int insert = template.update(sql, user.getLogin(), user.getPassword(), user.getFname(), user.getLname(), user.getEmail());
		if(insert == 1) {
			log.info("New user created " + user.getLogin());
		}
	}

	// GET
	@Override
	public Optional<UserDB> get(int id) {
		String sql = "SELECT id,login,password,fname,lname,email from user where id = ?";
		UserDB user = null;
		try {
			user = template.queryForObject(sql, new Object[]{id}, rowMapper);
		}catch(DataAccessException ex) {
			log.info("User not found: " + id);
		}
		return Optional.ofNullable(user);
	}

	// PUT
	@Override
	public void update(UserDB user, int id) {
		String sql = "update user set login = ?, password = ?, fname = ?, lname = ?, email = ? where id = ?";
		int update = template.update(sql, user.getLogin(), user.getPassword(), user.getFname(), user.getLname(), user.getEmail());
		if(update == 1) {
			log.info("User update: " + user.getLogin());
		}
	}

	// DELETE
	@Override
	public void delete(int id) {
		String sql = "delete from user where id = ?";
		template.update(sql,id);	
	}
	
	// for userDetails for JWT -- testing
	public UserDB getByUsername(String username) {
		String sql = "SELECT id,login,password,fname,lname,email from user where login = ?";
		UserDB user = null;
		try {
			user = template.queryForObject(sql, new Object[]{username}, rowMapper);
		}catch(DataAccessException ex) {
			log.info("User not found: " + username);
		}
		return user;
	}
	
	// has to be implemented because of interface, only used in RoleDAO
	@Override
	public UserDB getRole(int id) {
		// TODO Auto-generated method stub
		return null;
	}	
}
