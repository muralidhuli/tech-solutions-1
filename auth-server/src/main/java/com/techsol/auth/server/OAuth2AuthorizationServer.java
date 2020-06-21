package com.techsol.auth.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServer extends AuthorizationServerConfigurerAdapter {
	
	@Value("${clientid}")
	String clientid;
	@Value("${clientsecret}")
	String clientsecret;
	@Value("${redirectUris}")
	String redirectUris;
	@Value("${accessTokenValiditySeconds}")
	int accessTokenValiditySeconds;
	@Value("${refreshTokenValiditySeconds}")
	int refreshTokenValiditySeconds;
	@Value("${scopes}")
	String scopes;
	
	@Autowired 
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
	JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	converter.setSigningKey("ravi");
	return converter;
	}
	
	@Bean
	public JwtTokenStore jwtTokenStore() {
	return new JwtTokenStore(accessTokenConverter());
	}
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	//endpoints.tokenStore(tokenStore());
	endpoints.tokenStore(jwtTokenStore());
	
	endpoints.accessTokenConverter(accessTokenConverter());
	}
	
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
               .withClient(clientid)
               .secret(clientsecret)
               .redirectUris(redirectUris)
               .authorizedGrantTypes("authorization_code", "implicit", "password", "client_credentials", "refresh_token")
               .accessTokenValiditySeconds(accessTokenValiditySeconds)	
               .refreshTokenValiditySeconds(refreshTokenValiditySeconds)
               .scopes(scopes);
    }
    
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
    
}
