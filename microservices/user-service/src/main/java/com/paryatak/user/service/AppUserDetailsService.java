package com.paryatak.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.paryatak.user.exception.UserAlreadyExistsException;
import com.paryatak.user.model.AppUser;
import com.paryatak.user.model.User;
import com.paryatak.user.repository.UserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppUserDetailsService.class);

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found!");
		}
		else {
			LOGGER.info("user is:" + user);
			AppUser appUser = new AppUser(user);
			LOGGER.info("userDetails is: " + appUser);
			return appUser;
		}

	}

	public AppUserDetailsService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public void signup(User newUser) throws UserAlreadyExistsException {
		LOGGER.info("NEW User IS: " + newUser);
		System.out.println(newUser);
		User user = userRepository.findByUserName(newUser.getUserName());
		if (user != null) {
			throw new UserAlreadyExistsException();
		} else {
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = bCryptPasswordEncoder.encode(newUser.getPassword());
			newUser.setPassword(encodedPassword);
			userRepository.save(newUser);
		}

	}
}
