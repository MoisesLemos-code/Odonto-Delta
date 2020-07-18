package com.moises.odontoDelta.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.moises.odontoDelta.domain.Usuario;
import com.moises.odontoDelta.dto.UsuarioDTO;
import com.moises.odontoDelta.repositories.UsuarioRepository;
import com.moises.odontoDelta.resources.exception.FieldMessage;

public class UsuarioUpdateValidator implements ConstraintValidator<UsuarioUpdate, UsuarioDTO> {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private UsuarioRepository repo;

	@Override
	public void initialize(UsuarioUpdate ann) {
	}

	@Override
	public boolean isValid(UsuarioDTO objDto, ConstraintValidatorContext context) {

		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer url_Id = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		// inclua os testes aqui, inserindo erros na lista

		Usuario aux = repo.findByNome(objDto.getNome());
		if (aux != null && !aux.getCodigo().equals(url_Id)) {
			list.add(new FieldMessage("nome", "Nome de usuário já cadastrado!" + "(Usuario: " + aux.getNome() + ")"));
		}
		aux = null;
		 aux = repo.findByEmail(objDto.getEmail());
			if (aux != null && !aux.getCodigo().equals(url_Id)) {
				list.add(new FieldMessage("email", "Email já cadastrado!" + "(Usuario: " + aux.getNome() + ")"));
			}
		

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
