package com.moises.odontoDelta.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.moises.odontoDelta.domain.enums.Permissao_usuario;

public class UserSS implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private Integer codigo;
	private String nome;
	private String nomeCompleto;
	private String senha;
	private boolean isAdmin;
	Collection<? extends GrantedAuthority> authorities;
	
	public UserSS() {}
	
	public UserSS(Integer codigo, String nome, String nomeCompleto, String senha, Set<Permissao_usuario> tipo) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.nomeCompleto = nomeCompleto;
		this.senha = senha;
		this.isAdmin = tipo.contains(Permissao_usuario.ADMINISTRADOR);
		this.authorities = tipo.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
	}

	public Integer getCodigo() {
		return codigo;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return nome;
	}

	public String getNomeCompleto() { return nomeCompleto;}

	public boolean isAdmin() { return isAdmin;}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public boolean hasRole(Permissao_usuario permissao) {
		return getAuthorities().contains(new SimpleGrantedAuthority(permissao.getDescricao()));
	}
}
