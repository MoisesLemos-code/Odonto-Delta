package com.moises.odontoDelta.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.moises.odontoDelta.domain.Cliente;
import com.moises.odontoDelta.domain.enums.TipoCliente;
import com.moises.odontoDelta.dto.ClienteNewDTO;
import com.moises.odontoDelta.repositories.ClienteRepository;
import com.moises.odontoDelta.resources.exception.FieldMessage;
import com.moises.odontoDelta.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	private ClienteRepository repo;

	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido!"));
		}
		if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido!"));
		}

		Cliente aux = repo.findByCpfOuCnpj(objDto.getCpfOuCnpj());
		if (aux != null) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF/CNPJ já cadastrado!" + "(Cliente: " + aux.getNome() + ")"));
		}

		// inclua os testes aqui, inserindo erros na lista
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
