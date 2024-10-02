package com.manju.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.manju.filter.JwtRequestFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class WebSecurityConfig {
	@Autowired
	private UserDetailsService myUserDetailsService;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	
	@Bean
	  public DaoAuthenticationProvider authenticationProvider() {
	      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	       
	      authProvider.setUserDetailsService(myUserDetailsService);
	      authProvider.setPasswordEncoder(passwordEncoder());
	   
	      return authProvider;
	  }

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	@Bean
	  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
	    return authConfig.getAuthenticationManager();
	  }
	
	
	   @Bean
	   public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
		   httpSecurity.cors(Customizer.withDefaults());
		   httpSecurity.csrf(csrf -> csrf.disable());
		   httpSecurity.sessionManagement(sessionManager  -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		   httpSecurity.exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(
	        		unauthorizedHandler::commence) );

		   httpSecurity.authorizeHttpRequests(authorizeHttpRequest -> authorizeHttpRequest
				   .requestMatchers(HttpMethod.POST,"/accounts/login").permitAll()
					.requestMatchers(HttpMethod.POST,"/accounts/createUser").permitAll()
					 .requestMatchers(HttpMethod.GET,"/actuator/**").permitAll()
					.anyRequest().authenticated()
			);
				
		
		   httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);


		return httpSecurity.build();
	   }
	   
	   
//	   @Bean
//	   public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
//		httpSecurity.csrf().disable()
//				.authorizeHttpRequests()
//				.requestMatchers(HttpMethod.POST,"/accounts/login").permitAll()
//				.requestMatchers(HttpMethod.POST,"/accounts/createUser").permitAll()
//				.anyRequest().authenticated().and()
//				//.exceptionHandling().and()
//				.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//				.sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		
//		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//
//		httpSecurity.cors();
//		return httpSecurity.build();
//	   }


//	@Override
//	protected void configure(HttpSecurity httpSecurity) throws Exception {
//		httpSecurity.csrf().disable()
//				.authorizeRequests()
//				.antMatchers("/accounts/login").permitAll()
//				.antMatchers("/accounts/createUser").permitAll()
//				.anyRequest().authenticated().and()
//				//.exceptionHandling().and()
//				.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//				.sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		
//		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//
//		httpSecurity.cors();
//	}

}