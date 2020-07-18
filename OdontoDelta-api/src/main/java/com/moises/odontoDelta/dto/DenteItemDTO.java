package com.moises.odontoDelta.dto;

import java.io.Serializable;

public class DenteItemDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private Integer status;
	private Integer dente_codigo;
	private Integer servico_codigo;
	private Integer peca_codigo;
	private Integer orcamento_codigo;
	
	public DenteItemDTO() {}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getDente_codigo() {
		return dente_codigo;
	}

	public void setDente_codigo(Integer dente_codigo) {
		this.dente_codigo = dente_codigo;
	}

	public Integer getServico_codigo() {
		return servico_codigo;
	}

	public void setServico_codigo(Integer servico_codigo) {
		this.servico_codigo = servico_codigo;
	}

	public Integer getPeca_codigo() {
		return peca_codigo;
	}

	public void setPeca_codigo(Integer peca_codigo) {
		this.peca_codigo = peca_codigo;
	}

	public Integer getOrcamento_codigo() {
		return orcamento_codigo;
	}

	public void setOrcamento_codigo(Integer orcamento_codigo) {
		this.orcamento_codigo = orcamento_codigo;
	}

}
