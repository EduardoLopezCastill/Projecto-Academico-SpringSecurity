package edu.cibertec.sesion8SecurityRoles.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.cibertec.sesion8SecurityRoles.dto.UsuarioDto;
import edu.cibertec.sesion8SecurityRoles.model.Rol;
import edu.cibertec.sesion8SecurityRoles.model.Usuario;
import edu.cibertec.sesion8SecurityRoles.repository.RolRepository;
import edu.cibertec.sesion8SecurityRoles.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RolRepository rolRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void saveUsuario(UsuarioDto usuarioDto) {
		Rol rol = rolRepository.findByName("ROLE_USER");
		
		if(rol == null)
			rol = rolRepository.save(new Rol("ROLE_USER"));
		
		Usuario usuario = new Usuario(
				usuarioDto.getName(), 
				usuarioDto.getEmail(),
				passwordEncoder.encode(usuarioDto.getPassword()), 
				Arrays.asList(rol));
		
		usuarioRepository.save(usuario);
	}

	@Override
	public Usuario findUsuarioByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
}
