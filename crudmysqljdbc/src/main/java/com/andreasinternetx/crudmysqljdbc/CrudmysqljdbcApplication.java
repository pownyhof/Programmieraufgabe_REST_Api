package com.andreasinternetx.crudmysqljdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.UserDetails;

import com.andreasinternetx.crudmysqljdbc.dao.DAO;
import com.andreasinternetx.crudmysqljdbc.models.UserDB;
import com.andreasinternetx.crudmysqljdbc.services.MyUserDetailsService;

@SpringBootApplication
public class CrudmysqljdbcApplication {
	
	@Autowired
	private static MyUserDetailsService s;
	
	private static DAO<UserDB> dao;
	
	public CrudmysqljdbcApplication(DAO<UserDB> dao, MyUserDetailsService s) {
		this.dao = dao;
		this.s = s;
	}

	public static void main(String[] args) {
		SpringApplication.run(CrudmysqljdbcApplication.class, args);
		
		// test lines
				
		/*System.out.println("\n Create User --- for testing");
		UserDB tester = new UserDB("admin","admin","admin","admin","admin");
		dao.create(tester);
		
		System.out.println("\n All Users --- for testing");
		List<UserDB> users = dao.list();
		users.forEach(System.out::println);
		
		UserDetails test = s.loadUserByUsername("test");
		*/	
	}
}
