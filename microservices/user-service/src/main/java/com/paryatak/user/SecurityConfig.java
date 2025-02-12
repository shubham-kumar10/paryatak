package com.paryatak.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.paryatak.user.security.JwtAuthorizationFilter;
import com.paryatak.user.service.AppUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

	@Autowired
	AppUserDetailsService appUserDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(appUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		LOGGER.info("Start");
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors();

		httpSecurity.csrf().disable().httpBasic().and().authorizeRequests()
		.antMatchers("/signUp*").permitAll()
		.antMatchers("/skills").permitAll()
		.antMatchers("/Mentor").permitAll();

		httpSecurity.csrf().disable().httpBasic().and().authorizeRequests().antMatchers("/authenticate")
				.hasAnyAuthority("U", "A", "S")
				.antMatchers("/admin/request").hasAuthority("S")
				.anyRequest().authenticated()
				.and().addFilter(new JwtAuthorizationFilter(authenticationManager()));
	}
}
