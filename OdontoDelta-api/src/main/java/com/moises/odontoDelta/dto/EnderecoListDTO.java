package com.moises.odontoDelta.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.moises.odontoDelta.domain.Endereco;

public class EnderecoListDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer codigo;
	@NotEmpty(message = "Preenchimento obrigatório!")
	private String logradouro;
	
	@NotEmpty(message = "Preenchimento obrigatório!")
	private String numero;
	private String complemento;
	private String bairro;
	
	@NotEmpty(message = "Preenchimento obrigatório!")
	private String cep;

	private Integer clienteId;
	private Integer cidadeId;
	
	public EnderecoListDTO() {}
	
	public EnderecoListDTO(Endereco obj) {
		codigo = obj.getCodigo();
		logradouro = obj.getLogradouro();
		numero = obj.getNumero();
		complemento = obj.getComplemento();
		bairro = obj.getBairro();
		cep = obj.getCep();
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getClienteId() {
		return clienteId;
	}

	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}

}
