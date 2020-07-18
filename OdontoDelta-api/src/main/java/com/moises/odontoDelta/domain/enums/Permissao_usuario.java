package com.moises.odontoDelta.domain.enums;

public enum Permissao_usuario {
	
	ADMINISTRADOR(1, "ROLE_ADMIN"),
	USER_FIND(2,"ROLE_USER_FIND"),
	CLIENTE_UPDATE(3, "ROLE_CLIENTE_UPDATE"),
	CLIENTE_DELETE(4, "ROLE_CLIENTE_DELETE"),
	DENTE_UPDATE(5,"ROLE_DENTE_UPDATE"),
	FINALIZADOR_UPDATE(6, "ROLE_FINALIZADOR_UPDATE"),
	ORCAMENTO_INSERT(7, "ROLE_ORCAMENTO_INSERT"),
	ORCAMENTO_UPDATE(8, "ROLE_ORCAMENTO_UPDATE"),
	ORCAMENTO_DELETE(9, "ROLE_ORCAMENTO_DELETE"),
	PAGAMENTO_UPDATE(10, "ROLE_PAGAMENTO_UPDATE"),
	PAGAMENTO_DELETE(11, "ROLE_PAGAMENTO_DELETE"),
	PECA_UPDATE(12, "ROLE_PECA_UPDATE"),
	SERVICO_UPDATE(13, "SERVICO_UPDATE");
	
	
	private int cod;
	private String descricao;
	
	private Permissao_usuario (int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static Permissao_usuario toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(Permissao_usuario x : Permissao_usuario.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Código enumerado inválido: "+ cod);
	}
}
