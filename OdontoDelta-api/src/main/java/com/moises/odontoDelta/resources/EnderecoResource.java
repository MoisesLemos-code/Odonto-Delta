package com.moises.odontoDelta.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

import com.moises.odontoDelta.domain.Endereco;
import com.moises.odontoDelta.dto.EnderecoListDTO;
import com.moises.odontoDelta.services.EnderecoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value="/endereco")
public class EnderecoResource {

	@Autowired
	private EnderecoService service;
	
	@RequestMapping(value="/find/{id}",method=RequestMethod.GET)
	public ResponseEntity<Endereco> find(@PathVariable Integer id) {
		Endereco obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PreAuthorize("hasAnyRole('CLIENTE_UPDATE')" + 
	"|| hasAnyRole('ADMIN')")
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody EnderecoListDTO objDto){
		Endereco obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		//Fornecer URI após inserir!
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getCodigo()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('CLIENTE_UPDATE')" + 
	"|| hasAnyRole('ADMIN')")
	@RequestMapping(value="/update/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Endereco obj, @PathVariable Integer id){
		obj.setCodigo(id);
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('CLIENTE_UPDATE')" + 
	"|| hasAnyRole('ADMIN')")
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Endereco> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public ResponseEntity<List<EnderecoListDTO>> findAll() {
		List<Endereco> list = service.findAll();
		List<EnderecoListDTO> listDto = list.stream().map(obj -> new EnderecoListDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public ResponseEntity<Page<EnderecoListDTO>> findPage(
			@RequestParam(value="page",defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage",defaultValue="24")Integer linesPerPage, 
			@RequestParam(value="orderBy",defaultValue="bairro")String orderBy, 
			@RequestParam(value="direction",defaultValue="ASC")String direction) {
		Page<Endereco> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<EnderecoListDTO> listDto = list.map(obj -> new EnderecoListDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
}
