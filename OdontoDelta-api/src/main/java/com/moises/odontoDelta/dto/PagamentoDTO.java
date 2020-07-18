package com.moises.odontoDelta.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.moises.odontoDelta.domain.Finalizador;
import com.moises.odontoDelta.domain.Orcamento;
import com.moises.odontoDelta.domain.Pagamento;

public class PagamentoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private Integer codigo;
	private Integer status;
	private double valor_total;
	private double valor_pago;
	private Integer parcela;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date data_vencimento;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date data_pagamento;
	
	private Orcamento orcamento;

	private Finalizador finalizador;
	
	public PagamentoDTO() {}
	
	public PagamentoDTO(Pagamento obj) {
		codigo = obj.getCodigo();
		status = obj.getStatus().getCod();
		valor_total = obj.getValor_total();
		valor_pago = obj.getValor_pago();
		parcela = obj.getParcela();
		data_vencimento = obj.getData_vencimento();
		data_pagamento = obj.getData_pagamento();
		orcamento = obj.getOrcamento();
		finalizador = obj.getFinalizador();
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public double getValor_total() {
		return valor_total;
	}

	public void setValor_total(double valor_total) {
		this.valor_total = valor_total;
	}

	public double getValor_pago() {
		return valor_pago;
	}

	public void setValor_pago(double valor_pago) {
		this.valor_pago = valor_pago;
	}

	public Integer getParcela() {
		return parcela;
	}

	public void setParcela(Integer parcela) {
		this.parcela = parcela;
	}

	public Date getData_vencimento() {
		return data_vencimento;
	}

	public void setData_vencimento(Date data_vencimento) {
		this.data_vencimento = data_vencimento;
	}

	public Date getData_pagamento() {
		return data_pagamento;
	}

	public void setData_pagamento(Date data_pagamento) {
		this.data_pagamento = data_pagamento;
	}

	public Orcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}

	public Finalizador getFinalizador() {
		return finalizador;
	}

	public void setFinalizador(Finalizador finalizador) {
		this.finalizador = finalizador;
	}
	
}
