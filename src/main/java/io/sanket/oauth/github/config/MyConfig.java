package io.sanket.oauth.github.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class MyConfig extends AbstractHttpConfigurer<MyConfig, HttpSecurity>  {

    public SecurityFilterChain filterChain(HttpSecurity httpSecurity){
        try{
            httpSecurity.authorizeRequests(a -> a
                            .antMatchers("/", "/error", "/webjars/**").permitAll()
                            .anyRequest().authenticated()
                    )
                    .exceptionHandling(e -> e .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)) )
                    .oauth2Login()
                    .and()
                    .logout(l -> l.logoutSuccessUrl("/").permitAll())
                    .csrf(c -> c.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()));
            return httpSecurity.build();
        }catch(Exception e){

        }
        return null;
    }
}
