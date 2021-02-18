package com.andreasinternetx.crudmysqljdbc.dao;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.andreasinternetx.crudmysqljdbc.models.RoleDB;


@Component
public class RoleDAO implements DAO<RoleDB> {
	
	private static final Logger log = LoggerFactory.getLogger(RoleDAO.class);
	private JdbcTemplate template;
	
	RowMapper<RoleDB> rowMapper = (rs, rowNum) -> {
		RoleDB role = new RoleDB();
		role.setId(rs.getInt("id"));
		role.setUserId(rs.getInt("user_id"));
		role.setAdmin(rs.getInt("role_admin"));
		role.setDevelop(rs.getInt("role_develop"));
		role.setCctld(rs.getInt("role_cctld"));
		role.setGtld(rs.getInt("role_gtld"));
		role.setBilling(rs.getInt("role_billing"));
		role.setRegistry(rs.getInt("role_registry"));
		role.setPurchaseRead(rs.getInt("role_purchase_read"));
		role.setPurchaseWrite(rs.getInt("role_purchase_write"));
		role.setSaleWrite(rs.getInt("role_sale_write"));
		role.setSql(rs.getInt("role_sql"));
		return role;
	};
	
	public RoleDAO(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public List<RoleDB> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(RoleDB t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<RoleDB> get(int id) {
		String sql = "SELECT id,user_id,role_admin,role_develop,role_cctld,role_gtld,role_billing,role_registry,role_purchase_read,role_purchase_write,role_sale_write,role_sql from role where user_id = ?";
		RoleDB role = null;
		try {
			role = template.queryForObject(sql, new Object[]{id}, rowMapper);
		}catch(DataAccessException ex) {
			log.info("User not found: " + id);
		}
		return Optional.ofNullable(role);
	}
	
	public RoleDB getRole(int id) {
		String sql = "SELECT id,user_id,role_admin,role_develop,role_cctld,role_gtld,role_billing,role_registry,role_purchase_read,role_purchase_write,role_sale_write,role_sql from role where user_id = ?";
		RoleDB role = null;
		try {
			role = template.queryForObject(sql, new Object[]{id}, rowMapper);
		}catch(DataAccessException ex) {
			log.info("User not found: " + id);
		}
		return role;
	}

	@Override
	public void update(RoleDB t, int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int it) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RoleDB getByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
