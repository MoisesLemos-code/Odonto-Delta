package com.moises.odontoDelta.domain.enums;

public enum TipoOrcamento {
	
	ORCAMENTO(1, "Orçamento"),
	PROCEDIMENTO(2, "Procedimento");
	
	private int cod;
	private String descricao;
	
	private TipoOrcamento (int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoOrcamento toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(TipoOrcamento x : TipoOrcamento.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Código enumerado inválido: "+ cod);
	}
}
