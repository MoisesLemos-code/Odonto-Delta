package com.moises.odontoDelta.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moises.odontoDelta.domain.enums.Permissao_usuario;

@Entity
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigo;
	
	private String nome;
	private String nome_completo;
	
	@JsonIgnore
	private String senha;
	private String email;
	
	@ElementCollection(fetch=FetchType.EAGER) 
	@CollectionTable(name="PERMISSOES")
	private Set <Integer> permissao = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="usuario")
	private List<Orcamento> orcamentos = new ArrayList<>();
	
	public Usuario() {}

	public Usuario(Integer codigo, String nome, String nome_completo, String senha, String email) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.nome_completo = nome_completo;
		this.senha = senha;
		this.email = email;
	}

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
	
	public void clearTipo() {
		permissao.clear();
	}

	public List<Orcamento> getOrcamentos() {
		return orcamentos;
	}

	public void setOrcamentos(List<Orcamento> orcamentos) {
		this.orcamentos = orcamentos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
}
