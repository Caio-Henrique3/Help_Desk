package com.helpdesk.HelpDesk.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Perfil {

	ADMIN(0, "ROLE_ADMIN"), CLIENTE(1, "ROLE_CLIENTE"), TECNICO(2, "ROLE_TECNICO");
	
	private Integer codigo;
	private String descicao;
	
	public static Perfil toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		
		for (Perfil i : Perfil.values()) {
			if (codigo.equals(i.getCodigo())) {
				return i;
			}
		}
		
		throw new IllegalArgumentException("Perfil inválido!");
	}
}
