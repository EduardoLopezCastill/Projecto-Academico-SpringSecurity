package edu.cibertec.sesion8SecurityRoles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import edu.cibertec.sesion8SecurityRoles.dto.UsuarioDto;
import edu.cibertec.sesion8SecurityRoles.model.Usuario;
import edu.cibertec.sesion8SecurityRoles.service.UsuarioService;
import jakarta.validation.Valid;

@Controller
public class LoginController {
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}
	
	@GetMapping("/registration")
	public String registerForm(Model model) {
		UsuarioDto usuarioDto = new UsuarioDto();
		model.addAttribute("usuario",usuarioDto);
		return "registration";
	}
	
	@PostMapping("/registration")
	public String register(@Valid @ModelAttribute("usuario") UsuarioDto usuarioDto,
			BindingResult result, Model model) {
		
		Usuario findUsuario = usuarioService.findUsuarioByEmail(usuarioDto.getEmail());
		
		if(findUsuario != null)
			result.rejectValue("email", null, "Usuario already registered!");
		
		if(result.hasErrors()) {
			model.addAttribute("usuario", usuarioDto);
			return "/registration";
		}
		
		usuarioService.saveUsuario(usuarioDto);
		return "redirect:/registration?success";
	}
}
