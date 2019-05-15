package com.example.demo.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
/**
 * Da acceso a los recursos cuando el token sea valido
 * @author A752845
 *
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{
	//Aqui damos permisos desde el OAUTH
	@Override
	/**
	 * Nos permite implementar todos los permisos para los endpoints(rutas)
	 */
	public void configure(HttpSecurity http) throws Exception {
		//Inicamos que a la ruta que le pasemos con el tipo de peticion indicado todos tengan permiso
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/competiciones").permitAll()
		.anyRequest().authenticated();
	}
	
}
