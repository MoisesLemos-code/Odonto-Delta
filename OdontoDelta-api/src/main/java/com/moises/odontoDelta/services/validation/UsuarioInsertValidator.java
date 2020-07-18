package com.moises.odontoDelta.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.moises.odontoDelta.domain.Usuario;
import com.moises.odontoDelta.dto.UsuarioNewDTO;
import com.moises.odontoDelta.repositories.UsuarioRepository;
import com.moises.odontoDelta.resources.exception.FieldMessage;

public class UsuarioInsertValidator implements ConstraintValidator<UsuarioInsert, UsuarioNewDTO> {

	@Autowired
	private UsuarioRepository repo;

	@Override
	public void initialize(UsuarioInsert ann) {
	}

	@Override
	public boolean isValid(UsuarioNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();
		// inclua os testes aqui, inserindo erros na lista
		
		Usuario aux = repo.findByNome(objDto.getNome());
		if (aux != null) {
			list.add(new FieldMessage("nome", "Nome de usuário já cadastrado! " + "(Usuario: " + aux.getNome() + ")"));
		}
		aux = null;
		aux = repo.findByEmail(objDto.getEmail());
		if (aux != null) {
			list.add(new FieldMessage("email", "e-mail já cadastrado! " + "(Usuario: " + aux.getNome() + ")"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
