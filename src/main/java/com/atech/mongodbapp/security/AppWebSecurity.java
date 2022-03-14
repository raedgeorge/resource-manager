package com.atech.mongodbapp.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class AppWebSecurity extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    public AppWebSecurity(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

            http.authorizeRequests()
                .antMatchers("/employees/list").hasRole("EMPLOYEE")
                .antMatchers("/employees/add").hasRole("ADMIN")
                .antMatchers("/employees/save").hasRole("ADMIN")
                .antMatchers("/employees/delete").hasRole("MANAGER")
                .antMatchers("/employees/update").hasAnyRole("ADMIN","MANAGER")
                .antMatchers("/employees/**").hasAnyRole("ADMIN","MANAGER")
                .antMatchers("/").hasAnyRole("ADMIN","MANAGER","EMPLOYEE")
                .and()
                    .formLogin()
                    .loginPage("/loginPage")
                    .loginProcessingUrl("/authenticateTheUser")
                    .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");

    }
}
