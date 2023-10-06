package edu.cibertec.sesion8SecurityRoles.service;

import edu.cibertec.sesion8SecurityRoles.dto.UsuarioDto;
import edu.cibertec.sesion8SecurityRoles.model.Usuario;

public interface UsuarioService {

	void saveUsuario(UsuarioDto usuarioDto);
	
	Usuario findUsuarioByEmail(String email);
}
