package com.codeoftheweb.salvo;

import com.codeoftheweb.salvo.model.*;
import com.codeoftheweb.salvo.repository.*;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@SpringBootApplication
public class SalvoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SalvoApplication.class);
    }

	@Bean
	public PasswordEncoder passwordEncoder(){
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Autowired
	PasswordEncoder passwordEncoder;

	@Configuration
	class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(inputName -> {
				Player player = playerRepository.findByUsername(inputName);
				if (player != null) {
                    return new User(player.getUserName(), player.getPassword(),
							AuthorityUtils.createAuthorityList("USER"));
				} else {
					throw new UsernameNotFoundException("Unknown user: " + inputName);
				}
			});
		}

		@Autowired
		private PlayerRepository playerRepository;

	}

	@EnableWebSecurity
	@Configuration
	public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Autowired
		private WebApplicationContext applicationContext;
		private WebSecurityConfiguration webSecurityConfiguration;

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/rest/players*").permitAll()
                    .antMatchers("/").permitAll()
                    .antMatchers("/web/home*").permitAll()
                    .antMatchers("/web/main.js").permitAll()
                    .antMatchers("/web/players*").permitAll()
                    .antMatchers("/api/players").permitAll()
                    .antMatchers("/api/game*").permitAll()
                    .antMatchers("/api/login*").permitAll()
                    .antMatchers("/web/game*").permitAll()
                    .antMatchers("/api/games/players*").permitAll()
                    .antMatchers("/web/game_view*").permitAll()
                    .antMatchers("/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .loginPage("/api/login")
                    .and()
                    .logout()
                    .logoutUrl("/api/logout");

			// if user is not authenticated, just send an authentication failure response
			http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> {
				System.out.println(exc);
				res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			});

			// if login is successful, just clear the flags asking for authentication
			http.formLogin().successHandler((req, res, auth) -> {
				clearAuthenticationAttributes(req);
			});

			// if login fails, just send an authentication failure response
			http.formLogin().failureHandler((req, res, exc) -> {
				System.out.println(exc);
				res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			});

			// if logout is successful, just send a success response
			http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
		}

        private void clearAuthenticationAttributes(HttpServletRequest request) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            }
        }
	}
}




