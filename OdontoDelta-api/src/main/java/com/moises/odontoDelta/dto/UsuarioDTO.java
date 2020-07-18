package com.moises.odontoDelta.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.moises.odontoDelta.domain.enums.Permissao_usuario;
import com.moises.odontoDelta.services.validation.UsuarioUpdate;

@UsuarioUpdate
public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer codigo;
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Length(min = 2, max = 120, message = "O tamanho deve ser entre 2 e 120 caracteres!")
	private String nome;
	
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Length(min = 2, max = 120, message = "O tamanho deve ser entre 2 e 120 caracteres!")
	private String nome_completo;
	
	@NotEmpty(message = "Preenchimento obrigatório!")
	private String senha;
	
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Email(message="Email inválido!")
	private String email;
	
	@ElementCollection(fetch=FetchType.EAGER) 
	private Set <Integer> permissao = new HashSet<>();
	
	public UsuarioDTO() {}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome_completo() {
		return nome_completo;
	}

	public void setNome_completo(String nome_completo) {
		this.nome_completo = nome_completo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Permissao_usuario> getPermissao(){
		return permissao.stream().map(x -> Permissao_usuario.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addPermissao(Permissao_usuario permissaoUser) {
		permissao.add(permissaoUser.getCod());
	}
}
