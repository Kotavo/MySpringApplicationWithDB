package com.example.MySpringApplicationWithDB.config;

import com.example.MySpringApplicationWithDB.security.JwtConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtConfigurer jwtConfigurer;

    private static final String LOGIN_ENDPOINT = "/MyLittleCompany/auth/login";

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
                .antMatchers("**/swagger-ui.html/**").permitAll()
                .antMatchers("/MyLittleCompany/swagger-ui.html/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(jwtConfigurer);
    }

/*    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/", "/*.js", "/*.css","/*.html",
                "/swagger-ui.html/**", "/swagger-resources/**", "/v2/api-docs",
                "/MyLittleCompany/swagger-ui.html/**", "/MyLittleCompany/swagger-resources/**");
    }*/
}
