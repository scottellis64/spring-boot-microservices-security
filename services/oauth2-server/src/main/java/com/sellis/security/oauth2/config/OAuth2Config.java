package com.sellis.security.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
   private final AuthenticationManager authenticationManager;
   private UserDetailsService userDetailsService;
   private final PasswordEncoder passwordEncoder;

   @Autowired
   public OAuth2Config(
         AuthenticationManager authenticationManager,
         @Qualifier("oauthUserDetailsService") UserDetailsService userDetailsService,
         PasswordEncoder passwordEncoder) {
      this.authenticationManager = authenticationManager;
      this.userDetailsService = userDetailsService;
      this.passwordEncoder = passwordEncoder;
   }

   @Override
   public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
      clients.inMemory()
            .withClient("ui")
            .secret(passwordEncoder.encode("uisecret"))
            .authorizedGrantTypes("authorization_code", "implicit", "password", "client_credentials", "refresh_token")
            .scopes("ui")
            .redirectUris(
                  "http://localhost:8084/authtest"
            );
   }

   @Override
   public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
      endpoints.authenticationManager(authenticationManager)
            .userDetailsService(userDetailsService);
   }
}
