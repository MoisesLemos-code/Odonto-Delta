package com.moises.odontoDelta.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.moises.odontoDelta.domain.Pagamento;
import com.moises.odontoDelta.domain.enums.StatusPagamento;
import com.moises.odontoDelta.dto.PagamentoDTO;
import com.moises.odontoDelta.repositories.PagamentoRepository;
import com.moises.odontoDelta.services.exceptions.DataIntegrityException;
import com.moises.odontoDelta.services.exceptions.ObjectNotFoundException;

@Service
public class PagamentoService {

	@Autowired
	private PagamentoRepository repo;
	
	@Autowired
	private FinalizadorService finalizadorService;
	
	@Autowired
	private OrcamentoService orcamentoService;

	public Pagamento find(Integer id) {
		Optional<Pagamento> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pagamento.class.getSimpleName()));
	}
	
	public Pagamento insert(Pagamento obj) {
		obj.setCodigo(null);
		return repo.save(obj);
	}
	
	public Pagamento update(Pagamento obj) {
		Pagamento newObj = find(obj.getCodigo());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um pagamento que possui vinculos!");
		}
	}
	
	public List<Pagamento> findAll(){
		return repo.findAll();
	}
	
	public Page<Pagamento> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(pageRequest);
	}
	
	public Pagamento fromDTO(PagamentoDTO objDto) {
		finalizadorService.find(objDto.getFinalizador().getCodigo());
		orcamentoService.find(objDto.getOrcamento().getCodigo());
			
		Pagamento pag = new Pagamento(objDto.getCodigo(), StatusPagamento.toEnum(objDto.getStatus()), objDto.getValor_total(), objDto.getValor_pago(), objDto.getParcela(), objDto.getData_vencimento(), objDto.getData_pagamento(), objDto.getOrcamento(), objDto.getFinalizador());
		
		return pag;
	}
	
	private void updateData(Pagamento newObj, Pagamento obj) {
		finalizadorService.find(obj.getFinalizador().getCodigo());
		orcamentoService.find(obj.getOrcamento().getCodigo());
		
		newObj.setStatus(obj.getStatus());
		newObj.setValor_total(obj.getValor_total());
		newObj.setValor_pago(obj.getValor_pago());
		newObj.setParcela(obj.getParcela());
		newObj.setData_vencimento(obj.getData_vencimento());
		newObj.setData_pagamento(obj.getData_pagamento());
		newObj.setFinalizador(obj.getFinalizador());
		newObj.setOrcamento(obj.getOrcamento());
		
		if(newObj.getValor_pago() >= newObj.getValor_total()) {
			newObj.setData_pagamento(new Date());
			newObj.setStatus(StatusPagamento.CONCLUIDO);
		}
	}
}
