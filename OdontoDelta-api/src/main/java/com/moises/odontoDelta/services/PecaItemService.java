package com.moises.odontoDelta.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.moises.odontoDelta.domain.Orcamento;
import com.moises.odontoDelta.domain.Peca;
import com.moises.odontoDelta.domain.PecaItem;
import com.moises.odontoDelta.dto.PecaItemDTO;
import com.moises.odontoDelta.repositories.PecaItemRepository;
import com.moises.odontoDelta.services.exceptions.DataIntegrityException;
import com.moises.odontoDelta.services.exceptions.ObjectNotFoundException;

@Service
public class PecaItemService {

	@Autowired
	private PecaItemRepository repo;
	@Autowired
	private OrcamentoService orcamentoService;
	@Autowired
	private PecaService pecaService;
	
	
	public PecaItem find(Integer peca, Integer orcamento) {
		Optional<PecaItem> obj = repo.fiPecaItemOrcamento(peca, orcamento);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! peca: " + peca + ", orçamento: " + orcamento + ", Tipo: " + PecaItem.class.getSimpleName()));
	}
	
	public PecaItem insert(PecaItem obj) {
		return repo.save(obj);
	}
	
	public PecaItem update(PecaItem obj) {
		PecaItem newObj = find(obj.getPeca().getCodigo(), obj.getOrcamento().getCodigo());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer peca, Integer orcamento) {
		find(peca, orcamento);
		try {
		repo.delPecaItemOrcamento(peca, orcamento);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um item de peça que possui vinculos!");
		}
	}
	
	public PecaItem fromDTO(PecaItemDTO objDto) {
		Orcamento orc = orcamentoService.find(objDto.getOrcamento_codigo());
		Peca peca = pecaService.find(objDto.getPeca_codigo());
		
		PecaItem pi = new PecaItem(orc, peca, objDto.getDesconto(), objDto.getAcrescimo(), objDto.getValor(), objDto.getQuantidade());
	
		return pi;
	}
	
	public List<PecaItem> findAll(){
		return repo.findAll();
	}
	
	public Page<PecaItem> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(pageRequest);
	}
	
	private void updateData(PecaItem newObj, PecaItem obj) {
		newObj.setDesconto(obj.getDesconto());
		newObj.setAcrescimo(obj.getAcrescimo());
		newObj.setQuantidade(obj.getQuantidade());
		newObj.setValor(obj.getValor());
	}
}
