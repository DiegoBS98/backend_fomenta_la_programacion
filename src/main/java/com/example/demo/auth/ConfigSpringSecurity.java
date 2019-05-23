package com.example.demo.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class ConfigSpringSecurity extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService usuarioService;
	
	//Parecido a component que es para clases propias, crea el pbjeto y lo registra en el contenedor
	//Esto hace lo mismo, el objeto que retorna lo guarda al contenedor para usarlo
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.usuarioService).passwordEncoder(passwordEncoder());
	}
	
	//Anotamos con bean para poder inyectarlo en la clase
	//ConfigSpringSecurity que necesita
	@Bean("authenticationManager")
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	//Aqui indicamos permisos desde spring en vez de darselos desde OAUTH
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		//Como tenemos el frontend en angular, no tenemos que proteger el formulario desde spring
		//ya que esta protegido por token. Tenemos que deshabilitar esta proteccion y no hace falta que indiquemos
		//la ruta ya que de esto no se encarga Spring Standard si no OAUTH
		http.authorizeRequests()
		.anyRequest().authenticated()
		.and()
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//Tambien podemos deshabiltar el manejo de la sesion por parte de spring security;
		//Como utilizamos token y no tenemos todo el proyecto junto, no utilizamos sesiones para gestionar esto ya que
		//los datos los guardamos en el token
	}
	
	
	
}
