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

import com.moises.odontoDelta.domain.PecaItem;
import com.moises.odontoDelta.dto.PecaItemDTO;
import com.moises.odontoDelta.services.PecaItemService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value="/pecaitem")
public class PecaItemResource {

	@Autowired
	private PecaItemService service;
	
	@RequestMapping(value="/find/{peca}/{orcamento}",method=RequestMethod.GET)
	public ResponseEntity<PecaItem> find(@PathVariable Integer peca, @PathVariable Integer orcamento) {
		PecaItem obj = service.find(peca, orcamento);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody PecaItemDTO objDto){
		PecaItem obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		//Fornecer URI após inserir!
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getPeca().getCodigo()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ORCAMENTO_UPDATE')" + 
	"|| hasAnyRole('ADMIN')")
	@RequestMapping(value="/update/{peca}/{orcamento}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody PecaItemDTO objDto, @PathVariable Integer peca, @PathVariable Integer orcamento){
		PecaItem obj = service.fromDTO(objDto);
		obj.getPeca().setCodigo(peca);
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/delete/{peca}/{orcamento}",method=RequestMethod.DELETE)
	public ResponseEntity<PecaItem> delete(@PathVariable Integer peca, @PathVariable Integer orcamento) {
		service.delete(peca, orcamento);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public ResponseEntity<List<PecaItem>> findAll() {
		List<PecaItem> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public ResponseEntity<Page<PecaItem>> findPage(
			@RequestParam(value="page",defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage",defaultValue="24")Integer linesPerPage, 
			@RequestParam(value="orderBy",defaultValue="valor")String orderBy, 
			@RequestParam(value="direction",defaultValue="ASC")String direction) {
		Page<PecaItem> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
	
}
