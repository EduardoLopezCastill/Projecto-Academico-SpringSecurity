package edu.cibertec.sesion8SecurityRoles.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.cibertec.sesion8SecurityRoles.model.Usuario;
import edu.cibertec.sesion8SecurityRoles.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRepository.findByEmail(usernameOrEmail);
		if(usuario != null) {
			return new User(usuario.getEmail(), usuario.getPassword(), 
					usuario.getRoles().stream()
					.map((rol) -> new SimpleGrantedAuthority(rol.getName()))
					.collect(Collectors.toList()));
		}
		else {
			throw new UsernameNotFoundException("Invalid email or password");
		}
	}

}
