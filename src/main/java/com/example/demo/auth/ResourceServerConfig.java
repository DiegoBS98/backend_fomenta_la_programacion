package com.example.demo.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
/**
 * Da acceso a los recursos cuando el token sea valido
 * @author A752845
 *
 */
import org.springframework.web.filter.CorsFilter;
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
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/competiciones", "/institutos").permitAll()
		/*.antMatchers(HttpMethod.GET, "/competiciones/{id}","/institutos/{id}").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.POST, "/competiciones","/institutos").hasAnyRole("ADMIN")
		.antMatchers(HttpMethod.PUT, "/competiciones/{id}","/institutos/{id}").hasAnyRole("ADMIN")
		.antMatchers(HttpMethod.DELETE, "/competiciones/{id}","/institutos/{id}").hasAnyRole("ADMIN")
		.antMatchers("/competiciones/**","/institutos/**").hasAnyRole("ADMIN")*/
		.anyRequest().authenticated()
		/*.and().cors().configurationSource(corsConfigurationSource())*/;
		
	}
	
	/*@Bean
	  public CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedOrigins(Arrays.asList("http://localhost.com:4200"));
	        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE", "OPTIONS"));
	        configuration.setAllowCredentials(true);
	        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
	        
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);
	        return source;
	    }
	 
	 @Bean
	 public FilterRegistrationBean<CorsFilter> corsFilter(){
		 //Recibe una instanca de CorsFilter
		 FilterRegistrationBean<CorsFilter>	bean = new FilterRegistrationBean<CorsFilter>( new CorsFilter(corsConfigurationSource()));
		 bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		 return bean;
	 }*/
	
}
