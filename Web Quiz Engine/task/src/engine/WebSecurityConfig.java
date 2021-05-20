package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and().sessionManagement().disable();
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/actuator/shutdown").anonymous()
                .antMatchers(HttpMethod.POST, "/actuator/shutdown").permitAll()
                .antMatchers("/api/register").permitAll()
                .antMatchers("/api/users").permitAll()
                .antMatchers("/birthdays", "/", "/app.js").permitAll()
                .antMatchers("/api/quizzes").hasRole("USER")
                .antMatchers("/api/quizzes/completed").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserService();
    }


    // It's necessary to point our UserService as an Authentication Manager!!!
    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userService);
    }
}