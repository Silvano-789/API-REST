package br.com.niu.api.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.niu.api.model.Usuario;
import br.com.niu.api.repository.UsuarioRepository;


@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {
	
	@Autowired /*injeção de dependencia*/
	private UsuarioRepository usuarioRepository;
	
	
	/*Consulta registro pelo id utilizando o metodo GET captura o id na url*/
	@GetMapping(value = "/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Usuario> buscaPorId(@PathVariable(value = "id") Long id) {
	    
		Optional<Usuario> usuario = usuarioRepository.findById(id);	
		return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
	}
	
	
	/*Consulta uma lista de usuario na base utilizando o metodo GET*/
	@GetMapping(value = "/")
	@ResponseBody
	public ResponseEntity<List<Usuario>> listaUsuario() {
		
		List<Usuario> list = usuarioRepository.findAll();
		return new ResponseEntity<List<Usuario>>(list, HttpStatus.OK);
	}

	
	/*Cadastra registro na base de dados utilizando o metodo POST*/
	@PostMapping(value = "/salva-usuario")
	@ResponseBody
	public ResponseEntity<Usuario> salvaUsuario(@RequestBody Usuario usuario){
		/*Varre a lista de telefone atribui i id do usuario*/
		for (int i=0; i < usuario.getTelefone().size(); i++) {
			 usuario.getTelefone().get(i).setUsuario(usuario);
		}
		
		Usuario user = usuarioRepository.save(usuario);
		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
	}
	
	
	/*Atualiza registro na base de dados utilizando o metodo PUT*/
	@PutMapping(value = "/atualiza-usuario")
	@ResponseBody
	public ResponseEntity<Usuario> atualizaUsuario(@RequestBody Usuario usuario){
		
		/*Varre a lista de telefone atribui i id do usuario*/
		for (int i=0; i < usuario.getTelefone().size(); i++) {
			 usuario.getTelefone().get(i).setUsuario(usuario);
		}
		
		Usuario user = usuarioRepository.saveAndFlush(usuario);
		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
	}
	
	
	/*Deleta registro na base de dados utilizando o metodo DELETE*/
	@DeleteMapping(value = "/deleta-usuario")
	@ResponseBody
	public ResponseEntity<String> deletaUsuario(@RequestParam Long id){
		if(id != null) {
		usuarioRepository.deleteById(id);	
		return new ResponseEntity<String>("Excluido", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Erro", HttpStatus.CREATED);
	}
}
