package com.example.online_bookstore.Config;

import com.example.online_bookstore.Custom.Oauth2SuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("abc")
    private Oauth2SuccessHandler oauth2SuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
    }

    private static final String[] PUBLIC_MATCHERS = {
            "/css/**",
            "/js/**",
            "/fonts/**",
            "/images/**",
            "/registration",
            "/login",
            "/"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers(PUBLIC_MATCHERS)
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable().cors().disable()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .and()
                .oauth2Login()
                .loginPage("/registration")
                .successHandler(oauth2SuccessHandler);
    }

    private PasswordEncoder getPasswordEncoder(){
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return true;
            }
        };
    }

}
