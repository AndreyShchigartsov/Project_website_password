package com.andrey.spring.config;

import com.andrey.spring.database.entity.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(urlConfig -> urlConfig
                                .antMatchers("/login", "/registration","/v3/api-docs/**","/swagger-ui/**").permitAll()
                                .antMatchers("/admin/**").hasAuthority(Role.ADMIN.getAuthority())
                                .anyRequest().authenticated()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .deleteCookies("JSESSIONID")
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/users/storage"));
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
