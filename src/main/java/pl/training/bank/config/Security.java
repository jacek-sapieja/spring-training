package pl.training.bank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class Security extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .passwordEncoder(passwordEncoder)
                .dataSource(dataSource)
                .usersByUsernameQuery("select email, password, enabled from customer where email = ?");
//                .inMemoryAuthentication()
//                .withUser("maria").password("202cb962ac59075b964b07152d234b70").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http//.csrf().disable()
                .authorizeRequests()
                .antMatchers("/resources/**", "/customer.html")
                    .permitAll()
                .antMatchers("/**")
                    .hasRole("ADMIN")
                .and()
                    .formLogin()
                    .loginPage("/login.html")
                    .permitAll()
                .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout.html"))
                    .logoutSuccessUrl("/login.html");
//                .httpBasic();

    }

}
