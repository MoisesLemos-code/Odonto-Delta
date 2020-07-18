package com.moises.odontoDelta.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.moises.odontoDelta.domain.Orcamento;
import com.moises.odontoDelta.domain.enums.StatusOrcamento;
import com.moises.odontoDelta.domain.enums.TipoOrcamento;

public class OrcamentoListDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer codigo;
	
	private Integer tipo;
	
	private double valor_total;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date data_abertura;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date data_fechamento;
	
	private Integer status;
	
	private String cliente_nome;
	
	public OrcamentoListDTO() {}
	
	public OrcamentoListDTO(Orcamento obj) {
		codigo = obj.getCodigo();
		tipo = obj.getTipo().getCod();
		valor_total = obj.getValorTotal();
		data_abertura = obj.getData_abertura();
		data_fechamento = obj.getData_fechamento();
		status = obj.getStatus().getCod();
		cliente_nome = obj.getCliente().getNome();
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public TipoOrcamento getTipo() {
		return TipoOrcamento.toEnum(tipo);
	}

	public void setTipo(TipoOrcamento tipo) {
		this.status = tipo.getCod();
	}

	public double getValor_total() {
		return valor_total;
	}

	public void setValor_total(double valor_total) {
		this.valor_total = valor_total;
	}

	public Date getData_abertura() {
		return data_abertura;
	}

	public void setData_abertura(Date data_abertura) {
		this.data_abertura = data_abertura;
	}

	public Date getData_fechamento() {
		return data_fechamento;
	}

	public void setData_fechamento(Date data_fechamento) {
		this.data_fechamento = data_fechamento;
	}
	
	public StatusOrcamento getStatus() {
		return StatusOrcamento.toEnum(status);
	}

	public void setStatus(StatusOrcamento status) {
		this.status = status.getCod();
	}

	public String getCliente_nome() {
		return cliente_nome;
	}

	public void setCliente_nome(String cliente_nome) {
		this.cliente_nome = cliente_nome;
	}
}
