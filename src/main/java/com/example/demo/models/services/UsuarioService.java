package com.example.demo.models.services;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.dao.IUsuarioDao;
import com.example.demo.models.entity.Usuario;
@Service
public class UsuarioService implements UserDetailsService {
	@Autowired
	private IUsuarioDao usuarioDao;
	private Logger logger = LoggerFactory.getLogger(UsuarioService.class);
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
			
		Usuario usuario = usuarioDao.findByNombreUsuario(nombreUsuario);
		//Con el metodo Stream convertimos la respuesta en un flujo para poder mapear a lo que necesitemos
		//con el metodo map.
		List<GrantedAuthority> permisos = usuario.getRoles()
				.stream()
				.map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
				.peek(permiso -> logger.info("Role: "+ permiso.getAuthority())).collect(Collectors.toList());
		return new User(usuario.getNombreUsuario(), usuario.getPassword(), true, true, true, true, permisos);
	}

	}
	;