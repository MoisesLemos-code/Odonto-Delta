package com.moises.odontoDelta.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.moises.odontoDelta.domain.Usuario;
import com.moises.odontoDelta.repositories.UsuarioRepository;
import com.moises.odontoDelta.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UsuarioRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
		Usuario user = repo.findByNome(nome);
		if(user == null) {
			throw new UsernameNotFoundException(nome);
		}

		return new UserSS(user.getCodigo(), user.getNome(), user.getNome_completo(),
				user.getSenha(), user.getPermissao());
	}

}
