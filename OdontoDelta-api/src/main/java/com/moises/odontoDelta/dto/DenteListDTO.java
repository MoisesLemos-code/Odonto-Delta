package com.moises.odontoDelta.dto;

import java.io.Serializable;

import com.moises.odontoDelta.domain.Dente;

public class DenteListDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer codigo;
	private String nome;
	private String descricao;
	
	public DenteListDTO() {}
	
	public DenteListDTO(Dente obj) {
		codigo = obj.getCodigo();
		nome = obj.getNome();
		descricao = obj.getDescricao();
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
