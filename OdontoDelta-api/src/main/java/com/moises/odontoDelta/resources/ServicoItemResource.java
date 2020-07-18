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

import com.moises.odontoDelta.domain.ServicoItem;
import com.moises.odontoDelta.dto.ServicoItemDTO;
import com.moises.odontoDelta.services.ServicoItemService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value="/servicoitem")
public class ServicoItemResource {

	@Autowired
	private ServicoItemService service;
	
	@RequestMapping(value="/find/{servico}/{orcamento}",method=RequestMethod.GET)
	public ResponseEntity<ServicoItem> find(@PathVariable Integer servico, @PathVariable Integer orcamento) {
		ServicoItem obj = service.find(servico, orcamento);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ServicoItemDTO objDto){
		ServicoItem obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		//Fornecer URI após inserir!
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getServico().getCodigo()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ORCAMENTO_UPDATE')" + 
	"|| hasAnyRole('ADMIN')")
	@RequestMapping(value="/update/{servico}/{orcamento}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ServicoItemDTO objDto, @PathVariable Integer servico, @PathVariable Integer orcamento){
		ServicoItem obj = service.fromDTO(objDto);
		obj.getServico().setCodigo(servico);
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/delete/{servico}/{orcamento}",method=RequestMethod.DELETE)
	public ResponseEntity<ServicoItem> delete(@PathVariable Integer servico, @PathVariable Integer orcamento) {
		service.delete(servico, orcamento);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public ResponseEntity<List<ServicoItem>> findAll() {
		List<ServicoItem> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public ResponseEntity<Page<ServicoItem>> findPage(
			@RequestParam(value="page",defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage",defaultValue="24")Integer linesPerPage, 
			@RequestParam(value="orderBy",defaultValue="valor")String orderBy, 
			@RequestParam(value="direction",defaultValue="ASC")String direction) {
		Page<ServicoItem> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
	
}
