package com.myy803.diplomas_mgt_app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.myy803.diplomas_mgt_app.service.UserServiceImpl;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
    private CustomSecuritySuccessHandler customSecuritySuccessHandler;
	
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests()
        // URL matching for accessibility
        .antMatchers("/", "/login", "/register", "/save").permitAll()
        .antMatchers("/student/**").hasAnyAuthority("STUDENT")
        .antMatchers("/professor/**").hasAnyAuthority("PROFESSOR")
        .anyRequest().authenticated()
        .and()
        // form login
        .csrf().disable().formLogin()
        .loginPage("/login")
        .failureUrl("/login?error=true")
        .successHandler(customSecuritySuccessHandler)
       .usernameParameter("username")
       .passwordParameter("password")
        .and()
        // logout
        .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/")
        .and()
        .exceptionHandling()
        .accessDeniedPage("/access-denied");

        http.authenticationProvider(authenticationProvider());
        http.headers().frameOptions().sameOrigin();

        return http.build();
    }

}
