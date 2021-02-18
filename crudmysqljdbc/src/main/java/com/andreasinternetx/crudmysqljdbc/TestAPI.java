package com.andreasinternetx.crudmysqljdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.andreasinternetx.crudmysqljdbc.dao.DAO;
import com.andreasinternetx.crudmysqljdbc.models.AuthenticationRequest;
import com.andreasinternetx.crudmysqljdbc.models.AuthenticationResponse;
import com.andreasinternetx.crudmysqljdbc.models.UserDB;
import com.andreasinternetx.crudmysqljdbc.services.MyUserDetailsService;
import com.andreasinternetx.crudmysqljdbc.util.JwtUtil;

@RestController
public class TestAPI {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	private static DAO<UserDB> dao;
	
	public TestAPI(DAO<UserDB> dao) {
		this.dao = dao;
	}
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@RequestMapping({"/admin"})
	public String admin() {		
		return "you are an admin";
	}
	
	@RequestMapping({"/getusers"})
	public List<UserDB> getUsers() {
		return dao.list();
	}
	
	@RequestMapping({"/test"})
	public String test() {
		return "juhu!!";
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	// takes request (username + password)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		try {
		// and authenticationManager tries to authenticate it	
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
	    }catch (BadCredentialsException e) {
	    	// if it fails throw exception
	    	throw new Exception("Incorrect username or password ", e);
	    }
		// else get userDetails by username
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		// and generate token with userDetails
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		
		// return token
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}
