package pl.training.bank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

public class OAuth2 {

    @EnableResourceServer
    @Configuration
    public static class ResourceServer extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().antMatchers("/api-v1/**").hasRole("ADMIN");
        }

    }

    @EnableAuthorizationServer
    @Configuration
    public static class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

        @Autowired
        private AuthenticationManager authenticationManagerBean;

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.inMemory()
                    .withClient("rest")
                    .authorizedGrantTypes("password")
                    .scopes("read", "write");
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints.authenticationManager(authenticationManagerBean);
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
            security.allowFormAuthenticationForClients();
        }

    }

}
