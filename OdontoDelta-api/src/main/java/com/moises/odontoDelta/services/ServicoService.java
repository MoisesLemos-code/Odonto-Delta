package com.moises.odontoDelta.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.moises.odontoDelta.domain.Servico;
import com.moises.odontoDelta.repositories.ServicoRepository;
import com.moises.odontoDelta.services.exceptions.DataIntegrityException;
import com.moises.odontoDelta.services.exceptions.ObjectNotFoundException;

@Service
public class ServicoService {

	@Autowired
	private ServicoRepository repo;

	public Servico find(Integer id) {
		Optional<Servico> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Servico.class.getSimpleName()));
	}
	
	public Servico insert(Servico obj) {
		obj.setCodigo(null);
		return repo.save(obj);
	}
	
	public Servico update(Servico obj) {
		Servico newObj = find(obj.getCodigo());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um registro que possui vinculos!");
		}
	}
	
	public List<Servico> findAll(){
		return repo.findAll();
	}
	
	public Page<Servico> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(pageRequest);
	}
		
	private void updateData(Servico newObj, Servico obj) {
		newObj.setDescricao(obj.getDescricao());
		newObj.setValor(obj.getValor());
	}
}
