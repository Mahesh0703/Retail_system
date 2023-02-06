package com.retail_syst.config;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.retail_syst.jwtutil.JwtUtil;
import com.retail_syst.service.RetailDetailsService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private RetailDetailsService retailDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//get jwt------getting JWT token 
		//Bearer-------checking token start with Bearer or not
		//validat------validating this token 
		
		String requestTokenHeader = request.getHeader("Authorization");
		String username=null;
		String jwtToken=null;
		
		//checking null and format
		if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer")) {
			
			jwtToken=requestTokenHeader.substring(7);
			
			try {
				
				username= this.jwtUtil.getUsernameFromToken(jwtToken);
			}
			catch(Exception e) {
				
			}	
			
			UserDetails userDetail = this.retailDetailsService.loadUserByUsername(username);
			
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			else {
				System.out.println("Token is not validated ::  ");
			}
			
		}
		
		filterChain.doFilter(request, response);
		
	}

}
