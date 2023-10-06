package edu.cibertec.sesion8SecurityRoles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.cibertec.sesion8SecurityRoles.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Long>{

	Rol findByName(String name);
}
