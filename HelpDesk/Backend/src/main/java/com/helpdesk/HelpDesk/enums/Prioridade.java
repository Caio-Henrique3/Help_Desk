package com.helpdesk.HelpDesk.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Prioridade {

	BAIXA(0, "BAIXA"), MEDIA(1, "MEDIA"), ALTA(2, "ALTA");
	
	private Integer codigo;
	private String descicao;
	
	public static Prioridade toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		
		for (Prioridade i : Prioridade.values()) {
			if (codigo.equals(i.getCodigo())) {
				return i;
			}
		}
		
		throw new IllegalArgumentException("Prioridade inválida!");
	}
}
