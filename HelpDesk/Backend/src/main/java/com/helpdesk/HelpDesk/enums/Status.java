package com.helpdesk.HelpDesk.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {

	ABERTO(0, "ABERTO"), ANDAMENTO(1, "ANDAMENTO"), ENCERRADO(2, "ENCERRADO");
	
	private Integer codigo;
	private String descicao;
	
	public static Status toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		
		for (Status i : Status.values()) {
			if (codigo.equals(i.getCodigo())) {
				return i;
			}
		}
		
		throw new IllegalArgumentException("Status inválido!");
	}
}
