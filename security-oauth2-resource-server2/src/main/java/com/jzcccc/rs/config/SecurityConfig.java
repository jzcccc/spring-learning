package com.jzcccc.rs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableResourceServer
public class SecurityConfig extends ResourceServerConfigurerAdapter {

//    @Autowired
//    private RemoteTokenServices remoteTokenServices;
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        DefaultAccessTokenConverter userTokenConverter = new CustomAccessTokenConverter();
//        remoteTokenServices.setAccessTokenConverter(userTokenConverter);
//        resources.tokenServices(remoteTokenServices);
//    }

  @Autowired
  private JwtAccessTokenConverter jwtAccessTokenConverter;

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) {
    DefaultAccessTokenConverter userTokenConverter = new CustomAccessTokenConverter();
    jwtAccessTokenConverter.setAccessTokenConverter(userTokenConverter);
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().anyRequest().authenticated();
  }

}
