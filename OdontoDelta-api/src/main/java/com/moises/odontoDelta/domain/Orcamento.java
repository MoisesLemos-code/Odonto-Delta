package com.moises.odontoDelta.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.moises.odontoDelta.domain.enums.StatusOrcamento;
import com.moises.odontoDelta.domain.enums.TipoOrcamento;

@Entity
@Table(name="ORCAMENTO")
public class Orcamento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigo;
	
	private Integer tipo;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date data_abertura;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date data_fechamento;
	
	private Integer status;
	
	private double desconto_fechamento;
	
	private double acrescimo_fechamento;
	
	@ManyToOne
	@JoinColumn(name="cliente_codigo")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="usuario_codigo")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="endereco_codigo")
	private Endereco enderecoDeEntrega;
	
	// CascadeType.All = Deletar vinculos
	@OneToMany(mappedBy = "orcamento", cascade = CascadeType.ALL)
	private List<Pagamento> pagamentos = new ArrayList<>();
	
	@OneToMany(mappedBy="codigo.orcamento")
	private Set<PecaItem> pecasItens = new HashSet<>();
	
	@OneToMany(mappedBy="codigo.orcamento")
	private Set<ServicoItem> servicosItens = new HashSet<>();
	
	@OneToMany(mappedBy="codigo.orcamento")
	private Set<DenteItem> dentesItens = new HashSet<>();

	public Orcamento() {
	}

	public Orcamento(Integer codigo, TipoOrcamento tipo, Date data_abertura, Date data_fechamento, StatusOrcamento status,
			double desconto_fechamento, double acrescimo_fechamento, Cliente cliente, Endereco endereco) {
		super();
		this.codigo = codigo;
		this.tipo = (tipo==null) ? null : tipo.getCod();
		this.data_abertura = data_abertura;
		this.data_fechamento = data_fechamento;
		this.status = (status==null) ? null : status.getCod();
		this.desconto_fechamento = desconto_fechamento;
		this.acrescimo_fechamento = acrescimo_fechamento;
		this.cliente = cliente;
		this.enderecoDeEntrega = endereco;
	}

	public double getValorTotal() {
		double soma = 0.0;
		for(PecaItem ip : pecasItens) {
			soma = soma + ip.getSubTotal();
		}
		for(ServicoItem ip : servicosItens) {
			soma = soma + ip.getSubTotal();
		}
		return soma;
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
		this.tipo = tipo.getCod();
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

	public double getDesconto_fechamento() {
		return desconto_fechamento;
	}

	public void setDesconto_fechamento(double desconto_fechamento) {
		this.desconto_fechamento = desconto_fechamento;
	}

	public double getAcrescimo_fechamento() {
		return acrescimo_fechamento;
	}

	public void setAcrescimo_fechamento(double acrescimo_fechamento) {
		this.acrescimo_fechamento = acrescimo_fechamento;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Endereco getEnderecoDeEntrega() {
		return enderecoDeEntrega;
	}

	public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
		this.enderecoDeEntrega = enderecoDeEntrega;
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}
	
	public Set<PecaItem> getPecasItens() {
		return pecasItens;
	}

	public void setPecasItens(Set<PecaItem> pecasItens) {
		this.pecasItens = pecasItens;
	}

	public Set<ServicoItem> getServicosItens() {
		return servicosItens;
	}

	public void setServicosItens(Set<ServicoItem> servicosItens) {
		this.servicosItens = servicosItens;
	}

	public Set<DenteItem> getDentesItens() {
		return dentesItens;
	}

	public void setDentesItens(Set<DenteItem> dentesItens) {
		this.dentesItens = dentesItens;
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
		Orcamento other = (Orcamento) obj;
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
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		
		StringBuilder builder = new StringBuilder();
		builder.append("Orcamento número: ");
		builder.append(getCodigo());
		builder.append(", Data de abertura: ");
		builder.append(sdf.format(getData_abertura()));
		builder.append(", Cliente: ");
		builder.append(getCliente().getNome());
		builder.append(", Status: ");
		builder.append(getStatus());
		builder.append(", Peças: ");
		for(PecaItem pi : getPecasItens()) {
			builder.append(pi.toString());
		}
		builder.append(", Servicos: ");
		for(ServicoItem si : getServicosItens()) {
			builder.append(si.toString());
		}
		builder.append(", Dentes: ");
		for(DenteItem di : getDentesItens()) {
			builder.append(di.toString());
		}
		builder.append(", Valor total: ");
		builder.append(nf.format(getValorTotal()));
		return builder.toString();
	}

	
}
