package com.example.demo.auth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * Des login, crear token , validarlo.. desde OAUTH es la clsae que lo controla
 * 
 * @author A752845
 *
 */

@Configuration
//Para habilitar la configuracion...
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	// Como tenemos anotado como bean en la clase de configuracion, podemos
	// inyectarlo
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	@Qualifier("authenticationManager")
	// inyectamos el bean del metodo authenticationManager
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private InfoAdicionalToken infoAdicionalToken;
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		// TODO Auto-generated method stub
		// Generamos token con el Metodo, que da acceso a calquier usuario para
		// autenticarse en el endpoint
		security.tokenKeyAccess("permitAll()")// Indicamos que cualquiera puede entrar en esta ruta
				.checkTokenAccess("isAuthenticated()");// validamos el token, solo pueden entrar autenticados
	}

	/**
	 * Metodo en el que gestionaremos que cliente puede conectarse y desde que app
	 * Nos autenticamos con los datos del usuario y con las credenciales de la
	 * aplicacion que se conecta
	 * 
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		// refresh token se utiliza para obtener un token sin tener que volver a iniciar
		// sesion
		clients.inMemory().withClient("angularapp") // Creamos cliente
				.secret(passwordEncoder.encode("12345")) // Credencial de angular
				.scopes("read", "write") // Permisos para a aplicacion
				.authorizedGrantTypes("password", "refresh_token")// Tipo de autenticacion, ¿ como obtenemos el token?
																	// con password
				.accessTokenValiditySeconds(3600) // Tiempo que durara el token
				.refreshTokenValiditySeconds(3600);
	}

	@Override
	// Se encarga de todo el proceso de autenticacion y validar el token
	// Cuando iniciamos sesion, enviamos user y password y si sale bien genera un
	// token que
	// envia a usuario y con el , este puede acceder a las distintas paginas

	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(infoAdicionalToken, accessTokenConverter()));
		
		// Tenemos que registrar el manager que inyectamos
		endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
				.accessTokenConverter(accessTokenConverter())
				.tokenEnhancer(tokenEnhancerChain);
		// Ahora registramos el accesTokenConverter que es el encargado de manejar el
		// almacenamiento de datos de usuario de roles, username...
		// No deberian ponerse datos compremetedores como tarjetas o passwords

	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	// JwtAccesTokenConverter Lo utilizamos para codificar y descodificar datos
	// cuando creamos el token
	// la config del endpoints tambien trabaja con tokenStorage (podemo ver en la
	// clase jwtacces..)
	// por ello no es necesario añadirlo
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		jwtAccessTokenConverter.setSigningKey(JwtConfig.RSA_PRIVADA); // Ciframos con privada
		jwtAccessTokenConverter.setVerifierKey(JwtConfig.RSA_PUBLICA);// Usamos publica para validar que se cifre con
																		// privada correcta
		return jwtAccessTokenConverter;
	}

}
