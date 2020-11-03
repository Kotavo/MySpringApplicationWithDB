package com.example.MySpringApplicationWithDB.config;

import com.example.MySpringApplicationWithDB.security.JwtConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtConfigurer jwtConfigurer;

    private static final String LOGIN_ENDPOINT = "/auth/login";
    private static final String LOGOUT_ENDPOINT = "/auth/logout";

    public SecurityConfig(JwtConfigurer jwtConfigurer) {
        this.jwtConfigurer = jwtConfigurer;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(LOGIN_ENDPOINT).permitAll()
          //      .antMatchers(LOGOUT_ENDPOINT).permitAll()
          //      .antMatchers("**/swagger-ui.html/**").permitAll()
                .antMatchers("/swagger-ui.html/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(jwtConfigurer);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

/*
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().mvcMatchers(LOGIN_ENDPOINT);
        web.ignoring().antMatchers(LOGIN_ENDPOINT);
    }*/

/*    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/", "/*.js", "/*.css","/*.html",
                "/swagger-ui.html/**", "/swagger-resources/**", "/v2/api-docs",
                "/MyLittleCompany/swagger-ui.html/**", "/MyLittleCompany/swagger-resources/**");
    }*/
}
