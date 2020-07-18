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
import org.springframework.transaction.annotation.Transactional;

import com.moises.odontoDelta.domain.Cliente;
import com.moises.odontoDelta.domain.DenteItem;
import com.moises.odontoDelta.domain.Endereco;
import com.moises.odontoDelta.domain.Orcamento;
import com.moises.odontoDelta.domain.PecaItem;
import com.moises.odontoDelta.domain.ServicoItem;
import com.moises.odontoDelta.domain.enums.StatusOrcamento;
import com.moises.odontoDelta.repositories.DenteItemRepository;
import com.moises.odontoDelta.repositories.OrcamentoRepository;
import com.moises.odontoDelta.repositories.PecaItemRepository;
import com.moises.odontoDelta.repositories.ServicoItemRepository;
import com.moises.odontoDelta.services.exceptions.DataIntegrityException;
import com.moises.odontoDelta.services.exceptions.ObjectNotFoundException;

@Service
public class OrcamentoService {

	@Autowired
	private OrcamentoRepository repo;

	@Autowired
	private PecaItemRepository pecaItemRepository;
	@Autowired
	private ServicoItemRepository servicoItemRepository;
	@Autowired
	private DenteItemRepository denteItemRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EnderecoService enderecoService;

	@Autowired
	private PecaService pecaService;
	@Autowired
	private ServicoService servicoService;
	@Autowired
	private DenteService denteService;
	
//	@Autowired
//	private EmailService emailService;

	public Orcamento find(Integer id) {
		Optional<Orcamento> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Orcamento.class.getSimpleName()));
	}

	@Transactional
	public Orcamento insert(Orcamento obj) {

		obj.setCodigo(null);
		obj.setData_abertura(new Date());


		if (obj.getCliente() != null) {
			Cliente cli = clienteService.find(obj.getCliente().getCodigo());

			obj.setCliente(cli);
		}
		
		if(obj.getEnderecoDeEntrega() != null) {
			enderecoService.find(obj.getEnderecoDeEntrega().getCodigo());
		}

		obj = repo.save(obj);

		for (PecaItem pi : obj.getPecasItens()) {
			pi.setPeca(pecaService.find(pi.getPeca().getCodigo()));
			pi.setOrcamento(obj);
		}
		pecaItemRepository.saveAll(obj.getPecasItens());
		
		for (ServicoItem si : obj.getServicosItens()) {
			si.setServico(servicoService.find(si.getServico().getCodigo()));
			si.setOrcamento(obj);
		}
		servicoItemRepository.saveAll(obj.getServicosItens());
		
		for (DenteItem di : obj.getDentesItens()) {
			di.setDente(denteService.find(di.getDente().getCodigo()));
			di.setOrcamento(obj);
		}
		denteItemRepository.saveAll(obj.getDentesItens());
//		emailService.sendOrderConfirmationEmail(obj);
		return obj;
	}
	
	public Orcamento update(Orcamento obj) {
		Orcamento newObj = find(obj.getCodigo());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um orçamento que possui vinculos!");
		}
	}
	
	public List<Orcamento> findAll(){
		return repo.findAll();
	}
	
	public Page<Orcamento> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(pageRequest);
	}
	
	private void updateData(Orcamento newObj, Orcamento obj) {
		newObj.setTipo(obj.getTipo());
		newObj.setStatus(obj.getStatus());
		newObj.setAcrescimo_fechamento(obj.getAcrescimo_fechamento());
		newObj.setDesconto_fechamento(obj.getDesconto_fechamento());
		
		newObj.setCliente(obj.getCliente());
		newObj.setEnderecoDeEntrega(obj.getEnderecoDeEntrega());
		
		if (obj.getCliente() != null) {
			Cliente cli = clienteService.find(obj.getCliente().getCodigo());

			newObj.setCliente(cli);
		}
		
		if(obj.getEnderecoDeEntrega() != null) {
			Endereco end = enderecoService.find(obj.getEnderecoDeEntrega().getCodigo());
			newObj.setEnderecoDeEntrega(end);
		}
		
		if(obj.getStatus().getCod() == StatusOrcamento.FECHADO.getCod()) {
			newObj.setData_fechamento(new Date());
		}
	}
}
