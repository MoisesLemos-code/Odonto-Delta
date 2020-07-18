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

import com.moises.odontoDelta.domain.DenteItem;
import com.moises.odontoDelta.dto.DenteItemDTO;
import com.moises.odontoDelta.services.DenteItemService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value="/denteitem")
public class DenteItemResource {

	@Autowired
	private DenteItemService service;
	
	@RequestMapping(value="find/{dente}/{orcamento}",method=RequestMethod.GET)
	public ResponseEntity<DenteItem> find(@PathVariable Integer dente, @PathVariable Integer orcamento) {
		DenteItem obj = service.find(dente, orcamento);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody DenteItemDTO objDto){
		DenteItem obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		//Fornecer URI após inserir!
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getDente().getCodigo()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ORCAMENTO_UPDATE')" + 
	"|| hasAnyRole('ADMIN')")
	@RequestMapping(value="/update/{dente}/{orcamento}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody DenteItemDTO objDto, @PathVariable Integer dente, @PathVariable Integer orcamento){
		DenteItem obj = service.fromDTO(objDto);
		obj.getDente().setCodigo(dente);
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/delete/{dente}/{orcamento}",method=RequestMethod.DELETE)
	public ResponseEntity<DenteItem> delete(@PathVariable Integer dente, @PathVariable Integer orcamento) {
		service.delete(dente, orcamento);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public ResponseEntity<List<DenteItem>> findAll() {
		List<DenteItem> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public ResponseEntity<Page<DenteItem>> findPage(
			@RequestParam(value="page",defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage",defaultValue="24")Integer linesPerPage, 
			@RequestParam(value="orderBy",defaultValue="status")String orderBy, 
			@RequestParam(value="direction",defaultValue="ASC")String direction) {
		Page<DenteItem> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
	
}
