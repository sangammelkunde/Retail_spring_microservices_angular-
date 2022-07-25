package com.cognizant.authentication.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Jwtutil class for token creation and token validation method
 */
@Service
public class JwtUtil {
	/**
	 * SecretKey
	 */
	private String secretkey = "${jwt.secret}";

	/**
	 * Extract username
	 * 
	 * @param token
	 * @return Claims subject with token
	 */
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	/**
	 * Extract Claims
	 * 
	 * @param <T>
	 * @param token
	 * @param claimsResolver
	 * @return claims which will going to be applied
	 */
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	/**
	 * Extract All Claims
	 * 
	 * @param token
	 * @return signingKey with token
	 */
	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token).getBody();
	}

	/**
	 * Generate Token
	 * 
	 * @param userDetails
	 * @return username with claims
	 */
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userDetails.getUsername());
	}

	/**
	 * Method to create Token
	 * 
	 * @param claims
	 * @param subject
	 * @return token with time limit and secret key
	 */
	private String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 120))
				.signWith(SignatureAlgorithm.HS256, secretkey).compact();
	}

	/**
	 * Validate Token
	 * 
	 * @param token
	 * @return if token is correct then it returns true else false
	 */
	public Boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token).getBody();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}