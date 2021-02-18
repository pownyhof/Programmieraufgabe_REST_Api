package com.andreasinternetx.crudmysqljdbc.dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

	// get all
	List<T> list();
	
	// C
	void create (T t);
	// R
	Optional<T> get(int id);
	// U
	void update(T t, int id);
	// D
	void delete(int it);
	
	//////////////////
	
	// for userDetails for JWT 
	T getByUsername(String username);
	
	// for Role authorities in userDetails
	T getRole(int id);
}
