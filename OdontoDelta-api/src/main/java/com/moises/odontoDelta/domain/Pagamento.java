package com.moises.odontoDelta.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moises.odontoDelta.domain.enums.StatusPagamento;

@Entity
public class Pagamento implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigo;
	private Integer status;
	private double valor_total;
	private double valor_pago;
	private Integer parcela;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date data_vencimento;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date data_pagamento;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="orcamento_codigo")
	private Orcamento orcamento;
	
	@ManyToOne
	@JoinColumn(name="finalizador_codigo")
	private Finalizador finalizador;
	
	public Pagamento() {}

	public Pagamento(Integer codigo, StatusPagamento status, double valor_total, double valor_pago, Integer parcela, Date data_vencimento,
			Date data_pagamento,Orcamento orcamento, Finalizador finalizador) {
		super();
		this.codigo = codigo;
		this.status = (status==null) ? null : status.getCod();
		this.valor_total = valor_total;
		this.valor_pago = valor_pago;
		this.parcela = parcela;
		this.data_vencimento = data_vencimento;
		this.data_pagamento = data_pagamento;
		this.orcamento = orcamento;
		this.finalizador = finalizador;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public StatusPagamento getStatus() {
		return StatusPagamento.toEnum(status);
	}

	public void setStatus(StatusPagamento status) {
		this.status = status.getCod();
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
		Pagamento other = (Pagamento) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	
}
