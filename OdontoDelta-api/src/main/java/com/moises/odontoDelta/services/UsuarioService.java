package com.moises.odontoDelta.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.moises.odontoDelta.domain.Usuario;
import com.moises.odontoDelta.domain.enums.Permissao_usuario;
import com.moises.odontoDelta.dto.UsuarioDTO;
import com.moises.odontoDelta.dto.UsuarioNewDTO;
import com.moises.odontoDelta.repositories.UsuarioRepository;
import com.moises.odontoDelta.security.UserSS;
import com.moises.odontoDelta.services.exceptions.AuthorizationException;
import com.moises.odontoDelta.services.exceptions.DataIntegrityException;
import com.moises.odontoDelta.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder pe;

	//Obter o usuário logado
	public static UserSS authenticated() {
		try {
		return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();		
		}
		catch(Exception e) {
			return null;
		}
	}
	
	public Usuario find(Integer id) {
		Optional<Usuario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getSimpleName()));
	}
	
	public Usuario insert(Usuario obj) {
		obj.setCodigo(null);
		return repo.save(obj);
	}
	
	public Usuario update(Usuario obj) {
		Usuario newObj = find(obj.getCodigo());
		
		UserSS user = authenticated();
		if(user==null || !user.hasRole(Permissao_usuario.ADMINISTRADOR) && !obj.getCodigo().equals(user.getCodigo())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um usuario que possui vinculos!");
		}
	}
	
	public List<Usuario> findAll(){
		return repo.findAll();
	}
	
	public Page<Usuario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(pageRequest);
	}
	
	public Usuario fromDTO(UsuarioDTO objDto) {
		Usuario user = new Usuario(null, objDto.getNome(),objDto.getNome_completo(), pe.encode(objDto.getSenha()), objDto.getEmail());
		
		for(Permissao_usuario tu : objDto.getPermissao()) {
			user.addPermissao(Permissao_usuario.toEnum(tu.getCod()));
		}
		
		return user;
	}

	public Usuario fromDTO(UsuarioNewDTO objDto) {
		Usuario user = new Usuario(null, objDto.getNome(), objDto.getNome_completo(), pe.encode(objDto.getSenha()), objDto.getEmail());
		return user;
	}
	
	private void updateData(Usuario newObj, Usuario obj) {
		newObj.setNome(obj.getNome());
		newObj.setNome_completo(obj.getNome_completo());
		newObj.setSenha(obj.getSenha());
		newObj.setEmail(obj.getEmail());
		newObj.clearTipo();

		for(Permissao_usuario tu : obj.getPermissao()) {
			newObj.addPermissao(Permissao_usuario.toEnum(tu.getCod()));
		}
		
	}
}
