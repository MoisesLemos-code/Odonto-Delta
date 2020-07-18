package com.moises.odontoDelta.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moises.odontoDelta.domain.enums.StatusDente;

@Entity
public class DenteItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private DenteItemPK codigo = new DenteItemPK();

	private Integer status;

	@ManyToOne
	@JoinColumns(value = { @JoinColumn(name = "orcamento_servico", referencedColumnName = "orcamento_codigo"),
			@JoinColumn(name = "servico", referencedColumnName = "servico_codigo") })
	private ServicoItem servico;

	@ManyToOne
	@JoinColumns(value = { @JoinColumn(name = "orcamento_peca", referencedColumnName = "orcamento_codigo"),
			@JoinColumn(name = "peca", referencedColumnName = "peca_codigo") })
	private PecaItem peca;

	public DenteItem() {
	}

	public DenteItem(Orcamento orcamento, PecaItem peca, ServicoItem servico, Dente dente, StatusDente status) {
		super();
		codigo.setOrcamento(orcamento);
		this.peca = peca;
		this.servico = servico;
		codigo.setDente(dente);
		this.status = (status == null) ? null : status.getCod();
	}

	@JsonIgnore
	public Orcamento getOrcamento() {
		return codigo.getOrcamento();
	}

	public void setOrcamento(Orcamento orcamento) {
		codigo.setOrcamento(orcamento);
	}

	public ServicoItem getServico() {
		return servico;
	}

	public void setServico(ServicoItem servico) {
		this.servico = servico;
	}

	public PecaItem getPeca() {
		return peca;
	}

	public void setPeca(PecaItem peca) {
		this.peca = peca;
	}

	public Dente getDente() {
		return codigo.getDente();
	}

	public void setDente(Dente dente) {
		codigo.setDente(dente);
	}

	public DenteItemPK getCodigo() {
		return codigo;
	}

	public void setCodigo(DenteItemPK codigo) {
		this.codigo = codigo;
	}

	public StatusDente getStatus() {
		return StatusDente.toEnum(status);
	}

	public void setStatus(StatusDente status) {
		this.status = status.getCod();
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
		DenteItem other = (DenteItem) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getDente().getDescricao());
		builder.append(", Status: ");
		builder.append(getStatus());
		if (getPeca() != null) {
			builder.append(", Peca: ");
			builder.append(getPeca().getPeca().getDescricao());
		}
		if (getServico() != null) {
			builder.append(", Serviço: ");
			builder.append(getServico().getServico().getDescricao());
		}
		builder.append("\n");
		return builder.toString();
	}
}
