package com.moises.odontoDelta.dto;

import java.io.Serializable;

import com.moises.odontoDelta.domain.ServicoItem;

public class ServicoItemDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private double desconto;
	private double acrescimo;
	private double valor;
	private Integer quantidade;
	
	private Integer servico_codigo;
	private Integer orcamento_codigo;
	
	public ServicoItemDTO() {}
	
	public ServicoItemDTO(ServicoItem obj) {
		desconto = obj.getDesconto();
		acrescimo = obj.getAcrescimo();
		valor = obj.getValor();
		quantidade = obj.getQuantidade();
		orcamento_codigo = obj.getOrcamento().getCodigo();
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public double getAcrescimo() {
		return acrescimo;
	}

	public void setAcrescimo(double acrescimo) {
		this.acrescimo = acrescimo;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getServico_codigo() {
		return servico_codigo;
	}

	public void setServico_codigo(Integer servico_codigo) {
		this.servico_codigo = servico_codigo;
	}

	public Integer getOrcamento_codigo() {
		return orcamento_codigo;
	}

	public void setOrcamento_codigo(Integer orcamento_codigo) {
		this.orcamento_codigo = orcamento_codigo;
	}
}
