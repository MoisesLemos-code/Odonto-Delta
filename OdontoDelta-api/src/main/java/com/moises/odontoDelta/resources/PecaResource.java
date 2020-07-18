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

import com.moises.odontoDelta.domain.Peca;
import com.moises.odontoDelta.dto.PecaListDTO;
import com.moises.odontoDelta.resources.utils.URL;
import com.moises.odontoDelta.services.PecaService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value="/peca")
public class PecaResource {

	@Autowired
	private PecaService service;
	
	@RequestMapping(value="/find/{id}",method=RequestMethod.GET)
	public ResponseEntity<Peca> find(@PathVariable Integer id) {
		Peca obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Peca obj){
		obj = service.insert(obj);
		//Fornecer URI após inserir!
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getCodigo()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('PECA_UPDATE')" + 
	"|| hasAnyRole('ADMIN')")
	@RequestMapping(value="/update/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Peca obj, @PathVariable Integer id){
		obj.setCodigo(id);
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Peca> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public ResponseEntity<List<PecaListDTO>> findAll() {
		List<Peca> list = service.findAll();
		List<PecaListDTO> listDto = list.stream().map(obj -> new PecaListDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public ResponseEntity<Page<PecaListDTO>> findPage(
			@RequestParam(value="page",defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage",defaultValue="24")Integer linesPerPage, 
			@RequestParam(value="orderBy",defaultValue="descricao")String orderBy, 
			@RequestParam(value="direction",defaultValue="ASC")String direction) {
		Page<Peca> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<PecaListDTO> listDto = list.map(obj -> new PecaListDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/categorias", method=RequestMethod.GET)
	public ResponseEntity<Page<PecaListDTO>> findPage(
			@RequestParam(value="descricao",defaultValue="") String descricao,
			@RequestParam(value="categorias",defaultValue="") String categorias, 
			@RequestParam(value="page",defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage",defaultValue="24")Integer linesPerPage, 
			@RequestParam(value="orderBy",defaultValue="descricao")String orderBy, 
			@RequestParam(value="direction",defaultValue="ASC")String direction) {
		String nomeDecoded = URL.decodeParam(descricao);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Peca> list = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<PecaListDTO> listDto = list.map(obj -> new PecaListDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
}
