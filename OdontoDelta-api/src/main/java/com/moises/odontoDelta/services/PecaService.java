package com.moises.odontoDelta.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.moises.odontoDelta.domain.Categoria;
import com.moises.odontoDelta.domain.Peca;
import com.moises.odontoDelta.repositories.CategoriaRepository;
import com.moises.odontoDelta.repositories.PecaRepository;
import com.moises.odontoDelta.services.exceptions.DataIntegrityException;
import com.moises.odontoDelta.services.exceptions.ObjectNotFoundException;

@Service
public class PecaService {

	@Autowired
	private PecaRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public Peca find(Integer id) {
		Optional<Peca> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Peca.class.getSimpleName()));
	}
	
	public Peca insert(Peca obj) {
		obj.setCodigo(null);
		return repo.save(obj);
	}
	
	public Peca update(Peca obj) {
		Peca newObj = find(obj.getCodigo());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma peca que possui vinculos!");
		}
	}
	
	public List<Peca> findAll(){
		return repo.findAll();
	}
	
	
	public Page<Peca> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(pageRequest);
	}
	
	public Page<Peca> search(String descricao, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids); 
		return repo.search(descricao, categorias, pageRequest);
	}

	private void updateData(Peca newObj, Peca obj) {
		newObj.setDescricao(obj.getDescricao());
		newObj.setValor(obj.getValor());
		
	}
}
