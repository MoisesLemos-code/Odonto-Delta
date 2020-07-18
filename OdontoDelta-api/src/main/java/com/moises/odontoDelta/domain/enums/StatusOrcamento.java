package com.moises.odontoDelta.domain.enums;

public enum StatusOrcamento {
	
	ABERTO(1, "Aberto"),
	FECHADO(2, "Fechado"),
	ANDAMENTO(3, "Em andamento");
	
	private int cod;
	private String descricao;
	
	private StatusOrcamento (int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static StatusOrcamento toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(StatusOrcamento x : StatusOrcamento.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Código enumerado inválido: "+ cod);
	}
}
