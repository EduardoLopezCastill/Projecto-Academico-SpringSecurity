package edu.cibertec.sesion8SecurityRoles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.cibertec.sesion8SecurityRoles.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByEmail(String email);
}
