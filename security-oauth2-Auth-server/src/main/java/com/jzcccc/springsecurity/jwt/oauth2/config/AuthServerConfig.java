package com.jzcccc.springsecurity.jwt.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.List;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  public AuthServerConfig(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
    oauthServer.checkTokenAccess("isAuthenticated()");
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory()
//        .withClient("admin")
//        .secret(passwordEncoder.encode("admin"))
//        .accessTokenValiditySeconds(36000)
//        .refreshTokenValiditySeconds(864000)
//        .authorizedGrantTypes("authorization_code", "password", "refresh_token", "client_credentials")
//        .redirectUris("https://www.baidu.com")
//        .scopes("user")
//        .and()
        .withClient("c1")
        .secret(passwordEncoder.encode("c1"))
        .accessTokenValiditySeconds(36000)
        .refreshTokenValiditySeconds(864000)
        .authorizedGrantTypes("authorization_code", "password", "refresh_token", "client_credentials")
        .redirectUris("https://www.baidu.com")
        .scopes("user");
//        .and()
//        .withClient("c2")
//        .secret(passwordEncoder.encode("c2"))
//        .accessTokenValiditySeconds(36000)
//        .refreshTokenValiditySeconds(864000)
//        .authorizedGrantTypes("authorization_code", "password", "refresh_token", "client_credentials")
//        .redirectUris("https://www.baidu.com")
//        .scopes("user");
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
    TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
    tokenEnhancerChain.setTokenEnhancers(List.of(new CustomTokenEnhancer(), accessTokenConverter()));
    endpoints.authenticationManager(authenticationManager)
        .tokenEnhancer(tokenEnhancerChain)
        .accessTokenConverter(accessTokenConverter())
        .tokenStore(tokenStore());
  }

  @Bean
  public JwtAccessTokenConverter accessTokenConverter() {
    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    converter.setAccessTokenConverter(new CustomAccessTokenConverter());
    converter.setSigningKey("123456");
    return converter;
  }

  @Bean
  public TokenStore tokenStore() {
    return new JwtTokenStore(accessTokenConverter());
  }

}
