package com.moises.odontoDelta.domain.enums;

public enum StatusDente {
	
	NORMAL(1, "Normal"),
	RESTAURADO(2, "Restaurado");

	private int cod;
	private String descricao;
	
	private StatusDente (int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static StatusDente toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(StatusDente x : StatusDente.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Código enumerado inválido: "+ cod);
	}
}
