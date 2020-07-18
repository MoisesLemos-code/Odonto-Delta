package com.moises.odontoDelta.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.moises.odontoDelta.domain.Cidade;
import com.moises.odontoDelta.domain.Cliente;
import com.moises.odontoDelta.domain.Endereco;
import com.moises.odontoDelta.dto.EnderecoListDTO;
import com.moises.odontoDelta.repositories.EnderecoRepository;
import com.moises.odontoDelta.services.exceptions.DataIntegrityException;
import com.moises.odontoDelta.services.exceptions.ObjectNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repo;
	
	@Autowired
	private ClienteService clienteService;

	@Autowired
	private CidadeService cidadeService;

	public Endereco find(Integer id) {
		Optional<Endereco> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Endereco.class.getSimpleName()));
	}
	
	public Endereco insert(Endereco obj) {
		obj.setCodigo(null);
		return repo.save(obj);
	}
	
	public Endereco update(Endereco obj) {
		Endereco newObj = find(obj.getCodigo());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um endereco que possui vinculos!");
		}
	}
	
	public List<Endereco> findAll(){
		return repo.findAll();
	}
	
	public Page<Endereco> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(pageRequest);
	}
	
	public Endereco fromDTO(EnderecoListDTO objDto) {
		clienteService.find(objDto.getClienteId());
		cidadeService.find(objDto.getCidadeId());
		
		Cliente cli = new Cliente(objDto.getClienteId(), null, null, null, null);
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		
	   return end;
	}
	
	private void updateData(Endereco newObj, Endereco obj) {
		newObj.setLogradouro(obj.getLogradouro());
		newObj.setNumero(obj.getNumero());
		newObj.setComplemento(obj.getComplemento());
		newObj.setBairro(obj.getBairro());
		newObj.setCep(obj.getCep());
		newObj.setCidade(obj.getCidade());
	}
}
