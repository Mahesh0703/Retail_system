package com.retail_syst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.retail_syst.jwtutil.JwtUtil;
import com.retail_syst.model.JwtRequest;
import com.retail_syst.model.JwtResponse;
import com.retail_syst.service.RetailDetailsService;

@RestController
public class JwtRetailSystemController {

//	@Autowired
//	private AuthenticationManager retailAuthenticationManager;
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private RetailDetailsService retailDetailsService;
	
	@Autowired
	private JwtUtil jwtutil;
	
	@RequestMapping(value="/genearte-token", method=RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtrequest) throws Exception{
	
		System.out.println("JWT Request :: "+jwtrequest);
		
		//Authenticate username & password 
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
					(jwtrequest.getUsername(), jwtrequest.getPassword()));
		}
		catch(UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("User Name not found :: ");
		}
		catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("Bad Credentials :: ");
		}
		//finding user details from RetailDetailService
		UserDetails userDetails = this.retailDetailsService.loadUserByUsername(jwtrequest.getUsername());
		
		//generating token 
		String token = this.jwtutil.generateToken(userDetails);
		
		System.out.println("Token ::"+token);
		
		//need token in JSON format there for 
		
		return ResponseEntity.ok(new JwtResponse(token));
		
		
		
		
	}
}
