package com.moises.odontoDelta.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.moises.odontoDelta.domain.Dente;
import com.moises.odontoDelta.domain.DenteItem;
import com.moises.odontoDelta.domain.Orcamento;
import com.moises.odontoDelta.domain.PecaItem;
import com.moises.odontoDelta.domain.ServicoItem;
import com.moises.odontoDelta.domain.enums.StatusDente;
import com.moises.odontoDelta.dto.DenteItemDTO;
import com.moises.odontoDelta.repositories.DenteItemRepository;
import com.moises.odontoDelta.services.exceptions.DataIntegrityException;
import com.moises.odontoDelta.services.exceptions.ObjectNotFoundException;

@Service
public class DenteItemService {

	@Autowired
	private DenteItemRepository repo;
	@Autowired
	private DenteService denteService;
	@Autowired
	private OrcamentoService orcamentoService;
	@Autowired
	private ServicoItemService servicoItemService;
	@Autowired
	private PecaItemService pecaItemService;

	public DenteItem find(Integer dente, Integer orcamento) {
		Optional<DenteItem> obj = repo.fiDenteItemOrcamento(dente, orcamento);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! dente: " + dente
				+ ", orçamento: " + orcamento + ", Tipo: " + DenteItem.class.getSimpleName()));
	}

	public DenteItem insert(DenteItem obj) {
		return repo.save(obj);
	}

	public DenteItem update(DenteItem obj) {
		DenteItem newObj = find(obj.getDente().getCodigo(), obj.getOrcamento().getCodigo());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer dente, Integer orcamento) {
		find(dente, orcamento);
		try {
			repo.delDenteItemOrcamento(dente, orcamento);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um item de dente que possui vinculos!");
		}
	}

	public DenteItem fromDTO(DenteItemDTO objDto) {
		Dente dente = denteService.find(objDto.getDente_codigo());
		Orcamento orc = orcamentoService.find(objDto.getOrcamento_codigo());

		PecaItem peca = new PecaItem(null, null, 0.0, 0.0, 0.0, 0);
		ServicoItem servico = new ServicoItem(null, null, 0.0, 0.0, 0.0, 0);
		
		DenteItem di = new DenteItem(orc, null, null, dente, StatusDente.toEnum(objDto.getStatus()));

		if (objDto.getPeca_codigo() != null) {
			peca = pecaItemService.find(objDto.getPeca_codigo(), objDto.getOrcamento_codigo());
			di.setPeca(peca);
		}
		if (objDto.getServico_codigo() != null) {
			servico = servicoItemService.find(objDto.getServico_codigo(), objDto.getOrcamento_codigo());
			di.setServico(servico);
		}
		

		

		return di;
	}

	public List<DenteItem> findAll() {
		return repo.findAll();
	}

	public Page<DenteItem> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		return repo.findAll(pageRequest);
	}

	private void updateData(DenteItem newObj, DenteItem obj) {
		newObj.setStatus(obj.getStatus());
		newObj.setPeca(obj.getPeca());
		newObj.setServico(obj.getServico());
		newObj.setOrcamento(obj.getOrcamento());
	}
}
