package com.miw.databeestjes.crittr.configuration;

import com.miw.databeestjes.crittr.service.implementation.CrittrUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Milo Ottenhoff <m.a.ottenhoff@st.hanze.nl
 * <p>
 * This class configures the basic security settings
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class CrittrSecurityConfiguration extends WebSecurityConfigurerAdapter {

    CrittrUserDetailsService crittrUserDetailsService;

    public CrittrSecurityConfiguration(CrittrUserDetailsService crittrUserDetailsService) {
        this.crittrUserDetailsService = crittrUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider () {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(crittrUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("Admin")
                .password(passwordEncoder().encode("admin")).roles("ADMIN");
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/css/**", "/webjars/**", "/favicon.ico", "/assets/**").permitAll()
                .antMatchers("/", "/home", "/animals", "/animals/details/**", "/animals/results/**", "/users/new").permitAll()
                .anyRequest().authenticated().and()
                .formLogin().usernameParameter("email").permitAll()
                .and()
                .logout().logoutSuccessUrl("/home");
    }
}
