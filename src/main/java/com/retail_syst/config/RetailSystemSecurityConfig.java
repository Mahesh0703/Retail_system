package com.retail_syst.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.retail_syst.custome_firewall.AnnotatingHttpFireWall;
import com.retail_syst.service.RetailDetailsService;

@Configuration
@EnableWebSecurity
public class RetailSystemSecurityConfig extends WebSecurityConfigurerAdapter{
	/*
	 * Websecurity classes extende:---
	 * for overriding default method and chanes security configuration for uses.
	 * 
	 */
	@Autowired
	private RetailDetailsService retailDetailService;
	
	@Autowired
	private JwtAuthenticationFilter jwtFilter;
	
	@Autowired
	private JwtAuthenticationStart entryStart;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	public RetailSystemSecurityConfig() {
		super();
		return;
	}

	public RetailSystemSecurityConfig(boolean disableDefaults) {
		super(disableDefaults);
		// TODO Auto-generated constructor stub
	}

	public RetailSystemSecurityConfig(RetailDetailsService retailDetailService) {
		super();
		this.retailDetailService = retailDetailService;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		//setting custome firewall
		super.configure(web);
		web.httpFirewall(new AnnotatingHttpFireWall());
		return;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		/*In this method we defining our filters like which URL need to be Authenticate 
		 * Disabling CRFS,giving permission to user and other we doing in this method
		 */
		
		http.csrf().disable()
			.cors().disable()
			.authorizeRequests()
			.antMatchers("/genearte-token").permitAll()
			.antMatchers("/retail-home/users").permitAll()
			.antMatchers("/retail-home/all-items").permitAll()
			.antMatchers("/retail-home/obtain/*").permitAll()
			.anyRequest().authenticated()
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.exceptionHandling().authenticationEntryPoint(entryStart);
			
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*
		 * In this method we declaring which authentication we are using 
		 */
		
		auth.userDetailsService(retailDetailService);	
		
	}
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		
		return super.authenticationManager();
	}
}
