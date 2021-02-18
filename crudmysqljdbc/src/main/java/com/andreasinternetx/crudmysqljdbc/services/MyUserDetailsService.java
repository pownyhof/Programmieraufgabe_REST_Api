package com.andreasinternetx.crudmysqljdbc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.andreasinternetx.crudmysqljdbc.dao.DAO;
import com.andreasinternetx.crudmysqljdbc.models.RoleDB;
import com.andreasinternetx.crudmysqljdbc.models.UserDB;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
public class MyUserDetailsService implements UserDetailsService {
	
	private static final Logger log = LoggerFactory.getLogger(MyUserDetailsService.class);
	
	private DAO<UserDB> dao;
	private DAO<RoleDB> roledao;
	
	ArrayList<String> permissionList = new ArrayList<String>(); 
	
	public MyUserDetailsService(DAO<UserDB> dao, DAO<RoleDB> roledao) {
		this.dao = dao;
		this.roledao = roledao;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
				
		// get user from MySQL DB by name
		UserDB test = dao.getByUsername(userName);
		
		return new User(test.getLogin(),test.getPassword(), getAuthorities(userName));		

	}	
	

	// method to extract roles from role table and save to authorities from userDetails
    public Collection<? extends GrantedAuthority> getAuthorities(String userName) {
    	
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        
		// get user from MySQL DB by name
		UserDB testUser = dao.getByUsername(userName);
		// get user roles/permissions
		RoleDB testRole = roledao.getRole(testUser.getId());
		testRole.populateList();
		// save permissions in arrayList
		log.info("admin " + testRole.getAdmin());
		
		permissionList = testRole.getPermissions();
		
		log.info("size of list: " + permissionList.size());
		// save permissions in AuthorityList
        for (int i = 0; i < permissionList.size(); i++) {
        	log.info("ROLE: " + permissionList.get(i));
            authorities.add(new SimpleGrantedAuthority(permissionList.get(i)));
        }

        return authorities;
    }
}
