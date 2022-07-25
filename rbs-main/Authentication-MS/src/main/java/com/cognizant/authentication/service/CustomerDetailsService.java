package com.cognizant.authentication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognizant.authentication.model.AppUser;
import com.cognizant.authentication.repository.UserRepository;

/**
 * Customer Detail Service to handle user Details
 */

@Service
public class CustomerDetailsService implements UserDetailsService {

	/**
	 * UserRepository
	 */
	@Autowired
	private UserRepository userRepo;
	
	String NotValid="${CustomerDetailsService.NotValid}";
	
	/**
	 * Method to load user by name and check if they are empty
	 * 
	 * @param userid
	 * @return Return new user if userid password is correct else return
	 *         UserNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {

		AppUser user = null;
		user = userRepo.findById(userid).get();

		if (user != null) {
			List<GrantedAuthority> grantedAuthorities = AuthorityUtils
					.commaSeparatedStringToAuthorityList("ROLE_" + user.getRole());
			return new User(user.getUserid(), user.getPassword(), grantedAuthorities);
		} else {
			throw new UsernameNotFoundException(NotValid);
		}
	}

}
