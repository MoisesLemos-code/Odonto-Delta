package com.moises.odontoDelta.dto;

import java.io.Serializable;

import com.moises.odontoDelta.domain.Peca;

public class PecaListDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer codigo;
	private String descricao;
	private double valor;
	
	public PecaListDTO() {}
	
	public PecaListDTO(Peca obj) {
		codigo = obj.getCodigo();
		descricao = obj.getDescricao();
		valor = obj.getValor();
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

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
	
}
