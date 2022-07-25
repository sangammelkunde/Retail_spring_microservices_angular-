package com.cognizant.authentication.service;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

/**
 * JwtRequestFilter class for filtering Request
 * 
 */

@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

	/**
	 * Autowired Customer Details Service class
	 */
	@Autowired
	private CustomerDetailsService customerDetailsService;

	/**
	 * Autowired Jwtutil class
	 */
	@Autowired
	private JwtUtil jwtUtil;
	
	String SettingToken="${JwtRequestFilter.SettingToken}";
	String FinishingToken="${JwtRequestFilter.FinishingToken}";
	String Chaining="${JwtRequestFilter.Chaining}";

	/**
	 * doFileterInternal method to filter the request and response
	 * 
	 * @param request
	 * @param response
	 * @param filterchain
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authHeadder = request.getHeader("Authorization");

		String username = null;
		String jwt = null;

		if (authHeadder != null && authHeadder.startsWith("Bearer ")) {

			jwt = authHeadder.substring(7);
			username = jwtUtil.extractUsername(jwt);

		}
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.customerDetailsService.loadUserByUsername(username);

			if (jwtUtil.validateToken(jwt)) {

				log.info(SettingToken);
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null,
						userDetails.getAuthorities());
				token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(token);
				log.info(FinishingToken);
			}
		}
		log.info(Chaining);
		filterChain.doFilter(request, response);
	}

}