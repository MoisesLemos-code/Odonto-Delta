package com.moises.odontoDelta.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.moises.odontoDelta.domain.Cliente;

public class ClienteListDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer codigo;
	
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres!")
	private String nome;
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Email(message="Email inválido!")
	private String email;
	
	public ClienteListDTO() {}

	public ClienteListDTO(Cliente obj) {
		codigo = obj.getCodigo();
		nome = obj.getNome();
		email = obj.getEmail();
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
