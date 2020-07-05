package main.com.dragonsoft.onlineapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import main.com.dragonsoft.credentials.AuthorityType;
import main.com.dragonsoft.credentials.UserDetailsServiceImpl;
import main.com.dragonsoft.interceptor.CustomAccessDeniedHandler;
import main.com.dragonsoft.route.Routehandler;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@ComponentScan("main.com.dragonsoft")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	 @Autowired
	 Routehandler successHandler;
	
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
        .antMatchers("/css/**", "/fonts/**", "/images/**", "/js/**", "/vendor/**").permitAll()
        .antMatchers("/register").permitAll()
        .antMatchers("/login_role").hasAnyRole(AuthorityType.ADMIN.name(),AuthorityType.USER.name())
        .antMatchers("/").hasRole(AuthorityType.SYS_ROOT.name())
        .antMatchers("/edit**").hasRole(AuthorityType.ADMIN.name())
        .antMatchers("/new**").hasRole(AuthorityType.ADMIN.name())
        .antMatchers("/index_user").hasRole(AuthorityType.USER.name())
        .antMatchers("/index_admin").hasRole(AuthorityType.ADMIN.name())
        .antMatchers("/index_clients").hasRole(AuthorityType.ADMIN.name())
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .loginProcessingUrl("/perform_secure_login")
        .failureUrl("/login?error=true")
        .permitAll()
        .successHandler(successHandler)
        .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler())
        .and()
        .logout()
        .logoutUrl("/perform_logout")
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID")
        .logoutSuccessUrl("/login")
        .permitAll();
    }
}
