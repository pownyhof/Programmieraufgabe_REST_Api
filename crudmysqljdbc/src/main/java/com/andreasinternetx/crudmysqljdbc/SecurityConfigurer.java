package com.andreasinternetx.crudmysqljdbc;

import com.andreasinternetx.crudmysqljdbc.filters.JwtRequestFilter;
import com.andreasinternetx.crudmysqljdbc.services.MyUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
		
	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Override
	// allows request from everybody for "/authenticate", any other request need authentication first
	protected void configure(final HttpSecurity http) throws Exception{
		http.csrf().disable()
			// allow everybody to authenticate
			.authorizeRequests().antMatchers("/authenticate").permitAll()
			// admin API only for users with role admin
			.antMatchers("/admin/**").hasRole("ADMIN")
			// test API for everybody
			.antMatchers("/test").hasAnyRole("ADMIN", "DEVELOP", "CCTLD", "GTLD", "BILLING", "REGISTRY", "PURCHASEREAD", "PURCHASEWRITE", "SALEWRITE", "SQL")
			// every request except authenticate need authentication
			.anyRequest().authenticated()
			.and().sessionManagement()
			// do not create a session
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// do not use sessions because we created jwt token, instead use jwtRequestFilter and check every request
		 http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
		
		return daoAuthenticationProvider;
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
