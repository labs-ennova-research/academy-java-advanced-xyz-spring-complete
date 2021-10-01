package com.ennova_research.academy.xyzspring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ennova_research.academy.xyzspring.dao.model.BoUserRoleType;
import com.ennova_research.academy.xyzspring.security.CustomAccessDeniedHandler;
import com.ennova_research.academy.xyzspring.security.JwtTokenFilter;

/**
 * @author Alberto Ielpo
 */
@Configuration
@EnableWebSecurity
@Order(2)
public class SecurityConfigJwt extends WebSecurityConfigurerAdapter {

	@Autowired
    private JwtTokenFilter jwtTokenFilter;

	@Autowired
    @Qualifier("userDetailsServiceImpl")
    UserDetailsService userDetailsServiceImpl;

	@Autowired
	PasswordEncoder passwordEncoder;
	
    @Autowired
    public void configure(AuthenticationManagerBuilder auth)
            throws Exception {    	
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder);
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
 
	  http.cors().and().csrf().disable().sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
      	.antMatcher("/api/tp/**").authorizeRequests()
	  	.antMatchers("/api/tp/**/management/**").hasAnyAuthority(
	  			BoUserRoleType.ADMIN.getValue(),
	  			BoUserRoleType.WRITE_ALL.getValue())
	  	.antMatchers("/api/tp/**/employee/**").hasAnyAuthority(
	  			BoUserRoleType.ADMIN.getValue(),
	  			BoUserRoleType.READ_ALL.getValue(),
	  			BoUserRoleType.WRITE_ALL.getValue())
	  	.antMatchers("/api/tp/**/public/**").permitAll()
	  	.antMatchers("/api/tp/**").denyAll().and()
	  	.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
	  
      http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
		
 	}
	    
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
    	return new CustomAccessDeniedHandler();
    }
		
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
    
//	@Bean
//	public PasswordEncoder passwordEncoder() {		
//		return new BCryptPasswordEncoder();
//	}	
    
}
