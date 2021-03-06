package com.moises.odontoDelta.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.moises.odontoDelta.domain.Usuario;
import com.moises.odontoDelta.dto.UsuarioDTO;
import com.moises.odontoDelta.dto.UsuarioNewDTO;
import com.moises.odontoDelta.services.UsuarioService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/usuario")
public class UsuarioResource {

	@Autowired
	private UsuarioService service;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/find/{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> find(@PathVariable Integer id) {
		Usuario obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value="/insert",method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody UsuarioNewDTO objDto) {
		Usuario obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		// Fornecer URI após inserir!
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getCodigo())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody UsuarioDTO objDto, @PathVariable Integer id) {
		Usuario obj = service.fromDTO(objDto);
		obj.setCodigo(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Usuario> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('USER_FIND')" + 
	"|| hasAnyRole('ADMIN')")
	@RequestMapping(value="/all",method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> findAll() {
		List<Usuario> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@PreAuthorize("hasAnyRole('USER_FIND')" + 
	"|| hasAnyRole('ADMIN')")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<Usuario>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Usuario> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}

}
