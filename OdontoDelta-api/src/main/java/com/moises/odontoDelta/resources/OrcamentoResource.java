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

import com.moises.odontoDelta.domain.Orcamento;
import com.moises.odontoDelta.dto.OrcamentoListDTO;
import com.moises.odontoDelta.services.OrcamentoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value="/orcamento")
public class OrcamentoResource {

	@Autowired
	private OrcamentoService service;
	

	@RequestMapping(value="/find/{id}",method=RequestMethod.GET)
	public ResponseEntity<Orcamento> find(@PathVariable Integer id) {
		Orcamento obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PreAuthorize("hasAnyRole('ORCAMENTO_INSERT')" + 
	" || hasAnyRole('ADMIN')")
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Orcamento obj){
		obj = service.insert(obj);
		//Fornecer URI após inserir!
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getCodigo()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ORCAMENTO_UPDATE')" + 
	"|| hasAnyRole('ADMIN')")
	@RequestMapping(value="/update/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Orcamento> update(@Valid @RequestBody Orcamento obj, @PathVariable Integer id){
		obj.setCodigo(id);
		obj = service.update(obj);
		
		//return ResponseEntity.noContent().build();
		return ResponseEntity.ok().body(obj);
	}
	
	@PreAuthorize("hasAnyRole('ORCAMENTO_DELETE')" + 
	"|| hasAnyRole('ADMIN')")
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Orcamento> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public ResponseEntity<List<OrcamentoListDTO>> findAll() {
		List<Orcamento> list = service.findAll();
		List<OrcamentoListDTO> listDto = list.stream().map(obj -> new OrcamentoListDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public ResponseEntity<Page<OrcamentoListDTO>> findPage(
			@RequestParam(value="page",defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage",defaultValue="24")Integer linesPerPage, 
			@RequestParam(value="orderBy",defaultValue="codigo")String orderBy, 
			@RequestParam(value="direction",defaultValue="ASC")String direction) {
		Page<Orcamento> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<OrcamentoListDTO> listDto = list.map(obj -> new OrcamentoListDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
}
