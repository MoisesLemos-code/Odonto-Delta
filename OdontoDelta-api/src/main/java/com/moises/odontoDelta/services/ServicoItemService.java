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
import com.moises.odontoDelta.domain.Servico;
import com.moises.odontoDelta.domain.ServicoItem;
import com.moises.odontoDelta.dto.ServicoItemDTO;
import com.moises.odontoDelta.repositories.ServicoItemRepository;
import com.moises.odontoDelta.services.exceptions.DataIntegrityException;
import com.moises.odontoDelta.services.exceptions.ObjectNotFoundException;

@Service
public class ServicoItemService {

	@Autowired
	private ServicoItemRepository repo;
	@Autowired
	private OrcamentoService orcamentoService;
	@Autowired
	private ServicoService servicoService;
	
	
	public ServicoItem find(Integer servico, Integer orcamento) {
		Optional<ServicoItem> obj = repo.fiServicoItemOrcamento(servico, orcamento);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! servico: " + servico + ", orçamento: " + orcamento + ", Tipo: " + ServicoItem.class.getSimpleName()));
	}
	
	public ServicoItem insert(ServicoItem obj) {
		return repo.save(obj);
	}
	
	public ServicoItem update(ServicoItem obj) {
		ServicoItem newObj = find(obj.getServico().getCodigo(), obj.getOrcamento().getCodigo());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer servico, Integer orcamento) {
		find(servico, orcamento);
		try {
		repo.delServicoItemOrcamento(servico, orcamento);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um item de serviço que possui vinculos!");
		}
	}
	
	public ServicoItem fromDTO(ServicoItemDTO objDto) {
		Orcamento orc = orcamentoService.find(objDto.getOrcamento_codigo());
		Servico servico = servicoService.find(objDto.getServico_codigo());
		
		ServicoItem pi = new ServicoItem(orc, servico, objDto.getDesconto(), objDto.getAcrescimo(), objDto.getValor(), objDto.getQuantidade());
	
		return pi;
	}
	
	public List<ServicoItem> findAll(){
		return repo.findAll();
	}
	
	public Page<ServicoItem> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(pageRequest);
	}
	
	private void updateData(ServicoItem newObj, ServicoItem obj) {
		newObj.setDesconto(obj.getDesconto());
		newObj.setAcrescimo(obj.getAcrescimo());
		newObj.setQuantidade(obj.getQuantidade());
		newObj.setValor(obj.getValor());
	}
}
