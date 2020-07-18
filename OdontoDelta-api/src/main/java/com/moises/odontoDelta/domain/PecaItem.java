package com.moises.odontoDelta.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PecaItem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@EmbeddedId
	private PecaItemPK codigo = new PecaItemPK();
	
	private double desconto;
	private double acrescimo;
	private double valor;
	private Integer quantidade;
	
	public PecaItem() {}
	
	public PecaItem(Orcamento orcamento, Peca peca, double desconto, double acrescimo, double valor, Integer quantidade) {
		super();
		codigo.setOrcamento(orcamento);
		codigo.setPeca(peca);
		this.desconto = desconto;
		this.acrescimo = acrescimo;
		this.valor = valor;
		this.quantidade = quantidade;
	}

	public double getSubTotal() {
		return (valor - desconto) * quantidade + acrescimo;
	}

	@JsonIgnore
	public Orcamento getOrcamento() {
		return codigo.getOrcamento();
	}
	
	public void setOrcamento(Orcamento orcamento) {
		codigo.setOrcamento(orcamento);
	}
	
	public Peca getPeca() {
		return codigo.getPeca();
	}
	
	public void setPeca(Peca peca) {
		codigo.setPeca(peca);
	}
	
	public PecaItemPK getCodigo() {
		return codigo;
	}

	public void setCodigo(PecaItemPK codigo) {
		this.codigo = codigo;
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
		PecaItem other = (PecaItem) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		StringBuilder builder = new StringBuilder();
		builder.append(getPeca().getDescricao());
		builder.append(", Qtde: ");
		builder.append(getQuantidade());
		builder.append(", Preço unitário: ");
		builder.append(nf.format(getValor()));
		builder.append(", Subtotal: ");
		builder.append(nf.format(getSubTotal()));
		builder.append("\n");
		return builder.toString();
	}

}
