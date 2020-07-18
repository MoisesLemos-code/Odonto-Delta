package com.moises.odontoDelta.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.moises.odontoDelta.domain.Finalizador;
import com.moises.odontoDelta.repositories.FinalizadorRepository;
import com.moises.odontoDelta.services.exceptions.DataIntegrityException;
import com.moises.odontoDelta.services.exceptions.ObjectNotFoundException;

@Service
public class FinalizadorService {

	@Autowired
	private FinalizadorRepository repo;

	public Finalizador find(Integer id) {
		Optional<Finalizador> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Finalizador.class.getSimpleName()));
	}
	
	public Finalizador insert(Finalizador obj) {
		obj.setCodigo(null);
		return repo.save(obj);
	}
	
	public Finalizador update(Finalizador obj) {
		Finalizador newObj = find(obj.getCodigo());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um finalizador que possui vinculos!");
		}
	}
	
	public List<Finalizador> findAll(){
		return repo.findAll();
	}
	
	public Page<Finalizador> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(pageRequest);
	}
	
	private void updateData(Finalizador newObj, Finalizador obj) {
		newObj.setDescricao(obj.getDescricao());
		newObj.setGera_financeiro(obj.isGera_financeiro());
	}
}
