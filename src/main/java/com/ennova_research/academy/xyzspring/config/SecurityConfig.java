package com.ennova_research.academy.xyzspring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ennova_research.academy.xyzspring.security.CustomBasicAuthenticationEntryPoint;

/**
 * @author Alberto Ielpo
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static String REALM = "MY_TEST_REALM";
	
		
//	@Autowired
//	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//		
//		/* in memory */
//		//PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		//String hashedPassword = passwordEncoder.encode("abc1234");
//		//auth.inMemoryAuthentication().withUser("bill").password(hashedPassword).roles("ADMIN");
//		//auth.inMemoryAuthentication().withUser("tom").password(hashedPassword).roles("USER");
//		
//
//	}

	@Autowired
    @Qualifier("userDetailsServiceImpl")
    UserDetailsService userDetailsServiceImpl;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
            throws Exception {    	
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
 
	  http.cors().and().csrf().disable()
	  	.authorizeRequests()
	  	.antMatchers("/api/course/**").permitAll().and()
		.httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint());
 	}
	
	@Bean
	public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
		return new CustomBasicAuthenticationEntryPoint();
	}
	
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
    
    
	@Bean
	public PasswordEncoder passwordEncoder() {		
		return new BCryptPasswordEncoder();
	}	

    
}
